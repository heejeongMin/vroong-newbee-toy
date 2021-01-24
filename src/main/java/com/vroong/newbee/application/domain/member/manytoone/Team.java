package com.vroong.newbee.application.domain.member.manytoone;

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
@Entity(name ="TEAM")
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


  //멤버쪽과 팀쪽 둘다 추가하는 코드가 있다. 편의를 위해 추가할수 있으나, 무한루프에 빠지지 않게 조심해야한다. 나는 한쪽에만 두기로 함.
//  public void addMember(Member member){
//    if(!members.contains(member)) { //기존에 있는 멤버가 아니면 넣어주고,
//      members.add(member);
//    }
//
//    if(member.getTeam() != this) { //지금 이 새팀이 아니면, 그 아닌 team에서도 제거하는 코드가 필요.
//      member.getTeam().getMembers().remove(member);
//      member.setTeam(this);
//
//    }
//  }

}
