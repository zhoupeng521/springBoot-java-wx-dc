package com.tts.logdome.service.impl;

import com.tts.logdome.common.enums.OrderStatusEnum;
import com.tts.logdome.data.OrderDetail;
import com.tts.logdome.data.OrderMaster;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void findOne() {

        OrderDto orderDto = orderService.findOne("3140a174-851b-4220-8adf-d5eb85a59329");

        log.info("orderDto ==" + orderDto.toString());

    }

    @Test
    public void findByBuyerOpenid() {
    }

    @Test
    public void findAllList() {
        Page<OrderDto> orderDtoPage = orderService.findAllList(PageRequest.of(0,10));
        System.out.println(orderDtoPage);
    }

    @Test
    public void create() {

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("周平");
        orderDto.setBuyerAddress("墨客家");
        orderDto.setBuyerOpenid("asd");
        orderDto.setBuyerPhone("1569082438");

        List<OrderDetail> detailList = new ArrayList<>();
        OrderDetail detail = new OrderDetail();
        detail.setProductId("1f73319d-7a75-4909-aaae-5310abc62ef7");
        detail.setProductQuantity(1);
        detailList.add(detail);

        orderDto.setOrderDetailList(detailList);
        OrderMaster orderMaster = orderService.create(orderDto);

    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne("3140a174-851b-4220-8adf-d5eb85a59329");
        OrderDto orderDto1 = orderService.cancel(orderDto);
        Assert.assertNotNull(orderDto1);
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}