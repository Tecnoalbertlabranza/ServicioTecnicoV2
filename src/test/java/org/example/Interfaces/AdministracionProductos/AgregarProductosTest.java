package org.example.Interfaces.AdministracionProductos;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgregarProductosTest {

    AgregarProductos producto;

    @BeforeEach
    void setUp() {
        producto = new AgregarProductos();
    }

    @AfterEach
    void tearDown() {
        producto = null;
    }

    //-------- PARSEAR_DOUBLE --------
    @Test
    void testParsearDouble_conValorValido() {
        String texto = "123.45";
        String campo = "Precio";

        double resultado = producto.parsearDouble(texto, campo);

        assertEquals(123.45, resultado, "El valor debe ser correctamente convertido a double.");
    }

    @Test
    void testParsearDouble_conValorInvalido() {
        String texto = "abc";
        String campo = "Precio";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            producto.parsearDouble(texto, campo);
        });

        assertEquals("Por favor, ingrese un valor numérico válido en el campo Precio", thrown.getMessage());
    }

    //-------- VALIDAR TODOS LOS CAMPOS --------
    @Test
    void testValidarCampos_conCamposValidos() {
        String nombre = "Producto";
        String categoria = "Electrónica";
        double valor = 100.0;
        double stock = 10.0;

        assertDoesNotThrow(() -> producto.validarCampos(nombre, categoria, valor, stock),
                "No debe lanzar excepción si todos los campos son válidos.");
    }

    @Test
    void testValidarCampos_conCampoNombreVacio() {
        String nombre = "";
        String categoria = "Electrónica";
        double valor = 100.0;
        double stock = 10.0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            producto.validarCampos(nombre, categoria, valor, stock);
        });

        assertTrue(thrown.getMessage().contains("Nombre"), "Debe lanzar excepción de nombre vacío.");
    }

    @Test
    void testValidarCampos_conValorNegativo() {
        String nombre = "Producto";
        String categoria = "Electrónica";
        double valor = -1.0;
        double stock = 10.0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            producto.validarCampos(nombre, categoria, valor, stock);
        });

        assertTrue(thrown.getMessage().contains("El Valor debe ser mayor a cero"),
                "Debe lanzar excepción si el valor es negativo.");
    }

    @Test
    void testValidarCampos_conStockNegativo() {
        String nombre = "Producto";
        String categoria = "Electrónica";
        double valor = 100.0;
        double stock = -5.0;

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            producto.validarCampos(nombre, categoria, valor, stock);
        });

        assertTrue(thrown.getMessage().contains("El Stock debe ser mayor a cero"),
                "Debe lanzar excepción si el stock es negativo.");
    }

    //-------- TEXTO --------
    @Test
    void testValidarTexto_conTextoValido() {
        String texto = "Producto";
        String mensajeError = "Nombre";
        boolean soloLetras = true;

        Optional<String> resultado = producto.validarTexto(texto, mensajeError, soloLetras);

        assertFalse(resultado.isPresent(), "El texto es válido, no debería haber error.");
    }

    @Test
    void testValidarTexto_conTextoVacio() {
        String texto = "";
        String mensajeError = "Nombre";
        boolean soloLetras = true;

        Optional<String> resultado = producto.validarTexto(texto, mensajeError, soloLetras);

        assertTrue(resultado.isPresent(), "El texto vacío debería generar un mensaje de error.");
        assertEquals("Nombre", resultado.get(), "El mensaje de error debe ser 'Nombre'.");
    }

    @Test
    void testValidarTexto_conTextoConNumeros() {
        String texto = "1Producto23";
        String mensajeError = "Nombre";
        boolean soloLetras = true;

        Optional<String> resultado = producto.validarTexto(texto, mensajeError, soloLetras);

        assertTrue(resultado.isPresent(), "El texto con números al pricipio no debería ser válido.");
        assertEquals("El texto solo puede contener letras en el campo: Nombre", resultado.get(),
                "El error debe ser sobre la presencia de números en el nombre.");
    }

    //-------- VALIDAR NUMERO --------
    @Test
    void testValidarNumero_conNumeroPositivo() {
        double valor = 10.0;
        String mensajeError = "El valor debe ser mayor a cero";

        Optional<String> resultado = producto.validarNumero(valor, mensajeError);

        assertFalse(resultado.isPresent(), "El número positivo no debe generar error.");
    }

    @Test
    void testValidarNumero_conNumeroNegativo() {
        double valor = -5.0;
        String mensajeError = "El valor debe ser mayor a cero";

        Optional<String> resultado = producto.validarNumero(valor, mensajeError);

        assertTrue(resultado.isPresent(), "El número negativo debe generar un mensaje de error.");
        assertEquals(mensajeError, resultado.get(), "El mensaje de error debe ser 'El valor debe ser mayor a cero'.");
    }

    @Test
    void testValidarNumero_conNumeroCero() {
        double valor = 0.0;
        String mensajeError = "El valor debe ser mayor a cero";

        Optional<String> resultado = producto.validarNumero(valor, mensajeError);

        assertTrue(resultado.isPresent(), "El valor cero debe generar un mensaje de error.");
        assertEquals(mensajeError, resultado.get(), "El mensaje de error debe ser 'El valor debe ser mayor a cero'.");
    }
}