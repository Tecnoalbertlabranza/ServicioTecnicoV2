package org.example;
import java.util.*;

public class Cliente implements Usuarios {
	private String direccion;
	private String contraseña;
	private String nombre;
	private String apellido;
	private String email;
	AgendaServicios agendaCliente;
	CarritoDeCompras carrito;
	Collection<Venta> comprassCliente;
	List<Productos> compraProductosCliente;
	List<Servicio> serviciosRealizados;

	public Cliente(String nombre, String apellido, String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}
	public static void ServicioFinalizado(String nombreServicio){

	}

	public static void CompraProductoFinalizada(){
		
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