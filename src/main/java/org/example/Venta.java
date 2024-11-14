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

	public void CalcularIva() {
		iva = (int)(total * 0.16);
	}

	public void CalcularTotal(Cliente cliente) {
		total += cliente.agendaCliente.getCostoTotal();
		total += cliente.carrito.getValorTotalProductos();
	}

}