package com.vroong.newbee.order.domain;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class MessageEvent extends ApplicationEvent {

  private String message;

  /**
   * Create a new {@code ApplicationEvent}.
   *
   * @param source the object on which the event initially occurred or with which the event is
   *               associated (never {@code null})
   */
  public MessageEvent(Object source) {
    super(source);
  }

  public MessageEvent (Object source, String message) {
    super(source);
    this.message = message;
  }
}
