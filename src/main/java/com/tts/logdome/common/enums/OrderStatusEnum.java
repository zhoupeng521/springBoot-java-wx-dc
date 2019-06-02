package com.tts.logdome.common.enums;

import lombok.Getter;

/**
 * @创建人 pc801
 * @创建时间 2019/5/30
 * @描述
 */
@Getter
public enum OrderStatusEnum {

    NEW_ORDER(0,"新订单"),
    FINISH_ORDER(1,"完成订单"),
    CANCLEL_ORDER(2,"取消订单"),;

    private int code ;

    private String msg;

    OrderStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
