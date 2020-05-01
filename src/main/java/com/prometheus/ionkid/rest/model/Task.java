package com.prometheus.ionkid.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column
  private Boolean status;
  @Column
  private String description;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "program_id")
  @JsonIgnoreProperties("tasks")
  private Program program;

  public Task() {
  }

  public Task(Boolean status, String description) {
    this.status = status;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Program getProgram() {
    return program;
  }

  public void setProgram(Program program) {
    this.program = program;
  }
}

