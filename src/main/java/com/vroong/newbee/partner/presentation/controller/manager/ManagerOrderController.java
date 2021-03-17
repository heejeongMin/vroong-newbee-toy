package com.vroong.newbee.partner.presentation.controller.manager;

import com.vroong.newbee.order.application.OrderService;
import com.vroong.newbee.order.application.TestService;
import com.vroong.newbee.order.domain.OrderEventPublisher;
import com.vroong.newbee.order.presentation.request.OrderReq;
import com.vroong.newbee.order.presentation.response.OrderRes;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ManagerOrderController {

  private final OrderService orderService;
  private final TestService testService;

  @Value("${payload.max.size}")
  private int payloadMaxSize=77777;


  @GetMapping("/")
  public String test(){
    System.out.println(this.getClass().toString());
    System.out.println(testService.getClass().toString()); //testService has Transactional --> cglib proxy를 반환

    testService.test();

    System.out.println("우선순위 테스트 : " + this.payloadMaxSize);

    if (false) {
      System.out.println("test");
    } else
      System.out.println("123");


    for (int i=0; i<=4; i++) {
      System.out.println(i);
    }




//    testService.test();

    return "test";
  }

  @PostMapping("/manager/order/v1/create")
  public ResponseEntity<OrderRes> createOrder(@RequestBody OrderReq req) {
    return new ResponseEntity<OrderRes>((OrderRes) orderService.createOrder(req), HttpStatus.OK);
  }



}
