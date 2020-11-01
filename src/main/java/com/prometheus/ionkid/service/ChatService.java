package com.prometheus.ionkid.service;

import com.prometheus.ionkid.repository.ChatRepository;
import com.prometheus.ionkid.model.Chat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ChatService {

  private final ChatRepository chatRepository = new ChatRepository();

  public List<Chat> getAll() throws ExecutionException, InterruptedException {
    return chatRepository.findAll();
  }

  public Chat getById(String docId) throws ExecutionException, InterruptedException {
    return chatRepository.findById(docId);
  }

  public Chat save(String docId, Chat chat) {
    return chatRepository.save(docId, chat);
  }

  public void deleteById(String docId) {
    chatRepository.deleteById(docId);
  }

}
