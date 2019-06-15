package com.tts.logdome.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.common.model.builder.AlipayTradePayRequestBuilder;
import com.tts.logdome.common.utils.AlipayUtils;
import com.tts.logdome.common.utils.ResultVOUtils;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.service.OrderService;
import com.tts.logdome.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 zp
 * @创建时间 2019/6/13
 * @描述 支付宝
 */
@RestController
public class AlipayController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/pay")
    public ResultVO<AlipayTradePayResponse> pay(@RequestParam("orderId") String orderId,
                                                @RequestParam("authCode") String authCode){
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            throw new SellException(SellExceptionEnum.ORDER_NOT_EXIST);
        }
        AlipayTradePayRequest alipayTradePayRequest = AlipayUtils.getAlipayTradePayRequest(orderDto,authCode);

        return ResultVOUtils.success();
    }

}
