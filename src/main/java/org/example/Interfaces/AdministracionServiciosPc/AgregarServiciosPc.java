
package org.example.Interfaces.AdministracionServiciosPc;

import org.example.Interfaces.InicioSesion.InicioSesion;
import org.example.ServicioTecnico;
import org.example.firebase;


import javax.swing.*;


public class AgregarServiciosPc extends javax.swing.JPanel {
    private firebase firebaseInstance;
    private ServicioTecnico servicioTecnico;



    public AgregarServiciosPc() {

    }
   public void AgregarServicioParaPc(){
       String nombre = txtNombre.getText();
       String tiempoEstimado = txtTiempoEstimado.getText();
       String tipoDeComputadora = txtTipoDeComputador.getText();
       String lineaDeProcesador = txtLineaDeProcesador.getText();
       String usoDeComputadora = txtUsoDeComputadora.getText();
       String valorServicio = txtValorServicioPc.getText();

       // Desde aqui hacia abajo pueden colocar condiciones a los datos ingresados
       if (!esValido(nombre, tiempoEstimado, tipoDeComputadora, lineaDeProcesador, usoDeComputadora, valorServicio)) {
           JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos correctamente");
           return;
       }

       double valorServicioFinal = Double.parseDouble(valorServicio);
       // Hasta aqui pueden agregar condiciones

       servicioTecnico.registrarServicioComputador(nombre,valorServicioFinal,tiempoEstimado,tipoDeComputadora,lineaDeProcesador,usoDeComputadora,firebaseInstance);
       JOptionPane.showMessageDialog(this, "Servicio agregado correctamente");

       limpiarCampos();
   }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtValorServicioPc.setText("");
        txtTiempoEstimado.setText("");
        txtTipoDeComputador.setText("");
        txtLineaDeProcesador.setText("");
        txtUsoDeComputadora.setText("");
    }

    public boolean esValido(String nombre, String tiempoEstimado, String tipoDeComputadora, String lineaDeProcesador, String usoDeComputadora, String valorServicio) {
        return !esVacio(nombre) && !esVacio(tiempoEstimado) && !esVacio(tipoDeComputadora) && !esVacio(lineaDeProcesador) && !esVacio(usoDeComputadora) && esNumero(valorServicio);
    }

    public boolean esVacio(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    public boolean esNumero(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    public AgregarServiciosPc(firebase firebaseInstance){
       this.servicioTecnico = InicioSesion.getServicioTecnico();
       this.firebaseInstance = firebaseInstance;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValorServicioPc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTiempoEstimado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTipoDeComputador = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtUsoDeComputadora = new javax.swing.JTextField();
        btnAgregarServiciosPc = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtLineaDeProcesador = new javax.swing.JTextField();

        setBackground(new java.awt.Color(153, 153, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingrese su nuevo servicio para pc");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 230, 30));

        jLabel3.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Valor Servicio");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        txtValorServicioPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtValorServicioPc, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 230, 30));

        jLabel5.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tiempo Estimado");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        txtTiempoEstimado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtTiempoEstimado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 230, 30));

        jLabel6.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tipo De Computador");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        txtTipoDeComputador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtTipoDeComputador, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 230, 30));

        jLabel7.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Uso de Computadora");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, -1, -1));

        txtUsoDeComputadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtUsoDeComputadora, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 230, 30));

        btnAgregarServiciosPc.setText("Agregar Servicio Pc");
        btnAgregarServiciosPc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarServiciosPcActionPerformed(evt);
            }
        });
        add(btnAgregarServiciosPc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, 150, 40));

        jLabel8.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Linea De Procesador");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 130, 20));
        add(txtLineaDeProcesador, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 230, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarServiciosPcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServiciosPcActionPerformed
        AgregarServicioParaPc();
    }//GEN-LAST:event_btnAgregarServiciosPcActionPerformed



    private javax.swing.JButton btnAgregarServiciosPc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtLineaDeProcesador;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTiempoEstimado;
    private javax.swing.JTextField txtTipoDeComputador;
    private javax.swing.JTextField txtUsoDeComputadora;
    private javax.swing.JTextField txtValorServicioPc;

}
