package com.tts.logdome.repository;

import com.tts.logdome.data.OrderDetail;
import com.tts.logdome.data.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderDetailRespositoryTest {

    @Autowired
    private OrderDetailRespository respository;

    @Autowired
    private ProductInfoRespository productInfoRespository;

    @Test
    public void findByBuyerOpenid(){
        List<OrderDetail> orderDetailList = respository.findByOrderId("070b29b5-0e3f-427a-b542-f8b0060f9378");
        for (OrderDetail orderDetail : orderDetailList){
            System.out.println(orderDetail.toString());
        }
    }

    @Test
    public void saveTest(){
        ProductInfo productInfo = productInfoRespository.findById("f2415eda-e4f5-417d-836c-0cf1574a292a").orElse(null);
        if(productInfo == null){
            log.error("商品不存在！！");
            return;
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(UUID.randomUUID().toString());
        orderDetail.setOrderId("070b29b5-0e3f-427a-b542-f8b0060f9378");
        orderDetail.setProductId("f2415eda-e4f5-417d-836c-0cf1574a292a");
        orderDetail.setProductName(productInfo.getProductName());
        orderDetail.setProductPrice(productInfo.getProductPrice());
        orderDetail.setProductIcon(productInfo.getProductIcon());
        orderDetail.setProductQuantity(1);
        OrderDetail detail = respository.save(orderDetail);
        Assert.assertNotNull(detail);
    }

}