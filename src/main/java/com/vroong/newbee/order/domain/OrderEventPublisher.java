package com.vroong.newbee.order.domain;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;


  public void publishCustomEvent(final String message) {
    System.out.println(Thread.currentThread().getName() + " Publishing my order event");
    OrderEvent orderEvent = new OrderEvent(this, message);
    applicationEventPublisher.publishEvent(orderEvent);
  }

}
