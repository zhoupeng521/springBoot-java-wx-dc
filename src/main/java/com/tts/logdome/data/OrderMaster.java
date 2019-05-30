package com.tts.logdome.data;

import com.tts.logdome.common.enums.OrderStatusEnum;
import com.tts.logdome.common.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单主表
 */
@Data
@Entity
@DynamicUpdate
public class OrderMaster {

    /**
     * 主键ID
     */
    @Id
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
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
