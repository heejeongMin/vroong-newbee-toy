package com.vroong.newbee.order.application;

import com.vroong.newbee.common.configuration.Test;
import com.vroong.newbee.order.domain.OrderEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@RequiredArgsConstructor
@Service
public class TestService {

  private final OrderEventPublisher publisher;

  private final SelfInvocationTestService selfInvocationTestService;

  @Transactional
  public void test() {
    System.out.println(Thread.currentThread().getName() + " test()");
    System.out.println(TransactionAspectSupport.currentTransactionStatus().toString());
//    publisher.publishCustomEvent("test?");
//    System.out.println("생성자 주입 " + System.identityHashCode(selfInvocationTestService) + " " + selfInvocationTestService.getClass().toString());
    selfInvocationTestService.call();

  }



}
