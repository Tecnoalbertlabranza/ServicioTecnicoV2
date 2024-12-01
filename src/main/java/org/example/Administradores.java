package org.example;

public class Administradores  {
	private String nombre;
	private String rut;
	private String contrasena;
	private String apellido;
	private String email;

	public Administradores(String nombre, String apellido, String rut, String contrasena, String email ){
		this.nombre = nombre;
		this.rut = rut;
		this.contrasena = contrasena;
		this.apellido = apellido;
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}