package com.tts.logdome.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tts.logdome.common.enums.OrderStatusEnum;
import com.tts.logdome.common.enums.PayStatusEnum;
import com.tts.logdome.common.utils.serializer.Date2LongSerializer;
import com.tts.logdome.data.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @创建人 pc801
 * @创建时间 2019/5/30
 * @描述
 */
@Data
public class OrderDto {

    /**
     * 主键ID
     */
    private String orderId;

    /**
     * 买家名称
     */
    private String buyerName;

    /**
     * 买家手机号码
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openId
     */
    private String buyerOpenid;

    /**
     * 订单总额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态
     */
    private Integer orderStatus = OrderStatusEnum.NEW_ORDER.getCode();

    /**
     * 支付状态
     */
    private Integer payStatus = PayStatusEnum.WAIT_PAY.getCode();

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetailList;
}
