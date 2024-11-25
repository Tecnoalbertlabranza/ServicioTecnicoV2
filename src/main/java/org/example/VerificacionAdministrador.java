package org.example;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.Interfaces.PaginaPrincipal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class VerificacionAdministrador implements Usuarios{
    private firebase firebaseInstance;
    private String rutIngresado;
    private String emailIngresado;
    private String contraseñaIngresada;

    public VerificacionAdministrador(firebase firebaseInstance, String rutIngresado, String emailIngresado, String contraseñaIngresada) {
        this.firebaseInstance = firebaseInstance;
        this.rutIngresado = rutIngresado;
        this.emailIngresado = emailIngresado;
        this.contraseñaIngresada = contraseñaIngresada;
    }

    public boolean IniciarSesion() {
        CollectionReference administradoresCollection = firebaseInstance.getFirestore().collection("Administradores");
        ApiFuture<QuerySnapshot> querySnapshotAdministradores = administradoresCollection.get();

        try {
            List<QueryDocumentSnapshot> documentsAdministradores = querySnapshotAdministradores.get().getDocuments();

            for (QueryDocumentSnapshot document : documentsAdministradores) {
                Map<String, Object> administradorData = document.getData();
                String rutAdministrador = (String) administradorData.get("Rut");
                String emailAdministrador = (String) administradorData.get("Email");
                String contraseñaAdministrador = (String) administradorData.get("Contraseña");

                if (rutAdministrador.equals(rutIngresado) && emailAdministrador.equals(emailIngresado) && contraseñaAdministrador.equals(contraseñaIngresada)) {
                    System.out.println("Administrador encontrado: " + administradorData);
                    PaginaPrincipal interfazPaginaPrincipal = new PaginaPrincipal(firebaseInstance);
                    interfazPaginaPrincipal.setVisible(true);
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
