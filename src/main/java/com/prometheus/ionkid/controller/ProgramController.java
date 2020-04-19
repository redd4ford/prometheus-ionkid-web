package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.business.ProgramService;
import com.prometheus.ionkid.domain.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/programs")
@RestController
public class ProgramController {
  private AtomicInteger programIdCounter = new AtomicInteger();
  @Autowired
  private ProgramService programService;

  @GetMapping
  public List<Program> getPrograms() {
    return programService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Program> getProgramById(@PathVariable("id") Integer programId) {
    if (programService.getById(programId) != null) {
      return new ResponseEntity<>(programService.getById(programId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public Program createProgram(final @RequestBody Program program) {
    program.setProgramId(programIdCounter.incrementAndGet());
    programService.create(program);

    return program;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Program> updateProgram(final @PathVariable("id") Integer programId,
                                               final @RequestBody Program program) {
    if (programService.getById(programId) != null) {
      program.setProgramId(programId);
      return new ResponseEntity<>(programService.update(programId, program), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Program> deleteProgram(@PathVariable("id") Integer programId) {
    if (programService.getById(programId) != null) {
      programService.deleteById(programId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
