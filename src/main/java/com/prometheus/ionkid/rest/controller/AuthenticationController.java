package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.UserService;
import com.prometheus.ionkid.rest.dto.CaptchaResponseDto;
import com.prometheus.ionkid.rest.model.Doctor;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class AuthenticationController {

  private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Value("${recaptcha.secret}")
  private String secret;

  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(@RequestParam String email, @RequestParam String password,
                        @RequestParam String firstName, @RequestParam String lastName,
                        @RequestParam String gender, @RequestParam String dateOfBirth,
                        @RequestParam String country, @RequestParam String city,
                        @RequestParam String phoneNumber, @RequestParam String certificationId,
                        @RequestParam String organization, @RequestParam String specialty,
                        @RequestParam("g-recaptcha-response") String captchaResponse,
                        Map<String, Object> model) {
    String url = String.format(CAPTCHA_URL, secret, captchaResponse);
    CaptchaResponseDto response = restTemplate.postForObject(url, null, CaptchaResponseDto.class);
    if (!response.isSuccess()) {
      model.put("message", "Captcha is not filled.");
      return "registration";
    }
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
    user.setPassword(passwordEncoder.encode(password));
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setGender(gender);
    user.setDateOfBirth(dateOfBirth);
    user.setCountry(country);
    user.setCity(city);
    user.setPhoneNumber(phoneNumber);
    ((Doctor) user).setOrganization(organization);
    ((Doctor) user).setSpecialty(specialty);
    ((Doctor) user).setCertificationId(certificationId);
    userService.createNotOAuth2User(user);

    return "redirect:/login";
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

}