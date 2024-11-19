/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.example.Interfaces.CarritoDeComprasCliente;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

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
    private DefaultTableModel modeloTablaCarrito;
    private Firestore db;
    private double subtotal = 0.0;
    private final double IVA_porcentaje = 0.19;

    /**
     * Creates new form CarritoDeComprasCliente
     */
    public CarritoDeComprasCliente(Firestore db) {
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
                double stock = doc.getDouble("Stock");
                modeloLista.addElement(nombre + " - $" + valor + " - Stock: " + stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
        ListaDeProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 1) {
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
        String nombreProducto = partes[0];
        String[] precioYStock = partes[1].split("Stock: ");



        if(precioYStock.length<2 ){
            JOptionPane.showMessageDialog(this, "El producto no tiene stock disponible");
            return;
        }

        String precioString = precioYStock[0].replace("$", "");
        double precio = Double.parseDouble(precioString);
        double stock = Double.parseDouble(precioYStock[1]);

        if(stock<=0){
            JOptionPane.showMessageDialog(this, "El producto no tiene stock disponible");
            return;
        }

        boolean productoYaEnCarrito = false;

        for(int i=0;i < modeloTablaCarrito.getRowCount(); i++){
            if(modeloTablaCarrito.getValueAt(i,0).equals(nombreProducto)){
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
        }if(!productoYaEnCarrito){
            modeloTablaCarrito.addRow(new Object[]{nombreProducto,1,precio,precio});
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

    public void guardarVenta(){
        String rutCliente = txtRutCliente.getText();
        if(!validarRut(rutCliente)){
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un RUT válido");
            return;
        }
        try{
            Map<String, Object> detallesVenta = new HashMap<>();
            detallesVenta.put("RutCliente", rutCliente);
            detallesVenta.put("Subtotal", subtotal);
            detallesVenta.put("IVA", subtotal * IVA_porcentaje);
            detallesVenta.put("Total", subtotal + (subtotal * IVA_porcentaje));
            detallesVenta.put("Productos", obtenerProductosDelCarrito());

            db.collection("Registro De Ventas").add(detallesVenta);
            JOptionPane.showMessageDialog(this, "Venta guardada exitosamente.");
            modeloTablaCarrito.setRowCount(0);
            actualizarTotales();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error al guardar venta: " + e.getMessage());
        }
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





    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
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

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Aqui va el carrito de compras");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 17, 180, 32));

        jLabel2.setText("Ingrese El Rut del cliente para agregar sus compras");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 320, 30));

        txtRutCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutClienteActionPerformed(evt);
            }
        });
        add(txtRutCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 280, 30));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardarVenta;
    private javax.swing.JList<String> ListaDeProductos;
    private javax.swing.JTable TablaCarrito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtIVA;
    private javax.swing.JTextField txtRutCliente;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
