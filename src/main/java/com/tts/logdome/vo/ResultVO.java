package com.tts.logdome.vo;

import lombok.Data;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述
 */
@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 相应数据
     */
    private T data;

}
