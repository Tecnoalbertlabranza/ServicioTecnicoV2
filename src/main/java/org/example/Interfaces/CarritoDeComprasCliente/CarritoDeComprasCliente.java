/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.example.Interfaces.CarritoDeComprasCliente;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.example.Cliente;
import org.example.Interfaces.InicioSesion.InicioSesion;
import org.example.ServicioTecnico;
import org.example.firebase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author basty
 */
public class CarritoDeComprasCliente extends javax.swing.JPanel {
    private ServicioTecnico servicioTecnico;
    private firebase firebaseInstance;
    private DefaultTableModel modeloTablaCarrito;
    private Firestore db;
    private double subtotal = 0.0;
    private final double IVA_porcentaje = 0.19;

    /**
     * Creates new form CarritoDeComprasCliente
     */
    public CarritoDeComprasCliente(firebase firebaseInstance, Firestore db,ServicioTecnico servicioTecnico) {
        this.servicioTecnico = servicioTecnico;
        this.firebaseInstance = firebaseInstance;
        this.db = db;
        initComponents();
        configurarTablaCarrito();
        cargarListaDeProductos();
    }

    private void configurarTablaCarrito() {
        modeloTablaCarrito = new DefaultTableModel(new Object[][]{}, new String[]{"Producto", "Cantidad", "PrecioUnitario", "Total"}) {
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
        TablaCarrito.setModel(modeloTablaCarrito);
    }

    private void cargarListaDeProductos() {
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        ListaDeProductos.setModel(modeloLista);

        try {
            List<QueryDocumentSnapshot> productos = db.collection("Registro de Producto").get().get().getDocuments();
            for (QueryDocumentSnapshot doc : productos) {
                String nombre = doc.getString("Nombre");
                double valor = doc.getDouble("Valor");
                int stock = doc.getLong("Stock").intValue();
                modeloLista.addElement(nombre + " - $" + valor + " - Stock: " + stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
        ListaDeProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                   agregarProductoAlCarrito();
                }
            }
        });
    }

    private void agregarProductoAlCarrito(){
        String seleccion = ListaDeProductos.getSelectedValue();
        if(seleccion== null){
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto");
            return;
        }

        String[] partes = seleccion.split(" - ");
        String nombre = partes[0];
        double precio = Double.parseDouble(partes[1].replace("$", ""));
        double stock = Double.parseDouble(partes[2].replace("Stock: ", ""));

        if(stock<=0){
            JOptionPane.showMessageDialog(this, "El producto no tiene stock disponible");
            return;
        }

        boolean productoYaEnCarrito = false;

        for(int i=0;i < modeloTablaCarrito.getRowCount(); i++){
            if(modeloTablaCarrito.getValueAt(i,0).equals(nombre)){
                int cantidad = (int) modeloTablaCarrito.getValueAt(i,1)+1;
                if(cantidad>stock){
                    JOptionPane.showMessageDialog(this, "No hay stock suficiente para agregar más productos");
                    return;
                }
                modeloTablaCarrito.setValueAt(cantidad, i, 1);
                modeloTablaCarrito.setValueAt(precio*cantidad, i, 3);
                productoYaEnCarrito = true;
                break;
            }
        }

        if(!productoYaEnCarrito){
            modeloTablaCarrito.addRow(new Object[]{nombre,1,precio,precio});
        }

        actualizarTotales();
    }

    private void actualizarTotales(){
        subtotal = 0.0;
        for(int i=0; i < modeloTablaCarrito.getRowCount();i++){
            subtotal += (double) modeloTablaCarrito.getValueAt(i,3);
        }
        double iva = subtotal * IVA_porcentaje;
        double total = subtotal + iva;
        txtSubtotal.setText(String.format("$ %.2f", subtotal));
        txtIVA.setText(String.format("$ %.2f", iva));
        txtTotal.setText(String.format("$ %.2f", total));
    }

    private boolean validarRut(String rut) {
        return rut.matches("\\d{7,8}-[\\dkK]");
    }

    public void guardarVenta() {
        String rutCliente = txtRutCliente.getText();
        System.out.println(" rut ingresado"+rutCliente);

        String nombreCliente = txtNombreCliente.getText();
        String apellidoCliente = txtApellidoDelCliente.getText();
        String fechaVenta = txtFecha.getText();
        String totalVenta = txtTotal.getText();
        String totalIva = txtIVA.getText();
        String rut = txtRutCliente.getText();

        if (rutCliente.isEmpty() || nombreCliente.isEmpty() || apellidoCliente.isEmpty() || fechaVenta.isEmpty() || totalVenta.isEmpty() || totalIva.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar el rut de el cliente y obtener sus datos primero.");
            return;
        }

        servicioTecnico.registrarVenta(nombreCliente,apellidoCliente,fechaVenta,totalIva,totalVenta,rutCliente,firebaseInstance);
        JOptionPane.showMessageDialog(null,"Se registro la venta ");
    }


    private String obtenerProductosDelCarrito(){
        StringBuilder productos = new StringBuilder();
        for (int i = 0; i < modeloTablaCarrito.getRowCount(); i++) {
            String nombre = (String) modeloTablaCarrito.getValueAt(i, 0);
            int cantidad = (int) modeloTablaCarrito.getValueAt(i, 1);
            productos.append(nombre).append(" x").append(cantidad).append(", ");
        }
        return productos.toString();
    }

    public void cargarInformacionCliente(){
        String rutIngresado = txtRutCliente.getText();
        Cliente cliente = InicioSesion.getServicioTecnico().obtenerDatosDelCliente(rutIngresado);
        if (cliente != null){
            txtNombreCliente.setText(cliente.getNombre());
            txtApellidoDelCliente.setText(cliente.getApellido());
            txtTelefonoDelCliente.setText(cliente.getTelefono());
            txtEmailDelCliente.setText(cliente.getEmail());
        }else{
            JOptionPane.showMessageDialog(this, "No se encontró el cliente con el RUT ingresado");
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

        jLabel2 = new javax.swing.JLabel();
        txtRutCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaDeProductos = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaCarrito = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        BtnGuardarVenta = new javax.swing.JButton();
        txtSubtotal = new javax.swing.JTextField();
        txtIVA = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        BotonObtenerDatosDelCliente = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtApellidoDelCliente = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTelefonoDelCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtEmailDelCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Ingrese El Rut del cliente para agregar sus compras");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 320, 30));

        txtRutCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutClienteActionPerformed(evt);
            }
        });
        add(txtRutCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 280, 30));

        ListaDeProductos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(ListaDeProductos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 360, 230));

        TablaCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TablaCarrito);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, 540, 320));

        jLabel3.setText("Lista de productos disponibles");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 220, 30));

        jLabel4.setText("Carrito de Compras Del Cliente");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, -1));

        jLabel5.setText("SubTotal de la venta :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 390, 170, 20));

        jLabel6.setText("IVA :");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 450, -1, -1));

        jLabel7.setText("Total");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 510, -1, -1));

        BtnGuardarVenta.setText("Guardar venta");
        BtnGuardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarVentaActionPerformed(evt);
            }
        });
        add(BtnGuardarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 420, 160, 80));

        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });
        add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 390, 180, 30));

        txtIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIVAActionPerformed(evt);
            }
        });
        add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 450, 210, 30));

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });
        add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 510, 200, 30));

        BotonObtenerDatosDelCliente.setText("Obtener Datos Del cliente");
        BotonObtenerDatosDelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonObtenerDatosDelClienteActionPerformed(evt);
            }
        });
        add(BotonObtenerDatosDelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 190, 50));

        jLabel8.setText("Nombre :");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 60, 20));

        txtNombreCliente.setEditable(false);
        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });
        add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 160, 30));

        jLabel9.setText("Apellido :");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        txtApellidoDelCliente.setEditable(false);
        txtApellidoDelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoDelClienteActionPerformed(evt);
            }
        });
        add(txtApellidoDelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 160, 30));

        jLabel10.setText("Telefono:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        txtTelefonoDelCliente.setEditable(false);
        txtTelefonoDelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoDelClienteActionPerformed(evt);
            }
        });
        add(txtTelefonoDelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 160, 30));

        jLabel11.setText("Email :");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        txtEmailDelCliente.setEditable(false);
        txtEmailDelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailDelClienteActionPerformed(evt);
            }
        });
        add(txtEmailDelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, 160, 30));

        jLabel12.setText("fecha para venta");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 440, -1, -1));

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 440, 170, 30));

        jPanel1.setBackground(new java.awt.Color(102, 204, 255));
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 590));
    }// </editor-fold>//GEN-END:initComponents

    private void txtRutClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRutClienteActionPerformed

    private void BtnGuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarVentaActionPerformed
        guardarVenta();

    }//GEN-LAST:event_BtnGuardarVentaActionPerformed

    private void txtIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIVAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIVAActionPerformed

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtApellidoDelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoDelClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoDelClienteActionPerformed

    private void BotonObtenerDatosDelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonObtenerDatosDelClienteActionPerformed
        cargarInformacionCliente();
    }//GEN-LAST:event_BotonObtenerDatosDelClienteActionPerformed

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed

    private void txtTelefonoDelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoDelClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoDelClienteActionPerformed

    private void txtEmailDelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailDelClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailDelClienteActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonObtenerDatosDelCliente;
    private javax.swing.JButton BtnGuardarVenta;
    private javax.swing.JList<String> ListaDeProductos;
    private javax.swing.JTable TablaCarrito;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtApellidoDelCliente;
    private javax.swing.JTextField txtEmailDelCliente;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtRutCliente;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTelefonoDelCliente;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
