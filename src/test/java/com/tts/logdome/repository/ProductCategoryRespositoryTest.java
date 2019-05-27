package com.tts.logdome.repository;

import com.tts.logdome.data.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRespositoryTest {

    @Autowired
    private ProductCategoryRespository productCategoryRespository;

    @Test
    public void findOne(){
        List<ProductCategory> productGategories = productCategoryRespository.findAll();
        System.out.println(productGategories.toString());
    }

}