package org.example;
public class Venta {

	private String fechaVenta;
	private double total;
	private int iva;

	public void CalcularIva() {
		iva = (int)(total * 0.16);
	}

	public void CalcularTotal() {

	}

}