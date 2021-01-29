package com.vroong.newbee.application.service.manager;

import com.vroong.newbee.application.service.OrderService;
import com.vroong.newbee.application.service.model.order.DtoMapper;
import com.vroong.newbee.application.service.request.OrderReq;
import com.vroong.newbee.application.service.response.OrderRes;
import com.vroong.newbee.domain.order.Order;
import com.vroong.newbee.domain.order.OrderRepository;
import com.vroong.newbee.domain.partner.Customer;
import com.vroong.newbee.domain.partner.CustomerRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManagerOrderService implements OrderService<OrderReq, OrderRes> {

  private final OrderRepository orderRepository;
  private final DtoMapper dtoMapper;

  @Transactional //안붙이면 dirtychecking이 안됨
  @Override
  public OrderRes createOrder(OrderReq orderReq) {

    Order order = orderRepository.save(dtoMapper.mapToOrder(orderReq));
    order.setOrderNumber("20210126#111");//dirtychecking!!!

    return new OrderRes(dtoMapper.mapToOrderDto(order));
  }
}
