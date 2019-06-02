package com.tts.logdome.service;

import com.tts.logdome.data.OrderMaster;
import com.tts.logdome.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单主表 service 接口
 */
public interface OrderService {

    /** 查询单个订单 **/
    OrderDto findOne(String orderId);

    /** 查询订单列表 **/
    Page<OrderDto> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    /** 查询订单全部列表 **/
    Page<OrderDto> findAllList(Pageable pageable);

    /** 创建订单 **/
    OrderMaster create(OrderDto orderDto);

    /** 取消订单 **/
    OrderDto cancel(OrderDto orderDto);

    /** 完结订单 **/
    OrderDto finish(OrderDto orderDto);

    /** 支付订单 **/
    OrderDto paid(OrderDto orderDto);

}
