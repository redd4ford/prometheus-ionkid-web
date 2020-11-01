package com.prometheus.ionkid.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.ionkid.helper.Helper;
import com.prometheus.ionkid.model.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ChatRepository {

  private final Firestore database = FirestoreClient.getFirestore();

  public List<Chat> findAll() throws ExecutionException, InterruptedException {
    List<Chat> list = new ArrayList<>();
    CollectionReference objects = database.collection("chats");
    ApiFuture<QuerySnapshot> querySnapshot = objects.get();
    for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
      Chat object = doc.toObject(Chat.class);
      list.add(object);
    }

    return list;
  }

  public Chat findById(String docId) throws ExecutionException, InterruptedException {
    return Helper.helperChat(docId);
  }

  public Chat findByFirstOrSecondEmail(String email) throws ExecutionException, InterruptedException {
    // TODO

    return Helper.helperChat(email);
  }

  public Chat save(String docId, Chat object) {
    database
        .collection("chats")
        .document(docId)
        .set(object);

    return object;
  }

  public void deleteById(String docId) {
    database.collection("chats").document(docId).delete();
  }

  public Integer countAll() throws ExecutionException, InterruptedException {
    return findAll().size();
  }

}
