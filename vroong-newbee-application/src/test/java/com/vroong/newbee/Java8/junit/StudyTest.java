package com.vroong.newbee.Java8.junit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.RegisterExtension;

//확장모델
//junit4 : @Runwith(
//junit5 : @Extension

//테스트 인스턴스를 클래스로 가지고 가고,  순서를 가지고 가면 정한 순서대로 value값이 올라가는걸 볼수 있다.
//@TestInstance(Lifecycle.PER_CLASS)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class) //properties에서 정할 수 있음. 그래도 @DisplayName이 붙어있으면, 그게 더 우선수위를 가진다.
//@ExtendWith(FindSlowTestExtension.class) // 확장 사용방법 1. 선언 방법
@TestMethodOrder(OrderAnnotation.class) //Alphanumeric, Random, OrderAnnotation
class StudyTest {

  int value = 1;

  //확장 사용방법 2. 프로그래밍적 방법 (static 으로 사용해야 한다.), 확장 3번 사용 방법은 junit-platform.properties에 설정하는 방법인데 흔히 사용하지는 않는다고 한다.
  @RegisterExtension
  static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);


  //두개의 테스트가 각각 다른 인스턴스인것을 해시값을 보면 알수 있다.
  //따라서 value값이 계속 1
  //junit5에서는 테스트릉 항상 생성하지 않는 방법이 있다. @TestInstance(Lifecycle.PER_CLASS)
  //테스트 클래스 마다 설정해 줄 수도 있고, 글로벌하게 사용하고 싶다면 test아래 resource 파일 junit-platofrm.propertiesdp
  //junit.jupiter.testinstance.lifecyle.default=per_class
  //를 사용할 수 있다. 대신 이때 beforeall, afterall은 다시 static으로 사용하여야 함.


  @Disabled
  @Order(2)
  @Test
  @DisplayName("가독성을 높일 수 있습니다.")
  void new_Study(){
    Study study = new Study(1);
    assertThat(study.getLimit()).isGreaterThan(0);
    System.out.println(this);
    System.out.println(value++);
    //com.vroong.newbee.Java8.junit.StudyTest@5d740a0f
    //1
  }

  @Order(1)
  @Test
  @DisplayName("DisplayName을 사용하여")
  void newStudy2() throws InterruptedException {
    Thread.sleep(1005L);
    System.out.println(this);
    System.out.println("test1 " + value++);
    //com.vroong.newbee.Java8.junit.StudyTest@43d7741f
    //test1 1
  }


  //테스트 인스턴스를 하나만 만들면 beforeall, after all이 스태틱일 필요가 없다.
  @BeforeAll
  static void beforeAll(){
    System.out.println("before all");
  }

  @AfterAll
  static void afterAll(){
    System.out.println("after all");
  }



}