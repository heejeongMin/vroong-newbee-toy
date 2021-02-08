package com.vroong.newbee.Java8;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class OnlineClass {

  private int id;
  private String title;
  private boolean closed;

  public OnlineClass(int id, String title, boolean closed) {
    this.id = id;
    this.title = title;
    this.closed = closed;
  }

}
