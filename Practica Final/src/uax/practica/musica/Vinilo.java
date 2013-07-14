package uax.practica.musica;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;

/**
 * Clase Vinilo que hereda de Música y añade la propiedad: "segunda mano" para saber en qué fecha
 * se incorporó el Vinilo a la tienda. 
 * @version v0.1 	25 Junio 2013
 * @author Alberto Hidalgo
 */
public class Vinilo extends Musica {
	private Calendar segundaMano = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMMM/yyyy");
	
	/** Constructores */
	public Vinilo(){
		super();
		boolean fallo = false;
		Calendar fecha = Calendar.getInstance();
		fecha.setLenient(false);
		
		System.out.println("\tIntroduce la fecha en que la tienda adquirió el Vinilo");
		do{
			try{
				System.out.println("\tIntroduce el dia: ");
				fecha.set(Calendar.DAY_OF_MONTH, Integer.parseInt(read.nextLine()));
				System.out.println("\tIntroduce el mes: ");
				fecha.set(Calendar.MONTH, Integer.parseInt(read.nextLine())-1);
				System.out.println("\tIntroduce el año: ");
				fecha.set(Calendar.YEAR, Integer.parseInt(read.nextLine()));
			
				setSegundaMano(fecha);
				fallo = false;
			}
			catch(InputMismatchException ex){
				System.out.println("\nDebe introducir un número correcto en la fecha");
				fallo = true;
			}
			catch(IllegalArgumentException ex){
				System.out.println("\nDebe introducir un número correcto en la fecha");
				fallo = true;
			}
		}while(fallo);
	}
	
	public Vinilo(String album, String autor){
		super(album, autor);
	}
	
	/** Getter and Setter */
	public Calendar getSegundaMano() {
		return segundaMano;
	}

	public void setSegundaMano(Calendar segundaMano) {
		this.segundaMano = segundaMano;
	}
	
	/** Funciones */
	public String imprimir(){
		return super.imprimir() + "\tAdquisición de 2ª mano: " + sdf.format(getSegundaMano().getTime()) + "\n" +
								  "\tTipo de formato: Vinilo\n";
	}
	
	public boolean equals(Musica m){
		if(m instanceof Vinilo){
			if( getAlbum().equals(m.getAlbum()) && getAutor().equals(m.getAutor()))
				return true;
		}

		return false;
	}
	
}
