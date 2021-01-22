package com.vroong.newbee.application.domain.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String username;

  @ManyToOne //객체 연관관계
  @JoinColumn(name = "TEAM_ID") //테이블 연관관. Joincolumn은 생략은 가능하나, 생략하게 되면 기본전략으로 필드명_참조테이블컬럼명 으로 간다. = team_TEAM_ID
  private Team team;

  @Builder
  public Member(String username){
    this.username = username;
  }

  //양방향관계인경우, 양쪽에 모두 저장해 주는 메서드를 한곳에 구현.
  public void setTeam(Team team) {

    if(this.team != null) {
      this.getTeam().getMembers().remove(this); //기존 팀에 들어있던 member를 삭제해주어야함.
    }

    this.team = team;
    team.getMembers().add(this);
  }

}
