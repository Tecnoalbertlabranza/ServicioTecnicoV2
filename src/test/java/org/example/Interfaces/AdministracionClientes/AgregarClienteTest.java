package org.example.Interfaces.AdministracionClientes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class AgregarClienteTest {

    private String nombreValido;
    private String apellidoValido;
    private String telefonoValido;
    private String emailValido;
    private String rutValido;
    private String contrasenaValida;
    private String regionValida;
    private String comunaValida;

    @BeforeEach
    public void setUp() {
        nombreValido = "Lucas";
        apellidoValido = "Henriquez";
        telefonoValido = "987654321";
        emailValido = "lucpro@gmail.com";
        rutValido = "239861278";
        contrasenaValida = "ABCDEf";
        regionValida = "Metropolitana";
        comunaValida = "Santiago";
    }

    @AfterEach
    public void tearDown() {
        nombreValido = null;
        apellidoValido = null;
        telefonoValido = null;
        emailValido = null;
        rutValido = null;
        contrasenaValida = null;
        regionValida = null;
        comunaValida = null;
    }

    @Test
    public void testCampoValido() {
        String resultado = AgregarCliente.validarUnCampo(nombreValido, "Nombre", v -> v.matches("^[A-Z]{1}[a-záéíóúÁÉÍÓÚñÑ]+"));
        assertEquals("", resultado, "Debería devolver un string vacío ya que el nombre es válido");
    }

    @Test
    public void testCampoVacio() {
        String resultado = AgregarCliente.validarUnCampo("", "Nombre", v -> v.matches("^[A-Z]{1}[a-záéíóúÁÉÍÓÚñÑ]+"));
        assertEquals("Nombre no puede estar vacío", resultado, "Debería devolver que el campo 'Nombre' no puede estar vacío");
    }

    @Test
    public void testValidarUnCamposSinErrores() {
        List<String> errores = AgregarCliente.validarCampos(nombreValido,
                apellidoValido, telefonoValido, emailValido,
                rutValido, contrasenaValida, regionValida, comunaValida);
        assertTrue(errores.isEmpty(), "Campos Invalidos");
    }

    @Test
    public void testValidarUnCamposConErrores() {
        List<String> errores = AgregarCliente.validarCampos("juan", "perez", "123", "juan@perez", "123456", "123", "Metropolitana", "Santiago");
        assertFalse(errores.isEmpty(), "Debería haber errores de validación");
        assertTrue(errores.contains("Nombre inválido"), "Debe contener error de nombre");
        assertTrue(errores.contains("Telefono inválido"), "Debe contener error de teléfono");
    }

    @Test
    public void testValidarRutValido() {
        String rut = "12345678K";
        Boolean esValido = AgregarCliente.validarRut(rut);
        assertTrue(esValido, "El RUT debería ser válido");
    }

    @Test
    public void testValidarRutInvalido() {
        String rut = "12345678";
        Boolean esValido = AgregarCliente.validarRut(rut);
        assertFalse(esValido, "El RUT debería ser inválido");
    }
}
