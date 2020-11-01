package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.model.Kid;
import com.prometheus.ionkid.service.KidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class KidController {

  private static final Logger log = LoggerFactory.getLogger(KidController.class);

  private final KidService kidService = new KidService();

  @GetMapping(path = "/users/parents/{email}/kids")
  public List<Kid> getByParent(@PathVariable("email") String email)
      throws ExecutionException, InterruptedException {
    return kidService.getByParentId(email);
  }

  @GetMapping(path = "/users/parents/{email}/kids/{name}")
  public ResponseEntity<Kid> getByName(@PathVariable("email") String email,
                                       @PathVariable("name") String name)
      throws InterruptedException, ExecutionException {
    Kid kid = kidService.getByName(email, name);
    if (kid != null) {
      log.info("GET    200 : id" + email + ", kid " + name);
      return new ResponseEntity<>(kid, HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + email + ", kid " + name);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(path = "/users/parents/{email}/kids")
  public ResponseEntity<Kid> create(@PathVariable("email") String email,
                                    @RequestBody @Valid Kid kid,
                                    BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + kid.getDocID());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      kidService.save(email, kid);
      log.info("CREATE 200 : id" + kid.getDocID());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PutMapping(path = "/users/parents/{email}/kids")
  public ResponseEntity<Kid> update(@PathVariable("email") String email,
                                    @RequestBody @Valid Kid kid,
                                    BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (bindingResult.hasErrors() || kidService.getByParentId(email) == null) {
      log.error("UPDATE 400 : id" + kid.getDocID());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      kidService.save(email, kid);
      log.info("UPDATE 200 : id" + kid.getDocID());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping(path = "/users/parents/{email}/kids/{docId}")
  public ResponseEntity<Void> delete(@PathVariable("email") String email,
                                     @PathVariable("docId") Integer docId)
      throws ExecutionException, InterruptedException {
    kidService.deleteById(email, String.valueOf(docId));
    log.info("DELETE 200 : id" + email + "/" + docId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
