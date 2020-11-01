package com.prometheus.ionkid.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
@Configuration
public class FirebaseInitializer {

  @PostConstruct
  @Primary
  @Bean
  public void initialize() throws IOException {

    InputStream serviceAccount = this.getClass()
        .getClassLoader()
        .getResourceAsStream("./firebase-private-key.json");

    assert serviceAccount != null : "firebase-private-key.json file is empty";

    FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://iot-test-spring-connection.firebaseio.com")
        .build();

    if (FirebaseApp.getApps().isEmpty()) {
      FirebaseApp.initializeApp(options);
    }

  }

//  @Bean
//  public Firestore getDatabase() throws IOException {
//    FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
//        .setCredentials(GoogleCredentials.getApplicationDefault()).build();
//    return firestoreOptions.getService();
//  }

}
