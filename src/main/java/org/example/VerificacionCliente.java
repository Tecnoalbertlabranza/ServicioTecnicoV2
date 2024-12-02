package org.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.Interfaces.InicioSesion.InicioSesion;
import org.example.Interfaces.InterfazDeCliente.InterfazParaElCliente;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class VerificacionCliente implements Usuarios{
    private InicioSesion inicioSesion;
    private firebase firebaseInstance;
    private String rutIngresado;
    private String emailIngresado;
    private String contrasenaIngresada;

    public VerificacionCliente(InicioSesion inicioSesion ,firebase firebaseInstance, String rutIngresado, String emailIngresado, String contrasenaIngresada) {
        this.firebaseInstance = firebaseInstance;
        this.rutIngresado = rutIngresado;
        this.emailIngresado = emailIngresado;
        this.contrasenaIngresada = contrasenaIngresada;
        this.inicioSesion = inicioSesion;
    }

    public boolean IniciarSesion() {
        CollectionReference clientesCollection = firebaseInstance.getFirestore().collection("Registro De Clientes");
        ApiFuture<QuerySnapshot> querySnapshot = clientesCollection.get();
        try {
            List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

            Optional<QueryDocumentSnapshot> clienteEncontrado = documents.stream()
                    .filter(document -> {
                        Map<String, Object> clienteData = document.getData();
                        String rutCliente = (String) clienteData.get("Rut");
                        String emailCliente = (String) clienteData.get("Email");
                        String contrasenaCliente = (String) clienteData.get("Contraseña");
                        return rutCliente.equals(rutIngresado) && emailCliente.equals(emailIngresado) && contrasenaCliente.equals(contrasenaIngresada);
                    })
                    .findFirst();

            if (clienteEncontrado.isPresent()) {
                Map<String, Object> clienteData = clienteEncontrado.get().getData();
                System.out.println("Cliente encontrado: " + clienteData);
                InterfazParaElCliente interfazCliente = new InterfazParaElCliente(firebaseInstance);
                interfazCliente.setVisible(true);
                inicioSesion.dispose();
                return true;
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error durante la operación: " + e.getMessage());
        }
        return false;
    }
}
