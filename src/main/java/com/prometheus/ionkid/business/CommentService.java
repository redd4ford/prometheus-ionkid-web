package com.prometheus.ionkid.business;

import com.prometheus.ionkid.dataaccess.CommentRepository;
import com.prometheus.ionkid.rest.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends AbstractService<Comment> {

  @Autowired
  private CommentRepository commentRepository;

  @Override
  protected JpaRepository<Comment, Integer> getRepository() {
    return commentRepository;
  }

}