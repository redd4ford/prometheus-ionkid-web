package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.domain.Program;
import com.prometheus.ionkid.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

  List<Task> findByProgram(Program program);

}