package com.vroong.newbee.application.domain.member.manytomany.customMidTable;

import com.vroong.newbee.application.domain.member.manytomany.Product;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.NoArgsConstructor;

/*
@IdClass는 //복합기본키를 제공하기 위해 사용. @EmbeddedId를 사용할 수도 있음.
JPA에서 복합 키를 사용하려면 별도의 식별자 클래스를 만들어야하고, 식별관계에 있다고 함.
식별관계 (Identifying Relationship)
- 회원과 상품 (부모 테이블)의 기본키를 받아 자신의 기본키 + 외래키로 사용하는 것을 일컫음.
 */
@NoArgsConstructor
@Entity
@IdClass(MemberProductId.class)
public class MemberProduct {

  @Id
  @ManyToOne
  @JoinColumn(name="MEMBER_ID")
  private Member4 member; //MemberProductId.member와 연결

  @Id
  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private Product2 product;//MemberProductId.product와 연결

  private int orderAmount;

  @Builder
  public MemberProduct(Member4 member, Product2 product, int orderAmount){
    this.member = member;
    this.product = product;
    this.orderAmount = orderAmount;
  }

}
