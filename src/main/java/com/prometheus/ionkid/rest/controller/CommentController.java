package com.prometheus.ionkid.rest.controller;

import com.prometheus.ionkid.business.CommentService;
import com.prometheus.ionkid.rest.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/comments")
@RestController
public class CommentController {
  private AtomicInteger commentIdCounter = new AtomicInteger();
  @Autowired
  private CommentService commentService;

  @GetMapping
  public List<Comment> getComments() {
    return commentService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Comment> getCommentById(@PathVariable("id") Integer commentId) {
    if (commentService.getById(commentId) != null) {
      return new ResponseEntity<>(commentService.getById(commentId), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
  public Comment createComment(final @RequestBody Comment comment) {
    comment.setId(commentIdCounter.incrementAndGet());
    commentService.create(comment);

    return comment;
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Comment> updateComment(final @PathVariable("id") Integer commentId,
                                               final @RequestBody Comment comment) {
    if (commentService.getById(commentId) != null) {
      comment.setId(commentId);
      return new ResponseEntity<>(commentService.update(commentId, comment), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Comment> deleteDoctor(@PathVariable("id") Integer commentId) {
    if (commentService.getById(commentId) != null) {
      commentService.deleteById(commentId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
