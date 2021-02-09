package com.vroong.newbee.Java8.junit;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME) //실행시점에도 정보를 참조해야하기 때문에
public @interface SlowTest {

}
