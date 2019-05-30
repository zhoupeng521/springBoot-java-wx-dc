package com.tts.logdome.service.impl;

import com.tts.logdome.data.OrderMaster;
import com.tts.logdome.repository.OrderMasterRespository;
import com.tts.logdome.service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单主表Service实现
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRespository orderMasterRespository;

    @Override
    public OrderMaster findOne(String orderId) {
        return orderMasterRespository.findById(orderId).orElse(null);
    }

    @Override
    public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {
        return orderMasterRespository.findByBuyerOpenid(buyerOpenid,pageable);
    }

    @Override
    public Page<OrderMaster> findAll(Pageable pageable) {
        return orderMasterRespository.findAll(pageable);
    }

    @Override
    public OrderMaster save(OrderMaster orderMaster) {
        return orderMasterRespository.save(orderMaster);
    }
}
