
package org.example.Interfaces.InterfazDeCliente;
import org.example.Interfaces.InicioSesion.InicioSesion;
import org.example.ServicioConsolas;
import org.example.ServicioTecnico;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ServiciosParaConsolasCliente extends javax.swing.JPanel {
    private ServicioTecnico servicioTecnico;


    public ServiciosParaConsolasCliente() {
        initComponents();
        this.servicioTecnico = InicioSesion.getServicioTecnico();
        cargarServiciosConsolasATabla();
    }

    private void cargarServiciosConsolasATabla() {
        DefaultTableModel modeloTablaServicioConsolas = (DefaultTableModel) TablaServicioConsolasClientes.getModel();
        modeloTablaServicioConsolas.setRowCount(0);

        Collection<ServicioConsolas> servicioConsolasCol = servicioTecnico.getServiciosConsola();
        List<ServicioConsolas> servicioConsolas = new ArrayList<>(servicioConsolasCol);

        servicioConsolas.stream()
                .map(servicio -> new Object[]{
                        servicio.getNombre(),
                        servicio.getTiempoEstimado(),
                        servicio.getValorServicio(),
                        servicio.getModeloConsola(),
                        servicio.getMarcaConsola()
                })
                .forEach(modeloTablaServicioConsolas :: addRow);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaServicioConsolasClientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 255));
        setPreferredSize(new java.awt.Dimension(1100, 420));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Servicios Disiponibles Para Las Consolas");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 360, 40));

        TablaServicioConsolasClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Tiempo Estimado", "Valor Servicio", "Modelo Consola", "Marca Consola"
            }
        ));
        jScrollPane1.setViewportView(TablaServicioConsolasClientes);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 790, 340));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("<html><center>Aqui podra ver todos los servicios, actualizaciones y reparaciones para cualquiera de sus consolas de videojuegos");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 200, 220));
    }// </editor-fold>//GEN-END:initComponents



    private javax.swing.JTable TablaServicioConsolasClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;

}
