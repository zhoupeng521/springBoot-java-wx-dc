package com.tts.logdome.common.convert;

import com.tts.logdome.data.OrderMaster;
import com.tts.logdome.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 将OrderMaster转成OrderDto转换器
 */
public class OrderMasterConvertToOrderDTO {

    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }


    public static List<OrderDto> convert(List<OrderMaster> orderMasters){
        return orderMasters.stream().map(e ->
            convert(e)
        ).collect(Collectors.toList());
    }
}
