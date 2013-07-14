package uax.practica.musica;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/** 
 * Clase base de los distintos objetos de música.
 * @version v0.1 	Fecha: 24/6/13
 * @author Alberto Hidalgo
 */
public abstract class Musica {
	/** Variables */
	private String album;
	private String autor;
	private String genero;
	Calendar año;
	private Float precio;
	Scanner read = new Scanner(System.in);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
	
	/** Constructor vacío que inicializa las variables de la clase pidiéndoselas al usuario*/
	public Musica(){
		boolean fallo;
		Calendar fecha = Calendar.getInstance();
		//fecha.setLenient(false);
		
		System.out.println("\tIntroduce el nombre del album: ");
		setAlbum(read.nextLine().trim());
		System.out.println("\tIntroduce el autor: ");
		setAutor(read.nextLine().trim());
		System.out.println("\tIntroduce el género: ");
		setGenero(read.nextLine().trim());
		
		System.out.println("\tIntroduce la fecha de publicación: ");
		System.out.println("\t(Se autocorregirá en caso de ser erronea. Ej: 13 meses = 1 año y 1 mes) ");
		do{
			try{
				System.out.println("\tIntroduce el dia: ");
				fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(read.nextLine()));
				System.out.println("\tIntroduce el mes: ");
				fecha.set(Calendar.MONTH, Integer.parseInt(read.nextLine())-1);
				System.out.println("\tIntroduce el año: ");
				fecha.set(Calendar.YEAR, Integer.parseInt(read.nextLine()));
			
				setAño(fecha);
				fallo = false;
			}
			catch(InputMismatchException ex){
				System.out.println("\tDebe introducir un número correcto en la fecha");
				fallo = true;
			}
			catch(IllegalArgumentException ex){
				System.out.println("\tDebe introducir un número correcto en la fecha");
				fallo = true;
			}
		}while(fallo);
		
		do{
			try{
				System.out.println("\tIntroduce el precio: ");
				setPrecio(Float.parseFloat(read.nextLine()));
				fallo = false;
			}
			catch (NumberFormatException e) {
				System.out.println("\tDebe introducir un precio correcto");
				fallo = true;
			}
		}while(fallo);
	}
	
	public Musica(String album, String autor){
		setAlbum(album);
		setAutor(autor);
	}
	
	/** Getter and Setter */
	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Calendar getAño() {
		return año;
	}

	public void setAño(Calendar año) {
		this.año = año;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	public abstract boolean equals(Musica m);
	
	public String imprimir(){
		String cad = "Música\n";
		DecimalFormat df = new DecimalFormat("#.##");
		
		cad += 	"\tAlbum: " + getAlbum() + "\n" +
				"\tAutor: " + getAutor() + "\n" +
				"\tGenero: " + getGenero() + "\n" +
				"\tFecha de publicación: " + sdf.format(getAño().getTime()) + "\n" +
				"\tPrecio: " + df.format(getPrecio()) + "€\n";
		
		return cad;
	}
}
