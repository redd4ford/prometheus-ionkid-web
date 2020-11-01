package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.service.MessageService;
import com.prometheus.ionkid.model.Chat;
import com.prometheus.ionkid.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class MessageController {

  private static final Logger log = LoggerFactory.getLogger(MessageController.class);

  private final MessageService messageService = new MessageService();

  @GetMapping(path = "/chats/{chatDocId}/messages")
  public List<Message> getByChat(@PathVariable("chatDocId") String chatDocId)
      throws ExecutionException, InterruptedException {
    return messageService.getByChatId(chatDocId);
  }

  @PostMapping(path = "/chats/{chatDocId}/messages")
  public ResponseEntity<Chat> create(@PathVariable("chatDocId") String chatDocId,
                                     Message message,
                                     BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + chatDocId + "/" + message.getDocID());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      messageService.save(chatDocId, message);
      log.info("CREATE 200 : id" + chatDocId + "/" + message.getDocID());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PutMapping(path = "/chats/{chatDocId}/messages/{messageDocId}")
  public ResponseEntity<Chat> update(@PathVariable("chatDocId") String chatDocId,
                                     @PathVariable("messageDocId") String messageDocId,
                                     @RequestBody @Valid Message message,
                                     BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (bindingResult.hasErrors() || messageService.getByChatId(chatDocId) == null) {
      log.error("CREATE 400 : id" + chatDocId + "/" + messageDocId);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      messageService.save(chatDocId, message);
      log.info("CREATE 200 : id" + messageDocId);
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping(path = "/chats/{chatDocId}/messages/{messageDocId}")
  public ResponseEntity<Void> delete(@PathVariable("chatDocId") String chatDocId,
                                     @PathVariable("messageDocId") String messageDocId)
      throws ExecutionException, InterruptedException {
    if (messageService.getByChatId(chatDocId) != null) {
      messageService.deleteById(chatDocId, messageDocId);
      log.info("DELETE 200 : id" + chatDocId + "/" + messageDocId);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.error("DELETE 404 : id" + chatDocId + "/" + messageDocId);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

// збочення піздець
}
