
package org.example.Interfaces.AdministracionServiciosConsolas;

import org.example.*;
import org.example.Errores.CampoVacioException;
import org.example.Errores.ErrorHandler;
import org.example.Errores.ValorInvalidoException;
import org.example.Interfaces.InicioSesion.InicioSesion;
import javax.swing.*;
import java.util.Optional;


public class AgregarServicioConsola extends javax.swing.JPanel {
    private firebase firebaseInstance;
    private ServicioTecnico servicioTecnico;


    public AgregarServicioConsola(firebase firebaseInstance) {
        this.servicioTecnico = InicioSesion.getServicioTecnico();
        this.firebaseInstance = firebaseInstance;
        initComponents();
    }

    public AgregarServicioConsola() {
    }

    public void AgregarServiciosParaConsolas() {
        String nombre = txtNombre.getText();
        String tiempoEstimado = txtTiempoEstimado.getText();
        String modeloDeConsola = txtModeloDeConsola.getText();
        String marcaConsola = txtMarcaConsola.getText();

        try {
            // Validar que todos los campos estén completos
            if (isAnyFieldEmpty(nombre, tiempoEstimado, modeloDeConsola, marcaConsola)) {
                ErrorHandler.throwCampoVacioException();
                return;
            }

            // Parsear el valor del servicio
            Optional<Double> valorServicioOpt = parseValorServicio(txtValor.getText());

            if (valorServicioOpt.isEmpty()) {
                ErrorHandler.throwValorInvalidoException();
                return;
            }

            double valorServicio = valorServicioOpt.get();

            if (valorServicio <= 0) {
                JOptionPane.showMessageDialog(this, "El valor del servicio debe ser mayor que 0.");
                return;
            }

            servicioTecnico.registrarServicioConsolas(nombre, valorServicio, tiempoEstimado, modeloDeConsola, marcaConsola, firebaseInstance);
            JOptionPane.showMessageDialog(this, "Servicio de consola registrado correctamente");

        } catch (ValorInvalidoException ex) {
            // Manejar el error lanzado por 'throwValorInvalidoException'
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (CampoVacioException ex) {
            // Manejar el error lanzado por 'throwCampoVacioException'
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }


    public boolean isAnyFieldEmpty(String... fields) {
        // Verificar si algún campo está vacío
        for (String field : fields) {
            if (isEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateCampos(String nombre, String tiempoEstimado, String modeloDeConsola, String marcaConsola) {
        return !isEmpty(nombre) && !isEmpty(tiempoEstimado) && !isEmpty(modeloDeConsola) && !isEmpty(marcaConsola);
    }

    public boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public Optional<Double> parseValorServicio(String valor) {
        try {
            return Optional.of(Double.parseDouble(valor));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTiempoEstimado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtModeloDeConsola = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMarcaConsola = new javax.swing.JTextField();
        btnAgregarServicioConsolas = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ingrese los datos para su nuevo servicio para consolas");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 220, 30));

        jLabel3.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Valor");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 220, 30));

        jLabel5.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Tiempo estimado");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 160, 30));

        txtTiempoEstimado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtTiempoEstimado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 220, 30));

        jLabel6.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Modelo De La Consola");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, -1, -1));

        txtModeloDeConsola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtModeloDeConsola, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 220, 30));

        jLabel7.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Marca Consola");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, -1, -1));

        txtMarcaConsola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtMarcaConsola, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 220, 30));

        btnAgregarServicioConsolas.setBackground(new java.awt.Color(204, 204, 255));
        btnAgregarServicioConsolas.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        btnAgregarServicioConsolas.setText("Agregar Servicio");
        btnAgregarServicioConsolas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAgregarServicioConsolas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarServicioConsolasActionPerformed(evt);
            }
        });
        add(btnAgregarServicioConsolas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 180, 50));
    }// </editor-fold>//GEN-END:initComponents


    private void btnAgregarServicioConsolasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServicioConsolasActionPerformed
        AgregarServiciosParaConsolas();
    }//GEN-LAST:event_btnAgregarServicioConsolasActionPerformed
    

    private javax.swing.JButton btnAgregarServicioConsolas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtMarcaConsola;
    private javax.swing.JTextField txtModeloDeConsola;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTiempoEstimado;
    private javax.swing.JTextField txtValor;

}
