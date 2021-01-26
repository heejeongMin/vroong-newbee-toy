package com.vroong.newbee.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.vroong.newbee")
public class VroongNewbeeApplication {

  public static void main(String[] args) {
    SpringApplication.run(VroongNewbeeApplication.class, args);//내장 was 실행
    System.out.println("vroong-newbee-apiapplication");
  }

}
