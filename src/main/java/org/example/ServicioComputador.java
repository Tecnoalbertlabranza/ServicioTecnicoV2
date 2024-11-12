package org.example;
public class ServicioComputador extends Servicio {
	private int TipoComputadora;
	private int UsoComputadora;
	private int LineaDePorcesador;

	public ServicioComputador(double valorServicio, String nombre, int tiempoEstimado, String detalleServicio, int tipoComputadora, int lineaDePorcesador, int usoComputadora) {
		super(valorServicio, nombre, tiempoEstimado, detalleServicio);
		TipoComputadora = tipoComputadora;
		LineaDePorcesador = lineaDePorcesador;
		UsoComputadora = usoComputadora;
	}
}