package com.prometheus.ionkid.controller;

import com.prometheus.ionkid.model.Chat;
import com.prometheus.ionkid.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RequestMapping("/chats")
@RestController
public class ChatController {

  private static final Logger log = LoggerFactory.getLogger(ChatController.class);

  private final ChatService chatService = new ChatService();

  @GetMapping
  public List<Chat> getChats() throws ExecutionException, InterruptedException {
    return chatService.getAll();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Chat> getOne(@PathVariable("id") String id)
      throws InterruptedException, ExecutionException {
    Chat chat = chatService.getById(id);
    if (chat != null) {
      log.info("GET    200 : id" + id);
      return new ResponseEntity<>(chat, HttpStatus.OK);
    } else {
      log.error("GET    404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping
  public ResponseEntity<Chat> create(@RequestBody @Valid Chat chat, BindingResult bindingResult)
      throws ExecutionException, InterruptedException {
    if (bindingResult.hasErrors()) {
      log.error("CREATE 400 : id" + chat.getDocID());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      chatService.save(chat.getDocID(), chat);
      log.info("CREATE 200 : id" + chat.getDocID());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Chat> update(@PathVariable("id") String id,
                                        @RequestBody @Valid Chat chat,
                                        BindingResult bindingResult)
      throws InterruptedException, ExecutionException {
    if (bindingResult.hasErrors() || chatService.getById(id) == null) {
      log.error("CREATE 400 : id" + chat.getDocID());
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      chatService.save(id, chat);
      log.info("CREATE 200 : id" + chat.getDocID());
      return new ResponseEntity<>(HttpStatus.OK);
    }
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") String id)
      throws ExecutionException, InterruptedException {
    Chat chat = chatService.getById(id);
    if (chat != null) {
      chatService.deleteById(id);
      log.info("DELETE 200 : id" + id);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.error("DELETE 404 : id" + id);
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
