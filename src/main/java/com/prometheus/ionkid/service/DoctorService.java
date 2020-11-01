package com.prometheus.ionkid.service;

import com.prometheus.ionkid.repository.DoctorRepository;
import com.prometheus.ionkid.model.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DoctorService {

  private final DoctorRepository doctorRepository = new DoctorRepository();

  public List<Doctor> getAll() throws ExecutionException, InterruptedException {
    return doctorRepository.findAll();
  }

  public Doctor getByEmail(String email) throws ExecutionException, InterruptedException {
    return doctorRepository.findById(email);
  }

  public Doctor getByFirstAndLastName(String firstName, String lastName) throws ExecutionException, InterruptedException {
    return doctorRepository.findByFirstAndLastName(firstName, lastName);
  }

  public List<Doctor> getByCountry(String country) throws ExecutionException, InterruptedException {
    return doctorRepository.findByCountry(country);
  }

  public Doctor save(String email, Doctor doctor) {
    return doctorRepository.save(email, doctor);
  }

  public void deleteByEmail(String email) {
    doctorRepository.deleteById(email);
  }

}
