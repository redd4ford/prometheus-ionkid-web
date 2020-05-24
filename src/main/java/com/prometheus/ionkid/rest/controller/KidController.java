package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.KidService;
import com.prometheus.ionkid.rest.model.Kid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/kids")
@RestController
public class KidController {

  private AtomicInteger kidIdCounter = new AtomicInteger();

  @Autowired
  private KidService kidService;

  @GetMapping
  public List<Kid> getKids() {
    return kidService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Kid> getKidById(@PathVariable("id") Integer kidId) {
    if (kidService.getById(kidId) != null) {
      return new ResponseEntity<>(kidService.getById(kidId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public Kid createKid(final @RequestBody Kid kid) {
    kid.setId(kidIdCounter.incrementAndGet());
    kidService.create(kid);

    return kid;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Kid> updateKid(final @PathVariable("id") Integer kidId,
                                       final @RequestBody Kid kid) {
    if (kidService.getById(kidId) != null) {
      kid.setId(kidId);
      return new ResponseEntity<>(kidService.update(kidId, kid), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Kid> deleteKid(@PathVariable("id") Integer kidId) {
    if (kidService.getById(kidId) != null) {
      kidService.deleteById(kidId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}