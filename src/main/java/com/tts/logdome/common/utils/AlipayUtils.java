package com.tts.logdome.common.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.tts.logdome.common.config.AlipayConfig;
import com.tts.logdome.common.model.builder.AlipayTradePayRequestBuilder;
import com.tts.logdome.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @创建人 zp
 * @创建时间 2019/6/13
 * @描述 支付宝--工具类
 */
@Component
public class AlipayUtils {

    @Autowired
    private AlipayConfig alipayConfig;

    public AlipayClient getAlipayClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getUrl(), alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(),
                alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
        return  alipayClient;
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
}
