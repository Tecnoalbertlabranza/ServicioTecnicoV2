/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.example.Interfaces.InterfazDeCliente;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.*;
import org.example.Interfaces.InicioSesion.InicioSesion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author basty
 */
public class InterfazParaElCliente extends javax.swing.JFrame {
    private ServicioTecnico servicioTecnico;
    private firebase firebaseInstance;

    /**
     * Creates new form InterfazParaElCliente
     */
    public InterfazParaElCliente(firebase firebaseInstance) {
        this.firebaseInstance = firebaseInstance;
        this.servicioTecnico = InicioSesion.getServicioTecnico();
        firebaseInstance.inicializarconexion();
        initComponents();
        servicioTecnico.cargarClientesDesdeFirebase(firebaseInstance);
       cargarProductosDesdeFirebase();
       cargarServicioConsolasDesdeFirebase();
       cargarServiciosComputadorDesdeLaFirebase();
       servicioTecnico.procesarVentasClientes();
        ServiciosParaConsolasCliente servconcli = new ServiciosParaConsolasCliente();
       MostrarPanel(servconcli);
    }



    private void MostrarPanel(JPanel pag ){

        pag.setSize(1100, 420);
        pag.setLocation(0,0);

        PanelClientes.removeAll();
        PanelClientes.add(pag, BorderLayout.CENTER);
        PanelClientes.revalidate();
        PanelClientes.repaint();
    }

    public firebase getFirebaseInstance(){
        return firebaseInstance;
    }

    private void cargarProductosDesdeFirebase(){
        List<Producto> listaProductosLocal = new ArrayList<>();

        try{
            Firestore db = firebaseInstance.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("Registro de Producto").get();
            QuerySnapshot querySnapshot = future.get();

            listaProductosLocal = querySnapshot.getDocuments().stream()
                    .map(document -> new Producto(
                            document.getString("Nombre"),
                            document.getString("Categoría"),
                            document.getDouble("Valor"),
                            document.getLong("Stock").intValue()))
                    .collect(Collectors.toList());
            servicioTecnico.setListaProductos(listaProductosLocal);

            System.out.println("Productos existentes");
            listaProductosLocal.stream().forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al cargar datos"+ e.getMessage());
        }
    }

    private void cargarServicioConsolasDesdeFirebase(){
        List<ServicioConsolas> listaServiciosConsolasLocal = new ArrayList<>();
        try{
            Firestore db = firebaseInstance.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("Registro de servicio consola").get();
            QuerySnapshot querySnapshot = future.get();

            listaServiciosConsolasLocal = querySnapshot.getDocuments().stream()
                    .map(document -> new ServicioConsolas(
                            document.getString("Nombre"),
                            document.getDouble("Valor"),
                            document.getString("TiempoEstimado"),
                            document.getString("ModeloConsola"),
                            document.getString("MarcaConsola")))
                    .collect(Collectors.toList());

            servicioTecnico.setServiciosConsola(listaServiciosConsolasLocal);

            System.out.println("Servicios para consolas existentes");
            listaServiciosConsolasLocal.stream().forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al cargar datos"+ e.getMessage());
        }

    }

    private void cargarServiciosComputadorDesdeLaFirebase(){
        List<ServicioComputador> listaServiciosPCLocal = new ArrayList<>();
        try{
            Firestore db = firebaseInstance.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("Registro de servicio computador").get();
            QuerySnapshot querySnapshot = future.get();

            listaServiciosPCLocal = querySnapshot.getDocuments().stream()
                    .map(document-> new ServicioComputador(
                            document.getString("Nombre"),
                            document.getDouble("Valor"),
                            document.getString("TiempoEstimado"),
                            document.getString("TipoComputadora"),
                            document.getString("LineaDePorcesador"),
                            document.getString("UsoComputadora")))
                    .collect(Collectors.toList());

            servicioTecnico.setServiciosComputador(listaServiciosPCLocal);
            System.out.println("Servicios para pc existentes");
            listaServiciosPCLocal.stream().forEach(System.out::println);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error al cargar datos"+ e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnServiciosConsolas = new javax.swing.JButton();
        btnServiciosParaPc = new javax.swing.JButton();
        btnProductos = new javax.swing.JButton();
        btnComprasHechas = new javax.swing.JButton();
        PanelClientes = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnServiciosConsolas.setText("Servicios Para Consolas");
        btnServiciosConsolas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServiciosConsolasActionPerformed(evt);
            }
        });
        jPanel2.add(btnServiciosConsolas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 160, 40));

        btnServiciosParaPc.setText("Servicios Para Pc");
        btnServiciosParaPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnServiciosParaPcActionPerformed(evt);
            }
        });
        jPanel2.add(btnServiciosParaPc, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 170, 40));

        btnProductos.setText("Productos");
        btnProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosActionPerformed(evt);
            }
        });
        jPanel2.add(btnProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 160, 40));

        btnComprasHechas.setText("Compras Hechas");
        btnComprasHechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprasHechasActionPerformed(evt);
            }
        });
        jPanel2.add(btnComprasHechas, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 180, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 1120, 70));
        getContentPane().add(PanelClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 1100, 420));

        jPanel1.setBackground(new java.awt.Color(0, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bienvenido A El Menu De Su Servicio Tecnico Preferido");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 490, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnServiciosConsolasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiciosConsolasActionPerformed
        ServiciosParaConsolasCliente servconcli = new ServiciosParaConsolasCliente();
        MostrarPanel(servconcli);
    }//GEN-LAST:event_btnServiciosConsolasActionPerformed

    private void btnServiciosParaPcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnServiciosParaPcActionPerformed
        ServiciosParaPcCliente serpccli = new ServiciosParaPcCliente();
        MostrarPanel(serpccli);
    }//GEN-LAST:event_btnServiciosParaPcActionPerformed

    private void btnProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosActionPerformed
       ProductosCliente prodcli = new ProductosCliente();
       MostrarPanel(prodcli);
    }//GEN-LAST:event_btnProductosActionPerformed

    private void btnComprasHechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprasHechasActionPerformed
        ComprasHechasCliente comcli = new ComprasHechasCliente();
        MostrarPanel(comcli);
    }//GEN-LAST:event_btnComprasHechasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazParaElCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazParaElCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazParaElCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazParaElCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                firebase firebaseInstance = new firebase();
                new InterfazParaElCliente(firebaseInstance).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelClientes;
    private javax.swing.JButton btnComprasHechas;
    private javax.swing.JButton btnProductos;
    private javax.swing.JButton btnServiciosConsolas;
    private javax.swing.JButton btnServiciosParaPc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
