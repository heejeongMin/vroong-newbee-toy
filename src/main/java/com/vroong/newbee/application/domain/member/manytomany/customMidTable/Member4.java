package com.vroong.newbee.application.domain.member.manytomany.customMidTable;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.NoArgsConstructor;

/*
ManyToMany의 자동생성되는 연결테이블의 한계는 그 연결테이블의 컬럼을 추가할 수 없다는것.
그래서 직접 만들어야 하는데 이경우 ManyToMany를 사용할 수 없고,
일대다, 다대일 관계로풀어야한다.
 */
@Entity
@NoArgsConstructor
public class Member4 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long id;

  private String username;

  //연결테이블이 1 이고, member가 다로 연관관계의 주인은 연결테이블
  @OneToMany(mappedBy = "member")
  private List<MemberProduct> memberProducts = new ArrayList<>();

  @Builder
  public Member4 (String username) {
    this.username = username;
  }

}
