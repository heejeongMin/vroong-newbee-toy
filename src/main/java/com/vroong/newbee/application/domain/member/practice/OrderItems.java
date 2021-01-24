package com.vroong.newbee.application.domain.member.practice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class OrderItems extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ITEMS_ID")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ITEMS_ID")
  private Items items;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ORDERS_ID")
  private Orders orders;

  private int orderPrice;

  private int count;

  public void setOrders(Orders order){
    this.orders = order;
  }


}
