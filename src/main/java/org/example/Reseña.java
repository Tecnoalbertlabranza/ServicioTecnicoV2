package org.example;
public class Reseña {
	private int Calificacion;
	private String Comentario;
	private int ID_Reseña;

	public Reseña(int calificacion, int ID_Reseña, String comentario) {
		Calificacion = calificacion;
		this.ID_Reseña = ID_Reseña;
		Comentario = comentario;
	}
}