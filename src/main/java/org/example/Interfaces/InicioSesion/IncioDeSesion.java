package org.example.Interfaces.InicioSesion;
import org.example.Main;
import org.example.Servicio;
import org.example.ServicioTecnico;
import org.example.firebase;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IncioDeSesion extends JFrame {
    ServicioTecnico s1 = new ServicioTecnico();
    public IncioDeSesion() {
        firebase f1 = new firebase();
        f1.inicializarconexion();
        s1.RegistrarCliente("juanito","perez","labranza");
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        Background = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        CasillaContraseña = new JTextField();
        jLabel4 = new JLabel();
        CasillaNombreUsuario = new JTextField();
        BotonIniciarSesion = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Background.setLayout(new AbsoluteLayout());

        jLabel1.setFont(new Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Logo");
        Background.add(jLabel1, new AbsoluteConstraints(110, 60, 90, 50));

        jLabel2.setFont(new Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel2.setText("Iniciar Sesion");
        Background.add(jLabel2, new AbsoluteConstraints(110, 140, 140, 40));

        jLabel3.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña");
        Background.add(jLabel3, new AbsoluteConstraints(110, 280, 80, 20));

        CasillaContraseña.setBorder(null);
        CasillaContraseña.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CasillaContraseñaActionPerformed(evt);
            }
        });
        Background.add(CasillaContraseña, new AbsoluteConstraints(110, 320, 380, 30));

        jLabel4.setFont(new Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Ingrese su Nombre");
        Background.add(jLabel4, new AbsoluteConstraints(110, 190, 190, 30));

        CasillaNombreUsuario.setBorder(null);
        CasillaNombreUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CasillaNombreUsuarioActionPerformed(evt);
            }
        });
        Background.add(CasillaNombreUsuario, new AbsoluteConstraints(110, 220, 380, 30));

        BotonIniciarSesion.setText("Iniciar Sesion");
        BotonIniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                BotonIniciarSesionActionPerformed(evt);
            }
        });
        Background.add(BotonIniciarSesion, new AbsoluteConstraints(240, 400, 150, 60));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Background, GroupLayout.PREFERRED_SIZE, 841, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Background, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }
    private void CasillaContraseñaActionPerformed(ActionEvent evt) {

    }
    private void CasillaNombreUsuarioActionPerformed(ActionEvent evt) {

    }
    private void BotonIniciarSesionActionPerformed(ActionEvent evt) {

    }

    public static void main(String args[]) {






































        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IncioDeSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(IncioDeSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(IncioDeSesion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(IncioDeSesion.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IncioDeSesion().setVisible(true);
            }
        });
    }

    private JPanel Background;
    private JButton BotonIniciarSesion;
    private JTextField CasillaContraseña;
    private JTextField CasillaNombreUsuario;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;

}

