package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.ProgramRepository;
import com.prometheus.ionkid.rest.model.Program;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProgramService extends AbstractService<Program> {
  @Autowired
  private ProgramRepository programRepository;

  @Override
  protected JpaRepository<Program, Integer> getRepository() {
    return programRepository;
  }

}