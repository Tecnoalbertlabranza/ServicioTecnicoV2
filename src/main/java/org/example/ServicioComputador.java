package org.example;
public class ServicioComputador extends Servicio {
	private int tipoComputadora;
	private int usoComputadora;
	private int lineaDePorcesador;

	public ServicioComputador(String nombre, double valorServicio, int tiempoEstimado, int tipoComputadora, int lineaDePorcesador, int usoComputadora) {
		super(nombre, valorServicio, tiempoEstimado);
		this.tipoComputadora = tipoComputadora;
		this.lineaDePorcesador = lineaDePorcesador;
		this.usoComputadora = usoComputadora;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\n")
		.append("Tipo Computadora: ").append(tipoComputadora).append("\n")
		.append("Uso Computadora: ").append(usoComputadora).append("\n");

		return sb.toString();
	}
}