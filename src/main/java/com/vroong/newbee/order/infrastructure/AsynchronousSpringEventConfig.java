package com.vroong.newbee.order.infrastructure;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsynchronousSpringEventConfig {

//  @Bean(name = "applicationEventMulticaster")
//  public ApplicationEventMulticaster simpleApplicationEventMulticaster(){
//    SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
//    eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
//    return eventMulticaster;
//  }


  @Bean(name = "threadPoolTaskExecutor", destroyMethod = "destroy")
  public Executor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(3);
    taskExecutor.setMaxPoolSize(30);
    taskExecutor.setQueueCapacity(10);
    taskExecutor.setThreadNamePrefix("Executor-");
    taskExecutor.initialize();
    return new HandlingExecutor(taskExecutor); // HandlingExecutor로 wrapping 합니다.
  }

  public class HandlingExecutor implements AsyncTaskExecutor {
    private AsyncTaskExecutor executor;

    public HandlingExecutor(AsyncTaskExecutor executor) {
      this.executor = executor;
    }

    @Override
    public void execute(Runnable task) {
      executor.execute(createWrappedRunnable(task));
    }

    @Override
    public void execute(Runnable task, long startTimeout) {
      executor.execute(createWrappedRunnable(task), startTimeout);
    }

    @Override
    public Future<?> submit(Runnable task) {
      return executor.submit(createWrappedRunnable(task));
    }

    @Override
    public <T> Future<T> submit(final Callable<T> task) {
      return executor.submit(createCallable(task));
    }

    private <T> Callable<T> createCallable(final Callable<T> task) {
      return new Callable<T>() {
        @Override
        public T call() throws Exception {
          try {
            return task.call();
          } catch (Exception ex) {
            handle(ex);
            throw ex;
          }
        }
      };
    }

    private Runnable createWrappedRunnable(final Runnable task) {
      return new Runnable() {
        @Override
        public void run() {
          try {
            task.run();
          } catch (Exception ex) {
            handle(ex);
          }
        }
      };
    }

    private void handle(Exception ex) {

    }

    public void destroy() {
      if(executor instanceof ThreadPoolTaskExecutor){
        ((ThreadPoolTaskExecutor) executor).shutdown();
      }
    }
  }

}
