package com.vroong.newbee.order.application;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


@Service
public class SelfInvocationTestService {

  @Lazy
  @Autowired
  private  SelfInvocationTestService self;

  private int n = 1;

  public void call(){
    this.subCall();
  }

//  @Retryable(value = RuntimeException.class, maxAttempts = 3, backoff = @Backoff(delay = 100))
//  @Async("threadPoolTaskExecutor")
  public void subCall(){
    System.out.println(Thread.currentThread().getName() + " subCall()");
    System.out.println(TransactionAspectSupport.currentTransactionStatus().toString());
    System.out.println(n);
    System.out.println(System.identityHashCode(self) + " " + self.getClass().toString());
    System.out.println(System.identityHashCode(this)  + " " + this.getClass().toString());
    self.test();
  }

  @Transactional
  public void test(){
    System.out.println(Thread.currentThread().getName() + " self test()");
    System.out.println(TransactionAspectSupport.currentTransactionStatus().toString());
    System.out.println(System.identityHashCode(self) + " " + self.getClass().toString());
    System.out.println(System.identityHashCode(this)  + " " + this.getClass().toString());
    System.out.println(123);
  }
}
