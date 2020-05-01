package com.prometheus.ionkid.dataaccess;

import com.prometheus.ionkid.rest.model.Program;
import com.prometheus.ionkid.rest.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {


}