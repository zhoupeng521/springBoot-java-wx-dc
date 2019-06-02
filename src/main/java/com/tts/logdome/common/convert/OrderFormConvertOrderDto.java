package com.tts.logdome.common.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.data.OrderDetail;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderFormConvertOrderDto {

    public static OrderDto convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        orderDto.setBuyerAddress(orderForm.getAddress());

        List<OrderDetail> orderDetailList = null;
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误,string=",orderForm.getItems());
            throw new SellException(SellExceptionEnum.PARAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

}
