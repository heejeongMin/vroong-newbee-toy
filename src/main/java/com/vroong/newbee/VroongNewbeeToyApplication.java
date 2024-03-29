package com.vroong.newbee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(scanBasePackages = "com.vroong.newbee")
public class VroongNewbeeToyApplication {

  public static void main(String[] args) {
    SpringApplication.run(VroongNewbeeToyApplication.class, args);//내장 was 실행
  }

}
