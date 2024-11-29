package org.example.Interfaces.AdministracionServiciosPc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgregarServiciosPcTest {
    private AgregarServiciosPc agregarServiciosPc;

    @BeforeEach
    void setUp() {
        agregarServiciosPc = new AgregarServiciosPc();
    }

    @AfterEach
    void tearDown() {
        agregarServiciosPc = null;
    }

    //------- ES VALIDO -------
    @Test
    public void testEsValidoValido() {
        assertTrue(agregarServiciosPc.esValido("Nombre", "10", "Laptop", "Intel", "Oficina", "100.0"));
    }

    @Test
    public void testEsValidoInvalido() {
        assertFalse(agregarServiciosPc.esValido("", "10", "", "Intel", "", "100.0"));
    }

    //------- ES VACIO -------
    @Test
    void testEsVacio_ValorVacio() {
        assertTrue(agregarServiciosPc.esVacio(" "));
    }

    @Test
    void testEsVacio_ValorNoVacio() {
        assertFalse(agregarServiciosPc.esVacio("Texto"));
    }

    //------- ES NUMERO -------
    @Test
    void testEsNumero_ValorNumerico() {
        assertTrue(agregarServiciosPc.esNumero("100.0"));
    }

    @Test
    void testEsNumero_ValorNoNumerico() {
        assertFalse(agregarServiciosPc.esNumero("texto"));
    }
}