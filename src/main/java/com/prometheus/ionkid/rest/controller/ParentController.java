package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.ParentService;
import com.prometheus.ionkid.rest.model.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/parents")
@RestController
public class ParentController {
  private AtomicInteger parentIdCounter = new AtomicInteger();
  @Autowired
  private ParentService parentService;

  @GetMapping
  public List<Parent> getParents() {
    return parentService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Parent> getParentById(@PathVariable("id") Integer parentId) {
    if (parentService.getById(parentId) != null) {
      return new ResponseEntity<>(parentService.getById(parentId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public Parent createParent(final @RequestBody Parent parent) {
    parent.setId(parentIdCounter.incrementAndGet());
    parentService.create(parent);

    return parent;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Parent> updateParent(final @PathVariable("id") Integer parentId,
                                             final @RequestBody Parent parent) {
    if (parentService.getById(parentId) != null) {
      parent.setId(parentId);
      return new ResponseEntity<>(parentService.update(parentId, parent), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Parent> deleteParent(@PathVariable("id") Integer parentId) {
    if (parentService.getById(parentId) != null) {
      parentService.deleteById(parentId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
