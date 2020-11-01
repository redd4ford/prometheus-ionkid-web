package com.prometheus.ionkid.service;

import com.prometheus.ionkid.model.Task;
import com.prometheus.ionkid.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TaskService {

  private final TaskRepository taskRepository = new TaskRepository();

  public List<Task> getByProgramId(Integer programDocId)
      throws ExecutionException, InterruptedException {
    return taskRepository.findByProgramId(programDocId);
  }

  public List<Task> getByDate(Integer programDocId, Date date) throws ExecutionException, InterruptedException {
    return taskRepository.findByDate(programDocId,date);
  }

  public Task save(Integer programDocId, Task task) {
    return taskRepository.save(programDocId, task);
  }

  public void deleteById(Integer programDocId, Integer docId) {
    taskRepository.deleteById(programDocId, docId);
  }

}
