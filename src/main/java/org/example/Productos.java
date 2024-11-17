package org.example;
public class Productos {
	private String nombreProducto;
	private String categoriaProducto;
	private String valorProducto;
	private String stockProducto;

	public Productos(String nombreProducto, String categoriaProducto,String valorProducto, String stockProducto) {
		this.nombreProducto = nombreProducto;
		this.categoriaProducto = categoriaProducto;
		this.valorProducto = valorProducto;
		this.stockProducto = stockProducto;
	}

	public String miniToString() {
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