/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.example.Interfaces.AdministracionClientes;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.firebase;
import javax.swing.*;
import java.util.Optional;
import java.util.concurrent.ExecutionException;


public class EliminarCliente extends javax.swing.JPanel {
    private Clientes clientes;
    private firebase firebaseInstance;


    public EliminarCliente(firebase firebaseInstance) {
        this.firebaseInstance = firebaseInstance;
        this.clientes = new Clientes(firebaseInstance);
        initComponents();
    }

    private void eliminarClientePorRut(String rut) {
        Firestore db = firebaseInstance.getFirestore();
        CollectionReference collectionRef = db.collection("Registro De Clientes");
        
        try {
           ApiFuture<QuerySnapshot> query = collectionRef.whereEqualTo("Rut", rut).get();
           QuerySnapshot querySnapshot = query.get();

           System.out.println("Consultando clientes con RUT: " + rut);

           Optional.of(querySnapshot.getDocuments())
                   .filter(document -> !document.isEmpty())
                   .ifPresentOrElse(
                           document -> {
                               document.stream()
                                       .map(QueryDocumentSnapshot::getReference)
                                       .forEach(ref -> {
                                           try {
                                               ref.delete().get();
                                           } catch (InterruptedException | ExecutionException e) {
                                               e.printStackTrace();
                                               System.err.println("Error al eliminar documento: " + e.getMessage());
                                           }
                                       });
                               JOptionPane.showMessageDialog(null, "Cliente con RUT " + rut + " eliminado de Firebase");
                               System.out.println("Cliente con RUT " + rut + " eliminado de Firebase");
                           },
                           () -> {
                               JOptionPane.showMessageDialog(null, "No hay clientes con RUT " + rut + " en Firebase");
                               System.out.println("No se encontró un cliente con RUT: " + rut);
                           }
                   );
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
        txtEliminarClientePorRut = new javax.swing.JTextField();
        BtnEliminarCliente = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Aqui se eliminaran los clientes");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingrese el rut de el cliente que decee eliminar");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, 20));

        txtEliminarClientePorRut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {}
        });
        add(txtEliminarClientePorRut, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 290, 30));

        BtnEliminarCliente.setBackground(new java.awt.Color(204, 204, 255));
        BtnEliminarCliente.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        BtnEliminarCliente.setText("Eliminar cliente");
        BtnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarClienteActionPerformed(evt);
            }
        });
        add(BtnEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 210, 80));
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarClienteActionPerformed
         String rut = txtEliminarClientePorRut.getText().trim();
         if(rut.isEmpty()){
             JOptionPane.showMessageDialog(null, "Debe ingresar el rut del cliente");
             return;
         }
         eliminarClientePorRut(rut);

    }//GEN-LAST:event_BtnEliminarClienteActionPerformed


    private javax.swing.JButton BtnEliminarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtEliminarClientePorRut;

}
