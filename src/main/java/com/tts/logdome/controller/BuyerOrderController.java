package com.tts.logdome.controller;

import com.tts.logdome.common.convert.OrderFormConvertOrderDto;
import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.common.utils.ResultVOUtils;
import com.tts.logdome.data.OrderMaster;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.form.OrderForm;
import com.tts.logdome.service.OrderService;
import com.tts.logdome.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家订单Controller
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    //创建订单
    @RequestMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        //检验参数是否错误
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(SellExceptionEnum.PARAM_ERROR,bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderFormConvertOrderDto.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【购物车】商品不存在 orderDto={}" ,orderDto);
            throw new SellException(SellExceptionEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderMaster orderMaster = orderService.create(orderDto);

        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("orderId",orderMaster.getOrderId());
        return ResultVOUtils.success(resultMap);
    }

    //订单列表
    public ResultVO<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){

        return null;
    }

    //订单详情
    public ResultVO detail(){
        return null;
    }

    //取消订单
    public ResultVO cancle(){
        return null;
    }

    //支付完成
    public ResultVO paid(){
        return null;
    }

}
