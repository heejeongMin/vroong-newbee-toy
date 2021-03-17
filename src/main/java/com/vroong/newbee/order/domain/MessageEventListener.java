package com.vroong.newbee.order.domain;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MessageEventListener {


  @EventListener
  public void handleOrderEventChain(MessageEvent event){
    System.out.println(Thread.currentThread().getName() + " Order Chain possible ? message: " + event.getMessage());
  }

}
