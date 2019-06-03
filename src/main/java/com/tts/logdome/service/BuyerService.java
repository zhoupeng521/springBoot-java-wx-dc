package com.tts.logdome.service;

import com.tts.logdome.dto.OrderDto;

/**
 * @创建人 pc801
 * @创建时间 2019/6/3
 * @描述
 */
public interface BuyerService {

    /**
     * 查询订单详情
     * @param openid
     * @param orderId
     * @return
     */
    OrderDto findOrderOne(String openid,String orderId);

    /**
     * 取消订单
     * @param openid
     * @param orderId
     * @return
     */
    OrderDto cancel(String openid,String orderId);

}
