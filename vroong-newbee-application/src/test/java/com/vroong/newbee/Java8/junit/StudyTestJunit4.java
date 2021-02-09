package com.vroong.newbee.Java8.junit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StudyTestJunit4 {

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {5, true},
        {22, false}
    });
  }

  @Parameter(0)
  public int limit;

  @Parameter(1)
  public boolean isWithinLimit;

  @Test
  public void isWithinLimit() {
    System.out.println("junit4 paramter test");
    assertThat(new Study(limit).isWithinLimit(), is(isWithinLimit));
  }

  @Test
  public void test(){
    System.out.println("test");
  }

}
