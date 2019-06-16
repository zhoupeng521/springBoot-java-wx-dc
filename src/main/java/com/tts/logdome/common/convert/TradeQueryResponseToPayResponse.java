package com.tts.logdome.common.convert;

import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.tts.logdome.common.config.AlipayResponseConstans;
import com.tts.logdome.common.utils.AlipayUtils;

public class TradeQueryResponseToPayResponse {

    public static AlipayTradePayResponse convert(AlipayTradeQueryResponse response){

        AlipayTradePayResponse payResponse = new AlipayTradePayResponse();
        // 只有查询明确返回成功才能将返回码设置为10000，否则均为失败
        payResponse.setCode(AlipayUtils.querySuccess(response) ? AlipayResponseConstans.SUCCESS : AlipayResponseConstans.FAILED);
        // 补充交易状态信息
        StringBuilder msg = new StringBuilder(response.getMsg())
                .append(" tradeStatus:")
                .append(response.getTradeStatus());
        payResponse.setMsg(msg.toString());
        payResponse.setSubCode(response.getSubCode());
        payResponse.setSubMsg(response.getSubMsg());
        payResponse.setBody(response.getBody());
        payResponse.setParams(response.getParams());

        // payResponse应该是交易支付时间，但是response里是本次交易打款给卖家的时间,是否有问题
        // payResponse.setGmtPayment(response.getSendPayDate());
        payResponse.setBuyerLogonId(response.getBuyerLogonId());
        payResponse.setFundBillList(response.getFundBillList());
        payResponse.setOpenId(response.getOpenId());
        payResponse.setOutTradeNo(response.getOutTradeNo());
        payResponse.setReceiptAmount(response.getReceiptAmount());
        payResponse.setTotalAmount(response.getTotalAmount());
        payResponse.setTradeNo(response.getTradeNo());
        return payResponse;

    }

}
