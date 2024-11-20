package org.example;
import java.util.*;

public class Cliente implements Usuarios {
	//---------Datos Personales-------------
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	private String rut;
	//------Parametros de Direccion-----------
	private String region;
	private String comuna;
	private String calle;
	private String numero;


	AgendaServicios agendaCliente;
	CarritoDeCompras carrito;
	Collection<Venta> comprassCliente;

	public Cliente(String nombre, String apellido, String telefono, String email, String rut,String region, String comuna, String calle, String numero) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.email = email;
		this.rut = rut;
		this.region = region;
		this.comuna = comuna;
		this.calle = calle;
		this.numero = numero;
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

	public void IniciarSesion() {
		System.out.println("hola mundo lolaso");

	}

	public void cerrarSesion(){
		System.out.println("PruebaPush");

	}

    public void ActualizarDatosPersonales(){

    }

    public void CambiarContraseña(){

    }


}