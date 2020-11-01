package com.prometheus.ionkid.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.ionkid.model.Parent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ParentRepository {

  private final Firestore database = FirestoreClient.getFirestore();

  public List<Parent> findAll() throws ExecutionException, InterruptedException {
    List<Parent> list = new ArrayList<>();
    CollectionReference objects = database.collection("parents");

    ApiFuture<QuerySnapshot> querySnapshot = objects.get();
    for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
      Parent object = doc.toObject(Parent.class);
      list.add(object);
    }

    return list;
  }

  public Parent findById(String email) throws ExecutionException, InterruptedException {
    DocumentReference parentRef = database.collection("parents").document(email);
    // asynchronously retrieve the document
    ApiFuture<DocumentSnapshot> future = parentRef.get();
    DocumentSnapshot document = future.get();

    assert document != null : "No document found - " + email;
    // convert document to POJO
    return document.toObject(Parent.class);
  }

  public Parent findByFirstAndLastName(String firstName, String lastName) throws ExecutionException, InterruptedException {

    DocumentReference docRef = database.collection("parent").document(firstName + " " + lastName);
    // asynchronously retrieve the document
    ApiFuture<DocumentSnapshot> future = docRef.get();
    DocumentSnapshot document = future.get();


    assert document != null : "No document found - " + firstName + " " + lastName;
    // convert document to POJO
    return document.toObject(Parent.class);


  }

  public List<Parent> findByCountry(String country) throws ExecutionException, InterruptedException {
    List<Parent> list = new ArrayList<>();
    CollectionReference objects = database.collection("country").document("parent").collection(country);
    ApiFuture<QuerySnapshot> querySnapshot = objects.get();
    for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
      Parent object = doc.toObject(Parent.class);
      list.add(object);
    }

    return list;

  }

  public Parent save(String email, Parent object) {
    database
        .collection("patient")
        .document(email)
        .set(object);

    return object;
  }

  public void deleteById(String email) {
    database.collection("parents").document(email).delete();
  }

  public Integer countAll() throws ExecutionException, InterruptedException {
    return findAll().size();
  }

}
