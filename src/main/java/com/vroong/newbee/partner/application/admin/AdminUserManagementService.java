package com.vroong.newbee.partner.application.admin;

import com.vroong.newbee.partner.domain.CustomerRepository;
import com.vroong.newbee.partner.presentation.dto.CustomerDto;
import com.vroong.newbee.partner.presentation.request.CustomerReq;
import com.vroong.newbee.partner.presentation.response.CustomerRes;
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
