package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.TaskService;
import com.prometheus.ionkid.rest.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/tasks")
@RestController
public class TaskController {

  private AtomicInteger taskIdCounter = new AtomicInteger();

  @Autowired
  private TaskService taskService;

  @GetMapping
  public List<Task> getTasks() {
    return taskService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable("id") Integer taskId) {
    if (taskService.getById(taskId) != null) {
      return new ResponseEntity<>(taskService.getById(taskId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public Task createTask(final @RequestBody Task task) {
    task.setId(taskIdCounter.incrementAndGet());
    taskService.create(task);

    return task;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Task> updateTask(final @PathVariable("id") Integer taskId,
                                         final @RequestBody Task task) {
    if (taskService.getById(taskId) != null) {
      task.setId(taskId);
      return new ResponseEntity<>(taskService.update(taskId, task), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Task> deleteTask(@PathVariable("id") Integer programId) {
    if (taskService.getById(programId) != null) {
      taskService.deleteById(programId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}