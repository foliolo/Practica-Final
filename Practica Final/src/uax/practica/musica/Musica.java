package uax.practica.musica;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/** 
 * Clase base de los distintos objetos de m�sica.
 * @version v0.1 	Fecha: 24/6/13
 * @author Alberto Hidalgo
 */
public abstract class Musica {
	/** Variables */
	private String album;
	private String autor;
	private String genero;
	Calendar a�o;
	private Float precio;
	Scanner read = new Scanner(System.in);
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
	
	/** Constructor vac�o que inicializa las variables de la clase pidi�ndoselas al usuario*/
	public Musica(){
		boolean fallo;
		Calendar fecha = Calendar.getInstance();
		//fecha.setLenient(false);
		
		System.out.println("\tIntroduce el nombre del album: ");
		setAlbum(read.nextLine().trim());
		System.out.println("\tIntroduce el autor: ");
		setAutor(read.nextLine().trim());
		System.out.println("\tIntroduce el g�nero: ");
		setGenero(read.nextLine().trim());
		
		System.out.println("\tIntroduce la fecha de publicaci�n: ");
		System.out.println("\t(Se autocorregir� en caso de ser erronea. Ej: 13 meses = 1 a�o y 1 mes) ");
		do{
			try{
				System.out.println("\tIntroduce el dia: ");
				fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(read.nextLine()));
				System.out.println("\tIntroduce el mes: ");
				fecha.set(Calendar.MONTH, Integer.parseInt(read.nextLine())-1);
				System.out.println("\tIntroduce el a�o: ");
				fecha.set(Calendar.YEAR, Integer.parseInt(read.nextLine()));
			
				setA�o(fecha);
				fallo = false;
			}
			catch(InputMismatchException ex){
				System.out.println("\tDebe introducir un n�mero correcto en la fecha");
				fallo = true;
			}
			catch(IllegalArgumentException ex){
				System.out.println("\tDebe introducir un n�mero correcto en la fecha");
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

	public Calendar getA�o() {
		return a�o;
	}

	public void setA�o(Calendar a�o) {
		this.a�o = a�o;
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
		String cad = "M�sica\n";
		DecimalFormat df = new DecimalFormat("#.##");
		
		cad += 	"\tAlbum: " + getAlbum() + "\n" +
				"\tAutor: " + getAutor() + "\n" +
				"\tGenero: " + getGenero() + "\n" +
				"\tFecha de publicaci�n: " + sdf.format(getA�o().getTime()) + "\n" +
				"\tPrecio: " + df.format(getPrecio()) + "�\n";
		
		return cad;
	}
}
