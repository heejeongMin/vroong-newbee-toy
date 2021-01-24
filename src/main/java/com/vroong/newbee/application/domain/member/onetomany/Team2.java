package com.vroong.newbee.application.domain.member.onetomany;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

//일대다 단반향 외래키를 관리하는 모습을 보기위
@Getter
@NoArgsConstructor
@Entity(name = "TEAM2")
public class Team2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TEAM2_ID")
  private Long id; //

  @OneToMany
  @JoinColumn(name = "TEAM_ID2") //멤버 엔티티에서 참조하는 외래키를 일대다에서는 일에서 관리한다.
  private List<Member2> members = new ArrayList();

}
