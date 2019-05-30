package com.tts.logdome.service;

import com.tts.logdome.data.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单主表 service 接口
 */
public interface OrderMasterService {

    OrderMaster findOne(String orderId);

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    Page<OrderMaster> findAll(Pageable pageable);

    OrderMaster save(OrderMaster orderMaster);

}
