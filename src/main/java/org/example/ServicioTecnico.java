package org.example;

import java.util.*;

public class ServicioTecnico {
	private String nombreServicio;
	Collection<Servicio> servicios;
	Collection<Productos> productos;
	List<Cliente> listaClientes;
	private String descripcion;

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public ServicioTecnico(String nombreServicio, Collection<Servicio> servicios, Collection<Productos> productos, List<Cliente> listaClientes, String descripcion) {
		this.nombreServicio = nombreServicio;
		this.servicios = servicios;
		this.productos = productos;
		this.listaClientes = new ArrayList<>();
		this.descripcion = descripcion;
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

	public void AgregarProducto(String nombreProducto, String categoriaProducto, double valorProducto, int stockProducto){
		productos.add(new Productos(nombreProducto, categoriaProducto, valorProducto, stockProducto));
	}
	public void AgregarServicioComputador(double valorServicio, String nombre, int tiempoEstimado, int tipoComputadora, int lineaDePorcesador, int usoComputadora){
		servicios.add(new ServicioComputador(valorServicio, nombre, tiempoEstimado, tipoComputadora, lineaDePorcesador, usoComputadora));
	}

	public void AgregarServicioConsolas(double valorServicio, String nombre, int tiempoEstimado, int modeloConsola, int marcaConsola){
		servicios.add(new ServicioConsolas(valorServicio, nombre, tiempoEstimado, modeloConsola, marcaConsola));
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