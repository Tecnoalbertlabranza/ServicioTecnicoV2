package org.example;
public class ServicioConsolas extends Servicio {
	private int modeloConsola;
	private int marcaConsola;

	public ServicioConsolas(double valorServicio, String nombre, int tiempoEstimado, String detalleServicio, int modeloConsola, int marcaConsola) {
		super(valorServicio, nombre, tiempoEstimado, detalleServicio);
		this.modeloConsola = modeloConsola;
		marcaConsola = marcaConsola;
	}
}