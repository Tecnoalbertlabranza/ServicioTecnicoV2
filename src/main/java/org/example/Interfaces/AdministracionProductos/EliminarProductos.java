
package org.example.Interfaces.AdministracionProductos;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.firebase;

import javax.swing.*;
import java.util.concurrent.ExecutionException;


public class EliminarProductos extends javax.swing.JPanel {
    private firebase firebaseInstance;
    private Productos productos;


    public EliminarProductos(firebase firebaseInstance) {
        this.firebaseInstance =  firebaseInstance;
        this.productos = new Productos(firebaseInstance);
        initComponents();
    }

    public void eliminarProductoPorNombre(String nombre) {
        try {
            Firestore db = firebaseInstance.getFirestore();
            CollectionReference collectionRef = db.collection("Registro de Producto");
            ApiFuture<QuerySnapshot> query = collectionRef.whereEqualTo("Nombre", nombre).get();
            QuerySnapshot querySnapshot = query.get();
            System.out.println("Consultando productos con nombre: " + nombre);
    
            if (!querySnapshot.isEmpty()) {
                querySnapshot.getDocuments().stream()
                        .map(QueryDocumentSnapshot::getReference)
                        .forEach(documentRef -> {
                            try {
                                documentRef.delete().get();
                                System.out.println("Producto con nombre " + nombre + " eliminado de la Firebase");
                            } catch (InterruptedException | ExecutionException e) {
                                e.printStackTrace();
                                System.err.println("Error durante la operación: " + e.getMessage());
                            }
                        });
                JOptionPane.showMessageDialog(null, "Producto con nombre " + nombre + " eliminado de la Firebase");
            } else {
                JOptionPane.showMessageDialog(null, "No hay productos con nombre " + nombre + " en la Firebase");
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
        txtEliminarProducto = new javax.swing.JTextField();
        btnEliminarProducto = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("eliminador de productos");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingrese el nombre de el producto que desee eliminar");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, 20));

        txtEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEliminarProductoActionPerformed(evt);
            }
        });
        add(txtEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 380, 70));

        btnEliminarProducto.setBackground(new java.awt.Color(204, 204, 255));
        btnEliminarProducto.setFont(new java.awt.Font("Stencil", 1, 13)); // NOI18N
        btnEliminarProducto.setText("Eliminar Producto");
        btnEliminarProducto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 200, 70));
    }// </editor-fold>//GEN-END:initComponents

    private void txtEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEliminarProductoActionPerformed

    }//GEN-LAST:event_txtEliminarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        String nombre = txtEliminarProducto.getText();
        if(nombre.isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe ingresar el nombre del producto");
            return;
        }
        eliminarProductoPorNombre(nombre);
    }//GEN-LAST:event_btnEliminarProductoActionPerformed



    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtEliminarProducto;

}
