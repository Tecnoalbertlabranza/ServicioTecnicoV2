package org.example;
import io.grpc.lb.v1.ClientStats;

import java.util.*;

public class ServicioTecnico {
	private String nombreServicio;
	Collection<ServicioConsolas> serviciosConsola;
	Collection<ServicioComputador> serviciosComputador;
	Collection<Producto> productos;
	List<Cliente> listaClientes;
	List<Venta> listaVentas;
	private String descripcion;


	public ServicioTecnico(String nombreServicio, Collection<ServicioConsolas> serviciosConsola, Collection<ServicioComputador> serviciosComputador, Collection<Producto> productos, List<Cliente> listaClientes, String descripcion) {
		this.nombreServicio = nombreServicio;
		this.serviciosConsola = serviciosConsola;
		this.serviciosComputador = serviciosComputador;
		this.productos = productos;
		this.listaClientes = listaClientes;
		this.descripcion = descripcion;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void registrarAdministrador (String nombre, String apellido, String rut, String contraseña, String email , firebase firebaseInstance) {
		Administradores admin = new Administradores(nombre, apellido, rut, contraseña, email);
		Map<String, Object> data = new HashMap<>();
		data.put("Nombre", nombre);
		data.put("Apellido", apellido);
		data.put("Rut", rut);
		data.put("Contraseña", contraseña);
		data.put("Email", email);

		firebaseInstance.insertardatos("Administradores", nombre + " " + apellido, data);
		System.out.println("Administradores registrado en firebase con id " + nombre + " " + apellido);
	}

	public void RegistrarCliente(String nombre, String apellido, String telefono, String email, String rut, String region, String comuna, String calle, String numero, firebase firebaseInstance) {
		if (listaClientes == null) {
			listaClientes = new ArrayList<>();
		}

		Cliente nuevoCliente = new Cliente(nombre, apellido, telefono, email, rut, region, comuna);
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

		firebaseInstance.insertardatos("Registro De Clientes", nombre + " " + apellido, data);

		System.out.println("Cliente registrado en Firebase con id" + nombre + " " + apellido);
	}


	public void registrarProducto(String nombreProducto, String categoriaProducto, double valorProducto, double stockProducto, firebase firebaseInstance) {
		if (productos == null) {
			productos = new ArrayList<>();
		}

		Producto producto = new Producto(nombreProducto, categoriaProducto, valorProducto, stockProducto);
		productos.add(producto);
		System.out.println("Producto agregado localmente: " + producto);

		Map<String, Object> detallesProducto = new HashMap<>();
		detallesProducto.put("Nombre", nombreProducto);
		detallesProducto.put("Categoría", categoriaProducto);
		detallesProducto.put("Categoria", categoriaProducto);
		detallesProducto.put("Valor", valorProducto);
		detallesProducto.put("Stock", stockProducto);

		firebaseInstance.insertardatos("Registro de Producto", nombreProducto + " ", detallesProducto);
		System.out.println("Producto registrado en Firebase con id" + nombreProducto);

	}

	public void registrarServicioComputador(String nombre, double valorServicio, String tiempoEstimado, String tipoComputadora, String lineaDePorcesador, String usoComputadora, firebase firebaseInstance) {
		if (serviciosComputador == null) {
			serviciosComputador = new ArrayList<>();
		}

		ServicioComputador servicioComputador = new ServicioComputador(nombre, valorServicio, tiempoEstimado, tipoComputadora, lineaDePorcesador, usoComputadora);
		serviciosComputador.add(servicioComputador);
		System.out.println("Servicio agregado localmente: " + servicioComputador);

		Map<String, Object> datosServicioComputador = new HashMap<>();
		datosServicioComputador.put("Nombre", nombre);
		datosServicioComputador.put("Valor", valorServicio);
		datosServicioComputador.put("TiempoEstimado", tiempoEstimado);
		datosServicioComputador.put("TipoComputadora", tipoComputadora);
		datosServicioComputador.put("LineaDePorcesador", lineaDePorcesador);
		datosServicioComputador.put("UsoComputadora", usoComputadora);

		firebaseInstance.insertardatos("Registro de servicio computador", nombre + " " + valorServicio, datosServicioComputador);
		System.out.println("Servicio computador registrado en firebase con id" + nombre + " " + valorServicio);
	}

	public void registrarServicioConsolas(String nombre,double valorServicio, String tiempoEstimado, String modeloConsola, String marcaConsola, firebase firebaseInstance) {
		if (serviciosConsola == null) {
			serviciosConsola = new ArrayList<>();
		}

		ServicioConsolas servicioConsolas = new ServicioConsolas(nombre, valorServicio, tiempoEstimado, modeloConsola, marcaConsola);
		serviciosConsola.add(servicioConsolas);
		System.out.println("Servicio agregado localmente: " + servicioConsolas);

		Map<String, Object> datosServicioConsolas = new HashMap<>();
		datosServicioConsolas.put("Nombre", nombre);
		datosServicioConsolas.put("Valor", valorServicio);
		datosServicioConsolas.put("TiempoEstimado", tiempoEstimado);
		datosServicioConsolas.put("ModeloConsola", modeloConsola);
		datosServicioConsolas.put("MarcaConsola", marcaConsola);

		firebaseInstance.insertardatos("Registro de servicio consola", nombre + " " + valorServicio, datosServicioConsolas);
		System.out.println("Servicio consola registrado en Firebase con id" + nombre + " " + valorServicio);
	}


	public Cliente obtenerDatosDelCliente(String rut){
		for (Cliente cliente : listaClientes){
			if (cliente.getRut().equals(rut)){
				return cliente;
			}
		}
		return null;
	}

	public void registrarVenta(String nombreCliente, String apellidoCliente, String fecha, String iva, String total,String rut, firebase firebaseInstance) {
		for (Cliente cliente : listaClientes) {
			if (cliente.getRut().equals(rut)) {

				if (cliente.getVentascliente() == null) {
					cliente.setVentascliente(new ArrayList<>());
				}

				String nombre = cliente.getNombre();
				String apellido = cliente.getApellido();
				Map<String, Object> detalleVenta = new HashMap<>();

				detalleVenta.put("Nombre Cliente", nombreCliente);
				detalleVenta.put("Apellido Cliente", apellidoCliente);
				detalleVenta.put("Fecha De Venta", fecha);
				detalleVenta.put("IVA Impuesto", iva);
				detalleVenta.put("Total Venta", total);
				detalleVenta.put("Rut Cliente", rut);

				Venta nuevaVenta = new Venta(fecha, iva, total);
				cliente.getVentascliente().add(nuevaVenta);


				firebaseInstance.insertardatos("Registro De Ventas", nombre + " " + apellido, detalleVenta);
				System.out.println("Fecha registrada con exito con fecha: " + fecha);

				if (listaVentas == null) {
					listaVentas = new ArrayList<>();
				}
				listaVentas.add(nuevaVenta);
			}
		}
	}


	public List<Venta> getListaVentas() {
		return listaVentas;
	}

	public void setListaVentas(List<Venta> listaVentas) {
		this.listaVentas = listaVentas;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public void setListaProductos(Collection<Producto> productos) {
		this.productos = productos;
	}

	public Collection<Producto> getProductos() {
		return productos;
	}

	public void setServiciosComputador(Collection<ServicioComputador> serviciosComputador) {
		this.serviciosComputador = serviciosComputador;
	}

	public void setServiciosConsola(Collection<ServicioConsolas> serviciosConsola) {
		this.serviciosConsola = serviciosConsola;
	}
}
