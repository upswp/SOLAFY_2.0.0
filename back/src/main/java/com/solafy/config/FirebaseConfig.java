package com.solafy.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.solafy.security.model.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FirebaseConfig {

    @Autowired
    private SecurityProperties securityProperties;

    private static final String FIREBASE_CONFIG_PATH = "solafy_firebase_admin_config.json";
    private static final String FIREBASE_DATABASE_URL_PATH ="https://solafy-460e6-default-rtdb.firebaseio.com";
    @Primary //우선순위 최상위
    @Bean    //메서드에서 return 하는 객체 빈 등록
    public FirebaseApp getFirebaseApp() throws IOException{
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream()))
                .setDatabaseUrl(FIREBASE_DATABASE_URL_PATH).build();
        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }
        return FirebaseApp.getInstance();



    }

    //get firebase Auth
    @Bean
    public FirebaseAuth getAuth() throws IOException{
        return FirebaseAuth.getInstance(getFirebaseApp());
    }

    //get firebase RealTimeDatabase
    @Bean
    public FirebaseDatabase firebaseDatabase(){
        return FirebaseDatabase.getInstance();
    }

    //get firebase Firestore
    @Bean
    public Firestore getFirestore() throws IOException{
        FirestoreOptions firestoreOptions = FirestoreOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(FIREBASE_CONFIG_PATH).getInputStream())).build();
        return firestoreOptions.getService();
    }

    //get FCM
    @Bean
    public FirebaseMessaging getMessaging(){
        return FirebaseMessaging.getInstance();
    }
}
