package com.tts.logdome.repository;

import com.tts.logdome.data.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述 商品Dao测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRespositoryTest {

    @Autowired
    private ProductInfoRespository productInfoRespository;

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(UUID.randomUUID().toString());
        productInfo.setProductName("七分裤子");
        productInfo.setProductPrice(new BigDecimal(120));
        productInfo.setProductStatus(0);
        productInfo.setProductStock(100);
        productInfo.setCategoryType(1);
        productInfo.setProductIcon("dasdkfjlkjlfkjlkajflksf");
        productInfo.setProductDescription("好的七分裤子");
        ProductInfo info = productInfoRespository.save(productInfo);
        Assert.assertNotNull(info);
    }

    @Test
    public void findByProductStatus(){
        List<ProductInfo> productInfoList = productInfoRespository.findByProductStatus(0);
        for (ProductInfo productInfo : productInfoList){
            System.out.println(productInfo.toString());
        }
    }
}