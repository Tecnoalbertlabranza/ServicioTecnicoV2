package org.example;
public class ServicioComputador extends Servicio {
	private String tipoComputadora;
	private String usoComputadora;
	private String lineaDePorcesador;

	public ServicioComputador(String nombre, double valorServicio, String tiempoEstimado, String tipoComputadora, String lineaDePorcesador, String usoComputadora) {
		super(nombre, valorServicio, tiempoEstimado);
		this.tipoComputadora = tipoComputadora;
		this.lineaDePorcesador = lineaDePorcesador;
		this.usoComputadora = usoComputadora;
	}

	public String getTipoComputadora() {
		return tipoComputadora;
	}

	public String getUsoComputadora() {
		return usoComputadora;
	}

	public String getLineaDePorcesador() {
		return lineaDePorcesador;
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