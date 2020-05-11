package com.prometheus.ionkid.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.prometheus.ionkid.*"})
@EnableJpaRepositories({"com.prometheus.ionkid.dataaccess"})
@EntityScan(
    basePackageClasses = {Application.class, Jsr310JpaConverters.class}
)
public class Application {
  public static void main(String[] args) throws Throwable {
    SpringApplication.run(Application.class, args);
  }

}