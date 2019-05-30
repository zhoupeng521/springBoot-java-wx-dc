package com.tts.logdome.repository;

import com.tts.logdome.data.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单详情
 */
public interface OrderDetailRespository extends JpaRepository<OrderDetail,String> {

    /**
     * 根据订单ID查询订单详情
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);

}
