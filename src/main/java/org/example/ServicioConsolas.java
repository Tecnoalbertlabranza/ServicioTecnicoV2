package org.example;
public class ServicioConsolas extends Servicio {
	public Object add;
	private String modeloConsola;
	private String marcaConsola;

	public ServicioConsolas(String nombre, double valorServicio, String tiempoEstimado, String modeloConsola, String marcaConsola) {
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