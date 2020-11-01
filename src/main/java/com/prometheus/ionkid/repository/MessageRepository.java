package com.prometheus.ionkid.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.ionkid.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MessageRepository {

  private final Firestore database = FirestoreClient.getFirestore();
  private final ChatRepository chatRepository = new ChatRepository();

  public List<Message> findByChatId(String chatDocId)
      throws ExecutionException, InterruptedException {
    // TODO
    List<Message> list = new ArrayList<>();
    CollectionReference objects = database.collection("chat").document(chatDocId).collection("message");
    ApiFuture<QuerySnapshot> querySnapshot = objects.get();
    for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
      Message object = doc.toObject(Message.class);
      list.add(object);
    }

    return list;

  }

  public Message save(String chatDocId, Message object) {
    // TODO
    database
            .collection("message")
            .document(chatDocId)
            .set(object);

    return object;

  }

  public void deleteById(String chatDocId, String docId) {
    database.collection("chat").document(docId).collection("message").document(chatDocId).delete();
    // TODO
  }

  public Integer getIdleId(String chatDocId) throws ExecutionException, InterruptedException {
    // TODO


    return findByChatId(chatDocId).size();
  }

}
