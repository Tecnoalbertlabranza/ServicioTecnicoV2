package org.example;

import static org.junit.jupiter.api.Assertions.*;
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

        listaClientes.add(new Cliente("Juan", "Pérez", "123456789", "juan@example.com", "12345678-9", "Santiago", "Providencia"));
        listaClientes.add(new Cliente("Ana", "González", "987654321", "ana@example.com", "98765432-1", "Valparaíso", "Viña del Mar"));

        servicioTecnico = new ServicioTecnico("TecnoAlbert Labranza", null, null, null, listaClientes, "Descripción de servicio");
    }

    @Test
    public void testObtenerDatosClientes() {
        String rut = "12345678-9";

        Cliente cliente = servicioTecnico.obtenerDatosDelCliente(rut);

        assertNotNull(cliente);
        assertEquals(rut, cliente.getRut());
        assertEquals("Juan", cliente.getNombre());
    }
}