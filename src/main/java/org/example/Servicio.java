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

	public double getValorServicio() {
		return valorServicio;
	}

	public void setValorServicio(double valorServicio) {
		this.valorServicio = valorServicio;
	}

	public String getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(String tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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