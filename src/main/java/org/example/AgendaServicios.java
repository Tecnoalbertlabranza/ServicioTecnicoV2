package org.example;
import java.util.*;

public class AgendaServicios {
	private String FechaServicio;
	List<Servicio> ServiciosRealizados;
	private int CostoTotal;

	public AgendaServicios(String fechaServicio, int costoTotal, List<Servicio> serviciosRealizados) {
		FechaServicio = fechaServicio;
		CostoTotal = costoTotal;
		ServiciosRealizados = serviciosRealizados;
	}

	public int getCostoTotal() {return CostoTotal;}

	public Collection<Servicio> getServiciosRealizados() {
		return ServiciosRealizados;
	}

	public void ConsultarHistorialCliente(Cliente cliente) {
		for (int i = 0; i < cliente.serviciosRealizados.size(); i++) {
			cliente.serviciosRealizados.get(i).toString();
		}
	}

	public void ConsultarServicios(Cliente cliente) {
		cliente.agendaCliente.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (ServiciosRealizados != null && ServiciosRealizados.isEmpty()) {
			sb.append("Fecha servicio: ").append(FechaServicio).append("\n");
			for (Servicio servicio : ServiciosRealizados) {
				sb.append(servicio.toString()).append("\n");
			}
			sb.append("Costo total: ").append(CostoTotal).append("\n");
		} else {
			sb.append("No hay servicios realizados. \n");
		}
		return sb.toString();
	}
}