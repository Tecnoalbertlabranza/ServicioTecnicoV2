package org.example;

public class Administradores implements Usuarios {
	private String nombre;
	private String rut;
	private String contraseña;
	private String apellido;
	private String email;

	public Administradores(String nombre, String apellido, String rut, String contraseña, String email ){
		this.nombre = nombre;
		this.rut = rut;
		this.contraseña = contraseña;
		this.apellido = apellido;
		this.email = email;
	}
	
	public void GestionarUsuarios() {

	}

	/*public void VerReportesDeVentas(ServicioTecnico servicioTecnico) {
		for (int i = 0; i < servicioTecnico.listaClientes.size(); i++) {
			Cliente cliente = servicioTecnico.listaClientes.get(i);
			System.out.println(cliente.getNombre());
			if (!cliente.compraProductosCliente.isEmpty()) {
				for (int j = 0; j < cliente.compraProductosCliente.size(); j++) {
					Producto producto = cliente.compraProductosCliente.get(j);
					System.out.println(producto.miniToString());
				}
			}
			if (cliente.serviciosRealizados.isEmpty()) {
				for (int j = 0; j < cliente.serviciosRealizados.size(); j++) {
					Servicio servicio = cliente.serviciosRealizados.get(j);
					System.out.println(servicio.miniToString());
				}
			}
			System.out.println(" ");
		}
	} */

	public void GestionarProductos() {

	}

	public void GestionarServicios() {

	}

	public void AsignarDescuentos() {

	}

	public void ConsultarReseñas() {
	}

	public void IniciarSesion() {

    }

    public void cerrarSesion() {

    }

    public void ActualizarDatosPersonales() {

    }

    public void CambiarContraseña() {

    }

    public void DetalleUsuario() {

    }



}