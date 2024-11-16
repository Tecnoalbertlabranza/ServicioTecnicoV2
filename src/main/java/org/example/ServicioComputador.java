package org.example;
public class ServicioComputador extends Servicio {
	private int tipoComputadora;
	private int usoComputadora;
	private int lineaDePorcesador;

	public ServicioComputador(double valorServicio, String nombre, int tiempoEstimado, String detalleServicio, int tipoComputadora, int lineaDePorcesador, int usoComputadora) {
		super(valorServicio, nombre, tiempoEstimado, detalleServicio);
		tipoComputadora = tipoComputadora;
		this.lineaDePorcesador = lineaDePorcesador;
		this.usoComputadora = usoComputadora;
	}
}