package org.example;
import java.util.*;

public class AgendaServicios {
	private String fechaServicio;
	List<Servicio> serviciosRealizados;
	private int costoTotal;

	public AgendaServicios(String fechaServicio, int costoTotal, List<Servicio> serviciosRealizados) {
		this.fechaServicio = fechaServicio;
		this.costoTotal = costoTotal;
		this.serviciosRealizados = serviciosRealizados;
	}

	public int getCostoTotal() {return costoTotal;}

	public Collection<Servicio> getServiciosRealizados() {
		return serviciosRealizados;
	}

	public void ConsultarServicios(Cliente cliente) {
		cliente.agendaCliente.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (serviciosRealizados != null && serviciosRealizados.isEmpty()) {
			sb.append("Fecha servicio: ").append(fechaServicio).append("\n");
			for (Servicio servicio : serviciosRealizados) {
				sb.append(servicio.toString()).append("\n");
			}
			sb.append("Costo total: ").append(costoTotal).append("\n");
		} else {
			sb.append("No hay servicios realizados. \n");
		}
		return sb.toString();
	}
}