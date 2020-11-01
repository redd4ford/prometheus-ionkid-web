package com.prometheus.ionkid.model;

import org.springframework.stereotype.Component;

@Component
public class SKIP {

  // DOC ID
  private Integer docID;

  private Integer kidID;

  private boolean isActivated;

  private Integer BPM;

  private Integer noiseLevel;

  private Integer stepsCounter;

  private Integer oxygenInBloodPercentage;

  // IGNORE

  private String SSID;

  private String password;

  public SKIP(Integer docID, Integer kidID, boolean isActivated, Integer BPM, Integer noiseLevel,
              Integer stepsCounter, Integer oxygenInBloodPercentage, String SSID,
              String password) {
    this.docID = docID;
    this.kidID = kidID;
    this.isActivated = isActivated;
    this.BPM = BPM;
    this.noiseLevel = noiseLevel;
    this.stepsCounter = stepsCounter;
    this.oxygenInBloodPercentage = oxygenInBloodPercentage;
    this.SSID = SSID;
    this.password = password;
  }

  public SKIP() {
  }

  public Integer getDocID() {
    return docID;
  }

  public void setDocID(Integer docID) {
    this.docID = docID;
  }

  public Integer getKidID() {
    return kidID;
  }

  public void setKidID(Integer kidID) {
    this.kidID = kidID;
  }

  public boolean isActivated() {
    return isActivated;
  }

  public void setActivated(boolean activated) {
    isActivated = activated;
  }

  public Integer getBPM() {
    return BPM;
  }

  public void setBPM(Integer BPM) {
    this.BPM = BPM;
  }

  public Integer getNoiseLevel() {
    return noiseLevel;
  }

  public void setNoiseLevel(Integer noiseLevel) {
    this.noiseLevel = noiseLevel;
  }

  public Integer getStepsCounter() {
    return stepsCounter;
  }

  public void setStepsCounter(Integer stepsCounter) {
    this.stepsCounter = stepsCounter;
  }

  public Integer getOxygenInBloodPercentage() {
    return oxygenInBloodPercentage;
  }

  public void setOxygenInBloodPercentage(Integer oxygenInBloodPercentage) {
    this.oxygenInBloodPercentage = oxygenInBloodPercentage;
  }

  public String getSSID() {
    return SSID;
  }

  public void setSSID(String SSID) {
    this.SSID = SSID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
