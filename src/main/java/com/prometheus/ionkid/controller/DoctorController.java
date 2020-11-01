package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.model.Doctor;
import com.prometheus.ionkid.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping("/users/doctors")
@RestController
public class DoctorController {

  private static final Logger log = LoggerFactory.getLogger(DoctorController.class);

  private final DoctorService doctorService = new DoctorService();

  @GetMapping
  public List<Doctor> getDoctors() throws ExecutionException, InterruptedException {
    return doctorService.getAll();
  }

  @GetMapping(path = "/{email}")
  public ResponseEntity<Doctor> getOne(@PathVariable("email") String email)
      throws InterruptedException, ExecutionException {
    Doctor doctor = doctorService.getByEmail(email);
    if (doctor != null) {
      log.info("GET    200 : id" + email);
      return new ResponseEntity<>(doctor, HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + email);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = "/by-name/{lastName}/{firstName}")
  public ResponseEntity<Doctor> getByFirstAndLastName(@PathVariable("lastName") String lastName,
                                                      @PathVariable("firstName") String firstName)
      throws InterruptedException, ExecutionException {
    Doctor doctor = doctorService.getByFirstAndLastName(firstName, lastName);
    if (doctor != null) {
      log.info("GET    200 : id" + doctor.getEmail());
      return new ResponseEntity<>(doctor, HttpStatus.OK);
    } else {
      log.error("GET    404 : " + firstName + ", " + lastName);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(path = "/by-country/{country}")
  public ResponseEntity<List<Doctor>> getByCountry(@PathVariable("country") String country)
      throws InterruptedException, ExecutionException {
    List<Doctor> doctors = doctorService.getByCountry(country);
    if (doctors != null) {
      log.info("GET    200 : country" + country);
      return new ResponseEntity<>(doctors, HttpStatus.OK);
    } else {
      log.error("GET    404 : country" + country);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Doctor> create(@RequestBody @Valid Doctor doctor,
                                       BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + doctor.getEmail());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      doctorService.save(doctor.getEmail(), doctor);
      log.info("CREATE 200 : id" + doctor.getEmail());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PutMapping(path = "/{email}")
  public ResponseEntity<Doctor> update(@PathVariable("email") String email,
                                       @RequestBody @Valid Doctor doctor,
                                       BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (bindingResult.hasErrors() || doctorService.getByEmail(email) == null) {
      log.error("CREATE 400 : id" + doctor.getEmail());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      doctorService.save(email, doctor);
      log.info("CREATE 200 : id" + doctor.getEmail());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping(path = "/{email}")
  public ResponseEntity<Void> delete(@PathVariable("email") String email)
      throws ExecutionException, InterruptedException {
    Doctor doctor = doctorService.getByEmail(email);
    if (doctor != null) {
      doctorService.deleteByEmail(email);
      log.info("DELETE 200 : id" + email);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.error("DELETE 404 : id" + email);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
