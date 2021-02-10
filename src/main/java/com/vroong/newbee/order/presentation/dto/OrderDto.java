package com.vroong.newbee.order.presentation.dto;

import com.vroong.newbee.common.BaseDto;
import com.vroong.newbee.order.domain.Order;
import com.vroong.newbee.order.domain.OrderStatus;
import com.vroong.newbee.partner.presentation.dto.CustomerDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
public class OrderDto extends BaseDto<OrderDto, Order> {

  private Long id;
  private String orderNumber;
  private OrderStatusDto orderStatus;
  private CustomerDto customerDto;

  @Builder
  public OrderDto (Long id, String orderNumber, OrderStatusDto orderStatus, CustomerDto customerDto) {
    this.id = id;
    this.orderNumber = orderNumber;
    this.orderStatus = orderStatus;
    this.customerDto = customerDto;
  }


  @Override
  public Order toEntity() {
    return new Order().builder()
                      .id(this.id)
                      .orderNumber(this.orderNumber)
                      .orderStatus(OrderStatus.valueOf(this.orderStatus.name()))
                      .customer(this.customerDto.toEntity())
                      .build();
  }

  @Override
  public OrderDto toDto(@NonNull Order order) {
    this.id = order.getId();
    this.orderStatus = OrderStatusDto.valueOf(order.getOrderStatus().name());
    this.orderNumber = order.getOrderNumber();
    this.customerDto = new CustomerDto().toDto(order.getCustomer());
    return this;
  }
}
