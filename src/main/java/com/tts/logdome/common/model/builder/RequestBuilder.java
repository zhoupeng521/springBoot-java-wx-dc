package com.tts.logdome.common.model.builder;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.http.converter.json.GsonFactoryBean;

/**
 * @创建人 zp
 * @创建时间 2019/6/15
 * @描述 支付宝——当面付请求参数封装
 */
@Data
public abstract class RequestBuilder {

    //详见应用授权概述
    private String appAuthToken;

    //支付宝服务器主动通知商户服务器里指定的页面http/https路径。
    private String notifyUrl;

    //获取bizContent对象，用于下一步转换为json字符串
    public abstract Object getBizContent();

    //将bizContent对象转换为json字符串
    public String toJsonString(){
        Gson gson = new Gson();
        return gson.toJson(this.getBizContent());
    }


}
