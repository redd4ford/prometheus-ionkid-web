package com.prometheus.ionkid.config;

import com.prometheus.ionkid.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@EnableOAuth2Client
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  public UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/");
    http
        .authorizeRequests(a -> a
            .antMatchers("/", "/registration**", "/login**", "/error", "/js/**", "/webjars/**").permitAll()
            .anyRequest().authenticated()
        )
        .logout(l -> l
            .logoutSuccessUrl("/").permitAll()
        )
        .exceptionHandling(e -> e
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        )
        .csrf(c -> c
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        )
        .oauth2Login(o -> o
            .authorizationEndpoint()
            .baseUri("/oauth2/authorization")
            .authorizationRequestRepository(authorizationRequestRepository())
        )
        .formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/", true)
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and()
        .httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService)
        .passwordEncoder(NoOpPasswordEncoder.getInstance());
  }

  @Bean
  public AuthorizationRequestRepository<OAuth2AuthorizationRequest>
  authorizationRequestRepository() {
    return new HttpSessionOAuth2AuthorizationRequestRepository();
  }

  @Bean
  public RequestCacheOAuth2ClientContextFilter requestCacheOAuth2ClientContextFilter() {
    return new RequestCacheOAuth2ClientContextFilter();
  }

  @Bean
  public FilterRegistrationBean oauth2ClientFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(requestCacheOAuth2ClientContextFilter());
    registration.setOrder(-100);
    return registration;
  }

}