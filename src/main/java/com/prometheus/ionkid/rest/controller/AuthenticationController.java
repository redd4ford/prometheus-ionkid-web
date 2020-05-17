package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.UserService;
import com.prometheus.ionkid.rest.model.Doctor;
import com.prometheus.ionkid.rest.model.Role;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

import static java.net.URLEncoder.encode;

@Controller
public class AuthenticationController {
  @Autowired
  private UserService userService;

  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(User user, Map<String, Object> model) throws UnsupportedEncodingException {
    User userFromDb = userService.loadByEmail(user.getEmail());
    if (userFromDb != null) {
      model.put("message", "Doctor exists!");
      return "registration";
    }

    user = new Doctor(user.getEmail(), user.getPassword(), false);
    user.setRoles(Collections.singleton(Role.DOCTOR));
    userService.createNotOAuth2User(user);

    return encode("login", StandardCharsets.UTF_8);
  }

  @RequestMapping("/login")
  public String login() throws NullPointerException {

//    return encode("/", StandardCharsets.UTF_8);
    return "login";
  }

  @PutMapping("/login/success")
  public String loginSuccess(@AuthenticationPrincipal User user, Map<String, Object> model) {
    User userFromDb = userService.loadByEmail(user.getEmail());
    if (userFromDb == null) {
      model.put("message", "There is no such user!");
      login();
    } else {
      userFromDb.setActive(true);
      userFromDb.setLastVisit(LocalDateTime.now());
      userService.update(userFromDb.getId(), userFromDb);
    }
    return "redirect:/";
  }
}