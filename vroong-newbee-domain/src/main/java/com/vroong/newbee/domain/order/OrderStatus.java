package com.vroong.newbee.domain.order;

public enum OrderStatus {
  AWAITING("대기"),
  SUBMITTED("접수"),
  ASSIGNED("배정"),
  PICKED_UP("픽업"),
  DELIVERED("완료"),
  CANCELED("취소"),
  EMPTY("");

  private String text;

  OrderStatus(String text) {
    this.text = text;
  }
}
