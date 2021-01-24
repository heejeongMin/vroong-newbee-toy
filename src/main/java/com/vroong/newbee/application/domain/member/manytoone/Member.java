package com.vroong.newbee.application.domain.member.manytoone;

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
@Entity(name ="MEMBER")
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

    if(this.team != null) {//기존에 지정된 팀이 있다면 기존 팀에서 해당 회원을 제외해 준다.
      this.getTeam().getMembers().remove(this);
    }

    this.team = team; //새팀을 정해준다.
    if(!team.getMembers().contains(this)) {// 그리고 그 정해진 새 팀에도 애 회원을 넣어주는데 없으면 넣어준다.
      team.getMembers().add(this);
    }
  }

}
