package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.UserService;
import com.prometheus.ionkid.rest.model.Doctor;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AuthenticationController {
  @Autowired
  private UserService userService;

  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(@RequestParam String email, @RequestParam String password,
                        @RequestParam String firstName, @RequestParam String lastName,
                        @RequestParam String gender, @RequestParam String dateOfBirth,
                        @RequestParam String country, @RequestParam String city,
                        @RequestParam String organization, @RequestParam String specialty,
                        Map<String, Object> model) {
    User user = userService.loadUserByUsername(email);
    if (user != null) {
      if (userService.isOAuth2User(user)) {
        model.put("message", "Someone registered this account by OAuth2.");
      } else {
        model.put("message", "Doctor exists!");
      }
      return "registration";
    }
    user = new Doctor();
    user.setEmail(email);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setGender(gender);
    user.setDateOfBirth(dateOfBirth);
    user.setCountry(country);
    user.setCity(city);
    ((Doctor) user).setOrganization(organization);
    ((Doctor) user).setSpecialty(specialty);
    userService.createNotOAuth2User(user);

    return "redirect:/login";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }
}