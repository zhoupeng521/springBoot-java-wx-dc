package com.tts.logdome.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述
 */
@Data
public class ProductInfoVO {

    @JsonProperty("id")
    private String productId;

    /**
     * 商品名称
     */
    @JsonProperty("name")
    private String productName;

    /**
     * 商品价格
     */
    @JsonProperty("price")
    private BigDecimal productPrice;

    /**
     * 商品描述
     */
    @JsonProperty("description")
    private String productDescription;

    /**
     * 商品小图
     */
    @JsonProperty("icon")
    private String productIcon;
}
