package com.tts.logdome.repository;

import com.tts.logdome.data.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @创建人 pc801
 * @创建时间 2019/5/30
 * @描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRespositoryTest {

    @Autowired
    private OrderMasterRespository respository;

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<OrderMaster> orderMasterPage = respository.findByBuyerOpenid("abc",pageRequest);
        System.out.println(orderMasterPage);
    }

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(UUID.randomUUID().toString());
        orderMaster.setBuyerAddress("中华大西北");
        orderMaster.setBuyerName("王德顺");
        orderMaster.setBuyerOpenid("cda");
        orderMaster.setBuyerPhone("15669082438");
        orderMaster.setOrderAmount(new BigDecimal(322));
        OrderMaster orderMaster1 = respository.save(orderMaster);
        Assert.assertNotNull(orderMaster1);
    }
}