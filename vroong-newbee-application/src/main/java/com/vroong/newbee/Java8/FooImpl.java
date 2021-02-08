package com.vroong.newbee.Java8;

public class FooImpl implements Foo{

  String name ;

  public FooImpl(){
  }

  public FooImpl(String name){
    this.name = name;
  }

  @Override
  public String printFoo() {
    return "testFoo";
  }

  @Override
  public String getName() {
    return this.name;
  }


}
