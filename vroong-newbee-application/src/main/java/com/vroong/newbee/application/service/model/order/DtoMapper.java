package com.vroong.newbee.application.service.model.order;

import com.vroong.newbee.application.service.request.OrderReq;
import com.vroong.newbee.domain.order.Order;
import com.vroong.newbee.domain.order.OrderStatus;
import com.vroong.newbee.domain.partner.Customer;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

  public Order mapToOrder(OrderReq req) {
      return new Order().builder()
                        .orderStatus(OrderStatus.valueOf(req.getOrderStatus().name()))
                        .customer(req.getCustomerDto().toEntity())
                        .build();
  }

  public OrderDto mapToOrderDto(Order order) {
    return new OrderDto().builder()
                         .id(order.getId())
                         .orderNumber(order.getOrderNumber())
                         .orderStatus(OrderStatusDto.valueOf(order.getOrderStatus().name()))
                         .build();

  }

  public CustomerDto mapToCustomerDto(Customer customer) {
    return new CustomerDto().builder()
                         .id(customer.getId())
                         .customerName(customer.getCustomerName())
                         .build();
  }



}
