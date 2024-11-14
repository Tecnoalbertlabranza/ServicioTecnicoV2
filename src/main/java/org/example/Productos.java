package org.example;
public class Productos {
	private String NombreProducto;
	private String CategoriaProducto;
	private double ValorProducto;
	private int StockProducto;

	public Productos(String nombreProducto, String categoriaProducto, double valorProducto, int stockProducto) {
		NombreProducto = nombreProducto;
		CategoriaProducto = categoriaProducto;
		ValorProducto = valorProducto;
		StockProducto = stockProducto;
	}

	public String toMiniString() {
		return "Producto= " + NombreProducto;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("NombreProducto: " + NombreProducto).append("\n")
		.append("CategoriaProducto: " + CategoriaProducto).append("\n")
		.append("ValorProducto: " + ValorProducto).append("\n")
		.append("StockProducto: " + StockProducto);
		return sb.toString();
	}
}