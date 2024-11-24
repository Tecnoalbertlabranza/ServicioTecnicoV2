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

	public void IniciarSesion() {

    }

    public void cerrarSesion() {

    }
}