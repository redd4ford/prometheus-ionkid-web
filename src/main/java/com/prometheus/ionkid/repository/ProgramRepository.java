package com.prometheus.ionkid.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.ionkid.model.Program;

import java.util.concurrent.ExecutionException;

public class ProgramRepository {

  private final Firestore database = FirestoreClient.getFirestore();
  private final KidRepository kidRepository = new KidRepository();
  private final DoctorRepository doctorRepository = new DoctorRepository();

  public Program findByDoctorEmail(String email) throws ExecutionException, InterruptedException {
    // TODO
    DocumentReference programRef = database.collection("program").document(email);
    // asynchronously retrieve the document
    ApiFuture<DocumentSnapshot> future = programRef.get();
    DocumentSnapshot document = future.get();

    assert document != null : "No document found - " + email;
    // convert document to POJO
    return document.toObject(Program.class);
  }

  public Program findById(Integer docId) throws ExecutionException, InterruptedException {
    // TODO
    DocumentReference programRef = database.collection("program").document(String.valueOf(docId));
    // asynchronously retrieve the document
    ApiFuture<DocumentSnapshot> future = programRef.get();
    DocumentSnapshot document = future.get();

    assert document != null : "No document found - " + docId;
    // convert document to POJO
    return document.toObject(Program.class);

  }

  public Program findByKidId(Integer kidDocId, Integer docId) throws ExecutionException, InterruptedException {
    // TODO
    DocumentReference programRef = database.collection("program").document(String.valueOf(docId)).collection("patient").document(String.valueOf(kidDocId));
    // asynchronously retrieve the document
    ApiFuture<DocumentSnapshot> future = programRef.get();
    DocumentSnapshot document = future.get();

    assert document != null : "No document found - " + docId+"and"+kidDocId;
    // convert document to POJO
    return document.toObject(Program.class);
  }

  public Program save(Integer kidDocId, Program object) {
    // TODO
    database

            .collection("patient")
            .document(String.valueOf(kidDocId))
            .set(object);

    return object;



  }

  public void deleteById(Integer kidDocId, Integer docId) {
    // TODO
    database.collection("program").document(String.valueOf(docId)).collection("patient").document(String.valueOf(kidDocId)).delete();
  }

}
