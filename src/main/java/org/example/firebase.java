package org.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class firebase {
    Firestore firestore;

    public firebase() {
    }



    public void inicializarconexion() {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream is = classLoader.getResourceAsStream("conexion_serviciotecnico_firebase.json");

            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(is);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(googleCredentials)
                    .build();

            FirebaseApp.initializeApp(options);
            firestore = FirestoreClient.getFirestore();

            System.out.println("Cargando json firebase");
        } catch (
                IOException ex) {
            System.out.println("" + ex);

        } catch (java.lang.IllegalStateException e) {
        }
    }

    public Firestore getFirestore(){
        if (firestore == null){
            throw new IllegalStateException("Firestore no se inicializo . debes llamar a inicializarconexion() primero");
        }
        return firestore;
    }

    public void insertardatos(
            String coleccion,
            String documento,
            Map<String, Object> data) {


        try {
            if (firestore != null) {
                DocumentReference docRef = firestore.collection(coleccion).document(documento);
                ApiFuture<WriteResult> resultado = docRef.set(data);
                System.out.println("" + resultado.get().getUpdateTime());
            }
        } catch (InterruptedException | ExecutionException e) {
        }
    }
}
