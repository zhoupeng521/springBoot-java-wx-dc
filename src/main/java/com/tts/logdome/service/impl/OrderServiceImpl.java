package com.tts.logdome.service.impl;

import com.tts.logdome.common.enums.SellExceptionEnum;
import com.tts.logdome.common.exception.SellException;
import com.tts.logdome.common.utils.KeyGenerateUtils;
import com.tts.logdome.data.OrderDetail;
import com.tts.logdome.data.OrderMaster;
import com.tts.logdome.data.ProductInfo;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.dto.ShopCarDto;
import com.tts.logdome.repository.OrderDetailRespository;
import com.tts.logdome.repository.OrderMasterRespository;
import com.tts.logdome.service.OrderService;
import com.tts.logdome.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Collectors;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单主表Service实现
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRespository orderMasterRespository;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRespository orderDetailRespository;

    @Override
    public OrderMaster findOne(String orderId) {
        return orderMasterRespository.findById(orderId).orElse(null);
    }

    @Override
    public Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public Page<OrderMaster> findAllList(Pageable pageable) {
        return null;
    }

    @Override
    public OrderMaster create(OrderDto orderDto) {
        BigDecimal amount = new BigDecimal(BigInteger.ZERO);
        String orderId = KeyGenerateUtils.UUIDGenerate();
        //1.查询商品(数量，价格)
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(SellExceptionEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            amount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(amount);
            //3.1 写入数据库(detail)
            orderDetail.setDetailId(KeyGenerateUtils.UUIDGenerate());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRespository.save(orderDetail);
        }
        //3.2 写入数据库(master)
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(amount);
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMasterRespository.save(orderMaster);
        //4.扣库存
       /* ShopCarDto shopCarDto = orderDto.getOrderDetailList().stream().map(e->
             new ShopCarDto(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());*/
        productInfoService.lessenStock(shopCarDto);
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
