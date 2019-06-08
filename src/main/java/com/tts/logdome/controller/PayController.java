package com.tts.logdome.controller;

import com.lly835.bestpay.model.PayResponse;
import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.service.OrderService;
import com.tts.logdome.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付--Controller
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    /**
     * 创建支付
     * @param orderId
     * @param returnUrl
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl){
        //1、查询订单
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            throw new SellException(SellExceptionEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        PayResponse payResponse = payService.create(orderDto);
        Map<String,Object> map = new HashMap<>();
        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);
        return new ModelAndView("pay/create",map);
    }

    /**
     * 异步通知
     * @param notifyData
     */
    @PostMapping("notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        //返回给微信处理结果
        return new ModelAndView("pay/success");
    }

}
