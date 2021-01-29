package com.vroong.newbee.application.model.order;

import com.vroong.newbee.application.model.BaseDto;
import com.vroong.newbee.application.model.partner.CustomerDto;
import com.vroong.newbee.domain.order.Order;
import com.vroong.newbee.domain.order.OrderStatus;
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
    return null;
  }
}
