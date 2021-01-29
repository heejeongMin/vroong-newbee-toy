package com.vroong.newbee.application.service.request;

import com.vroong.newbee.application.service.model.order.CustomerDto;
import com.vroong.newbee.application.service.model.order.OrderStatusDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReq {

  private OrderStatusDto orderStatus;

  private CustomerDto customerDto;




}

