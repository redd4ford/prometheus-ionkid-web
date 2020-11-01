package com.prometheus.ionkid.service;

import com.prometheus.ionkid.model.Message;
import com.prometheus.ionkid.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MessageService {

  private final MessageRepository messageRepository = new MessageRepository();

  public List<Message> getByChatId(String chatDocId)
      throws ExecutionException, InterruptedException {
    return messageRepository.findByChatId(chatDocId);
  }

  public Message save(String chatDocId, Message message) {
    return messageRepository.save(chatDocId, message);
  }

  public void deleteById(String chatDocId, String docId) {
    messageRepository.deleteById(chatDocId, docId);
  }

  public Integer getIdleId(String chatDocId)
      throws ExecutionException, InterruptedException {
    return messageRepository.getIdleId(chatDocId);
  }

}
