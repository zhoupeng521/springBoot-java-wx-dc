package com.tts.logdome.common.utils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.tts.logdome.common.config.AlipayConfig;
import com.tts.logdome.common.config.AlipayResponseConstans;
import com.tts.logdome.common.model.builder.AlipayTradePayRequestBuilder;
import com.tts.logdome.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @创建人 zp
 * @创建时间 2019/6/13
 * @描述 支付宝--工具类
 */
@Component
@Slf4j
public class AlipayUtils {

    @Autowired
    private AlipayConfig alipayConfig;


    /**
     * 远程调用支付宝支付api接口
     * @param alipayTradePayRequest
     * @return
     */
    public AlipayTradePayResponse getAlipayTradeResponse( AlipayTradePayRequest alipayTradePayRequest){
        AlipayClient  alipayClient = new DefaultAlipayClient(alipayConfig.getUrl(), alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(),
                alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
        try {
            AlipayTradePayResponse alipayTradePayResponse = alipayClient.execute(alipayTradePayRequest);
            if(alipayTradePayResponse != null){
                log.info("【支付宝当面支付响应参数】alipayTradePayResponse={}",alipayTradePayResponse.getBody());
            }
            return alipayTradePayResponse;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * 支付宝查询订单结果
     * @param request
     * @return
     */
    public AlipayTradeQueryResponse getAlipayTradeQueryResponse(AlipayTradeQueryRequest request){
        AlipayClient  alipayClient = new DefaultAlipayClient(alipayConfig.getUrl(), alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(),
                alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
        try {
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.execute(request);
            if(alipayTradeQueryResponse != null){
                log.info("【支付宝查询订单结果】alipayTradeQueryResponse={}",alipayTradeQueryResponse.getBody());
            }
            return alipayTradeQueryResponse;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 支付宝撤销订单结果
     * @param request
     * @return
     */
    public AlipayTradeCancelResponse getAlipayTradeCancelResponse(AlipayTradeCancelRequest request){
        AlipayClient  alipayClient = new DefaultAlipayClient(alipayConfig.getUrl(), alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(),
                alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
        try {
            AlipayTradeCancelResponse response = alipayClient.execute(request);
            if(response != null){
                log.info("【支付宝撤销订单】body={}",response.getBody());
            }
            return response;
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 组装请求参数
     * @param orderDto
     * @param authCode
     * @return
     */
    public static AlipayTradePayRequest getAlipayTradePayRequest(OrderDto orderDto,String authCode){
        AlipayTradePayRequestBuilder alipayTradePayRequestBuilder = getTradePayRequestBuilder(orderDto,authCode);
        AlipayTradePayRequest alipayTradePayRequest = new AlipayTradePayRequest();
        alipayTradePayRequest.setBizContent(alipayTradePayRequestBuilder.toJsonString());
        alipayTradePayRequest.setNotifyUrl(alipayTradePayRequestBuilder.getNotifyUrl());
        alipayTradePayRequest.putOtherTextParam("app_auth_token",alipayTradePayRequestBuilder.getAppAuthToken());
        return alipayTradePayRequest;
    }

    /**
     * 组装请求参数
     * @param orderDto
     * @param authCode
     * @return
     */
    public static AlipayTradePayRequestBuilder getTradePayRequestBuilder(OrderDto orderDto,String authCode){
        AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder();
        builder.setAuthCode(authCode);
        builder.setStoreId("NJ_001");
        builder.setTotalAmount(orderDto.getOrderAmount().toString());
        builder.setBody("好喝的东鹏特饮");
        return builder;
    }

    /**
     * 设置睡眠时间
     * @param time
     */
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询返回“支付成功”
     * @param response
     * @return
     */
    public static boolean querySuccess(AlipayTradeQueryResponse response) {
        return response != null &&
                AlipayResponseConstans.SUCCESS.equals(response.getCode()) &&
                ("TRADE_SUCCESS".equals(response.getTradeStatus()) ||
                        "TRADE_FINISHED".equals(response.getTradeStatus())
                );
    }

    /**
     * 判断是否停止查询
     * @param response
     * @return
     */
    public static boolean stopQuery(AlipayTradeQueryResponse response) {
        if (AlipayResponseConstans.SUCCESS.equals(response.getCode())) {
            if ("TRADE_FINISHED".equals(response.getTradeStatus()) ||
                    "TRADE_SUCCESS".equals(response.getTradeStatus()) ||
                    "TRADE_CLOSED".equals(response.getTradeStatus())) {
                // 如果查询到交易成功、交易结束、交易关闭，则返回对应结果
                return true;
            }
        }
        return false;
    }

    /**
     * 交易异常，或发生系统错误
     * @param response
     * @return
     */
    public static boolean tradeError(AlipayResponse response) {
        return response == null ||
                AlipayResponseConstans.ERROR.equals(response.getCode());
    }

}
