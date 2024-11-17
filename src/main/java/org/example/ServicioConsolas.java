package org.example;
public class ServicioConsolas extends Servicio {
	private int modeloConsola;
	private int marcaConsola;

	public ServicioConsolas(String nombre, double valorServicio, int tiempoEstimado, int modeloConsola, int marcaConsola) {
		super(nombre, valorServicio, tiempoEstimado);
		this.modeloConsola = modeloConsola;
		this.marcaConsola = marcaConsola;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append(super.toString()).append("\n")
		.append("Modelo Consola: ").append(modeloConsola).append("\n")
		.append("Marca Consola: ").append(marcaConsola).append("\n");

		return sb.toString();
	}
}