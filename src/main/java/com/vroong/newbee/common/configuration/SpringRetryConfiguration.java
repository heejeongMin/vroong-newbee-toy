package com.vroong.newbee.common.configuration;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.RetryConfiguration;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableRetry
public class SpringRetryConfiguration {

  @Autowired
  public RetryConfiguration retryConfiguration;

  /*
    Set order of Aspects among @Async, @Retryable, @Transactional
    See https://github.com/spring-projects/spring-retry/issues/22
   */
  @PostConstruct
  public void postConstruct() {
    this.retryConfiguration.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
  }
}