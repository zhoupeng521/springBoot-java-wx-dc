package com.tts.logdome.service.impl;

import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.tts.logdome.common.config.AlipayConfig;
import com.tts.logdome.common.config.AlipayResponseConstans;
import com.tts.logdome.common.convert.TradeQueryResponseToPayResponse;
import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.common.utils.AlipayUtils;
import com.tts.logdome.common.utils.MathUtil;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.service.OrderService;
import com.tts.logdome.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 支付服务service
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String WEIXIN_ORDER_NAME = "微信商城";

    @Autowired
    private AlipayUtils alipayUtils;

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    @Override
    public PayResponse create(OrderDto orderDto) {
        //支付订单参数
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderName(WEIXIN_ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付 payRequest={}",payRequest);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付 Response = {}",payResponse);
        return payResponse;
    }

    @Override
    public PayResponse notify(String notifyData) {
        //1.验证签名
        //2.支付的状态
        //3.支付金额
        //4.支付人（下单人 == 支付人）
        //异步通知结果
        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】 异步通知，payResponse = {}",payResponse);
        //查询订单
        OrderDto orderDto = orderService.findOne(payResponse.getOrderId());
        if(orderDto == null){
            log.error("【微信支付】异步通知，订单存在。orderId={}",payResponse.getOrderId());
            throw new SellException(SellExceptionEnum.ORDER_NOT_EXIST);
        }
        //判断金额是否一致
        if(!MathUtil.equals(payResponse.getOrderAmount(),orderDto.getOrderAmount().doubleValue())){
            log.error("【微信支付】异步通知，订单金额不一致。orderId={},微信通知金额={}，订单金额={}",payResponse.getOrderId(),payResponse.getOrderAmount(),orderDto.getOrderAmount());
            throw new SellException(SellExceptionEnum.ORDER_AMOUNT_ERROR);
        }
        //修改订单支付状态
        orderService.paid(orderDto);
        return payResponse;
    }

    /**
     * 退款
     * @param orderDto
     */
    @Override
    public RefundResponse refund(OrderDto orderDto) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDto.getOrderId());
        refundRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}",refundRequest);
        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】refundResponse={}",refundResponse);
        return refundResponse;
    }

    /**
     * 支付宝当面支付服务
     * @param orderDto
     * @param authCode
     * @return
     */
    @Override
    public AlipayTradePayResponse alipayTradePay(OrderDto orderDto,String authCode) {
        AlipayTradePayRequest alipayTradePayRequest = AlipayUtils.getAlipayTradePayRequest(orderDto,authCode);
        AlipayTradePayResponse alipayTradePayResponse = alipayUtils.getAlipayTradeResponse(alipayTradePayRequest);
        String outTradeNo = alipayTradePayResponse.getOutTradeNo();
        String tradeNo = alipayTradePayResponse.getTradeNo();
        if(alipayTradePayResponse != null && AlipayResponseConstans.SUCCESS.equals(alipayTradePayResponse.getCode())){
            // 支付交易明确成功
            return alipayTradePayResponse;
        }else if(alipayTradePayResponse != null && AlipayResponseConstans.PAYING.equals(alipayTradePayResponse.getCode())){
            // 返回用户处理中，则轮询查询交易是否成功，如果查询超时，则调用撤销
            AlipayTradeQueryResponse alipayTradeQueryResponse = loopQueryResult(outTradeNo,tradeNo);
            // 根据查询结果queryResponse判断交易是否支付成功，如果支付成功则更新result并返回，如果不成功则调用撤销
           return checkQueryAndCancel(outTradeNo,tradeNo,alipayTradeQueryResponse);

        }else if(alipayTradePayResponse == null || AlipayResponseConstans.ERROR.equals(alipayTradePayResponse.getCode())){
            // 系统错误，则查询一次交易，如果交易没有支付成功，则调用撤销
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
            request.setBizContent("{" +
                    "    \"out_trade_no\": "+outTradeNo+"," +
                    "    \"trade_no\":"+tradeNo+"}"); //设置业务参数
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayUtils.getAlipayTradeQueryResponse(request);
            return checkQueryAndCancel(outTradeNo,tradeNo,alipayTradeQueryResponse);
        }else{
            // 其他情况表明该订单支付明确失败
            return alipayTradePayResponse;
        }

    }

    /**
     * 根据查询结果queryResponse判断交易是否支付成功，如果支付成功则更新result并返回，如果不成功则调用撤销
     * @param outTradeNo
     * @param tradeNo
     * @param queryResponse
     * @return
     */
    protected AlipayTradePayResponse checkQueryAndCancel(String outTradeNo, String tradeNo,
                                                     AlipayTradeQueryResponse queryResponse) {
        AlipayTradePayResponse alipayTradePayResponse = TradeQueryResponseToPayResponse.convert(queryResponse);
        if(AlipayUtils.querySuccess(queryResponse)){
            //如果查询返回支付成功，则返回相应结果
            return TradeQueryResponseToPayResponse.convert(queryResponse);
        }
        // 如果查询结果不为成功，则调用撤销
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();//创建API对应的request类
        request.setBizContent("{" +
                "    \"out_trade_no\": "+outTradeNo+"," +
                "    \"trade_no\":"+tradeNo+"}"); //设置业务参数
        AlipayTradeCancelResponse response = alipayUtils.getAlipayTradeCancelResponse(request);
        //交易异常，或发生系统错误
        if(AlipayUtils.tradeError(response)){
            // 如果第一次同步撤销返回异常，则标记支付交易为未知状态
            alipayTradePayResponse.setCode(AlipayResponseConstans.ERROR);
            return alipayTradePayResponse;
        }else{
            //标记支付为失败，如果撤销未能成功，产生的单边帐由人工处理
            alipayTradePayResponse.setCode(AlipayResponseConstans.FAILED);
            return alipayTradePayResponse;
        }
    }

    /**
     * 轮询查询订单
     * @return
     */
    public AlipayTradeQueryResponse loopQueryResult(String outTradeNo,String tradeNo){
        AlipayTradeQueryResponse resultTradeQueryResponse = null;
        //轮询次数
        for(int i = 0;i < alipayConfig.getMaxQueryRetry() ; i++){
            AlipayUtils.sleep(alipayConfig.getQueryDuration());//每五毫秒轮询一次
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
            request.setBizContent("{" +
                    "    \"out_trade_no\": "+outTradeNo+"," +
                    "    \"trade_no\":"+tradeNo+"}"); //设置业务参数
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayUtils.getAlipayTradeQueryResponse(request);
            if(alipayTradeQueryResponse != null){
                if(AlipayUtils.stopQuery(alipayTradeQueryResponse)){
                    //返回对应的结果
                    return alipayTradeQueryResponse;
                }
                resultTradeQueryResponse = alipayTradeQueryResponse;
            }
        }
        return resultTradeQueryResponse;
    }


}
