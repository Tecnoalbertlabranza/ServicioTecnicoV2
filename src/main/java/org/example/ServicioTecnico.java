package org.example;

import java.util.*;

public class ServicioTecnico {
	private String NombreServicio;
	Collection<Servicio> servicios;
	Collection<Productos> productos;
	List<Cliente> listaClientes = new ArrayList<>();
	private String Descripcion;

	public ServicioTecnico(String nombreServicio, Collection<Servicio> servicios, Collection<Productos> productos, List<Cliente> listaClientes, String descripcion) {
		NombreServicio = nombreServicio;
		this.servicios = servicios;
		this.productos = productos;
		this.listaClientes = listaClientes;
		Descripcion = descripcion;
	}

	public void RegistrarCliente(String nombre, String apellido,String direccion) {
		listaClientes.add(new Cliente(nombre, apellido, direccion));
	}

	public void EliminarCliente(String nombre) {
		for (Cliente cliente : listaClientes) {
			if (cliente.getNombre().equalsIgnoreCase(nombre)) {
				listaClientes.remove(cliente);
			}
		}
	}
	
//Estos metodos se implementan en Java Swing
	public void MostrarProductos() {
		for (Productos producto : productos) {
			System.out.println(producto);
		}
	}

	public void MostrarServicios() {
		for (Servicio servicio : servicios) {
			System.out.println(servicio);
		}
	}

	public void MostrarReseñas() {

	}

}