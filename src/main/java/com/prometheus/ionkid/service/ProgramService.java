package com.prometheus.ionkid.service;

import com.prometheus.ionkid.model.Program;
import com.prometheus.ionkid.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProgramService {

  private final ProgramRepository programRepository = new ProgramRepository();

  public Program getByDoctorEmail(String email)
      throws ExecutionException, InterruptedException {
    return programRepository.findByDoctorEmail(email);
  }

  public Program getById(Integer docId) throws ExecutionException, InterruptedException {
    return programRepository.findById(docId);
  }

  public Program getByKidId(Integer kidDocId, Integer docId) throws ExecutionException, InterruptedException {
    return programRepository.findByKidId(kidDocId, docId);
  }

  public Program save(Integer kidDocId, Program program) {
    return programRepository.save(kidDocId, program);
  }

  public void deleteById(Integer kidDocId, Integer docId) {
    programRepository.deleteById(kidDocId, docId);
  }

}
