package com.tts.logdome.service.impl;

import com.tts.logdome.data.ProductCategory;
import com.tts.logdome.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void findOne(){
        ProductCategory productCategory = productCategoryService.findOne(1);
        System.out.println(productCategory.toString());
    }

}