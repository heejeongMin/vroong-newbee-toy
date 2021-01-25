package com.vroong.newbee.application.domain.member.practice;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Members extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMEBER_ID")
  private Long id;

  private String name;

  //값타입을 전환
//  private String city;
//  private String street;
//  private String zipcode;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "member")
  private List<Orders> orders;



}