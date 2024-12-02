package org.example.Errores;

import javax.swing.*;

public class ErrorHandler {

    public static void handleValorServicioError() {
        JOptionPane.showMessageDialog(null, "Por favor, ingrese un valor numérico en el campo Valor");
    }

    public static void handleCampoVacioError() {
        JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos");
    }

    public static void throwCampoVacioException() throws CampoVacioException {
        throw new CampoVacioException("Los campos no pueden estar vacíos o contener valores incorrectos");
    }

    public static void throwValorInvalidoException() throws ValorInvalidoException {
        throw new ValorInvalidoException("El valor ingresado no es válido. Debe ser un número.");
    }
}
