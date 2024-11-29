package org.example.Interfaces.AdministracionServiciosConsolas;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgregarServicioConsolaTest {

    AgregarServicioConsola agregarServicioConsola;

    @BeforeEach
    void setUp() {
        agregarServicioConsola = new AgregarServicioConsola();
    }

    @AfterEach
    void tearDown() {
        agregarServicioConsola = null;
    }

    @Test
    void testIsEmpty_conCadenaVacia() {
        String str = "";
        boolean resultado = agregarServicioConsola.isEmpty(str);
        assertTrue(resultado, "El método debería devolver true cuando la cadena está vacía.");
    }

    @Test
    public void Test (){
        String nombre = "Limpieza";
        String tiempoEstimado = "10 dias";
        String modeloDeConsola = "PS5";
        String marcaConsola = "Sony";

        boolean resultado = agregarServicioConsola.validateCampos(nombre, tiempoEstimado, modeloDeConsola, marcaConsola);
        assertTrue(resultado, "Error en ingreso de datos");
    }

    @Test
    void testValidateCampos_conCampoVacio() {
        String nombre = "Revicion";
        String tiempoEstimado = "";
        String modeloDeConsola = "PS5";
        String marcaConsola = "Sony";

        boolean resultado = agregarServicioConsola.validateCampos(nombre, tiempoEstimado, modeloDeConsola, marcaConsola);
        assertFalse(resultado, "El método debería devolver false cuando al menos un campo es vacío.");
    }

    @Test
    void testParseValorServicio_conValorValido() {
        String valor = "10.5";
        Optional<Double> resultado = agregarServicioConsola.parseValorServicio(valor);

        assertTrue(resultado.isPresent(), "El método debería devolver un valor presente para un valor numérico válido.");
        assertEquals(10.5, resultado.get(), "El valor devuelto debería ser el mismo que el valor parseado.");
    }

    @Test
    void testParseValorServicio_conValorNoValido() {
        String valor = "abc";
        Optional<Double> resultado = agregarServicioConsola.parseValorServicio(valor);

        assertFalse(resultado.isPresent(), "El método debería devolver un valor vacío para un valor no numérico.");
    }
}