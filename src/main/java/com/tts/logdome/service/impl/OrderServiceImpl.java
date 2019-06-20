package com.tts.logdome.service.impl;

import com.tts.logdome.common.convert.OrderMasterConvertToOrderDTO;
import com.tts.logdome.common.enums.OrderStatusEnum;
import com.tts.logdome.common.enums.PayStatusEnum;
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
import com.tts.logdome.service.PayService;
import com.tts.logdome.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单主表Service实现
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRespository orderMasterRespository;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRespository orderDetailRespository;

    @Autowired
    private PayService payService;

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRespository.findById(orderId).orElse(null);
        if(orderMaster == null){
            throw new SellException(SellExceptionEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRespository.findByOrderId(orderMaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(SellExceptionEnum.ORDER_DETAIL_NOT_EXIST);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    /**
     * 查询订单列表（根据买家）
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDto> findByBuyerOpenid(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRespository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDto> orderDtos = OrderMasterConvertToOrderDTO.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDto>(orderDtos,pageable,orderMasterPage.getTotalElements());
    }

    /**
     * 查询所有订单列表(卖家端)
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDto> findAllList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRespository.findAll(pageable);
        List<OrderDto> orderDtos = OrderMasterConvertToOrderDTO.convert(orderMasterPage.getContent());
        return new PageImpl<OrderDto>(orderDtos,pageable,orderMasterPage.getTotalElements());
    }

    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
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
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(amount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW_ORDER.getCode());
        orderMaster.setPayStatus(PayStatusEnum.FININSH_PAY.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW_ORDER.getCode());
        orderMaster = orderMasterRespository.save(orderMaster);
        //4.扣库存
        List<ShopCarDto> shopCarDtoList = orderDto.getOrderDetailList().stream().map(e->
             new ShopCarDto(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.lessenStock(shopCarDtoList);
        return orderMaster;
    }

    /**
     * 取消订单
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())){
            log.error("【取消订单】 订单状态不正确 orderId={} orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(SellExceptionEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCLEL_ORDER.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateOrder = orderMasterRespository.save(orderMaster);
        if(updateOrder == null){
            log.error("【取消订单】订单更新失败 orderMaster={}",orderMaster);
            throw new SellException(SellExceptionEnum.ORDER_UPDATE_ERROR);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            throw new SellException(SellExceptionEnum.ORDER_DETAIL_NOT_EXIST);
        }
        List<ShopCarDto> shopCarDtoList = orderDto.getOrderDetailList().stream().map(e ->
                            new ShopCarDto(e.getProductId(),e.getProductQuantity())
                                ).collect(Collectors.toList());
        productInfoService.increaseStock(shopCarDtoList);
        //如果已支付，需要退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.FININSH_PAY.getCode())){
            // TODO
            payService.refund(orderDto);
        }
        return orderDto;
    }

    /**
     * 订单完结
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())){
            log.error("【订单完成】订单状态不正确 orderId={} orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(SellExceptionEnum.ORDER_STATUS_ERROR);
        }
        //修改状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISH_ORDER.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster order = orderMasterRespository.save(orderMaster);
        if (order == null){
            log.error("【订单完成】状态更新失败 orderMaster={}" ,orderMaster);
            throw new SellException(SellExceptionEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }

    /**
     * 已支付
     * @param orderDto
     * @return
     */
    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW_ORDER.getCode())){
            log.error("【支付完成】订单状态不正确 orderId={} orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(SellExceptionEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDto.getPayStatus().equals(PayStatusEnum.WAIT_PAY)){
            log.error("【支付完成】订单支付状态不正确 orderId={} payStatus={}",orderDto.getOrderId(),orderDto.getPayStatus());
            throw new SellException(SellExceptionEnum.PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderDto.setPayStatus(PayStatusEnum.FININSH_PAY.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster order = orderMasterRespository.save(orderMaster);
        if (order == null){
            log.error("【支付完成】支付状态更新失败 orderMaster={}" ,orderMaster);
            throw new SellException(SellExceptionEnum.ORDER_UPDATE_ERROR);
        }
        return orderDto;
    }
}
