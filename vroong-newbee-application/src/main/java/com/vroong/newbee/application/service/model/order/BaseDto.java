package com.vroong.newbee.application.service.model.order;

public abstract class BaseDto<T, S> {

  public abstract S toEntity();
  public abstract T toDto(S entity);

}
