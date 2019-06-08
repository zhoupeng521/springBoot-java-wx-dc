package com.tts.logdome.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.tts.logdome.common.config.WechatPayConfig;
import com.tts.logdome.dto.OrderDto;
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

    @Override
    public PayResponse create(OrderDto orderDto) {
        //支付订单参数
        PayRequest payRequest = new PayRequest();
        payRequest.setOpenid(orderDto.getBuyerOpenid());
        payRequest.setOrderId(orderDto.getOrderId());
        payRequest.setOrderAmount(orderDto.getOrderAmount().doubleValue());
        payRequest.setOrderName(WEIXIN_ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】 payRequest={}",payRequest);
        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付Response】 = {}",payResponse);
        return payResponse;
    }

}
