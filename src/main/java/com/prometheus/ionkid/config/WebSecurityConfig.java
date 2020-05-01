package com.prometheus.ionkid.config;

import com.prometheus.ionkid.dataaccess.UserRepository;
import com.prometheus.ionkid.rest.model.Role;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests(a -> a
            .antMatchers("/", "/index", "/error", "/js/**", "/webjars/**").permitAll()
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
        .oauth2Login();
    http
        .httpBasic().disable();
  }

  @Bean
  public PrincipalExtractor principalExtractor(@Autowired UserRepository userRepository) {
    return map -> {
      String id = (String) map.get("id");
      User user = userRepository.findByGoogleId(id);
      if (user == null) {
        user = new User();
        user.setGoogleId(id);
        user.setFirstName((String) map.get("given_name"));
        user.setLastName((String) map.get("family_name"));
        user.setEmail((String) map.get("email"));
        user.setGender((String) map.get("gender"));
        user.setAvatarUrl((String) map.get("picture"));
        //find a better way to set roles so that we don't have to create a new role for each user.
        //maybe go for ENUM?
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ADMIN"));
        user.setRoles(roles);
      }
      user.setLastVisit(LocalDateTime.now());
      user.setActive(true);
      userRepository.save(user);
      return user;
    };
  }

}