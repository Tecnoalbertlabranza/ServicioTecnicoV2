package org.example;

public class Venta {
	private String fechaVenta;
	private double total;
	private int iva;

	public Venta(String fechaVenta, int iva, double total) {
		this.fechaVenta = fechaVenta;
		this.iva = iva;
		this.total = total;
	}
}