package com.tts.logdome.service;

import com.tts.logdome.data.OrderDetail;

import java.util.List;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单详情 Service
 */
public interface OrderDetailService {

    OrderDetail findOne(String detailId);

    List<OrderDetail> findByOrderId(String orderId);

    OrderDetail save(OrderDetail orderDetail);

}
