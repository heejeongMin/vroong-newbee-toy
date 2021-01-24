package com.vroong.newbee.application.service;

import com.vroong.newbee.application.domain.member.onetomany.Member2;
import com.vroong.newbee.application.domain.member.onetomany.Member2Repository;
import com.vroong.newbee.application.domain.member.onetomany.Team2;
import com.vroong.newbee.application.domain.member.onetomany.Team2Repository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeamService {

  private final Team2Repository team2Repository;
  private final Member2Repository member2Repository;


  @Transactional
  public void oneToMany(){
    Member2 member = new Member2();
    Member2 member2 = new Member2();

    Team2 team = new Team2();
    team.getMembers().add(member);
    team.getMembers().add(member2);
    team.getMembers().add(member2);

    team2Repository.save(team);
    member2Repository.save(member);
    member2Repository.save(member2);

  }




}

