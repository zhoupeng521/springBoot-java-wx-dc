package com.tts.logdome.data;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @创建人 zp
 * @创建时间 2019/5/30
 * @描述 订单详情表
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    /**
     * 主键ID
     */
    @Id
    private String detailId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 产品ID
     */
    private String productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品价格
     */
    private BigDecimal productPrice;

    /**
     * 产品数量
     */
    private Integer productQuantity;

    /**
     * 产品小图片
     */
    private String productIcon;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
