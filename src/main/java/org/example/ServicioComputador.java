package org.example;
public class ServicioComputador extends Servicio {
	private int tipoComputadora;
	private int usoComputadora;
	private int lineaDePorcesador;

	public ServicioComputador(double valorServicio, String nombre, int tiempoEstimado, int tipoComputadora, int lineaDePorcesador, int usoComputadora) {
		super(valorServicio, nombre, tiempoEstimado);
		this.tipoComputadora = tipoComputadora;
		this.lineaDePorcesador = lineaDePorcesador;
		this.usoComputadora = usoComputadora;
	}
}