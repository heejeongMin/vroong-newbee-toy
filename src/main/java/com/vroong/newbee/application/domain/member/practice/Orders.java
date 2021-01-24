package com.vroong.newbee.application.domain.member.practice;

import com.vroong.newbee.application.domain.member.manytoone.Member;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Orders {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "MEMBER_ID")
  private Members member;

  @OneToMany(mappedBy = "orders")
  private List<OrderItems> orderItems = new ArrayList<>();

  @OneToOne
  @JoinColumn(name = "DELIVERY_ID")
  private Delivery delivery;

  private Date orderDate;
  private OrderStatus orderStatus;

  //연관관계 메서드 ..

  //1.회원
  public void setMember(Members member){
    if(member != null) {
      this.member.getOrders().remove(this);
    }

    this.member = member;
    this.member.getOrders().add(this);
  }

  //2. 주문상품
  public void addOrderItems(OrderItems orderItem){
    this.orderItems.add(orderItem);
    orderItem.setOrders(this);
  }

  //3. 배송
  public void setDelivery(Delivery delivery){
    this.delivery = delivery;
    delivery.setOrders(this);
  }



}
