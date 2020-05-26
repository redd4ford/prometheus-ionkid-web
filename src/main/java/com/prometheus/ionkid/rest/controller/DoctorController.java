package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.DoctorService;
import com.prometheus.ionkid.rest.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/doctors")
@RestController
public class DoctorController {

  private AtomicInteger doctorIdCounter = new AtomicInteger();

  @Autowired
  private DoctorService doctorService;

  @GetMapping
  public List<Doctor> getDoctors() {
    return doctorService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Integer doctorId) {
    if (doctorService.getById(doctorId) != null) {
      return new ResponseEntity<>(doctorService.getById(doctorId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public Doctor createDoctor(final @RequestBody Doctor doctor) {
    doctor.setId(doctorIdCounter.incrementAndGet());
    doctorService.create(doctor);

    return doctor;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Doctor> updateDoctor(final @PathVariable("id") Integer doctorId,
                                             final @RequestBody Doctor doctor) {
    if (doctorService.getById(doctorId) != null) {
      doctor.setId(doctorId);
      return new ResponseEntity<>(doctorService.update(doctorId, doctor), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Doctor> deleteDoctor(@PathVariable("id") Integer doctorId) {
    if (doctorService.getById(doctorId) != null) {
      doctorService.deleteById(doctorId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}