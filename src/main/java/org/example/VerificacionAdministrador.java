package org.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.Interfaces.InicioSesion.InicioSesion;
import org.example.Interfaces.PaginaPrincipal;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class VerificacionAdministrador implements Usuarios{
    private InicioSesion inicioSesion;
    private firebase firebaseInstance;
    private String rutIngresado;
    private String emailIngresado;
    private String contrasenaIngresada;

    public VerificacionAdministrador(InicioSesion inicioSesion ,firebase firebaseInstance, String rutIngresado, String emailIngresado, String contrasenaIngresada) {
        this.firebaseInstance = firebaseInstance;
        this.rutIngresado = rutIngresado;
        this.emailIngresado = emailIngresado;
        this.contrasenaIngresada = contrasenaIngresada;
        this.inicioSesion = inicioSesion;
    }

    public boolean IniciarSesion() {
        CollectionReference administradoresCollection = firebaseInstance.getFirestore().collection("Administradores");
        ApiFuture<QuerySnapshot> querySnapshotAdministradores = administradoresCollection.get();
        try {
            List<QueryDocumentSnapshot> documentsAdministradores = querySnapshotAdministradores.get().getDocuments();

            Optional<QueryDocumentSnapshot> administradorEncontrado = documentsAdministradores.stream()
                    .filter(admin -> {
                        Map<String, Object> data = admin.getData();
                        String rut = (String) data.get("Rut").toString();
                        String email = (String) data.get("Email").toString();
                        String contrasena = (String) data.get("Contraseña").toString();
                        return rut.equals(rutIngresado) && email.equals(emailIngresado) && contrasena.equals(contrasenaIngresada);
                    })
                    .findFirst();
            if (administradorEncontrado.isPresent()) {
                Map<String, Object> data = administradorEncontrado.get().getData();
                System.out.println("Administrador encontrado " + data);
                PaginaPrincipal paginaPrincipal = new PaginaPrincipal(firebaseInstance);
                inicioSesion.dispose();
                paginaPrincipal.setVisible(true);
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error durante la operación: " + e.getMessage());
        }
        return false;
    }
}
