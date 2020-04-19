package com.prometheus.ionkid.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Program {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int programId;

  @Column
  private String programType;
  @Column
  private Integer dailyProgress;
  @Column
  private String comment;
  @ManyToOne
  @JoinColumn(name = "doctorId")
  private Doctor doctor;
  @OneToMany(mappedBy = "program")
  private List<Task> tasks = new ArrayList<>();
  @OneToMany(mappedBy = "program")
  private List<Kid> kids = new ArrayList<>();

  public Program() {
  }

  public Program(String programType, Integer dailyProgress, String comment) {
    this.programType = programType;
    this.dailyProgress = dailyProgress;
    this.comment = comment;
  }

  public int getProgramId() {
    return programId;
  }

  public void setProgramId(int programId) {
    this.programId = programId;
  }

  public String getProgramType() {
    return programType;
  }

  public void setProgramType(String programType) {
    this.programType = programType;
  }

  public Integer getDailyProgress() {
    return dailyProgress;
  }

  public void setDailyProgress(Integer dailyProgress) {
    this.dailyProgress = dailyProgress;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

}

