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

	public void RegistrarCliente(String nombre, String apellido, String telefono, String email, String rut, String region, String comuna, String calle, String numero, firebase firebaseInstance) {
		if (listaClientes == null) {
			listaClientes = new ArrayList<>();
		}

		Cliente nuevoCliente = new Cliente(nombre, apellido, telefono, email, rut, region, comuna, calle, numero);
		listaClientes.add(nuevoCliente);
		System.out.println("Cliente registrado localmente " + nuevoCliente);

		Map<String, Object> datosPersonales = new HashMap<>();
		datosPersonales.put("Nombre", nombre);
		datosPersonales.put("Apellido", apellido);
		datosPersonales.put("Teléfono", telefono);
		datosPersonales.put("Email", email);
		datosPersonales.put("Rut", rut);

		Map<String, Object> direccion = new HashMap<>();
		direccion.put("Region", region);
		direccion.put("Comuna", comuna);
		direccion.put("Calle", calle);
		direccion.put("Numero", numero);

		String idDocumento = nombre + " " + apellido;

		firebaseInstance.insertardatos("Registro De Clientes", idDocumento, "Datos personales", datosPersonales);
		firebaseInstance.insertardatos("Registro De Direcciones de clientes", idDocumento, "Direccion", direccion);

		System.out.println("Cliente registrado en Firebase con id: " + idDocumento);
	}


	public void AgregarProducto(String nombreProducto, String categoriaProducto, double valorProducto, int stockProducto, firebase firebaseInstance) {
		if (productos == null) {
			productos = new ArrayList<>();
		}

		Productos producto = new Productos(nombreProducto, categoriaProducto, valorProducto, stockProducto);
		productos.add(producto);
		System.out.println("Producto agregado localmente: " + producto);

		Map<String, Object> detallesProducto = new HashMap<>();
		detallesProducto.put("Nombre", nombreProducto);
		detallesProducto.put("Categoria", categoriaProducto);
		detallesProducto.put("Valor", valorProducto);
		detallesProducto.put("Stock", stockProducto);

		Map<String, Object> especificacionesProducto = new HashMap<>();
		especificacionesProducto.put("Descripcion", "Computadora de alto rendimiento");
		//Hay que agregar mas especificaciones del producto

		String idDocumento = nombreProducto;

		firebaseInstance.insertardatos("Registro De Productos", idDocumento, "Detalles", detallesProducto);
		firebaseInstance.insertardatos("Registro De Productos", idDocumento, "Especificaciones", especificacionesProducto);

		System.out.println("Producto agregado en Firebase con id: " + idDocumento);
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