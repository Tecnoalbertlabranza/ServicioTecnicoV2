package org.example.Interfaces.InicioSesion;
import org.example.Main;
import org.example.firebase;

import java.util.HashMap;
import java.util.Map;

public class IncioDeSesion extends javax.swing.JFrame {
    public IncioDeSesion() {
        firebase f1 = new firebase();
        f1.inicializarconexion();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        Background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CasillaContraseña = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CasillaNombreUsuario = new javax.swing.JTextField();
        BotonIniciarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Logo");
        Background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 90, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel2.setText("Iniciar Sesion");
        Background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 140, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña");
        Background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 80, 20));

        CasillaContraseña.setBorder(null);
        CasillaContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CasillaContraseñaActionPerformed(evt);
            }
        });
        Background.add(CasillaContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 380, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ingrese su Nombre");
        Background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 190, 30));

        CasillaNombreUsuario.setBorder(null);
        CasillaNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CasillaNombreUsuarioActionPerformed(evt);
            }
        });
        Background.add(CasillaNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 380, 30));

        BotonIniciarSesion.setText("Iniciar Sesion");
        BotonIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonIniciarSesionActionPerformed(evt);
            }
        });
        Background.add(BotonIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 150, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }
    private void CasillaContraseñaActionPerformed(java.awt.event.ActionEvent evt) {

    }
    private void CasillaNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {

    }
    private void BotonIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {

    }

    public static void main(String args[]) {






































        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IncioDeSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IncioDeSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IncioDeSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IncioDeSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IncioDeSesion().setVisible(true);
            }
        });
    }

    private javax.swing.JPanel Background;
    private javax.swing.JButton BotonIniciarSesion;
    private javax.swing.JTextField CasillaContraseña;
    private javax.swing.JTextField CasillaNombreUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;

}

