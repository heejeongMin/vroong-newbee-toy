package com.vroong.newbee.Java8;

//자바8부터 인터페이스 안에 static, default method 사용할 수 있다.
public interface Java8Interface {

  int test();

  //구현한 클래스에서 오버라이딩 할 수 없다.
  static String getStatic(){
    return "static";
  }

  default String getDefault(){
    return "default";
  }

}
