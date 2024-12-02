
package org.example.Interfaces.InterfazDeCliente;
import org.example.Interfaces.InicioSesion.InicioSesion;
import org.example.ServicioComputador;
import org.example.ServicioTecnico;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ServiciosParaPcCliente extends javax.swing.JPanel {
    private ServicioTecnico servicioTecnico;


    public ServiciosParaPcCliente() {
        initComponents();
        this.servicioTecnico = InicioSesion.getServicioTecnico();
        cargarServiciosParaPcATabla();
    }

    private void cargarServiciosParaPcATabla() {
        DefaultTableModel modeloTablaServicioParaPc = (DefaultTableModel) TablaServicioParaPc.getModel();
        modeloTablaServicioParaPc.setRowCount(0);

        Collection<ServicioComputador> servicioParaPcCol = servicioTecnico.getServiciosComputador();
        List<ServicioComputador> servicioParaPc = new ArrayList<>(servicioParaPcCol);

        servicioParaPc.stream()
                .map(
                        servicio -> new Object[]{
                                servicio.getNombre(),
                                servicio.getValorServicio(),
                                servicio.getTiempoEstimado(),
                                servicio.getTipoComputadora(),
                                servicio.getLineaDePorcesador(),
                                servicio.getUsoComputadora()
                        })
                .forEach(modeloTablaServicioParaPc :: addRow);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaServicioParaPc = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 255));
        setPreferredSize(new java.awt.Dimension(1100, 420));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Servicios Para Pc Disponible");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 280, 40));

        TablaServicioParaPc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Valor", "Tiempo Estimado", "Tipo De Computadora","Linea De Procesador", "Uso De Computadora"
            }
        ));
        jScrollPane1.setViewportView(TablaServicioParaPc);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 780, 350));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("<html><center>En este menu usted podra ver todos los servicios, actualizacines , modificaciones y reparaciones disponibles para su computadora , recuerde si busca alguno de los servicios de la lista no dude en dirigirse al servicio tecnico");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 250, 280));
    }// </editor-fold>//GEN-END:initComponents

    private javax.swing.JTable TablaServicioParaPc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;

}
