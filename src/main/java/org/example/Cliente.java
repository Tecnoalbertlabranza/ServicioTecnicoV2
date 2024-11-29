package org.example;
import java.util.ArrayList;
import java.util.List;

public class Cliente  {
	//---------Datos Personales-------------
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private String rut;
	private String contrasena;
	//------Parametros de Direccion-----------
	private String region;
	private String comuna;

	List<Venta> ventascliente;


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

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public void IniciarSesion() {
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
				'}';
	}
}