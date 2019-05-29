package com.tts.logdome.common.enums;

import lombok.Getter;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述 商品状态枚举
 */
@Getter
public enum ProductStatusEnum {

    UP(0,"上架"),
    DOWN(1,"下架"),
    ;

    private Integer code;

    private String msg;

    ProductStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
