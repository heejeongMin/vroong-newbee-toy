package com.vroong.newbee.Java8.junit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Store;


public class FindSlowTestExtension implements BeforeTestExecutionCallback,
    AfterTestExecutionCallback {

//  private static final long THRESHOLD = 1000L;

  //선언적 방법말고, 프로그래밍적 방법으로 threshold를 동적으로 가지고 오고 싶을 경우
  private long THRESHOLD;

  public FindSlowTestExtension(long THRESHOLD) {
    this.THRESHOLD = THRESHOLD;
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    Store store = getStore(context);
    store.put("START_TIME", System.currentTimeMillis());

  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    Method method = context.getRequiredTestMethod();
    Annotation annotation = method.getAnnotation(SlowTest.class);

    Store store = getStore(context);
    long start_time = store.remove("START_TIME", long.class);
    long duration = System.currentTimeMillis() - start_time;
    if(duration > THRESHOLD && annotation == null){
      System.out.printf("Please consider mark method [%s] with @SlowTest. \n", method.getName());
    }
  }

  public Store getStore(ExtensionContext context) {
    String testClassName = context.getRequiredTestClass().getName();
    String testMethodName = context.getRequiredTestMethod().getName();
    return context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
  }
}
