package com.tts.logdome.data;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class AlipayRecord {

    /**
     * 主键ID
     */
    @Id
    private String alipayId;

    /**
     * 支付结果code
     */
    @Column(name = "pay_code")
    private String code;

    /**
     * 支付结果消息
     */
    @Column(name = "pay_msg")
    private String msg;

    /**
     *  支付宝交易号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 买家在支付宝的用户id
     */
    private String buyerLoginId;

    /**
     * 交易金额
     */
    private String totalAmount;

    /**
     * 买家名称
     */
    private String buyerUserName;

    /**
     * 实收金额
     */
    private String receiptAmount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
