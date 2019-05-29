package com.tts.logdome.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @创建人 zp
 * @创建时间 2019/5/28
 * @描述 商品
 */
@Data
@Entity
public class ProductInfo {

    /**
     * 主鍵ID
     */
    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品状态：0正常；1下架
     */
    private Integer productStatus;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * 商品小图
     */
    private String productIcon;

    /**
     * 商品类目
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
