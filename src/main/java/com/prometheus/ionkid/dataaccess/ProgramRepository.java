package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.rest.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {


}
