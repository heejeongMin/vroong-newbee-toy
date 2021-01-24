package com.vroong.newbee.application.domain.member.manytomany.midTableNewId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class MyOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ID")
  private Long order_id;

  @ManyToOne
  @JoinColumn(name = "MEMBER_ID")
  private Member5 member;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private Product3 product;

  private int orderAmount;

  @Builder
  public MyOrder(Member5 member, Product3 product, int orderAmount){
    this.member = member;
    this.product = product;
    this.orderAmount = orderAmount;
  }
}
