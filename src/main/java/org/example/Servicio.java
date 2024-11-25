package org.example;
public abstract class  Servicio {
	private double valorServicio;
	private String tiempoEstimado;
	private String nombre;

	public Servicio(String nombre, double valorServicio, String tiempoEstimado) {
		this.nombre = nombre;
		this.valorServicio = valorServicio;
		this.tiempoEstimado = tiempoEstimado;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre : " + nombre).append("\n")
				.append("Valor: "+valorServicio).append("\n")
				.append("Tiempo estimado: "+tiempoEstimado).append("\n");
		return sb.toString();
	}
}