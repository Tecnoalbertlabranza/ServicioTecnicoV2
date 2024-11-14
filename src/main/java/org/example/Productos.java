package org.example;
public class Productos {
	private String nombreProducto;
	private String categoriaProducto;
	private double valorProducto;
	private int stockProducto;

	public Productos(String nombreProducto, String categoriaProducto, double valorProducto, int stockProducto) {
		this.nombreProducto = nombreProducto;
		this.categoriaProducto = categoriaProducto;
		this.valorProducto = valorProducto;
		this.stockProducto = stockProducto;
	}

	public String toMiniString() {
		return "Producto= " + nombreProducto;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NombreProducto: " + nombreProducto).append("\n")
		.append("CategoriaProducto: " + categoriaProducto).append("\n")
		.append("valorProducto: " + valorProducto).append("\n")
		.append("stockProducto: " + stockProducto);
		return sb.toString();
	}
}