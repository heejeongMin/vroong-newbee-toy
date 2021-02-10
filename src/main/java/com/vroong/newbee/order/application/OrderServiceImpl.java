package com.vroong.newbee.order.application;

import com.vroong.newbee.order.domain.Order;
import com.vroong.newbee.order.domain.OrderRepository;
import com.vroong.newbee.order.presentation.dto.OrderDto;
import com.vroong.newbee.order.presentation.request.OrderReq;
import com.vroong.newbee.order.presentation.response.OrderRes;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service(value = "managerOrderService")
public class OrderServiceImpl implements OrderService<OrderReq, OrderRes> {

  private final OrderRepository orderRepository;

  @Transactional //안붙이면 dirtychecking이 안됨
  @Override
  public OrderRes createOrder(OrderReq orderReq) {

    Order order = orderRepository.save(orderReq.getOrderDto().toEntity());
    order.setOrderNumber(LocalDateTime.now().toString());//dirtychecking!!!

    return new OrderRes(new OrderDto().toDto(order));
  }

  @Override
  public OrderRes assignOrder(OrderReq orderReq) {
    return null;
  }


}
