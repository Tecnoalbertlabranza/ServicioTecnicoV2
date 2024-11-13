package org.example;
import org.example.*;
import java.util.*;

public class Cliente implements Usuarios {
	private String Direccion;
	private String Contraseña;
	private String Nombre;
	private String Apellido;
	private String Email;
	AgendaServicios AgendaCliente;
	CarritoDeCompras Carrito;
	Collection<Venta> VentasCliente;

	public Cliente(String nombre, String apellido, String direccion) {
		Nombre = nombre;
		Apellido = apellido;
		Direccion = direccion;
	}

	public String getNombre() {
		return Nombre;
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

    public void DetalleUsuario(){

    }
}