package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.UserService;
import com.prometheus.ionkid.rest.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/users")
@RestController
public class UserController {
  private AtomicInteger userIdCounter = new AtomicInteger();
  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> getUsers() {
    return userService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Integer userId) {
    if (userService.getById(userId) != null) {
      return new ResponseEntity<>(userService.getById(userId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public User createUser(final @RequestBody User user) {
    user.setId(userIdCounter.incrementAndGet());
    userService.create(user);

    return user;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<User> updateUser(final @PathVariable("id") Integer userId,
                                         final @RequestBody User user) {
    if (userService.getById(userId) != null) {
      user.setId(userId);
      return new ResponseEntity<>(userService.update(userId, user), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") Integer userId) {
    if (userService.getById(userId) != null) {
      userService.deleteById(userId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
