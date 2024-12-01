package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;

class ServicioTecnicoTest {
    private ServicioTecnico servicioTecnico;
    private List<Cliente> listaClientes;

    @BeforeEach
    void setUp() {
        listaClientes = new ArrayList<>();
        listaClientes.add(new Cliente("Juan", "Pérez", "123456789", "juan@example.com", "123456789", "Santiago", "Providencia"));
        listaClientes.add(new Cliente("Ana", "González", "987654321", "ana@example.com", "987654321", "Valparaíso", "Viña del Mar"));

        servicioTecnico = new ServicioTecnico("TecnoAlbert Labranza", null, null, null, listaClientes, "Descripción de servicio");
    }

    @AfterEach
    void tearDown() {
        servicioTecnico = null;
    }

    //-------- DATOS CLIENTES --------
    @Test
    public void testObtenerDatosClientes() {
        String rut = "123456789";
        Cliente cliente = servicioTecnico.obtenerDatosDelCliente(rut);

        assertNotNull(cliente);
        assertEquals(rut, cliente.getRut());
        assertEquals("Juan", cliente.getNombre());
    }

    @Test
    public void testObtenerDatosClienteInvalido() {
        String rut = "133456789";
        Cliente cliente = servicioTecnico.obtenerDatosDelCliente(rut);
        assertNull(cliente);
    }
}