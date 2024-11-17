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

	public void RegistrarCliente(String nombre, String apellido,String telefono,String email, String rut,String region, String comuna, String calle, String numero ,firebase firebaseInstance) {
		if(listaClientes == null){
			listaClientes = new ArrayList<>();
		}

		Cliente nuevoCliente = new Cliente(nombre, apellido, telefono, email, rut, region,comuna,calle,numero );
		listaClientes.add(nuevoCliente);
		System.out.println("Cliente registrado localmente " + nuevoCliente);

			Map<String, Object> data = new HashMap<>();
			data.put("Nombre", nombre);
			data.put("Apellido", apellido);
			data.put("Telefono", telefono);
			data.put("Email", email);
			data.put("Rut", rut);
			data.put("Region", region);
			data.put("Comuna", comuna);
			data.put("Calle", calle);
			data.put("Numero", numero);

		firebaseInstance.insertardatos("Registro De Clientes",nombre+""+ apellido,data);

		System.out.println("Cliente registrado en Firebase con id" + nombre+""+apellido);
	}


	public void registrarProducto(String nombreProducto, String categoriaProducto, double valorProducto, int stockProducto, firebase firebaseInstance) {
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

		firebaseInstance.insertardatos("Registro de Productos",nombreProducto+""+ categoriaProducto,detallesProducto);
		System.out.println("Producto registrado en Firebase con id" + nombreProducto+""+categoriaProducto);

	}

	public void registrarServicioComputador(String nombre, double valorServicio, int tiempoEstimado, int tipoComputadora, int lineaDePorcesador, int usoComputadora){
		if (servicios == null) {
			servicios = new ArrayList<>();
		}

		ServicioComputador servicioComputador = new ServicioComputador(nombre, valorServicio, tiempoEstimado, tipoComputadora, lineaDePorcesador, usoComputadora);
		servicios.add(servicioComputador);
		System.out.println("Servicio agregado localmente: " + servicioComputador);

		Map<String, Object> datosServicioComputador = new HashMap<>();
		datosServicioComputador.put("Nombre", nombre);
		datosServicioComputador.put("Valor", valorServicio);
		datosServicioComputador.put("TiempoEstimado", tiempoEstimado);
		datosServicioComputador.put("TipoComputadora", tipoComputadora);
		datosServicioComputador.put("LineaDePorcesador", lineaDePorcesador);
		datosServicioComputador.put("UsoComputadora", usoComputadora);

	}

	public void registrarServicioConsolas(double valorServicio, String nombre, int tiempoEstimado, int modeloConsola, int marcaConsola){
		if (servicios == null) {
			servicios = new ArrayList<>();
		}

		ServicioConsolas servicioConsolas = new ServicioConsolas(nombre, valorServicio, tiempoEstimado, modeloConsola, marcaConsola);
		servicios.add(servicioConsolas);
		System.out.println("Servicio agregado localmente: " + servicioConsolas);

		Map<String, Object> datosServicioConsolas = new HashMap<>();
		datosServicioConsolas.put("Nombre", nombre);
		datosServicioConsolas.put("Valor", valorServicio);
		datosServicioConsolas.put("TiempoEstimado", tiempoEstimado);
		datosServicioConsolas.put("ModeloConsola", modeloConsola);
		datosServicioConsolas.put("MarcaConsola", marcaConsola);

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