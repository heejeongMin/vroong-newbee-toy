package com.vroong.newbee.domain.order;

import com.vroong.newbee.domain.partner.Customer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "orders_id")
  private Long id;

  @Column(name = "orders_number", unique = true)
  private String orderNumber;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Builder
  public Order (Long id, String orderNumber, OrderStatus orderStatus, Customer customer){
    this.id = id;
    this.orderNumber = orderNumber;
    this.orderStatus = orderStatus;
    this.customer = customer;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }





}
