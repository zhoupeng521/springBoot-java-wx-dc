package com.tts.logdome.dto;

import lombok.Data;

/**
 * @创建人 pc801
 * @创建时间 2019/5/30
 * @描述
 */
@Data
public class ShopCarDto {

    private String productId;

    private Integer productQuantity;

    public ShopCarDto() {

    }

    public ShopCarDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
