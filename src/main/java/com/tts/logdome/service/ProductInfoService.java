package com.tts.logdome.service;

import com.tts.logdome.data.ProductInfo;
import com.tts.logdome.dto.ShopCarDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述 商品Service
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(PageRequest pageRequest);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(ShopCarDto shopCarDto);

    //减库存
    void lessenStock(ShopCarDto shopCarDto);
}
