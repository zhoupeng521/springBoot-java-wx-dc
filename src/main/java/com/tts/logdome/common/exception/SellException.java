package com.tts.logdome.common.exception;

import com.tts.logdome.common.enums.SellExceptionEnum;

/**
 * @创建人 pc801
 * @创建时间 2019/5/30
 * @描述 异常
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(SellExceptionEnum sellExceptionEnum){
        super(sellExceptionEnum.getMessage());
        this.code = sellExceptionEnum.getCode();
    }

    public SellException(SellExceptionEnum sellExceptionEnum,String message){
        super(message);
        this.code = sellExceptionEnum.getCode();
    }

    public SellException(Integer code,String message){
        super(message);
        this.code = code;
    }

}
