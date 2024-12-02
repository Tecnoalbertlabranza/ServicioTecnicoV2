package org.example.Errores;

public class ErrorHandler {

    public static void throwCampoVacioException() throws CampoVacioException {
        throw new CampoVacioException("Los campos no pueden estar vacíos o contener valores incorrectos");
    }

    public static void throwValorInvalidoException() throws ValorInvalidoException {
        throw new ValorInvalidoException("El valor ingresado no es válido. Debe ser un número.");
    }
}
