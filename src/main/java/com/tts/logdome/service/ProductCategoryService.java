package com.tts.logdome.service;

import com.tts.logdome.data.ProductCategory;

import java.util.List;


public interface ProductCategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryId);

    ProductCategory save(ProductCategory productCategory);

}
