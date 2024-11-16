package org.example;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
            String tipotabla,
            String nombretabla,
            String rama,
            Map<String, Object> data) {

        try {
            if (firestore != null) {
                DocumentReference docRef = firestore.collection(tipotabla).document(nombretabla);

                ApiFuture<WriteResult> resultado = docRef.set(data);
                WriteResult writeResult = resultado.get();
                System.out.println("Documento principal actualizado en: " + writeResult.getUpdateTime());

                Map<String, Object> direccionData = new HashMap<>();

                CollectionReference direccionRef = docRef.collection("Direccion");

                ApiFuture<DocumentReference> direccionResult = direccionRef.add(direccionData);
                DocumentReference direccionDoc = direccionResult.get();
                System.out.println("Datos agregados a la subcolección 'Direccion' con ID: " + direccionDoc.getId());

                Map<String, Object> datosPersonalesData = new HashMap<>();

                CollectionReference datosPersonalesRef = docRef.collection("Datos personales");

                ApiFuture<DocumentReference> datosPersonalesResult = datosPersonalesRef.add(datosPersonalesData);
                DocumentReference datosPersonalesDoc = datosPersonalesResult.get();
                System.out.println("Datos agregados a la subcolección 'Datos personales' con ID: " + datosPersonalesDoc.getId());

            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error durante la operación: " + e.getMessage());
        }
    }


}
