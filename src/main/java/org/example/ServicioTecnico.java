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

	public void RegistrarCliente(String nombre, String apellido, String correo, String direccion) {
		listaClientes.add(new Cliente(nombre, apellido, direccion, correo));
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

	}

	public void MostrarServicios() {

	}

	public void MostrarReseñas() {

	}

}