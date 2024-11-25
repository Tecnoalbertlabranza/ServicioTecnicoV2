package org.example;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Usuarios {
	//---------Datos Personales-------------
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private String rut;
	private String contraseña;
	//------Parametros de Direccion-----------
	private String region;
	private String comuna;
	List<Venta> ventascliente;

	AgendaServicios agendaCliente;

	public Cliente(String nombre, String apellido, String telefono, String email, String rut,String region, String comuna) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.rut = rut;
		this.region = region;
		this.comuna = comuna;
		this.ventascliente = new ArrayList<>();
	}

	public List<Venta> getVentascliente() {
		return ventascliente;
	}

	public void setVentascliente(List<Venta> ventascliente) {
		this.ventascliente = ventascliente;
	}

	public String getApellido(){
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getRut() {
		return rut;
	}

    public String getTelefono(){
        return telefono;
    }

    public String getEmail(){
        return email;
    }

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public void IniciarSesion() {
	}

	public void cerrarSesion(){
	}

	public void addVenta(Venta venta) {
		if (ventascliente == null) {
			ventascliente = new ArrayList<>();
		}
		ventascliente.add(venta);
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"nombre='" + nombre + '\'' +
				", apellido='" + apellido + '\'' +
				", telefono='" + telefono + '\'' +
				", email='" + email + '\'' +
				", rut='" + rut + '\'' +
				", region='" + region + '\'' +
				", comuna='" + comuna + '\'' +
				", ventascliente=" + ventascliente +
				", agendaCliente=" + agendaCliente +
				'}';
	}
}