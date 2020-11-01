package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.model.Program;
import com.prometheus.ionkid.service.ProgramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@RestController
public class ProgramController {

  private static final Logger log = LoggerFactory.getLogger(ProgramController.class);

  private final ProgramService programService = new ProgramService();

  @GetMapping(path = "/users/doctors/{email}/programs")
  public Program getByDoctor(@PathVariable("email") String email)
      throws ExecutionException, InterruptedException {
    return programService.getByDoctorEmail(email);
  }

  @GetMapping(path = "/programs/{docId}")
  public ResponseEntity<Program> getById(@PathVariable("docId") Integer docId)
      throws InterruptedException, ExecutionException {
    Program program = programService.getById(docId);
    if (program != null) {
      log.info("GET    200 : id" + docId);
      return new ResponseEntity<>(program, HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + docId);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(path = "/kids/{docId}")
  public ResponseEntity<Program> create(@PathVariable("docId") Integer kidDocId,
                                        @RequestBody @Valid Program program,
                                        BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + program.getDocID());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      programService.save(kidDocId, program);
      log.info("CREATE 200 : id" + program.getDocID());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PutMapping(path = "/kids/{docId}/{programDocId}")
  public ResponseEntity<Program> update(@PathVariable("docId") Integer kidDocId,
                                        @PathVariable("programDocId") Integer programDocId,
                                        @RequestBody @Valid Program program,
                                        BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (bindingResult.hasErrors() || programService.getById(programDocId) == null) {
      log.error("UPDATE 400 : id" + programDocId);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      programService.save(kidDocId, program);
      log.info("UPDATE 200 : id" + programDocId);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping(path = "/kids/{kidDocId}/{docId}")
  public ResponseEntity<Void> delete(@PathVariable("kidDocId") Integer kidDocId,
                                     @PathVariable("docId") Integer docId)
      throws ExecutionException, InterruptedException {
    programService.deleteById(kidDocId, docId);
    log.info("DELETE 200 : id" + kidDocId + "/" + docId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

// фу блять
}
