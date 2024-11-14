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

	public ServicioTecnico() {
	}

	public void RegistrarCliente(String nombre, String apellido,String direccion) {
		firebase f1 = new firebase();
		listaClientes.add(new Cliente("Bastián", "Wenckhans", "Labranza"));
		listaClientes.add(new Cliente("Alessandro", "Duarte", "Freire"));

		for (int i = 0; i <= listaClientes.size(); i++) {
			Map<String, Object> data = new HashMap<>();
			data.put("Nombre", getListaClientes().get(Integer.parseInt(nombre)));
			data.put("Apellido", getListaClientes().get(Integer.parseInt(apellido)));
			data.put("Dirección", getListaClientes().get(Integer.parseInt(direccion)));
			f1.insertardatos("Registro De Clientes", "Clientes", data);
		}
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
}


	//	String nombreServicio, Collection<Servicio> servicios, Collection<Productos> productos, List<Cliente> listaClientes, String descripcion
/*NombreServicio = nombreServicio;
		this.servicios = servicios;
		this.productos = productos;
		this.listaClientes = listaClientes;
Descripcion = descripcion;*/