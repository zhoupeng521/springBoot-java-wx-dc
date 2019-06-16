package com.tts.logdome.service;

import com.alipay.api.response.AlipayTradePayResponse;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.tts.logdome.dto.OrderDto;
import com.tts.logdome.vo.ResultVO;
import org.hibernate.criterion.Order;

public interface PayService {

    PayResponse create(OrderDto orderDto);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDto orderDto);

    AlipayTradePayResponse alipayTradePay(OrderDto orderDto,String authCode);
}
