package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.business.*;
import com.prometheus.ionkid.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
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
    return "main";
  }

  @GetMapping("programs-list")
  public String displayAllPrograms(Map<String, Object> model) {
    Iterable<Program> programs = programService.getAll();
    model.put("programs", programs);

    Iterable<Task> tasks = taskService.getAll();
    model.put("tasks", tasks);

    return "programs";
  }

  @GetMapping("users-list")
  public String displayAllUsers(Map<String, Object> model) {
    Iterable<Doctor> doctors = doctorService.getAll();
    model.put("doctors", doctors);

    Iterable<Parent> parents = parentService.getAll();
    model.put("parents", parents);

    Iterable<Kid> kids = kidService.getAll();
    model.put("kids", kids);

    return "users";
  }

}