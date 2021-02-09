package com.vroong.newbee.application.service.admin;

import com.vroong.newbee.application.model.partner.CustomerDto;
import com.vroong.newbee.application.model.request.CustomerReq;
import com.vroong.newbee.application.model.response.CustomerRes;
import com.vroong.newbee.domain.partner.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminUserManagementService {

  private final CustomerRepository customerRepository;

  public CustomerRes createCustomer(CustomerReq customerReq) {
    return new CustomerRes(new CustomerDto().toDto(customerRepository.save(customerReq.getCustomerDto().toEntity())));
  }



}
