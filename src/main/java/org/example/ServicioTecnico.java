package org.example;

import java.util.*;

public class ServicioTecnico {
	private String NombreServicio;
	Collection<Servicio> servicios;
	Collection<Productos> productos;
	List<Cliente> listaClientes;
	private String Descripcion;

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public ServicioTecnico(String nombreServicio, Collection<Servicio> servicios, Collection<Productos> productos, List<Cliente> listaClientes, String descripcion) {
		NombreServicio = nombreServicio;
		this.servicios = servicios;
		this.productos = productos;
		this.listaClientes = new ArrayList<>();
		Descripcion = descripcion;
	}

	public void RegistrarCliente(String nombre, String apellido,String direccion,firebase firebaseInstance) {
		if(listaClientes == null){
			listaClientes = new ArrayList<>();
		}


		Cliente nuevoCliente = new Cliente(nombre, apellido, direccion);
		listaClientes.add(nuevoCliente);
		System.out.println("Cliente registrado localmente " + nuevoCliente);




			Map<String, Object> data = new HashMap<>();
			data.put("Nombre", nombre);
			data.put("Apellido", apellido);
			data.put("Dirección", direccion);
			firebaseInstance.insertardatos("Registro De Clientes", "Clientes", data);

			String idDocumento = "CLIENTE_" + System.currentTimeMillis();
		firebaseInstance.insertardatos("Registro De Clientes", idDocumento, data);
		    System.out.println("Cliente registrado en Firebase con id" + idDocumento);


	}

	public void EliminarCliente(String nombre) {
		for (Cliente cliente : listaClientes) {
			if (cliente.getNombre().equalsIgnoreCase(nombre)) {
				listaClientes.remove(cliente);
			}
		}
	}

	public void EliminarServicio(String nombreServicio) {
		for (Servicio servicio : servicios) {
			servicios.remove(servicio);
		}
	}
	
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
}