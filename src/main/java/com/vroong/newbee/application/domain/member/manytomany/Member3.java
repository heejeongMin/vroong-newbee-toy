package com.vroong.newbee.application.domain.member.manytomany;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member3 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER3_ID")
  private Long id;

  private String username;


  /*
  manytomany와 jointable을 같이 사용하면 연결테이블을 엔티티없이 바로 매팽할수 있따.
  댜대다 관계를 일대다, 다대일 관계로 풀기위한 연결테이블임.
  jointable 의 속성
  name : 연결테이블지정
  joinColumns : 현재방향의 회원과 매핑할 조인 컬럼 정보
  inverseJoinColumns : 반대 방향인 상품과 매핑할 조인 컬럼정
   */
  @ManyToMany
  @JoinTable(
      name = "MEMBER3_PRODUCT",
      joinColumns = @JoinColumn(name = "MEMBER3_ID"),
      inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
  )
  private List<Product> products = new ArrayList<>();

  @Builder
  public Member3 (String username, Product products) {
    this.username = username;
    if(products != null) this.products.add(products);
  }

  public void addProducts(Product product){
    if(!this.products.contains(product)) this.products.add(product);
    if(!product.getMember3List().contains(this)) product.getMember3List().add(this);

  }

}
