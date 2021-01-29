package com.vroong.newbee.application.model.request;

import com.vroong.newbee.application.model.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {

  private OrderDto orderDto;

}

