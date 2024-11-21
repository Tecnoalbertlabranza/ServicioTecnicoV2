package org.example;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
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

    public Map<String, Object> leerDatos(String coleccion, String documento){
        try {
            if (firestore != null) {
                DocumentReference docRef = firestore.collection(coleccion).document(documento);
                ApiFuture<DocumentSnapshot> future = docRef.get();
                DocumentSnapshot document = future.get();

                if (document.exists()) {
                    return document.getData();
                } else {
                    System.out.println("No such document!");
                    return null;
                }
            } else {
                throw new IllegalStateException("Firestore no se inicializo. Debe llamar a inicializarconexion() primero.");}
        } catch (InterruptedException | ExecutionException e){
            System.out.println("Error durante la lectura del documento: " + e.getMessage());
            return null;
        }
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

    public Firestore getFirestore() {
        if (firestore == null) {
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
            System.out.println("Error durante la inicializacion " + e.getMessage());
        }
    }

    public void eliminarDatos(String coleccion, String documento) {
        try {
            if (firestore != null) {
                DocumentReference docRef = firestore.collection(coleccion).document(documento);
                ApiFuture<WriteResult> resultado = docRef.delete();
                System.out.println("documento eliminado en"+ resultado.get().getUpdateTime());
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Error durante la operación" + e.getMessage());
        }
    }
}
