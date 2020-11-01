package com.prometheus.ionkid.model;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Program {

  private Integer docID;

  private String doctorEmail;

  private String description;

  private Integer progressPercentage;

  private Date startDate;

  private Date endDate;

  private List<Task> tasks;

  public Program(Integer docID, String doctorEmail, String description, Integer progressPercentage,
                 Date startDate, Date endDate) {
    this.docID = docID;
    this.doctorEmail = doctorEmail;
    this.description = description;
    this.progressPercentage = progressPercentage;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Program() {
  }

  public Integer getDocID() {
    return docID;
  }

  public void setDocID(Integer docID) {
    this.docID = docID;
  }

  public String getDoctorEmail() {
    return doctorEmail;
  }

  public void setDoctorEmail(String doctorEmail) {
    this.doctorEmail = doctorEmail;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getProgressPercentage() {
    return progressPercentage;
  }

  public void setProgressPercentage(Integer progressPercentage) {
    this.progressPercentage = progressPercentage;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

}
