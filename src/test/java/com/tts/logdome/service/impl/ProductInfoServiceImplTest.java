package com.tts.logdome.service.impl;

import com.tts.logdome.data.ProductInfo;
import com.tts.logdome.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述 商品Service 单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findAll(){
        PageRequest pageRequest = PageRequest.of(0,2);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        System.out.println(productInfoPage);
    }

}