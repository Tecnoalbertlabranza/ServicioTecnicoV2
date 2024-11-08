
package org.example.Interfaces.AdministracionClientes;

import java.awt.BorderLayout;
import javax.swing.JPanel;


public class Clientes extends javax.swing.JPanel {
     private void MostrarPanelCliente(JPanel pag ){
        
        pag.setSize(810, 410);
        pag.setLocation(0,0);
        
        PanelCliente.removeAll();
        PanelCliente.add(pag, BorderLayout.CENTER);
        PanelCliente.revalidate();
        PanelCliente.repaint(); 
     }


    public Clientes() {
        initComponents();
        
        AgregarCliente menucliente = new AgregarCliente ();
        MostrarPanelCliente(menucliente);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BotonAgregarCliente = new javax.swing.JButton();
        PanelCliente = new javax.swing.JPanel();
        BotonModificarCliente = new javax.swing.JButton();
        BotonEliminarCliente = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Aqui iran la lista de los clientes que debe tener base de datos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 350, 180));

        BotonAgregarCliente.setText("Agregar Cliente");
        BotonAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(BotonAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 330, 120, 30));

        javax.swing.GroupLayout PanelClienteLayout = new javax.swing.GroupLayout(PanelCliente);
        PanelCliente.setLayout(PanelClienteLayout);
        PanelClienteLayout.setHorizontalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );
        PanelClienteLayout.setVerticalGroup(
            PanelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 410, Short.MAX_VALUE)
        );

        jPanel1.add(PanelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 390, 410));

        BotonModificarCliente.setText("Modificar Cliente");
        BotonModificarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonModificarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(BotonModificarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 130, 30));

        BotonEliminarCliente.setText("Eliminar Cliente");
        BotonEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarClienteActionPerformed(evt);
            }
        });
        jPanel1.add(BotonEliminarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 330, 120, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 410));
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarClienteActionPerformed
        // TODO add your handling code here:
    }

    private void BotonModificarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonModificarClienteActionPerformed
        ModificarCliente modcli = new ModificarCliente();
        MostrarPanelCliente(modcli);
    }

    private void BotonEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarClienteActionPerformed
        EliminarCliente elicli = new EliminarCliente ();
        MostrarPanelCliente(elicli);
    }



    private javax.swing.JButton BotonAgregarCliente;
    private javax.swing.JButton BotonEliminarCliente;
    private javax.swing.JButton BotonModificarCliente;
    private javax.swing.JPanel PanelCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;

}
