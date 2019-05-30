package com.tts.logdome.repository;

import com.tts.logdome.data.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单主表 Dao
 */
public interface OrderMasterRespository extends JpaRepository<OrderMaster,String> {

    /**
     * 根据买家微信openid查询订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
