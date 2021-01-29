package com.vroong.newbee.application.service;

public interface OrderService<T, S> {

  //오더 생성
  public S createOrder(T orderReq);


  //오더 배정

  //오더 배정 취소

  //오더 픽업

  //오더 픽업 취소

  //오더 캔슬


}
