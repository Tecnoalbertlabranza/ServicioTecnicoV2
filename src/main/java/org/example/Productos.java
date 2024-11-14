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
		return NombreProducto + '\'' + CategoriaProducto + '\''
				+ ValorProducto + "$" + '\''
				+ StockProducto;
	}
}