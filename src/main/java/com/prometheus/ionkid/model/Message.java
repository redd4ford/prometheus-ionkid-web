package com.prometheus.ionkid.model;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Message {

  // DOC ID
  private Integer docID;

  private Integer chatID;

  private String email;

  private Date date;

  private String text;

  public Message(Integer docID, Integer chatID, String email, String text, Date date) {
    this.docID = docID;
    this.chatID = chatID;
    this.email = email;
    this.text = text;
    this.date = date;
  }

  public Message() {
  }

  public Integer getDocID() {
    return docID;
  }

  public void setDocID(Integer docID) {
    this.docID = docID;
  }

  public Integer getChatID() {
    return chatID;
  }

  public void setChatID(Integer chatID) {
    this.chatID = chatID;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

}
