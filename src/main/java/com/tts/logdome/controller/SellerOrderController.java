package com.tts.logdome.controller;

import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.service.OrderService;
import com.tts.logdome.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam("page") Integer page,
                             @RequestParam("size") Integer size){
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<OrderDto> orderDtoPage = orderService.findAllList(pageRequest);
        Map map = new HashMap();
        map.put("orderDtoPage",orderDtoPage);
        return new ModelAndView("/order/list",map);
    }

}
