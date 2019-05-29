package com.tts.logdome.repository;

import com.tts.logdome.data.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述 商品Dao
 */
public interface ProductInfoRespository extends JpaRepository<ProductInfo,String> {

    /**
     * 根据商品状态查询商品
     * @param productStatus
     * @return
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

}
