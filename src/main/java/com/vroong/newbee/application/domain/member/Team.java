package com.vroong.newbee.application.domain.member;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.IntSequenceGenerator;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@NoArgsConstructor
@Entity
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TEAM_ID")
  private String id;

  @Setter
  private String name;

  @OneToMany(mappedBy = "team") //mappedBy는 양방향일때 사용. 객체그래프 탐색에 사용되는 필드의 이름을 사용. 속성에 targetEntity 어노테이션이 없으니, 제니릭 타입에 사용된 Member가 타입이됨.
  private List<Member> members = new ArrayList<Member>();

  @Builder
  public Team(String name) {
    this.name = name;
  }

}
