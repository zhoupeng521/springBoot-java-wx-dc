package com.tts.logdome.controller;

import com.tts.logdome.common.utils.ResultVOUtils;
import com.tts.logdome.data.ProductCategory;
import com.tts.logdome.data.ProductInfo;
import com.tts.logdome.service.ProductCategoryService;
import com.tts.logdome.service.ProductInfoService;
import com.tts.logdome.vo.ProductInfoVO;
import com.tts.logdome.vo.ProductVO;
import com.tts.logdome.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();
        //2.查询类目（一次性查询）
        List<Integer> categoryTypeList = productInfoList.stream().map(e->e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(productCategory,productVO);
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if(productInfo.getCategoryType() == productCategory.getCategoryType()){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtils.success(productVOList);
    }

}
