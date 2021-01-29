package com.vroong.newbee.application.service.model.order;

import com.vroong.newbee.domain.order.Order;
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
                      .customer(this.customerDto.toEntity())
                      .build();
  }

  @Override
  public OrderDto toDto(@NonNull Order order) {
    return null;
  }
}
