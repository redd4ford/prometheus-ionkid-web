package com.prometheus.ionkid.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Task {

  // DOC ID
  private Integer docID;

  private Integer programID;

  private boolean status;

  private String description;

  private List<Comment> comments;

  public Task(Integer docID, Integer programID, boolean status, String description,
              List<Comment> comments) {
    this.docID = docID;
    this.programID = programID;
    this.status = status;
    this.description = description;
    this.comments = comments;
  }

  public Task() {
  }

  public Integer getDocID() {
    return docID;
  }

  public void setDocID(Integer docID) {
    this.docID = docID;
  }

  public Integer getProgramID() {
    return programID;
  }

  public void setProgramID(Integer programID) {
    this.programID = programID;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

}
