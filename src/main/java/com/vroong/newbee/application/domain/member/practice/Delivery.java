package com.vroong.newbee.application.domain.member.practice;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Delivery extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DELIVERY_ID")
  private Long id;

  @OneToOne(mappedBy = "delivery")
  private Orders orders;


  //값타입으로 전환
//  private String city;
//  private String street;
//  private String zipcode;

  @Embedded
  private Address address;

  @Enumerated(value = EnumType.STRING)
  private DeliveryStatus status;

  public void setOrders(Orders orders) {
    this.setOrders(orders);
  }
}
