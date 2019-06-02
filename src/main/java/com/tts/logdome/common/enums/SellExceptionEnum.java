package com.tts.logdome.common.enums;

import lombok.Getter;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 异常信息枚举
 */
@Getter
public enum SellExceptionEnum {

    PRODUCT_NOT_EXIST(1,"商品不存在"),
    PRODUCT_OF_LACK(2,"商品库存不足"),
    ORDER_NOT_EXIST(3,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(4,"订单详情不存在"),
    ORDER_STATUS_ERROR(5,"订单状态不正确"),
    ORDER_UPDATE_ERROR(6,"订单更新失败"),
    PAY_STATUS_ERROR(7,"订单支付状态不正确"),
    PARAM_ERROR(8,"参数不正确"),
    CART_ENPTY(9,"购物车不能为空");

    private Integer code;

    private String message;

    SellExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
