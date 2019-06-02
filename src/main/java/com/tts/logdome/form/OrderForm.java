package com.tts.logdome.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 订单表单实体
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号码必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "微信openid必填")
    private String openid;

    @NotEmpty(message = "购物车选项必填")
    private String items;

}
