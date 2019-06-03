package com.tts.logdome.service.impl;

import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.service.BuyerService;
import com.tts.logdome.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @创建人 pc801
 * @创建时间 2019/6/3
 * @描述
 */
@Service
@Slf4j
public class BuyerSerivceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    /**
     * 查询订单详情
     * @param openid
     * @param orderId
     * @return
     */
    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【订单详情】订单的openid与用户的openid不同，orderId={} opendid={}",orderId,openid);
            throw new SellException(SellExceptionEnum);
        }
        return null;
    }

    @Override
    public OrderDto cancel(String openid, String orderId) {
        return null;
    }
}
