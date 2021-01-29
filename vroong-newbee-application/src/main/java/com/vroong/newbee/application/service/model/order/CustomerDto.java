package com.vroong.newbee.application.service.model.order;

import com.vroong.newbee.domain.partner.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CustomerDto {

  private Long id;
  private String customerName;

  public CustomerDto setCustomerName(String customerName) {
    this.customerName = customerName;
    return this;
  }

  public Customer toEntity(){
    return new Customer().builder().id(this.id).customerName(this.customerName).build();
  }

  @Builder
  public CustomerDto(Long id, String customerName) {
    this.id = id;
    this. customerName = customerName;
  }

}
