package com.vroong.newbee.application.domain.member.practice;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("M")
public class Movie extends Items{

  private String director;
  private String actor;

}
