package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.*;
import com.prometheus.ionkid.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
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
  private DoctorService doctorService;
  @Autowired
  private KidService kidService;
  @Autowired
  private ParentService parentService;
  @Autowired
  private UserService userService;

  @GetMapping("/")
  public String main(Map<String, Object> model) {
    return "index";
  }

  @RequestMapping("/user")
  @ResponseBody
  public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
    userService.create(principal);
    return Collections.singletonMap("name", principal.getAttribute("name"));
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
    Iterable<Doctor> doctors = doctorService.getAll();
    model.put("doctors", doctors);

    Iterable<Parent> parents = parentService.getAll();
    model.put("parents", parents);

    Iterable<Kid> kids = kidService.getAll();
    model.put("kids", kids);

    return "userslist";
  }

}