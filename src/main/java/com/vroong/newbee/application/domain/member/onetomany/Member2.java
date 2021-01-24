package com.vroong.newbee.application.domain.member.onetomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

//일대다
@Getter
@NoArgsConstructor
@Entity(name = "MEMBER2")
public class Member2 {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER2_ID")
  private Long id;

  private String username;



}
