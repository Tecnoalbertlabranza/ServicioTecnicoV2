package org.example;

import org.example.*;

import java.util.*;

public class Cliente implements Usuarios {
	private int ID_Cliente;
	private String Direccion;
	private String Contraseña;
	private String Nombre;
	private String Apellido;
	private String Email;
	Collection<Reseña> ReseñasCliente;
	AgendaServicios AgendaCliente;
	CarritoDeCompras Carrito;
	Collection<Venta> VentasCliente;

	public void EliminarCliente() {

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