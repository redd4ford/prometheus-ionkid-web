package com.prometheus.ionkid.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Comment {

  // DOC ID
  private Integer docID;

  private Integer taskID;

  private String userEmail;

  private Date date;

  private String text;

  public Comment(Integer docID, Integer taskID, String userEmail, Date date, String text) {
    this.docID = docID;
    this.taskID = taskID;
    this.userEmail = userEmail;
    this.date = date;
    this.text = text;
  }

  public Comment() {
  }

  public Integer getDocID() {
    return docID;
  }

  public void setDocID(Integer docID) {
    this.docID = docID;
  }

  public Integer getTaskID() {
    return taskID;
  }

  public void setTaskID(Integer taskID) {
    this.taskID = taskID;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

}
