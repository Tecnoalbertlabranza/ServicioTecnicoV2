package org.example;
public class Descuentos {
	private String TipoDescuento;
	private String FechaInicio;
	private double ValorDescuento;
	private String FechaFin;

	public Descuentos(String tipoDescuento, String fechaInicio, double valorDescuento, String fechaFin) {
		TipoDescuento = tipoDescuento;
		FechaInicio = fechaInicio;
		ValorDescuento = valorDescuento;
		FechaFin = fechaFin;
	}
}