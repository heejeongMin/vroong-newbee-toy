package com.vroong.newbee.application.web.dto;

import com.vroong.newbee.application.domain.member.Member;
import com.vroong.newbee.application.domain.member.Team;
import lombok.Getter;


@Getter
public class MemberDTO {

  private String id;
  private String username;
  private TeamDTO team;

  public MemberDTO(Member entity) {
    this.id = entity.getId();
    this.username = entity.getUsername();
    this.team = new TeamDTO(entity.getTeam());
  }

}
