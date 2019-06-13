package com.tts.logdome.common.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.tts.logdome.common.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @创建人 zp
 * @创建时间 2019/6/13
 * @描述 支付宝--工具类
 */
@Component
public class AlipayUtils {

    @Autowired
    private AlipayConfig alipayConfig;

    public AlipayClient getAlipayClient(){
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getUrl(), alipayConfig.getAppId(), alipayConfig.getAppPrivateKey(),
                alipayConfig.getFormat(), alipayConfig.getCharset(), alipayConfig.getAlipayPublicKey(), alipayConfig.getSignType());
        return  alipayClient;
    }

}
