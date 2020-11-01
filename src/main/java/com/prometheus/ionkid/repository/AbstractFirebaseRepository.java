package com.prometheus.ionkid.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import javax.validation.constraints.NotNull;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class AbstractFirebaseRepository<T, ID> {

  private final String collectionName;
  private final Firestore database = FirestoreClient.getFirestore();

  private @SuppressWarnings("unchecked") final Class<T> objectClassName =
      (Class<T>) ((ParameterizedType) getClass()
      .getGenericSuperclass())
      .getActualTypeArguments()[0];

  public AbstractFirebaseRepository(String collectionName) {
    this.collectionName = collectionName;
  }

  public List<T> findAll() throws ExecutionException, InterruptedException {
    List<T> list = new ArrayList<>();
    CollectionReference objects = database.collection(collectionName);

    ApiFuture<QuerySnapshot> querySnapshot = objects.get();
    for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
      T object = doc.toObject(objectClassName);
      list.add(object);
    }

    return list;
  }

  public T findById(ID id) throws ExecutionException, InterruptedException {
    DocumentReference docRef = database.collection(collectionName).document(id.toString());
    // asynchronously retrieve the document
    ApiFuture<DocumentSnapshot> future = docRef.get();
    DocumentSnapshot document = future.get();

    assert document != null : "No document found - " + id;
    // convert document to POJO
    return document.toObject(objectClassName);
  }

  public T save(T object, ID id) {
    database
        .collection(collectionName)
        .document(id.toString())
        .set(object);
    
    return object;
  }

  public void deleteById(@NotNull ID id) {
    database.collection(collectionName).document(id.toString()).delete();
  }

  public Integer countAll() throws ExecutionException, InterruptedException {
    return findAll().size();
  }

}
