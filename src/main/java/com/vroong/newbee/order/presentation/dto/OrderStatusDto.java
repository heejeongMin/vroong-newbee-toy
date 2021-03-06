package com.vroong.newbee.order.presentation.dto;


public enum  OrderStatusDto {
  AWAITING("대기"),
  SUBMITTED("접수"),
  ASSIGNED("배정"),
  PICKED_UP("픽업"),
  DELIVERED("완료"),
  CANCELED("취소"),
  EMPTY("");

  private String text;

  OrderStatusDto(String text) {
    this.text = text;
  }

  public String getText(){
    return text;
  }

}
