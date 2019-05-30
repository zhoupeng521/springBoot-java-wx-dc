package com.tts.logdome.common.enums;

import lombok.Getter;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 异常信息枚举
 */
@Getter
public enum SellExceptionEnum {

    PRODUCT_NOT_EXIST(1,"商品不存在"),;

    private Integer code;

    private String message;

    SellExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
