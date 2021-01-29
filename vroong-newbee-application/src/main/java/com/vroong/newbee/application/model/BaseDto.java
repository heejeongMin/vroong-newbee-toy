package com.vroong.newbee.application.model;

public abstract class BaseDto<T, S> {

  public abstract S toEntity();
  public abstract T toDto(S entity);

}
