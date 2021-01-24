package com.vroong.newbee.application.domain.member.practice;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@DiscriminatorValue("B")
public class Book extends Items {

  private String author;
  private String isbn;

}
