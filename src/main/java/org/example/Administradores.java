package org.example;

public class Administradores implements Usuarios {
	private String nombre;
	private String rut;
	private String contraseña;
	private String apellido;
	private String email;

	public Administradores(int id_Administrador, String nombre, String rut, String contraseña, String apellido, String email ){
		this.nombre = nombre;
		this.rut = rut;
		this.contraseña = contraseña;
		this.apellido = apellido;
		this.email = email;
	}
	
	public void GestionarUsuarios() {

	}

	public void VerReportesDeVentas(ServicioTecnico servicioTecnico) {
		for (int i = 0; i < servicioTecnico.listaClientes.size(); i++) {

		}
	}

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