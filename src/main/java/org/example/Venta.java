package org.example;

public class Venta {
	private String fechaVenta;
	private String total;
	private String iva;

	public Venta(String fechaVenta, String iva, String total) {
		this.fechaVenta = fechaVenta;
		this.iva = iva;
		this.total = total;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getIva() {
		return iva;
	}

	public void setIva(String iva) {
		this.iva = iva;
	}
}