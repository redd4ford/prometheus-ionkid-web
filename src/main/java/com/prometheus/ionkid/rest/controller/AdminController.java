package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.AdminService;
import com.prometheus.ionkid.rest.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/admins")
@RestController
public class AdminController {
  private AtomicInteger adminIdCounter = new AtomicInteger();
  @Autowired
  private AdminService adminService;

  @GetMapping
  public List<Admin> getAdmins() {
    return adminService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Admin> getAdminById(@PathVariable("id") Integer adminId) {
    if (adminService.getById(adminId) != null) {
      return new ResponseEntity<>(adminService.getById(adminId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public Admin createAdmin(final @RequestBody Admin admin) {
    admin.setId(adminIdCounter.incrementAndGet());
    adminService.create(admin);

    return admin;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Admin> updateAdmin(final @PathVariable("id") Integer adminId,
                                           final @RequestBody Admin admin) {
    if (adminService.getById(adminId) != null) {
      admin.setId(adminId);
      return new ResponseEntity<>(adminService.update(adminId, admin), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") Integer adminId) {
    if (adminService.getById(adminId) != null) {
      adminService.deleteById(adminId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
