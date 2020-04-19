package com.prometheus.ionkid.domain;

import javax.persistence.*;

@Entity
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int taskId;

  @Column
  private String taskText;
  @Column
  private Boolean status;
  @Column
  private String comment;
  @ManyToOne
  @JoinColumn(name = "programId")
  private Program program;

  public Task() {
  }

  public Task(String taskText, Boolean status, String comment) {
    this.taskText = taskText;
    this.status = status;
    this.comment = comment;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  public String getTaskText() {
    return taskText;
  }

  public void setTaskText(String taskText) {
    this.taskText = taskText;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

}

