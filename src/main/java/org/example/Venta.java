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

	public String getTotal() {
		return total;
	}

	public String getIva() {
		return iva;
	}

	@Override
	public String toString() {
		return "Venta{" +
				"fechaVenta='" + fechaVenta + '\'' +
				", total='" + total + '\'' +
				", iva='" + iva + '\'' +
				'}';
	}
}