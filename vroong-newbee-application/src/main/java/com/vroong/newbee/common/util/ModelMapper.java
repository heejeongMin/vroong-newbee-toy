package com.vroong.newbee.common.util;

public interface ModelMapper <T, S> {

  S map(T model, S dto);

}
