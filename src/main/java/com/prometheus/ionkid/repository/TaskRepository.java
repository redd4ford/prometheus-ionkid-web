package com.prometheus.ionkid.repository;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.ionkid.helper.Helper;
import com.prometheus.ionkid.model.Task;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TaskRepository {

  private final Firestore database = FirestoreClient.getFirestore();
  private final ProgramRepository programRepository = new ProgramRepository();

  public List<Task> findByProgramId(Integer programDocId) throws ExecutionException, InterruptedException {


    return Helper.helperTask(programDocId);
  }

  public List<Task> findByDate(Integer programDocId,Date date) throws ExecutionException, InterruptedException {
   return Helper.helperTask(programDocId,date);
  }

  public Task save(Integer programDocId, Task object) {
    // TODO

    database
            .collection("program")
            .document(String.valueOf(programDocId))
            .set(object);

    return object;
  }

  public void deleteById(Integer programDocId, Integer docId) {
    // TODO
    database.collection("program").document(String.valueOf(docId)).collection("task").document(String.valueOf(programDocId)).delete();
  }

}
