package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.ProgramService;
import com.prometheus.ionkid.business.TaskService;
import com.prometheus.ionkid.business.UserService;
import com.prometheus.ionkid.rest.model.Program;
import com.prometheus.ionkid.rest.model.Task;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Map;

@Controller
public class WebsiteController {
  @Autowired
  private ProgramService programService;
  @Autowired
  private TaskService taskService;
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public String main() {
    return "index";
  }

  @RequestMapping("/user")
  @ResponseBody
  public Map<String, Object> user(@AuthenticationPrincipal Object user) {
    if (user instanceof OAuth2User) {
      userService.createOAuth2User((OAuth2User) user);
      return Collections.singletonMap("name", ((OAuth2User) user).getAttribute("name"));
    } else if (user instanceof User) {
      userService.updateLastVisit((User) user);
      return Collections.singletonMap("name", (((User) user).getFirstName() + " " + ((User) user).getLastName()));
    } else {
      return null;
    }
  }

  @GetMapping("programslist")
  public String programslist(Map<String, Object> model) {
    Iterable<Program> programs = programService.getAll();
    model.put("programs", programs);

    Iterable<Task> tasks = taskService.getAll();
    model.put("tasks", tasks);

    return "programslist";
  }

  @GetMapping("userslist")
  public String userslist(Map<String, Object> model) {
    Iterable<User> users = userService.getAll();
    model.put("users", users);

    return "userslist";
  }

}