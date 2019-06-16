package com.tts.logdome.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @创建人 zp
 * @创建时间 2019/6/13
 * @描述 支付宝参数配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

    //	支付宝网关（固定）
    private String url;

    //APPID 即创建应用后生成
    private String appId;

    //	开发者应用私钥，由开发者自己生成
    private String appPrivateKey;

    //参数返回格式，只支持 json 格式
    private String format;

    //请求和签名使用的字符编码格式，支持 GBK和 UTF-8
    private String charset;

    //支付宝公钥，由支付宝生成
    private String alipayPublicKey;

    //商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
    private String signType;

    //最大轮询次数
    private Integer maxQueryRetry;

    //每次轮询的间隔
    private Integer queryDuration;
}
