package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.*;
import com.prometheus.ionkid.rest.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

  @GetMapping("/")
  public String main(Map<String, Object> model) {
    return "index";
  }

  @GetMapping("user")
  public Map<String, Object> user(@AuthenticationPrincipal User principal) {
    return Collections.singletonMap("name", principal.getFirstName() + " " +
        principal.getLastName());
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