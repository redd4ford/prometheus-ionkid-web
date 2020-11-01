package com.prometheus.ionkid.service;

import com.prometheus.ionkid.model.Comment;
import com.prometheus.ionkid.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommentService {

  private final CommentRepository commentRepository = new CommentRepository();

  public List<Comment> getByTaskId(Integer taskDocId)
      throws ExecutionException, InterruptedException {
    return commentRepository.findByTaskId(taskDocId);
  }

  public List<Comment> getByDate(Integer taskDocId, Date date) throws ExecutionException, InterruptedException {
    return commentRepository.findByDate(date);
  }

  public Comment save(Integer taskDocId, Comment comment) {
    return commentRepository.save(taskDocId, comment);
  }

  public void deleteById(Integer taskDocId, Integer docId) {
    commentRepository.deleteById(taskDocId, docId);
  }

}
