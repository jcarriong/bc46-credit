package com.nttdata.bc46credit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Bc46CreditApplication {

  public static void main(String[] args) {
    SpringApplication.run(Bc46CreditApplication.class, args);
  }

}
