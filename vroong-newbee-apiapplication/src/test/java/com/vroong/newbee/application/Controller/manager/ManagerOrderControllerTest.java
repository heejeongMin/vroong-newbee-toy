package com.vroong.newbee.application.Controller.manager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vroong.newbee.application.service.model.order.CustomerDto;
import com.vroong.newbee.application.service.model.order.OrderStatusDto;
import com.vroong.newbee.application.service.request.OrderReq;
import com.vroong.newbee.domain.order.Order;
import com.vroong.newbee.domain.order.OrderRepository;
import com.vroong.newbee.domain.partner.Customer;
import com.vroong.newbee.domain.partner.CustomerRepository;
import java.util.List;
import org.junit.*;
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

@RunWith(SpringRunner.class)
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


  @Before
  public void setupCustomerData(){
    System.out.println(123);
    Customer customer = customerRepository.saveAndFlush(new Customer().builder().customerName("판쵸상점").build());
    System.out.println(customer.getId());
  }

  @Test
  public void test2() throws Exception {
    System.out.println("test");
    //given
    OrderReq orderReq = new OrderReq(OrderStatusDto.ASSIGNED, new CustomerDto().builder().id(1l).build());

    String url = "http://localhost:" + port + "/manager/order/v1/create";

    //when
    mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE)
                         .content(new ObjectMapper().writeValueAsString(orderReq))
    );

    //then
    List<Order> all = orderRepository.findAll();

    assertThat(all.get(0).getOrderStatus().name()).isEqualTo(orderReq.getOrderStatus().name());
    assertThat(all.get(0).getOrderNumber()).isNotNull(); //transactional 이 없었으면 dirtychecking이 되지 않아서 여기서 에러가 난다. response로 또 보낼때는 이 값이 있는데 그 사이 flush가 되는 것 같다.
    assertThat(all.get(0).getCustomer().getId()).isEqualTo(orderReq.getCustomerDto().getId());
  }



}
