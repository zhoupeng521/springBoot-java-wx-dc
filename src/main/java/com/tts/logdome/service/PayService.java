package com.tts.logdome.service;

import com.lly835.bestpay.model.PayResponse;
import com.tts.logdome.dto.OrderDto;

public interface PayService {

    PayResponse create(OrderDto orderDto);

}
