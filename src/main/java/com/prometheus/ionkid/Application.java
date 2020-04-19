package com.prometheus.ionkid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.prometheus.ionkid.dataaccess",
    "com.prometheus.ionkid.business",
    "com.prometheus.ionkid.controller"
})
@EnableJpaRepositories({"com.prometheus.ionkid.dataaccess"})
public class Application {
  public static void main(String[] args) throws Throwable {
    SpringApplication.run(Application.class, args);
  }

}