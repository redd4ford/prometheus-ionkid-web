package com.prometheus.ionkid.config;

import com.prometheus.ionkid.business.UserService;
import com.prometheus.ionkid.dataaccess.UserDetailsRepository;
import com.prometheus.ionkid.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/", "/registration").permitAll()
        .anyRequest().authenticated()
        .and()
        .logout()
        .permitAll()
        .and()
        .formLogin().disable();
    http
        .httpBasic().disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userService)
        .passwordEncoder(NoOpPasswordEncoder.getInstance());


  }

  @Bean
  public PrincipalExtractor principalExtractor(@Autowired UserDetailsRepository userDetailsRepository) {
    return map -> {
      String id = (String) map.get("sub");
      User user = userDetailsRepository.findById(id).orElseGet(() -> {
        User newUser = new User();
        newUser.setId(id);
        newUser.setFirstName((String) map.get("given_name"));
        newUser.setLastName((String) map.get("family_name"));
        newUser.setEmail((String) map.get("email"));
        newUser.setGender((String) map.get("gender"));
        newUser.setLocale((String) map.get("locale"));
        newUser.setUserpic((String) map.get("picture"));

        return newUser;
      });

      user.setLastVisit(LocalDateTime.now());
      return userDetailsRepository.save(user);
    };
  }

}