package org.example;
public class ServicioConsolas extends Servicio {
	private int modeloConsola;
	private int marcaConsola;

	public ServicioConsolas(String nombre, double valorServicio, int tiempoEstimado, int modeloConsola, int marcaConsola) {
		super(nombre, valorServicio, tiempoEstimado);
		this.modeloConsola = modeloConsola;
		this.marcaConsola = marcaConsola;
	}
}