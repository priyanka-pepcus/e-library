package com.pepcus.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootCrud {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootCrud.class, args);
  }

}
