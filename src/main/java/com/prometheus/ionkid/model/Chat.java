package com.prometheus.ionkid.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Chat {

  // DOC ID
  private String docID;

  private String firstUserEmail;

  private String secondUserEmail;

  private List<Message> messages;

  public Chat(String docID, String firstUserEmail, String secondUserEmail) {
    this.docID = docID;
    this.firstUserEmail = firstUserEmail;
    this.secondUserEmail = secondUserEmail;
  }

  public Chat() {
  }

  public String getDocID() {
    return docID;
  }

  public void setDocID(String docID) {
    this.docID = docID;
  }

  public String getFirstUserEmail() {
    return firstUserEmail;
  }

  public void setFirstUserEmail(String firstUserEmail) {
    this.firstUserEmail = firstUserEmail;
  }

  public String getSecondUserEmail() {
    return secondUserEmail;
  }

  public void setSecondUserEmail(String secondUserEmail) {
    this.secondUserEmail = secondUserEmail;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }

}
