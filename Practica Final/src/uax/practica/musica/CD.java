package uax.practica.musica;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal de entrada al programa
 * @version v0.1 	25 Junio 2013
 * @author Alberto Hidalgo
 */
public class CD extends Musica {
	private int numCanciones;
	private Scanner read = new Scanner(System.in);
	
	public CD(){
		super();
		boolean fallo = false;
		do{
			try{
				System.out.println("\tIntroduce el número de canciones: ");
				setNumCanciones(read.nextInt());
				fallo = false;
			}
			catch(NumberFormatException ex){
				System.out.println("\tDebe introducir un número correcto:");
				fallo = true;
			}
			catch(InputMismatchException ex){
				System.out.println("\tDebe introducir un número correcto en la fecha");
				fallo = true;
			}
		}while(fallo);
	}
	
	public CD(String album, String autor){
		super(album, autor);
	}
	
	public int getNumCanciones() {
		return numCanciones;
	}

	public void setNumCanciones(int numCanciones) {
		this.numCanciones = numCanciones;
	}
	
	public String imprimir(){
		return super.imprimir() + "\tNúmero de canciones: " + getNumCanciones() + "\n" +
								  "\tTipo de formato: CD\n";
	}
	
	public boolean equals(Musica m){
		if(m instanceof CD){
			if( getAlbum().equals(m.getAlbum()) && getAutor().equals(m.getAutor()))
				return true;
		}

		return false;
	}
	
}
