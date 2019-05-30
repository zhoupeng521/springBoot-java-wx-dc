package com.tts.logdome.service.impl;

import com.tts.logdome.data.OrderDetail;
import com.tts.logdome.repository.OrderDetailRespository;
import com.tts.logdome.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 商品详情Service实现
 */
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRespository orderDetailRespository;

    @Override
    public OrderDetail findOne(String detailId) {
        return orderDetailRespository.findById(detailId).orElse(null);
    }

    @Override
    public List<OrderDetail> findByOrderId(String orderId) {
        return orderDetailRespository.findByOrderId(orderId);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRespository.save(orderDetail);
    }
}
