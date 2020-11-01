package com.prometheus.ionkid.helper;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.ionkid.model.Chat;
import com.prometheus.ionkid.model.Comment;
import com.prometheus.ionkid.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Helper {

    public static List<Comment> helperComment(Integer value) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();

        List<Comment> list = new ArrayList<>();
        CollectionReference objects = database.collection("chats").document(String.valueOf(value)).collection("comment");
        ApiFuture<QuerySnapshot> querySnapshot = objects.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Comment object = doc.toObject(Comment.class);
            list.add(object);
        }

        return list;

    }

    public static List<Comment> helperComment(Date value) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();

        List<Comment> list = new ArrayList<>();
        CollectionReference objects = database.collection("chats").document(String.valueOf(value)).collection("comment");
        ApiFuture<QuerySnapshot> querySnapshot = objects.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Comment object = doc.toObject(Comment.class);
            list.add(object);
        }

        return list;

    }

    public static List<Task> helperTask(Integer value) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        List<Task> list = new ArrayList<>();
        CollectionReference objects = database.collection("program").document(String.valueOf(value)).collection("task");
        ApiFuture<QuerySnapshot> querySnapshot = objects.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Task object = doc.toObject(Task.class);
            list.add(object);
        }

        return list;}

    public static List<Task> helperTask(Integer firstValue,Date value) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        List<Task> list = new ArrayList<>();
        CollectionReference objects = database.collection("program").document(String.valueOf(firstValue)).collection("task").document("by-date").collection(String.valueOf(value));
        ApiFuture<QuerySnapshot> querySnapshot = objects.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Task object = doc.toObject(Task.class);
            list.add(object);
        }

        return list;
    }
    public static Chat helperChat(String value) throws ExecutionException, InterruptedException {
        final Firestore database = FirestoreClient.getFirestore();
        DocumentReference docRef = database.collection("chats").document(value);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        assert document != null : "No document found - " + value;
        // convert document to POJO
        return document.toObject(Chat.class);
    }
}
