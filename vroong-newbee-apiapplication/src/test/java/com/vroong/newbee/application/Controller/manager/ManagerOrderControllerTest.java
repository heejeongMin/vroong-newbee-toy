package com.vroong.newbee.application.Controller.manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vroong.newbee.application.model.partner.CustomerDto;
import com.vroong.newbee.application.model.order.OrderDto;
import com.vroong.newbee.application.model.order.OrderStatusDto;
import com.vroong.newbee.application.model.request.OrderReq;
import com.vroong.newbee.domain.order.Order;
import com.vroong.newbee.domain.order.OrderRepository;
import com.vroong.newbee.domain.partner.Customer;
import com.vroong.newbee.domain.partner.CustomerRepository;
import java.util.List;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ManagerOrderControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private MockMvc mvc;


  @BeforeEach
  public void setupCustomerData() throws Exception {
      customerRepository.saveAndFlush(new Customer().builder().customerName("판쵸상점").build());
  }

  @DisplayName("오더 생성하기")
  @Test
  public void createOrder() throws Exception {
    //given
    OrderReq orderReq = new OrderReq(new OrderDto().builder()
                                      .orderStatus(OrderStatusDto.SUBMITTED)
                                      .customerDto(new CustomerDto().builder().id(1l).build())
                                      .build());

    String url = "http://localhost:" + port + "/manager/order/v1/create";

    //when
    mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                         .content(new ObjectMapper().writeValueAsString(orderReq))
    );

    //then
    List<Order> all = orderRepository.findAll();

    assertThat(all.get(0).getOrderStatus().name()).isEqualTo((orderReq.getOrderDto().getOrderStatus().name()));
    assertThat(all.get(0).getOrderNumber()).isNotNull(); //transactional 이 없었으면 dirtychecking이 되지 않아서 여기서 에러가 난다. response로 또 보낼때는 이 값이 있는데 그 사이 flush가 되는 것 같다.
    assertThat(all.get(0).getCustomer().getId()).isEqualTo((orderReq.getOrderDto().getCustomerDto().getId()));
  }



}
