package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.domain.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {

  List<Program> findByProgramType(String programType);

}
