package com.prometheus.ionkid.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.prometheus.ionkid.model.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DoctorRepository {

    private final Firestore database = FirestoreClient.getFirestore();

    public List<Doctor> findAll() throws ExecutionException, InterruptedException {
        List<Doctor> list = new ArrayList<>();
        CollectionReference objects = database.collection("doctors");

        ApiFuture<QuerySnapshot> querySnapshot = objects.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Doctor object = doc.toObject(Doctor.class);
            list.add(object);
        }

        return list;
    }

    public Doctor findById(String email) throws ExecutionException, InterruptedException {
        DocumentReference docRef = database.collection("doctors").document(email);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        assert document != null : "No document found - " + email;
        // convert document to POJO
        return document.toObject(Doctor.class);
    }

    public Doctor findByFirstAndLastName(String firstName, String lastName) throws ExecutionException, InterruptedException {
        // TODO
        DocumentReference docRef = database.collection("doctors").document(firstName + " " + lastName);
        // asynchronously retrieve the document
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();


        assert document != null : "No document found - " + firstName + " " + lastName;
        // convert document to POJO
        return document.toObject(Doctor.class);

    }

    public List<Doctor> findByCountry(String country) throws ExecutionException, InterruptedException {
        // TODO
        List<Doctor> list = new ArrayList<>();
        CollectionReference objects = database.collection("country").document("parent").collection(country);
        ApiFuture<QuerySnapshot> querySnapshot = objects.get();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            Doctor object = doc.toObject(Doctor.class);
            list.add(object);
        }

        return list;


    }

    public Doctor save(String email, Doctor object) {
        database
                .collection("doctors")
                .document(email)
                .set(object);

        return object;
    }

    public void deleteById(String email) {
        database.collection("doctors").document(email).delete();
    }

    public Integer countAll() throws ExecutionException, InterruptedException {
        return findAll().size();
    }

}
