package uax.practica.tienda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import uax.practica.musica.*;

/**
 * Clase principal de entrada al programa
 * @version v0.1 	24 Junio 2013
 * @author Alberto Hidalgo
 */
public class Tienda {
	private ArrayList<Musica> musica;
	private ArrayList<Cliente> clientes;
	private ArrayList<Venta> ventas;
	private int opcion;
	private Scanner read;
	
	/** 
	 * Constructor de la tienda donde se inicializan las variables y el menú 
	 */
	Tienda(){
		musica = new ArrayList<Musica>();
		clientes = new ArrayList<Cliente>();
		ventas = new ArrayList<Venta>();
		read = new Scanner(System.in);
		setOpcion(0);
	}

	/** Métodos Getter and Setter */
	private int getOpcion() {
		return opcion;
	}
	
	private void setOpcion(int opcion) {
		this.opcion = opcion;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public boolean setClientes(Cliente cliente){
		if(getClientes().add(cliente))
			return true;
		return false;
	}

	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}
	/** 
	 * Obtiene el elemento indicado en la posición de la lista de música. 
	 * En caso de ser -1: Eliminamos el último elemento
	 */
	private Musica getMusica(int posicion) {
		if(posicion == -1)
			return musica.get(musica.size()-1);
		else
			return musica.get(posicion);
	}
	
	/** Obtiene el ULTIMO elemento de la lista de música */
	private ArrayList<Musica> getMusica() {
		return musica;
	}
	
	/** Inserta un elemento nuevo de música en la lista de música */
	private boolean setMusica() {
		int formato = -1;
		System.out.println("\tIntroduce el formato de la música: \n" +
				"\t 1. Musica\n" +
				"\t 2. CD\n" +
				"\t 3. Vinilo\n" +
				"\t 4. Casete");
		System.out.println("\tEsperando instrucción: ");
		
		do{
			try{
				formato = new Integer(read.nextLine());
				if((formato < 1) || (formato > 4)){
					System.out.print("\n\tDebes introducir un formato dentro de las opciones del menu.\n" +
							"\tEsperando instrucción: ");
				}
			}
			catch( NumberFormatException ex){
				System.out.print("\n\tDebes introducir un valor numérico: ");
				opcion = -1;
			}
		}while((formato < 1) || (formato > 4));
		
		switch(formato){
			//Música
			case 1:
				if(this.musica.add(new Musica()))
					return true;
				
			//CD
			case 2:
				if(this.musica.add(new CD()))
					return true;
				
			//Vinilo
			case 3:
				if(this.musica.add(new Vinilo()))
					return true;
				
			//Casete
			case 4:
				if(this.musica.add(new Casete()))
					return true;
		}
		return false;
	}
	
	/** 
	 * Función elimina una música de la lista dando su formato, album y autor.
	 * @param void
	 * @return boolean - true si lo elimina correctamente; false en caso contrario.
	 */
	private boolean borrarMusica() {
		Musica comp = null;
		String album, autor;
		int formato = -1;

		/** Pedimos al usuario que introduzca los campos de comparación */
		System.out.println("\tIntroduce el album de la canción");
		album = read.nextLine().trim();
		System.out.println("\tIntroduce el album de la canción");
		autor = read.nextLine().trim();
		
		/** Pedimos al usuario el formato del elemento que desea eliminar de la lista */
		System.out.println("\tIntroduce el formato de la música: \n" +
				"\t\t 1. Musica\n" +
				"\t\t 2. CD\n" +
				"\t\t 3. Vinilo\n" +
				"\t\t 4. Casete");
		System.out.print("\t\tEsperando instrucción: ");
		System.out.println();
		
		/** Control del dato introducido por el usuario */
		do{
			try{
				formato = new Integer(read.nextLine());
				if((formato < 1) || (formato > 4)){
					System.out.print("\n\t\tDebes introducir un formato dentro de las opciones del menu.\n" +
							"\t\tEsperando instrucción: ");
				}
			}
			catch( NumberFormatException ex){
				System.out.print("\n\t\tDebes introducir un valor numérico: ");
				opcion = -1;
			}
		}while((formato < 1) || (formato > 4));
		
		/** Creación del objeto que compararemos con los elementos de la lista */
		switch(formato){
			//Música
			case 1:
				comp = new Musica(album, autor);
				break;
				
			//CD
			case 2:
				comp = new CD(album, autor);
				break;
				
			//Vinilo
			case 3:
				comp = new Vinilo(album, autor);
				break;
				
			//Casete
			case 4:
				comp = new Casete(album, autor);
				break;
		}
		
		/** Comparación del objeto creado con los elementos de la lista:
		 *  	Si existe el elemento con el mismo formato, lo elimina.
		 *  	En caso contrario se indicará que no está en la lista.
		 */
		for(int i=0; i<getMusica().size(); i++){
			if(getMusica(i).equals(comp)){
				getMusica().remove(i);
				return true;
			}
		}
			
		return false;
	}
	

	/** 
	 * Función elimina una música de la lista dando su formato, album y autor.
	 * @param void
	 * @return boolean - true si lo elimina correctamente; false en caso contrario.
	 */
	private boolean borrarCliente() {
		boolean fallo = false;
		int codigo = -1;
		
		/** Control del código introducido por el usuario */
		do{
			try{
				System.out.print("\tIntroduce el código del cliente que desea borrar: ");
				codigo = new Integer(read.nextLine());
				fallo = false;
			}
			catch( NumberFormatException ex){
				System.out.print("\n\tDebes introducir un valor numérico: ");
				fallo = true;
			}
		}while(fallo);
		
		for(int i=0; i<getClientes().size(); i++){
			if(getClientes().get(i).getCodigo() == codigo){
				getClientes().remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	/** 
	 * Función para listar todas Las distintas músicas que posee la tienda
	 * @param void
	 * @return void
	 */
	private void listarMusica() {
		for(Musica musica : getMusica()){
			System.out.println(musica.imprimir());
		}
	}
	
	/** 
	 * Función para listar todas Las distintas músicas que posee la tienda
	 * @param void
	 * @return void
	 */
	private void listarClientes() {
		for(Cliente cliente : getClientes()){
			System.out.println(cliente.imprimir());
		}
	}
	
	/** 
	 * Función que realizará la impresión del menú
	 * @param void
	 * @return int - Elección del usuario
	 */
	private int menu(){
		int opcion = 0; 
		System.out.print("\n\nSelecciona la opción que quieres realizar:\n" +
				"1. Añadir música\n" +
				"2. Eliminar música\n" +
				"3. Listar músicas\n\n" +
				"4. Añadir cliente\n" +
				"5. Eliminar cliente\n" +
				"6. Listar clientes\n\n" +
				"7. Añadir venta\n" +
				"8. Eliminar venta\n" +
				"9. Listar ventas\n" +
				"0. Salir\n\n" +
				"Esperando instrucción: ");
		
		/** Repetir la petición de la opción mientras no esté dentro de las opciones posibles del menú */
		do{
			try{
				opcion = new Integer(read.nextLine());
				if((opcion < 0) || (opcion > 9)){
					System.out.print("\nDebes introducir un valor dentro de las opciones del menu.\n" +
							"Esperando instrucción: ");
				}
			}
			catch( NumberFormatException ex){
				System.out.print("\nDebes introducir un valor numérico: ");
				opcion = -1;
			}
		}while((opcion < 0) || (opcion > 9));

		return opcion;
	}
	
	/**
	 * Esta función se encarga buscar en la lista de clientes, cual es el proximo código que se encuentra
	 * disponible.
	 * @param void
	 * @return Integer - El código para el nuevo usuario
	 */
	private Integer getCodigo() {
		ArrayList<Integer> cod = new ArrayList<Integer>();
		int codigo = 0;
		
		/** Guardamos en un vector todos los códigos de los clientes */
		for(Cliente c : getClientes())
			cod.add(new Integer(c.getCodigo()));
		
		/** Ordenamos el vector */
		Arrays.sort(cod.toArray());
		
		/** La primera posición que no coincida con le número será la que se asignará al nuevo 
		 * cliente
		 */
		for(int i=0; i<cod.size(); i++){
			if(cod.get(i) != i){
				codigo = i;
				break;
			}
			else
				codigo = i+1;
		}
		
		return codigo;
	}
	
	/** 
	 * Función principal donde se instanciará la tienda de música
	 * @param args - Parámetros de entrada al programa
	 * @return void
	 */
	public static void main(String[] args) {
		Tienda tiendaMusica = new Tienda();
		
		/** Muestra el menu y obtiene la elección del usuario */
		do{
			tiendaMusica.setOpcion(tiendaMusica.menu());
			
			/** Selector para realizar las diferentes acciones en función del menu */
			switch(tiendaMusica.getOpcion()){
				// Añadir música
				case 1:
					System.out.println("\nAñadir música a la tienda");
					System.out.println("-------------------------");
					if(tiendaMusica.setMusica())
						System.out.println("Musica añadida correctamente");
					else
						System.out.println("Error al añadir la música");
					System.out.println();
					break;
					
				// Eliminar música	
				case 2: 
					System.out.println("\nEliminar música de la tienda");
					System.out.println("----------------------------");
					if(tiendaMusica.borrarMusica())
						System.out.println("Musica borrada correctamente");
					else
						System.out.println("Error al borrar la música");
					System.out.println();
					break;
					
				// Listar música
				case 3: 
					System.out.println("\nListar música de la tienda");
					System.out.println("--------------------------");
					tiendaMusica.listarMusica();
					System.out.println();
					break;
					
				// Añadir clientes		
				case 4: 
					System.out.println("\nAñadir clientes");
					System.out.println("---------------");
					int codigo = tiendaMusica.getCodigo();
					if(tiendaMusica.setClientes(new Cliente(codigo)))
						System.out.println("Cliente creado correctamente con código: " + codigo );
					else
						System.out.println("Error al añadir al cliente");
					System.out.println();
					
					break;
					
				case 5: // Eliminar clientes
					System.out.println("\nEliminar cliente");
					System.out.println("----------------");
					if(tiendaMusica.borrarCliente())
						System.out.println("Cliente borrado correctamente");
					else
						System.out.println("Error al borrar al cliente");
					System.out.println();
					break;
					
				case 6: // Listar clientes
					System.out.println("\nListar clientes");
					System.out.println("---------------");
					tiendaMusica.listarClientes();
					System.out.println();
					break;
					
				case 7: // Añadir ventas
					System.out.println("Opcion 7\n");
					break;
					
				case 8: // Eliminar ventas
					System.out.println("Opcion 8\n");
					break;
					
				case 9: // Listar ventas
					System.out.println("Opcion 9\n");
					break;
					
				case 0: // Salir
					System.out.println("Salir\n");
					break;
			}
		}while(tiendaMusica.getOpcion() != 0);
		
		tiendaMusica.read.close();
	}
}
