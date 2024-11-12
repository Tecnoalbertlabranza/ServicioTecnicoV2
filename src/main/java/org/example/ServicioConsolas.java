package org.example;
public class ServicioConsolas extends Servicio {
	private int ModeloConsola;
	private int MarcaConsola;

	public ServicioConsolas(double valorServicio, String nombre, int tiempoEstimado, String detalleServicio, int modeloConsola, int marcaConsola) {
		super(valorServicio, nombre, tiempoEstimado, detalleServicio);
		ModeloConsola = modeloConsola;
		MarcaConsola = marcaConsola;
	}
}