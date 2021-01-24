package com.vroong.newbee.application.domain.member.manytomany.customMidTable;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.Builder;
import lombok.NoArgsConstructor;


/*
상품에서 연결테이블로 연결이 되지 않은 이유는 이 방향으로 그래프 탐색이 필요없다고 생각하기 때문에, 단방향으로 생성
필요하면 연관관계를 만들어주면됨.
 */
@Entity
@NoArgsConstructor
public class Product2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PRODUCT_ID")
  private Long id;

  private String name;

  @Builder
  public Product2 (String name) {
    this.name = name;
  }

}
