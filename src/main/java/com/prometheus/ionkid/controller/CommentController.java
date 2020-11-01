package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.service.CommentService;
import com.prometheus.ionkid.model.Comment;
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
public class CommentController {

  private static final Logger log = LoggerFactory.getLogger(CommentController.class);

  private final CommentService commentService = new CommentService();

  @GetMapping(path = "/tasks/{taskDocId}/comments")
  public List<Comment> getByTask(@PathVariable("taskDocId") Integer taskDocId)
      throws ExecutionException, InterruptedException {
    return commentService.getByTaskId(taskDocId);
  }

  @GetMapping(path = "/tasks/{taskDocId}/comments/by-date/{date}")
  public ResponseEntity<List<Comment>> getByDate(@PathVariable("taskDocId") Integer taskDocId,
                                                 @PathVariable("date") Date date)
      throws InterruptedException, ExecutionException {
    if (commentService.getByTaskId(taskDocId) != null) {
      log.info("GET    200 : id" + taskDocId + "/" + date.toString());
      return new ResponseEntity<>(commentService.getByDate(taskDocId, date), HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + taskDocId + "/" + date.toString());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(path = "/tasks/{taskDocId}/comments")
  public ResponseEntity<Comment> create(@PathVariable("taskDocId") Integer taskDocId,
                                        @RequestBody @Valid Comment comment,
                                        BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + comment.getDocID());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      commentService.save(taskDocId, comment);
      log.info("CREATE 200 : id" + comment.getDocID());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PutMapping(path = "/tasks/{taskDocId}/comments/{commentDocId}")
  public ResponseEntity<Comment> update(@PathVariable("taskDocId") Integer taskDocId,
                                        @PathVariable("commentDocId") Integer commentDocId,
                                        @RequestBody @Valid Comment comment,
                                        BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (bindingResult.hasErrors() || commentService.getByTaskId(taskDocId) == null) {
      log.error("UPDATE 400 : id" + taskDocId + "/" + commentDocId);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      commentService.save(taskDocId, comment);
      log.info("UPDATE 200 : id" + taskDocId + "/" + commentDocId);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping(path = "/tasks/{taskDocId}/comments/{commentDocId}")
  public ResponseEntity<Void> delete(@PathVariable("taskDocId") Integer taskDocId,
                                     @PathVariable("commentDocId") Integer commentDocId)
      throws ExecutionException, InterruptedException {
    commentService.deleteById(taskDocId, commentDocId);
    log.info("DELETE 200 : id" + taskDocId + "/" + commentDocId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
