package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.model.Task;
import com.prometheus.ionkid.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class TaskController {

  private static final Logger log = LoggerFactory.getLogger(TaskController.class);

  private final TaskService taskService = new TaskService();

  @GetMapping(path = "/programs/{programDocId}/tasks")
  public List<Task> getByProgram(@PathVariable("programDocId") Integer programDocId)
      throws ExecutionException, InterruptedException {
    return taskService.getByProgramId(programDocId);
  }

  @GetMapping(path = "/programs/{programDocId}/tasks/by-date/{date}")
  public ResponseEntity<List<Task>> getByDate(@PathVariable("programDocId") Integer programDocId,
                                              @PathVariable("date") Date date)
      throws InterruptedException, ExecutionException {
    if (taskService.getByProgramId(programDocId) != null) {
      log.info("GET    200 : id" + programDocId + "/" + date.toString());
      return new ResponseEntity<>(taskService.getByDate(programDocId, date), HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + programDocId + "/" + date.toString());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(path = "/programs/{programDocId}/tasks")
  public ResponseEntity<Task> create(@PathVariable("programDocId") Integer programDocId,
                                     @RequestBody @Valid Task task,
                                     BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + task.getDocID());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      taskService.save(programDocId, task);
      log.info("CREATE 200 : id" + task.getDocID());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PutMapping(path = "/programs/{programDocId}/tasks/{taskDocId}")
  public ResponseEntity<Task> update(@PathVariable("programDocId") Integer programDocId,
                                     @PathVariable("taskDocId") Integer taskDocId,
                                     @RequestBody @Valid Task task,
                                     BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (bindingResult.hasErrors() || taskService.getByProgramId(programDocId) == null) {
      log.error("UPDATE 400 : id" + programDocId + "/" + taskDocId);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      taskService.save(programDocId, task);
      log.info("UPDATE 200 : id" + programDocId + "/" + taskDocId);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping(path = "/programs/{programDocId}/tasks/{taskDocId}")
  public ResponseEntity<Void> delete(@PathVariable("programDocId") Integer programDocId,
                                     @PathVariable("taskDocId") Integer taskDocId)
      throws ExecutionException, InterruptedException {
    taskService.deleteById(programDocId, taskDocId);
    log.info("DELETE 200 : id" + programDocId + "/" + taskDocId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  // сука

}
