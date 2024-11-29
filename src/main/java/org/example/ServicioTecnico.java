package org.example;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.firebase.cloud.FirestoreClient.getFirestore;

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

	public void registrarAdministrador(String nombre, String apellido, String rut, String contrasena, String email, firebase firebaseInstance) {
		Administradores admin = new Administradores(nombre, apellido, rut, contrasena, email);
		Map<String, Object> data = Map.of(
			"Nombre", nombre,
			"Apellido", apellido,
			"Rut", rut,
			"Contraseña", contrasena,
			"Email", email
				);

		firebaseInstance.insertardatos("Administradores", nombre + " " + apellido, data);
		System.out.println("Administradores registrado en firebase con id " + nombre + " " + apellido);
	}

	public void RegistrarCliente(String nombre, String apellido, String telefono, String email, String rut, String contrasena, String region, String comuna, firebase firebaseInstance) {
		listaClientes = java.util.stream.Stream.ofNullable(listaClientes)
				.findFirst()
				.orElseGet(ArrayList::new);

		Cliente nuevoCliente = new Cliente(nombre, apellido, telefono, email, rut, region, comuna);
		listaClientes.add(nuevoCliente);
		System.out.println("Cliente registrado localmente " + nuevoCliente);

		Map<String, Object> data = Map.of(
			"Nombre", nombre,
			"Apellido", apellido,
			"Telefono", telefono,
			"Email", email,
			"Rut", rut,
			"Contraseña", contrasena,
			"Region", region,
			"Comuna", comuna
				);

		firebaseInstance.insertardatos("Registro De Clientes", nombre + " " + apellido, data);
		System.out.println("Cliente registrado en Firebase con id" + nombre + " " + apellido);
	}

	public void registrarProducto(String nombreProducto, String categoriaProducto, double valorProducto, int stockProducto, firebase firebaseInstance) {
		productos = java.util.stream.Stream.ofNullable(productos)
				.findFirst()
				.orElseGet(ArrayList::new);

		Producto producto = new Producto(nombreProducto, categoriaProducto, valorProducto, stockProducto);
		productos.add(producto);
		System.out.println("Producto agregado localmente: " + producto);

		Map<String, Object> detallesProducto = Map.of(
			"Nombre", nombreProducto,
			"Categoría", categoriaProducto,
			"Categoria", categoriaProducto,
			"Valor", valorProducto,
			"Stock", stockProducto
				);

		firebaseInstance.insertardatos("Registro de Producto", nombreProducto + " ", detallesProducto);
		System.out.println("Producto registrado en Firebase con id" + nombreProducto);
	}

	public void registrarServicioComputador(String nombre, double valorServicio, String tiempoEstimado, String tipoComputadora, String lineaDePorcesador, String usoComputadora, firebase firebaseInstance) {
		serviciosComputador = java.util.stream.Stream.ofNullable(serviciosComputador)
				.findFirst()
				.orElseGet(ArrayList::new);

		ServicioComputador servicioComputador = new ServicioComputador(nombre, valorServicio, tiempoEstimado, tipoComputadora, lineaDePorcesador, usoComputadora);
		serviciosComputador.add(servicioComputador);
		System.out.println("Servicio agregado localmente: " + servicioComputador);

		Map<String, Object> datosServicioComputador = Map.of(
			"Nombre", nombre,
			"Valor", valorServicio,
			"TiempoEstimado", tiempoEstimado,
			"TipoComputadora", tipoComputadora,
			"LineaDePorcesador", lineaDePorcesador,
			"UsoComputadora", usoComputadora
					);

		firebaseInstance.insertardatos("Registro de servicio computador", nombre + " " + valorServicio, datosServicioComputador);
		System.out.println("Servicio computador registrado en firebase con id" + nombre + " " + valorServicio);
	}

	public void registrarServicioConsolas(String nombre, double valorServicio, String tiempoEstimado, String modeloConsola, String marcaConsola, firebase firebaseInstance) {
		serviciosConsola = java.util.stream.Stream.ofNullable(serviciosConsola)
				.findFirst()
				.orElseGet(ArrayList::new);

		ServicioConsolas servicioConsolas = new ServicioConsolas(nombre, valorServicio, tiempoEstimado, modeloConsola, marcaConsola);
		serviciosConsola.add(servicioConsolas);
		System.out.println("Servicio agregado localmente: " + servicioConsolas);

		Map<String, Object> datosServicioConsolas = Map.of(
			"Nombre", nombre,
			"Valor", valorServicio,
			"TiempoEstimado", tiempoEstimado,
			"ModeloConsola", modeloConsola,
			"MarcaConsola", marcaConsola
				);

		firebaseInstance.insertardatos("Registro de servicio consola", nombre + " " + valorServicio, datosServicioConsolas);
		System.out.println("Servicio consola registrado en Firebase con id" + nombre + " " + valorServicio);
	}

	public Cliente obtenerDatosDelCliente(String rut) {
		return listaClientes.stream()
				.filter(cliente -> cliente.getRut().equals(rut))
				.findFirst()
				.orElse(null);
	}

	public void registrarVenta(String nombreCliente, String apellidoCliente, String fecha, String iva, String total, String rut, firebase firebaseInstance) {
		listaClientes.stream()
				.filter(cliente -> cliente.getRut().equals(rut))
				.findFirst()
				.ifPresent(cliente -> {
					cliente.setVentascliente(
							cliente.getVentascliente() == null ? new ArrayList<>() : cliente.getVentascliente()
					);

					Map<String, Object> detalleVenta = Map.of(
							"Nombre Cliente", cliente.getNombre(),
							"Apellido Cliente", cliente.getApellido(),
							"Fecha De Venta", fecha,
							"IVA Impuesto", iva,
							"Total Venta", total,
							"Rut Cliente", rut
					);

					Venta nuevaVenta = new Venta(fecha, iva, total);
					cliente.getVentascliente().add(nuevaVenta);

					firebaseInstance.insertardatos("Registro De Ventas", cliente.getNombre() + " " + cliente.getApellido(), detalleVenta);
					System.out.println("Fecha registrada con exito con fecha: " + fecha);

					listaVentas = listaVentas == null ? new ArrayList<>() : listaVentas;
					listaVentas.add(nuevaVenta);
				});
	}

	public void cargarClientesDesdeFirebase(firebase firebaseInstance) {
		try {
			QuerySnapshot querySnapshot = firebaseInstance.getFirestore().collection("Registro De Clientes").get().get();
			List<Cliente> clientesFirebase = querySnapshot.getDocuments().stream()
					.map(document -> {
						String nombre = document.getString("Nombre");
						String apellido = document.getString("Apellido");
						String telefono = document.getString("Telefono");
						String email = document.getString("Email");
						String rut = document.getString("Rut");
						String region = document.getString("Region");
						String comuna = document.getString("Comuna");

						return new Cliente(nombre, apellido, telefono, email, rut, region, comuna);
					})
					.collect(Collectors.toList());
			setListaClientes(clientesFirebase);

			clientesFirebase.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al cargar datos de clientes : " + e.getMessage());
		}
	}

	public void procesarVentasClientes() {
		StringBuilder clientesConVentas = new StringBuilder();

		listaClientes.stream()
				.forEach(cliente -> {
					Firestore db = getFirestore();
					ApiFuture<QuerySnapshot> futureVentas = db.collection("Registro De Ventas")
							.whereEqualTo("Rut Cliente", cliente.getRut()).get();
					try {
						QuerySnapshot querySnapshotVentas = futureVentas.get();
						List<Venta> listaVentasCliente = querySnapshotVentas.getDocuments().stream()
								.map(documentVenta -> new Venta(
										documentVenta.getString("Fecha De Venta"),
										documentVenta.getString("IVA Impuesto"),
										documentVenta.getString("Total Venta")))
								.collect(Collectors.toList());

						cliente.setVentascliente(listaVentasCliente);

						clientesConVentas.append("Cliente: ").append(cliente.getNombre()).append(" ").append(cliente.getApellido()).append("\n");
						clientesConVentas.append("Ventas: \n");
						listaVentasCliente.forEach(venta -> clientesConVentas.append("- Fecha: ").append(venta.getFechaVenta()).append(", Total: ").append(venta.getTotal()).append("\n"));
						clientesConVentas.append("\n");
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Error al cargar ventas del cliente: " + cliente.getNombre() + " " + cliente.getApellido());
					}
				});

		System.out.println("\nClientes con ventas:");
		System.out.println(clientesConVentas.toString());
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

	public Collection<ServicioConsolas> getServiciosConsola() {
		return serviciosConsola;
	}

	public Collection<ServicioComputador> getServiciosComputador() {
		return serviciosComputador;
	}
}
