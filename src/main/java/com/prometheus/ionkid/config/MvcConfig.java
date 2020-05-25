package com.prometheus.ionkid.config;

import com.prometheus.ionkid.util.RedirectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/registration?", "login");
    registry.addRedirectViewController("/login?", "/");
    registry.addViewController("/registration").setViewName("registration");
    registry.addViewController("/login").setViewName("login");
    registry.addViewController("/").setViewName("index");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new RedirectInterceptor());
  }

  @Bean
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");
    registry.addResourceHandler("/static/image/**").addResourceLocations("classpath:/static/image/");
    registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
  }
}