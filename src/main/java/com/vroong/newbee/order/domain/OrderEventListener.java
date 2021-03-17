package com.vroong.newbee.order.domain;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


@Component
public class OrderEventListener {

  @EventListener
  public MessageEvent handleOrderEvent(OrderEvent event) {
    System.out.println(Thread.currentThread().getName() + " Received my orderevent - " + event.getMessage());
    System.out.println(TransactionAspectSupport.currentTransactionStatus().toString());
    return new MessageEvent(event.getSource(), "event chain test");

  }


//  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT, fallbackExecution = true)
//  public void handleOrderEventBeforeCommit(OrderEvent event) {
//    System.out.println("Received my orderevent before commit- " + event.getMessage());
//    System.out.println(Thread.currentThread().getName());
//  }


  @Transactional(propagation = Propagation.REQUIRES_NEW)
  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void handleOrderEventBeforeCommit(OrderEvent event) {
    System.out.println(Thread.currentThread().getName() + " Received my orderevent before commit- " + event.getMessage());
    System.out.println(TransactionAspectSupport.currentTransactionStatus().toString());
  }


}




