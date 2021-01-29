package com.vroong.newbee.application.Controller.manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vroong.newbee.application.service.OrderService;
import com.vroong.newbee.application.service.request.OrderReq;
import com.vroong.newbee.application.service.response.OrderRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ManagerOrderController {

  private final OrderService orderService;

  @GetMapping("/")
  public String test(){
    return "test";
  }

  @PostMapping("/manager/order/v1/create")
  public ResponseEntity<OrderRes> createOrder(@RequestBody OrderReq req)
      throws JsonProcessingException {
    return new ResponseEntity<OrderRes>((OrderRes) orderService.createOrder(req), HttpStatus.OK);
  }



}
