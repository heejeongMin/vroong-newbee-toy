package com.vroong.newbee.application.model.partner;

import com.vroong.newbee.domain.partner.Customer;
import com.vroong.newbee.application.model.BaseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CustomerDto extends BaseDto<CustomerDto, Customer> {

  private Long id;
  private String customerName;

  public CustomerDto setCustomerName(String customerName) {
    this.customerName = customerName;
    return this;
  }

  @Builder
  public CustomerDto(Long id, String customerName) {
    this.id = id;
    this. customerName = customerName;
  }

  @Override
  public Customer toEntity(){
    return new Customer().builder().id(this.id).customerName(this.customerName).build();
  }

  @Override
  public CustomerDto toDto(Customer customer) {
    this.id = customer.getId();
    this.customerName = customer.getCustomerName();
    return this;
  }

}
