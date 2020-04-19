package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.TaskRepository;
import com.prometheus.ionkid.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends AbstractService<Task> {
  @Autowired
  private TaskRepository taskRepository;

  @Override
  protected JpaRepository<Task, Integer> getRepository() {
    return taskRepository;
  }

}