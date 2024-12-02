
package org.example.Interfaces;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import org.example.Interfaces.AdministracionClientes.Clientes;
import org.example.Interfaces.AdministracionProductos.Productos;
import org.example.Interfaces.AdministracionServiciosConsolas.ServiciosConsolas;
import org.example.Interfaces.AdministracionServiciosPc.ServiciosPc;
import org.example.Interfaces.AdministracionVentas.VentasServicio;
import org.example.Interfaces.CarritoDeComprasCliente.CarritoDeComprasCliente;
import org.example.Interfaces.InicioSesion.InicioSesion;
import org.example.ServicioTecnico;
import org.example.Venta;
import org.example.firebase;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JPanel;


public class PaginaPrincipal extends javax.swing.JFrame {
    private  ServicioTecnico servicioTecnico;
    private  firebase firebaseInstance;

    private void MostrarPanel(JPanel pag ){
        
        pag.setSize(1500, 590);
        pag.setLocation(0,0);
        
        content.removeAll();
        content.add(pag, BorderLayout.CENTER);
        content.revalidate();
        content.repaint(); 
     }
    
    public PaginaPrincipal(firebase firebaseInstance) {
        this.firebaseInstance = firebaseInstance;
        this.servicioTecnico = InicioSesion.getServicioTecnico();
        cargarVentasDesdeFirebase(firebaseInstance);
        firebaseInstance.inicializarconexion();
        initComponents();

        InicioMenu menu = new InicioMenu();
        MostrarPanel(menu);
    }

    public firebase getFirebaseInstance(){
        return firebaseInstance;
    }

    public void cargarVentasDesdeFirebase(firebase firebaseInstance){
        List<Venta> ventasFirebase = new ArrayList<>();
        try {
            Firestore db = firebaseInstance.getFirestore();
            ApiFuture<QuerySnapshot> future = db.collection("Registro De Ventas").get();
            QuerySnapshot querySnapshot = future.get();

           ventasFirebase = querySnapshot.getDocuments().stream()
                    .map(document -> new Venta(

                            document.getString("Fecha De Venta"),
                            document.getString("IVA Impuesto"),
                            document.getString("Total Venta")


                    ))
                            .collect(Collectors.toList());

            servicioTecnico.setListaVentas(ventasFirebase);
            System.out.println("ventas existentes");

           servicioTecnico.getListaVentas().stream()
                   .forEach(System.out :: println);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar datos" + e.getMessage());
        }

    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        jColorChooser2 = new javax.swing.JColorChooser();
        jPanel2 = new javax.swing.JPanel();
        content = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        BotonProductos = new javax.swing.JButton();
        BotonServiciosConsolas = new javax.swing.JButton();
        BotonCerrarSesion = new javax.swing.JButton();
        BotonServiciosPc = new javax.swing.JButton();
        BotonClientes = new javax.swing.JButton();
        BotonCarritoDeCompras = new javax.swing.JButton();
        botonVentas = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1500, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        jPanel2.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1500, 590));

        jPanel1.setBackground(new java.awt.Color(204, 0, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotonProductos.setBackground(new java.awt.Color(102, 153, 255));
        BotonProductos.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        BotonProductos.setForeground(new java.awt.Color(255, 255, 255));
        BotonProductos.setText("Productos");
        BotonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonProductosActionPerformed(evt);
            }
        });
        jPanel1.add(BotonProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 112, 50));

        BotonServiciosConsolas.setBackground(new java.awt.Color(102, 153, 255));
        BotonServiciosConsolas.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        BotonServiciosConsolas.setForeground(new java.awt.Color(255, 255, 255));
        BotonServiciosConsolas.setText("Servicios para consolas");
        BotonServiciosConsolas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonServiciosConsolasActionPerformed(evt);
            }
        });
        jPanel1.add(BotonServiciosConsolas, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, 50));

        BotonCerrarSesion.setBackground(new java.awt.Color(102, 153, 255));
        BotonCerrarSesion.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        BotonCerrarSesion.setForeground(new java.awt.Color(255, 255, 255));
        BotonCerrarSesion.setText("Cerrar Sesion");
        BotonCerrarSesion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarSesionActionPerformed(evt);
            }
        });
        jPanel1.add(BotonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 10, 150, 50));

        BotonServiciosPc.setBackground(new java.awt.Color(102, 153, 255));
        BotonServiciosPc.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        BotonServiciosPc.setForeground(new java.awt.Color(255, 255, 255));
        BotonServiciosPc.setText("Servicios para Pc");
        BotonServiciosPc.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonServiciosPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonServiciosPcActionPerformed(evt);
            }
        });
        jPanel1.add(BotonServiciosPc, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 150, 50));

        BotonClientes.setBackground(new java.awt.Color(102, 153, 255));
        BotonClientes.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        BotonClientes.setForeground(new java.awt.Color(255, 255, 255));
        BotonClientes.setText("Clientes");
        BotonClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonClientesActionPerformed(evt);
            }
        });
        jPanel1.add(BotonClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 100, 50));

        BotonCarritoDeCompras.setBackground(new java.awt.Color(102, 153, 255));
        BotonCarritoDeCompras.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        BotonCarritoDeCompras.setForeground(new java.awt.Color(255, 255, 255));
        BotonCarritoDeCompras.setText("Carrito De Compras");
        BotonCarritoDeCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCarritoDeComprasActionPerformed(evt);
            }
        });
        jPanel1.add(BotonCarritoDeCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 190, 50));

        botonVentas.setBackground(new java.awt.Color(102, 153, 255));
        botonVentas.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        botonVentas.setForeground(new java.awt.Color(255, 255, 255));
        botonVentas.setText("Ventas ");
        botonVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVentasActionPerformed(evt);
            }
        });
        jPanel1.add(botonVentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 150, 50));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1500, 70));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(102, 153, 255));
        jLabel3.setFont(new java.awt.Font("Stencil", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 204));
        jLabel3.setText("Bienvenido a Su Administrdor");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 410, 40));

        jLabel1.setText("Menu Administrador");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 0, 111, 40));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonProductosActionPerformed
        Productos prod = new Productos (firebaseInstance);
        MostrarPanel(prod);
    }//GEN-LAST:event_BotonProductosActionPerformed

    private void BotonServiciosConsolasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonServiciosConsolasActionPerformed
       ServiciosConsolas sercon = new ServiciosConsolas(firebaseInstance);
       MostrarPanel(sercon);
    }//GEN-LAST:event_BotonServiciosConsolasActionPerformed

    private void BotonServiciosPcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonServiciosPcActionPerformed
       ServiciosPc serpc = new ServiciosPc(firebaseInstance);
        MostrarPanel(serpc);
       
    }//GEN-LAST:event_BotonServiciosPcActionPerformed

    private void BotonClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonClientesActionPerformed
        Clientes cli = new Clientes(firebaseInstance);
        MostrarPanel(cli);
    }//GEN-LAST:event_BotonClientesActionPerformed

    private void BotonCarritoDeComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCarritoDeComprasActionPerformed
        CarritoDeComprasCliente carrito = new CarritoDeComprasCliente(firebaseInstance, firebaseInstance.getFirestore(),servicioTecnico);
        MostrarPanel(carrito);
    }//GEN-LAST:event_BotonCarritoDeComprasActionPerformed

    private void botonVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVentasActionPerformed
        VentasServicio venser = new VentasServicio(servicioTecnico);
        MostrarPanel(venser);
    }//GEN-LAST:event_botonVentasActionPerformed

    private void BotonCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCerrarSesionActionPerformed
        this.dispose();

    }//GEN-LAST:event_BotonCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(PaginaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PaginaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PaginaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PaginaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                firebase firebaseInstance = new firebase();
                new PaginaPrincipal(firebaseInstance).setVisible(true);
            }
        });
    }


    private javax.swing.JButton BotonCarritoDeCompras;
    private javax.swing.JButton BotonCerrarSesion;
    private javax.swing.JButton BotonClientes;
    private javax.swing.JButton BotonProductos;
    private javax.swing.JButton BotonServiciosConsolas;
    private javax.swing.JButton BotonServiciosPc;
    private javax.swing.JButton botonVentas;
    private javax.swing.JPanel content;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JColorChooser jColorChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;

}
