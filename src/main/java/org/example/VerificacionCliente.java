package org.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.Interfaces.InterfazDeCliente.InterfazParaElCliente;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class VerificacionCliente implements Usuarios{
    private firebase firebaseInstance;
    private String rutIngresado;
    private String emailIngresado;
    private String contraseñaIngresada;

    public VerificacionCliente(firebase firebaseInstance, String rutIngresado, String emailIngresado, String contraseñaIngresada) {
        this.firebaseInstance = firebaseInstance;
        this.rutIngresado = rutIngresado;
        this.emailIngresado = emailIngresado;
        this.contraseñaIngresada = contraseñaIngresada;
    }

    public boolean IniciarSesion() {
        CollectionReference clientesCollection = firebaseInstance.getFirestore().collection("Registro De Clientes");
        ApiFuture<QuerySnapshot> querySnapshot = clientesCollection.get();

        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                Map<String, Object> clienteData = document.getData();
                String rutCliente = (String) clienteData.get("Rut");
                String emailCliente = (String) clienteData.get("Email");
                String contraseñaCliente = (String) clienteData.get("Contraseña");

                if (rutCliente.equals(rutIngresado) && emailCliente.equals(emailIngresado) && contraseñaCliente.equals(contraseñaIngresada)) {
                    System.out.println("Cliente encontrado: " + clienteData);
                    InterfazParaElCliente interfazCliente = new InterfazParaElCliente(firebaseInstance);
                    interfazCliente.setVisible(true);
                    return true;
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error durante la operación: " + e.getMessage());
        }
        return false;
    }
}
