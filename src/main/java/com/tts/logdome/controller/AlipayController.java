package com.tts.logdome.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.tts.logdome.common.utils.AlipayUtils;
import com.tts.logdome.common.utils.ResultVOUtils;
import com.tts.logdome.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 zp
 * @创建时间 2019/6/13
 * @描述 支付宝
 */
@RestController
public class AlipayController {

    @Autowired
    private AlipayUtils alipayUtils;

    @RequestMapping("/pay")
    public ResultVO<AlipayTradePayResponse> pay(){
        AlipayClient alipayClient = alipayUtils.getAlipayClient();
        AlipayTradePayRequest alipayTradePayRequest = new AlipayTradePayRequest();//创建API对应的request类
        alipayTradePayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20190320010101011\"," +
                "    \"scene\":\"bar_code\"," +
                "    \"auth_code\":\"281594966156459756\"," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"store_id\":\"NJ_001\"," +
                "    \"timeout_express\":\"2m\"," +
                "    \"total_amount\":\"1000\"" +
                "  }");
        AlipayTradePayResponse response = null;
        try {
            response = alipayClient.execute(alipayTradePayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return ResultVOUtils.success(response);
    }

}
