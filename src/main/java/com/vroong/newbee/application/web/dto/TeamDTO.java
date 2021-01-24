package com.vroong.newbee.application.web.dto;

import com.vroong.newbee.application.domain.member.manytoone.Team;
import lombok.Getter;

@Getter
public class TeamDTO {
  private String id;
  private String name;

  public TeamDTO (Team team){
    this.id = team.getId();
    this.name = team.getName();
  }

}
