package com.vroong.newbee.application.domain.member.practice;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("A")
public class Album extends Items {

  private String artist;
  private String etc;

}
