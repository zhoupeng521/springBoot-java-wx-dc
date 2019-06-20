package com.tts.logdome.controller;

import com.alipay.api.response.AlipayTradePayResponse;
import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.common.utils.ResultVOUtils;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.service.OrderService;
import com.tts.logdome.service.PayService;
import com.tts.logdome.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private PayService payService;

    /**
     * 支付宝---支付接口
     * @param orderId
     * @param authCode
     * @return
     */
    @GetMapping("/pay")
    public ResultVO<AlipayTradePayResponse> pay(@RequestParam("orderId") String orderId,
                                                @RequestParam("authCode") String authCode){
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            throw new SellException(SellExceptionEnum.ORDER_NOT_EXIST);
        }
        AlipayTradePayResponse alipayTradePayResponse = payService.alipayTradePay(orderDto,authCode);
        return ResultVOUtils.success(alipayTradePayResponse);
    }

    /**
     * 支付宝退款
     * @return
     */
    @GetMapping("/refund")
    public ResultVO<Void> refund(){
        return ResultVOUtils.success();
    }


}
