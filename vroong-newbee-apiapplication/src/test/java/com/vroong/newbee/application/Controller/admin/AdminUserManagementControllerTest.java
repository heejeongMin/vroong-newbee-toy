package com.vroong.newbee.application.Controller.admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vroong.newbee.application.model.partner.CustomerDto;
import com.vroong.newbee.application.model.request.CustomerReq;
import com.vroong.newbee.domain.partner.Customer;
import com.vroong.newbee.domain.partner.CustomerRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AdminUserManagementControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private MockMvc mvc;

  @Test
  public void setupCustomerData() throws Exception {

    CustomerReq customerReq = new CustomerReq(new CustomerDto().builder().customerName("판쵸상점").build());

    String url = "http://localhost:" + port + "/admin/customer/v1/create";

    mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(new ObjectMapper().writeValueAsString(customerReq)));



    List<Customer> all = customerRepository.findAll();
    assertThat(all.get(0).getCustomerName()).isEqualTo((customerReq.getCustomerDto().getCustomerName()));
  }

}