package com.tts.logdome.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
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

}
