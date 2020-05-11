package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.RoleService;
import com.prometheus.ionkid.rest.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/roles")
@RestController
public class RoleController {
  private AtomicInteger roleIdCounter = new AtomicInteger();
  @Autowired
  private RoleService roleService;

  @GetMapping
  public List<Role> getRoles() {
    return roleService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Role> getRoleById(@PathVariable("id") Integer roleId) {
    if (roleService.getById(roleId) != null) {
      return new ResponseEntity<>(roleService.getById(roleId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public Role createRole(final @RequestBody Role role) {
    role.setId(roleIdCounter.incrementAndGet());
    roleService.create(role);

    return role;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Role> updateRole(final @PathVariable("id") Integer roleId,
                                         final @RequestBody Role role) {
    if (roleService.getById(roleId) != null) {
      role.setId(roleId);
      return new ResponseEntity<>(roleService.update(roleId, role), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Role> deleteRole(@PathVariable("id") Integer roleId) {
    if (roleService.getById(roleId) != null) {
      roleService.deleteById(roleId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
