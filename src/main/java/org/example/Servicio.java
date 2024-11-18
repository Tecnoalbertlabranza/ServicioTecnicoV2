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

	public void MostrarValor() {
		System.out.println("Precio: " + valorServicio);
	}

	public void CalcularTiempoEstimado() {

	}

	public void ActualizarValorServicio(int NuevoValorServicio) {
		valorServicio = NuevoValorServicio;
	}

	public void ActualizarNombreServicio(String NuevoNombre) {
		nombre = NuevoNombre;
	}


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre : " + nombre).append("\n")
				.append("Valor: "+valorServicio).append("\n")
				.append("Tiempo estimado: "+tiempoEstimado).append("\n");
		return sb.toString();
	}

	public String miniToString() {
		return "Servicio: " + nombre;
	}
}