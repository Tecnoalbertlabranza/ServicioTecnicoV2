
package org.example.Interfaces.AdministracionServiciosConsolas;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.firebase;

import javax.swing.*;
import java.util.concurrent.ExecutionException;


public class EliminarServicioConsola extends javax.swing.JPanel {
    private firebase firebaseInstance;
    private ServiciosConsolas serviciosConsolas;


    public EliminarServicioConsola(firebase firebaseInstance) {
        this.firebaseInstance = firebaseInstance;
        this.serviciosConsolas = new ServiciosConsolas(firebaseInstance);
        initComponents();
    }

    public void eliminarServicioConsolaConNombre(String nombre){
        try {
            Firestore db = firebaseInstance.getFirestore();
            CollectionReference collectionRef = db.collection("Registro de servicio consola");
            ApiFuture<QuerySnapshot> query = collectionRef.whereEqualTo("Nombre", nombre).get();
            QuerySnapshot querySnapshot = query.get();
            System.out.println("Consultando Servicios para consola con nombre : " + nombre);

            if (!querySnapshot.isEmpty()) {
                querySnapshot.getDocuments().stream()
                        .map(QueryDocumentSnapshot::getReference)
                        .forEach(documentRef -> {
                            try {
                                documentRef.delete().get();
                                System.out.println("Servicios consola con nombre  " + nombre + " eliminado de la Firebase");
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                                System.err.println("Error durante la operación: " + e.getMessage());
                            }
                        });
                JOptionPane.showMessageDialog(null, "Servicios consola con nombre" + nombre + " eliminado de la Firebase");
            } else {
                JOptionPane.showMessageDialog(null, "No hay Servicios consola con nombre " + nombre + " en la Firebase");
                System.out.println("No se encontró un producto con nombre " + nombre);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.err.println("Error durante la operación: " + e.getMessage());
        }

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombreServicioConEliminar = new javax.swing.JTextField();
        btnEliminarServicio = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Aqui se eliminaran servicios consolas");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingrese El nombre de el servicio de consolas que desee eliminar");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));
        add(txtNombreServicioConEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 400, 60));

        btnEliminarServicio.setBackground(new java.awt.Color(204, 204, 255));
        btnEliminarServicio.setFont(new java.awt.Font("Stencil", 1, 13)); // NOI18N
        btnEliminarServicio.setText("Eliminar Servicio");
        btnEliminarServicio.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarServicioActionPerformed(evt);
            }
        });
        add(btnEliminarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 160, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarServicioActionPerformed
       String nombre = txtNombreServicioConEliminar.getText();
       if(nombre.isEmpty()){
           JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de servicio de consola");
           return;
       }
       eliminarServicioConsolaConNombre(nombre);

    }//GEN-LAST:event_btnEliminarServicioActionPerformed



    private javax.swing.JButton btnEliminarServicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtNombreServicioConEliminar;

}
