package com.vroong.newbee.Java8;

public interface Foo {

  String printFoo();

  /**
   * 제대로 구현하였는지 모르니 에러를 유발할 수 있음으로 상세하게 내용을 작성
   * @implSpec 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력
   */
  default String printAnother(){
    return getName().toUpperCase();
  }

  String getName();


}
