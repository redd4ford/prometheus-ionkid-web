package com.prometheus.ionkid.repository;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import com.prometheus.ionkid.helper.Helper;
import com.prometheus.ionkid.model.Comment;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CommentRepository {

    private final Firestore database = FirestoreClient.getFirestore();
    private final TaskRepository taskRepository = new TaskRepository();

    public List<Comment> findByTaskId(Integer taskDocId) throws ExecutionException, InterruptedException {
        // TODO
        return Helper.helperComment(taskDocId);
    }

    public List<Comment> findByDate(Date date) throws ExecutionException, InterruptedException {
        // TODO
        return Helper.helperComment(date);

    }

    public Comment save(Integer taskDocId, Comment object) {
        // TODO

        database
                .collection("chats")
                .document(String.valueOf(taskDocId))
                .set(object);

        return object;

    }

    public void deleteById(Integer taskDocId, Integer docId) {
        database.collection("chats").document(String.valueOf(docId)).collection("comment")
                .document(String.valueOf(taskDocId)).delete();
        // TODO
    }

}
