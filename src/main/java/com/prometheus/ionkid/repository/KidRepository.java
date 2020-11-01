package com.prometheus.ionkid.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.ionkid.model.Kid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class KidRepository {

  private final Firestore database = FirestoreClient.getFirestore();

  public List<Kid> findByParentId(String parentDocId) throws ExecutionException, InterruptedException {
    // TODO

    List<Kid> list = new ArrayList<>();
    CollectionReference objects = database.collection("Parents").document(parentDocId).collection("Kids");
    ApiFuture<QuerySnapshot> querySnapshot = objects.get();
    for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
      Kid object = doc.toObject(Kid.class);
      list.add(object);
    }

    return list;

  }

  public Kid findByName(String parentDocId, String name) throws ExecutionException, InterruptedException {
    // TODO

    DocumentReference parentRef = database.collection("Parents").document(String.valueOf(parentDocId));
    // asynchronously retrieve the document
    DocumentReference kidRef= parentRef.collection("Kids").document(name);
    ApiFuture<DocumentSnapshot> future = kidRef.get();
    DocumentSnapshot document = future.get();

    assert document != null : "No document found - " + parentDocId;
    // convert document to POJO
    return document.toObject(Kid.class);

  }

  public Kid save(String name, Kid object) {
    // TODO
    database

            .collection("patient")
            .document(name)
            .collection("kids")
            .document(String.valueOf(object.getFirstName()))
            .set(object);

    return object;


  }

  public void deleteById(String programDocId, String name) {
    // TODO
    database.collection("patient").document(programDocId).collection("kids").document(name).delete();
  }

}
