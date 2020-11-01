package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.model.Parent;
import com.prometheus.ionkid.service.ParentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping("/users/parents")
@RestController
public class ParentController {

  private static final Logger log = LoggerFactory.getLogger(ParentController.class);

  private final ParentService parentService = new ParentService();

  @GetMapping
  public List<Parent> getParents() throws ExecutionException, InterruptedException {
    return parentService.getAll();
  }

  @GetMapping(path = "/{email}")
  public ResponseEntity<Parent> getOne(@PathVariable("email") String email)
      throws InterruptedException, ExecutionException {
    Parent parent = parentService.getByEmail(email);
    if (parent != null) {
      log.info("GET    200 : id" + email);
      return new ResponseEntity<>(parent, HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + email);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = "/by-name/{lastName}/{firstName}")
  public ResponseEntity<Parent> getByFirstAndLastName(@PathVariable("lastName") String lastName,
                                                      @PathVariable("firstName") String firstName)
      throws InterruptedException, ExecutionException {
    Parent parent = parentService.getByFullName(firstName, lastName);
    if (parent != null) {
      log.info("GET    200 : id" + parent.getEmail());
      return new ResponseEntity<>(parent, HttpStatus.OK);
    } else {
      log.error("GET    404 : " + firstName + ", " + lastName);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = "/by-country/{country}")
  public ResponseEntity<List<Parent>> getByCountry(@PathVariable("country") String country)
      throws InterruptedException, ExecutionException {
    List<Parent> parents = parentService.getByCountry(country);
    if (parents != null) {
      log.info("GET    200 : country" + country);
      return new ResponseEntity<>(parents, HttpStatus.OK);
    } else {
      log.error("GET    404 : country" + country);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Parent> create(@RequestBody @Valid Parent parent,
                                       BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + parent.getEmail());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      parentService.save(parent.getEmail(), parent);
      log.info("CREATE 200 : id" + parent.getEmail());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PutMapping(path = "/{email}")
  public ResponseEntity<Parent> update(@PathVariable("email") String email,
                                       @RequestBody @Valid Parent parent,
                                       BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (bindingResult.hasErrors() || parentService.getByEmail(email) == null) {
      log.error("CREATE 400 : id" + parent.getEmail());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      parentService.save(email, parent);
      log.info("CREATE 200 : id" + parent.getEmail());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping(path = "/{email}")
  public ResponseEntity<Void> delete(@PathVariable("email") String email)
      throws ExecutionException, InterruptedException {
    Parent parent = parentService.getByEmail(email);
    if (parent != null) {
      parentService.deleteByEmail(email);
      log.info("DELETE 200 : id" + email);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.error("DELETE 404 : id" + email);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
