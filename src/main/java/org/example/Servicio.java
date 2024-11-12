package org.example;
public abstract class  Servicio {
	private double valorServicio;
	private String detalleServicio;
	private int tiempoEstimado;
	private String nombre;

	public Servicio(double valorServicio, String nombre, int tiempoEstimado, String detalleServicio) {
		this.valorServicio = valorServicio;
		this.nombre = nombre;
		this.tiempoEstimado = tiempoEstimado;
		this.detalleServicio = detalleServicio;
	}

	public void MostrarValor() {

	}

	public void CalcularTiempoEstimado() {

	}

	public void ActualizarValorServicio(int NuevoValorServicio) {
		valorServicio = NuevoValorServicio;
	}

	public void ActualizarDetallesServicio(String NuevoDetallesServicio) {
		detalleServicio = NuevoDetallesServicio;
	}
	public void ActualizarNombreServicio(String NuevoNombre) {
		nombre = NuevoNombre;
	}
	
	public void EliminarServicio() {

	}

	@Override
	public String toString() {
		return "Servicio: " + nombre;
	}
}