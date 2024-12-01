/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.example.Interfaces.CarritoDeComprasCliente;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
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
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

            productos.stream()
                    .map(doc -> {
                        String nombre = doc.getString("Nombre");
                        double valor = doc.getDouble("Valor");
                        int stock = doc.getLong("Stock").intValue();
                        return nombre + " - $" + valor + " - Stock: " + stock;
                    })
                    .forEach(modeloLista::addElement);

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

    private void agregarProductoAlCarrito() {
        String seleccion = ListaDeProductos.getSelectedValue();
        if (seleccion == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un producto");
            return;
        }

        String[] partes = seleccion.split(" - ");
        String nombre = partes[0];
        double precio = Double.parseDouble(partes[1].replace("$", ""));
        int stock = Integer.parseInt(partes[2].replace("Stock: ", ""));

        if (stock <= 0) {
            JOptionPane.showMessageDialog(this, "El producto no tiene stock disponible");
            return;
        }

        OptionalInt index = IntStream.range(0, modeloTablaCarrito.getRowCount())
                .filter(i -> modeloTablaCarrito.getValueAt(i, 0).equals(nombre))
                .findFirst();

        if (index.isPresent()) {
            int i = index.getAsInt();
            int cantidad = (int) modeloTablaCarrito.getValueAt(i, 1) + 1;
            if (cantidad > stock) {
                JOptionPane.showMessageDialog(this, "No hay stock suficiente para agregar más productos");
                return;
            }
            modeloTablaCarrito.setValueAt(cantidad, i, 1);
            modeloTablaCarrito.setValueAt(precio * cantidad, i, 3);
        } else {
            modeloTablaCarrito.addRow(new Object[]{nombre, 1, precio, precio});
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

    public void guardarVenta() {
        String rutCliente = txtRutCliente.getText();
        String nombreCliente = txtNombreCliente.getText();
        String apellidoCliente = txtApellidoDelCliente.getText();
        String fechaVenta = txtFecha.getText();
        String totalVenta = txtTotal.getText();
        String totalIva = txtIVA.getText();

        if (!validarCampos(rutCliente, nombreCliente, apellidoCliente, fechaVenta, totalVenta, totalIva)) {
            return;
        }
        if (!fechaVenta.matches("^([0-2][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}$")){
            JOptionPane.showMessageDialog(this, "Ingresar fecha como [dd-mm-yyyy]");
            return;
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) TablaCarrito.getModel();
        int filas = modeloTabla.getRowCount();

        WriteBatch batch = db.batch();

        List<String[]> productos = IntStream.range(0, filas)
                .mapToObj(i -> new String[]{
                        (String) modeloTabla.getValueAt(i, 0),
                        String.valueOf(modeloTabla.getValueAt(i, 1))
                })
                .collect(Collectors.toList());


        boolean stockSuficiente = productos.stream()
                .allMatch(producto -> {
                    String nombreProducto = producto[0];
                    int cantidadProducto = Integer.parseInt(producto[1]);
                    return actualizarStockProducto(nombreProducto, cantidadProducto, batch);
                });

        if (!stockSuficiente) {
            return;
        }

        registrarVenta(nombreCliente, apellidoCliente, fechaVenta, totalIva, totalVenta, rutCliente, batch);
        JOptionPane.showMessageDialog(null, "Se registró la venta con éxito.");
    }


    private boolean validarCampos(String rutCliente, String nombreCliente, String apellidoCliente,
                                  String fechaVenta, String totalVenta, String totalIva) {
        if (rutCliente.isEmpty() || nombreCliente.isEmpty() || apellidoCliente.isEmpty() ||
                fechaVenta.isEmpty() || totalVenta.isEmpty() || totalIva.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, rellene todos los espacios para continuar.");
            return false;
        }
        return true;
    }

    private boolean actualizarStockProducto(String nombreProducto, int cantidadProducto, WriteBatch batch) {
        try {
            Firestore db = firebaseInstance.getFirestore();
            Query query = db.collection("Registro de Producto").whereEqualTo("Nombre", nombreProducto);
            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot querySnapshot = future.get();

            if (querySnapshot.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontró el producto: " + nombreProducto);
                return false;
            }

            DocumentSnapshot documentoProducto = querySnapshot.getDocuments().get(0);
            int stockActual = documentoProducto.getLong("Stock").intValue();

            if (stockActual >= cantidadProducto) {
                Map<String, Object> datosActualizar = new HashMap<>();
                datosActualizar.put("Stock", stockActual - cantidadProducto);
                batch.update(documentoProducto.getReference(), datosActualizar);

                if (stockActual - cantidadProducto == 0) {
                    batch.delete(documentoProducto.getReference());
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay suficiente stock para el producto: " + nombreProducto);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al reducir stock del producto: " + e.getMessage());
            return false;
        }
        return true;
    }

    private void registrarVenta(String nombreCliente, String apellidoCliente, String fechaVenta,
                                String totalIva, String totalVenta, String rutCliente, WriteBatch batch) {
        try {
            servicioTecnico.registrarVenta(nombreCliente, apellidoCliente, fechaVenta, totalIva, totalVenta, rutCliente, firebaseInstance);
            batch.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al registrar la venta: " + e.getMessage());
        }
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

        setBackground(new java.awt.Color(102, 204, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ingrese El Rut del cliente para agregar sus compras");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 370, 30));

        txtRutCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtRutCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 300, 30));

        ListaDeProductos.setBackground(new java.awt.Color(242, 242, 242));
        ListaDeProductos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(ListaDeProductos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 360, 230));

        TablaCarrito.setBackground(new java.awt.Color(242, 242, 242));
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
        TablaCarrito.setSelectionBackground(new java.awt.Color(153, 153, 255));
        TablaCarrito.setShowGrid(false);
        jScrollPane2.setViewportView(TablaCarrito);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 50, 540, 320));

        jLabel3.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel3.setText("Lista de productos disponibles");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 280, 30));

        jLabel4.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel4.setText("Carrito de Compras Del Cliente");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, -1));

        jLabel5.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel5.setText("SubTotal de la venta :");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 387, 200, -1));

        jLabel6.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel6.setText("IVA :");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 430, -1, 30));

        jLabel7.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel7.setText("Total");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 480, -1, 30));

        BtnGuardarVenta.setBackground(new java.awt.Color(204, 204, 255));
        BtnGuardarVenta.setFont(new java.awt.Font("Stencil", 0, 12)); // NOI18N
        BtnGuardarVenta.setText("Guardar venta");
        BtnGuardarVenta.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnGuardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarVentaActionPerformed(evt);
            }
        });
        add(BtnGuardarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 430, 160, 80));

        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 380, 160, 30));

        txtIVA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 430, 210, 30));

        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 480, 200, 30));

        BotonObtenerDatosDelCliente.setBackground(new java.awt.Color(204, 204, 255));
        BotonObtenerDatosDelCliente.setFont(new java.awt.Font("Stencil", 1, 11)); // NOI18N
        BotonObtenerDatosDelCliente.setText("Obtener Datos Del cliente");
        BotonObtenerDatosDelCliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BotonObtenerDatosDelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonObtenerDatosDelClienteActionPerformed(evt);
            }
        });
        add(BotonObtenerDatosDelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 190, 50));

        jLabel8.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombre :");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 80, 30));

        txtNombreCliente.setEditable(false);
        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 220, 30));

        jLabel9.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Apellido :");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 330, 90, 30));

        txtApellidoDelCliente.setEditable(false);
        txtApellidoDelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtApellidoDelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 330, 220, 30));

        jLabel10.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Telefono:");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 380, 90, 30));

        txtTelefonoDelCliente.setEditable(false);
        txtTelefonoDelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtTelefonoDelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 220, 30));

        jLabel11.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Email :");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 430, 60, 30));

        txtEmailDelCliente.setEditable(false);
        txtEmailDelCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtEmailDelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, 220, 30));

        jLabel12.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel12.setText("fecha para venta:");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, -1, 30));

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            }
        });
        add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, 160, 30));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 590));
    }// </editor-fold>//GEN-END:initComponents



    private void BtnGuardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarVentaActionPerformed
        guardarVenta();
        cargarListaDeProductos();
    }//GEN-LAST:event_BtnGuardarVentaActionPerformed

    private void BotonObtenerDatosDelClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonObtenerDatosDelClienteActionPerformed
        cargarInformacionCliente();
    }//GEN-LAST:event_BotonObtenerDatosDelClienteActionPerformed

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
