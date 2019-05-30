package com.tts.logdome.common.enums;

import lombok.Getter;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 支付状态枚举
 */
@Getter
public enum PayStatusEnum {

    WAIT_PAY(0,"等待支付"),
    FININSH_PAY(1,"完成支付"),;

    private int code ;

    private String msg;

    PayStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
