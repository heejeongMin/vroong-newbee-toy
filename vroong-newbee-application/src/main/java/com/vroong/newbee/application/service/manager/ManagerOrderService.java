package com.vroong.newbee.application.service.manager;

import com.vroong.newbee.application.model.order.OrderDto;
import com.vroong.newbee.application.model.request.OrderReq;

import com.vroong.newbee.application.model.response.OrderRes;
import com.vroong.newbee.application.service.OrderService;
import com.vroong.newbee.domain.order.Order;
import com.vroong.newbee.domain.order.OrderRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManagerOrderService implements OrderService<OrderReq, OrderRes> {

  private final OrderRepository orderRepository;

  @Transactional //안붙이면 dirtychecking이 안됨
  @Override
  public OrderRes createOrder(OrderReq orderReq) {

    Order test = orderReq.getOrderDto().toEntity();

    Order order = orderRepository.save(orderReq.getOrderDto().toEntity());
    order.setOrderNumber("20210126#111");//dirtychecking!!!

    return new OrderRes(new OrderDto().toDto(order));
  }

}
