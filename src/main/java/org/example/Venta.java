package org.example;

public class Venta {
	private String fechaVenta;
	private double total;
	private double iva;

	public Venta(String fechaVenta, double iva, double total) {
		this.fechaVenta = fechaVenta;
		this.iva = iva;
		this.total = total;
	}
}