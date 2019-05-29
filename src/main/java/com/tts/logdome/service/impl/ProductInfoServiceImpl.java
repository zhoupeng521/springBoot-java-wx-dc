package com.tts.logdome.service.impl;

import com.tts.logdome.common.enums.ProductStatusEnum;
import com.tts.logdome.data.ProductInfo;
import com.tts.logdome.repository.ProductInfoRespository;
import com.tts.logdome.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @创建人 pc801
 * @创建时间 2019/5/28
 * @描述
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRespository productInfoRespository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRespository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRespository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(PageRequest pageRequest) {
        return productInfoRespository.findAll(pageRequest);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRespository.save(productInfo);
    }
}
