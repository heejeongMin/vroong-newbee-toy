package com.vroong.newbee.Java8;

public interface Bar extends Foo {

  //1. Bar를 구현하는 인스턴스가 Foo에 있는 printAnother를 가진다.
  //2. 하지만 구현하지 않게 하고 싶다면 추상메서드로 제정의하면된다. 대신 Bar구현하는 모든 인스턴스가 모두 직접 재정의를 해야한다.
//    String printAnother();

  //3. Diamond Problem : Foo에도 있고, Bar에도 있는데 구현체에서 둘다 구현하려고 하면 충돌으로 compile 에러가 나고, 충돌하는 default메서드는 override 해서 사용해야한다.
  default String printAnother(){
    return "Bar";
  }

}
