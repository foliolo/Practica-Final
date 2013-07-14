package uax.practica.tienda;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Clase Fichero que se encarga de escribir en el fichero la información relativa a las ventas
 * almacenando la información relevante.
 * @version v0.1 	24 Junio 2013
 * @author Alberto Hidalgo
 */
public class Fichero {
	
	// VARIABLES
	private FileWriter escribir;
	private String nombreArchivo;
	private BufferedWriter bufferedWriter ;
	private Calendar date = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	// CONSTRUCTORES
	public Fichero(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo + "_" + sdf.format(date.getTime()) + ".txt" ;
	}

	// METODOS
	/** 
	 * Función que inserta la información correspondiente a la venta en el fichero, separando los campos por ';'
	 * @param venta - Variable de tipo Venta 
	 * @return boolean - true si se escribe correctamente en el fichero; false en caso contrario.
	 */
	public boolean escribirArchivo(Venta venta) throws IOException{
		try{
			escribir = new FileWriter(nombreArchivo, true);
			bufferedWriter = new BufferedWriter(escribir);
			PrintWriter salida = new PrintWriter( bufferedWriter );
			
			salida.print(venta.getMusica().getAlbum()); 		salida.print(";");
			salida.print(venta.getMusica().getAutor()); 		salida.print(";");
			salida.print(venta.getMusica().getPrecio()); 		salida.print(";");
			salida.print(venta.getCliente().getNombre()); 		salida.print(";");
			salida.print(venta.getCliente().getApellidos()[0]); salida.print(";");
			salida.print(venta.getCliente().getApellidos()[1]); salida.print(";");
			salida.println(venta.getCodigo());
			
			salida.close();
			bufferedWriter.close();
			escribir.close();
			return true;
		}
		catch(FileNotFoundException ex){
			System.out.println("\n\tError al crear el fichero.");
			return false;
		}
	}
}
