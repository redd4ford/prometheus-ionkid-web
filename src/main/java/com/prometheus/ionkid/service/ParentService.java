package com.prometheus.ionkid.service;

import com.prometheus.ionkid.repository.ParentRepository;
import com.prometheus.ionkid.model.Parent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ParentService {

  private final ParentRepository parentRepository = new ParentRepository();

  public List<Parent> getAll() throws ExecutionException, InterruptedException {
    return parentRepository.findAll();
  }

  public Parent getByEmail(String email) throws ExecutionException, InterruptedException {
    return parentRepository.findById(email);
  }

  public Parent getByFullName(String firstName, String lastName) throws ExecutionException, InterruptedException {
    return parentRepository.findByFirstAndLastName(firstName, lastName);
  }

  public List<Parent> getByCountry(String country) throws ExecutionException, InterruptedException {
    return parentRepository.findByCountry(country);
  }

  public Parent save(String email, Parent parent) {
    return parentRepository.save(email, parent);
  }

  public void deleteByEmail(String email) {
    parentRepository.deleteById(email);
  }

}
