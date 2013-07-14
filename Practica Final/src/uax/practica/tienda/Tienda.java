package uax.practica.tienda;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import uax.practica.musica.CD;
import uax.practica.musica.Casete;
import uax.practica.musica.Musica;
import uax.practica.musica.Vinilo;

/**
 * Clase principal del programa que se encarga de lanzar el men� y llamar a las funciones principales
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
	 * Constructor de la tienda donde se inicializan las variables y el men�. 
	 */
	Tienda(){
		musica = new ArrayList<Musica>();
		clientes = new ArrayList<Cliente>();
		ventas = new ArrayList<Venta>();
		read = new Scanner(System.in);
		setOpcion(0);
		actualizarFichero("Ventas", 0); // Eliminamos el fichero del d�a. Al estar vac�a la lista, el c�digo de venta no influye 
	}

	/** M�todos Getter and Setter */
	public int getOpcion() {
		return opcion;
	}
	
	public void setOpcion(int opcion) {
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
	 * Funci�n que a�ade las ventas a la estructura
	 * @param codigo - C�digo que se le asigna a la venta
	 * @return boolean - true si se realiza la venta correctamente; false en caso contrario.
	 */
	public boolean setVentas(int codigo){
		try{
			// Para realiza una venta, comprobamos que hay M�sica y Clientes en las estructuras de datos.
			if((getMusica().size() != 0) || (getClientes().size() != 0)){
				//Localizamos la m�sica que deseamos vender
				int locMusica = localizarMusica();
				//Localizamos el cliente al que realizaremos al venta
				int locCliente = localizarCliente();
				
				Venta venta = new Venta(getMusica().get(locMusica), getClientes().get(locCliente), codigo);
				
				//Si se ha localizado la M�sica y el cliente, procedemos a a�adir la venta a la estructura de datos
				//Al realizar la venta procedemos a eliminar la m�sica de nuestra tienda.
				if(getVentas().add(venta) && !venta.isFallo()){
					getMusica().remove(locMusica);
					return true;
				}
				return false;
			}
			// Si algunas de las estructuras de datos esta vac�a, lo indicamos al usuario.
			else{
				System.out.println("\tLa lista de M�sica o Clientes est� vac�a.");
				return false;
			}
		}
		catch(ArrayIndexOutOfBoundsException ex){
			return false;
		}
	}
	

	/** 
	 * Obtiene el elemento indicado en la posici�n de la lista de m�sica. 
	 * En caso de ser -1: Eliminamos el �ltimo elemento
	 */
	public Musica getMusica(int posicion) {
		if(posicion == -1)
			return musica.get(musica.size()-1);
		else
			return musica.get(posicion);
	}
	
	/** Devuelve la lista de m�sica. */
	public ArrayList<Musica> getMusica() {
		return musica;
	}
	
	/** Inserta un elemento nuevo de m�sica en la lista de m�sica. */
	public boolean setMusica() {
		int formato = -1;
		System.out.println("\tIntroduce el formato de la m�sica: \n" +
				"\t 1. CD\n" +
				"\t 2. Vinilo\n" +
				"\t 3. Casete");
		System.out.println("\tEsperando instrucci�n: ");
		
		do{
			try{
				formato = new Integer(read.nextLine());
				if((formato < 1) || (formato > 3)){
					System.out.print("\n\tDebes introducir un formato dentro de las opciones del menu.\n" +
							"\tEsperando instrucci�n: ");
				}
			}
			catch( NumberFormatException ex){
				System.out.print("\n\tDebes introducir un valor num�rico: ");
				opcion = -1;
			}
		}while((formato < 1) || (formato > 3));
		
		switch(formato){
			//CD
			case 1:
				if(this.musica.add(new CD()))
					return true;
			//Vinilo
			case 2:
				if(this.musica.add(new Vinilo()))
					return true;
			//Casete
			case 3:
				if(this.musica.add(new Casete()))
					return true;
		}
		return false;
	}
	
	/** 
	 * Funci�n elimina una m�sica de la lista dando su formato, album y autor.
	 * @param void
	 * @return boolean - true si lo elimina correctamente; false en caso contrario.
	 */
	private boolean borrarMusica() {
		/** La funci�n "localizarCliente" devuelve -1 en caso de no encontrar el elemento,
		 * por lo que controlamos la excepci�n para indicar que no se ha eliminado el elemento.
		 */
		try{
			if(getMusica().size() != 0){
				getMusica().remove(localizarMusica());
				return true;
			}
			else{
				System.out.println("\tLista de m�sica vac�a.");
				return false;
			}
		}
		catch(ArrayIndexOutOfBoundsException ex){
			return false;
		}
	}

	/** 
	 * Funci�n elimina una m�sica de la lista dando su formato, album y autor.
	 * @param void
	 * @return boolean - true si lo elimina correctamente; false en caso contrario.
	 */
	private boolean borrarCliente() {
		/** La funci�n "localizarCliente" devuelve -1 en caso de no encontrar el elemento,
		 * por lo que controlamos la excepci�n para indicar que no se ha eliminado el elemento.
		 */
		try{
			if(getClientes().size() != 0){
				getClientes().remove(localizarCliente());
				return true;
			}
			else{
				System.out.println("\tLista de clientes vac�a.");
				return false;
			}
		}
		catch(ArrayIndexOutOfBoundsException ex){
			System.out.println("\n\tCliente no encontrado");
			return false;
		}
	}
	
	/** 
	 * Funci�n elimina una m�sica de la lista dando su formato, album y autor.
	 * @param void
	 * @return boolean - true si lo elimina correctamente; false en caso contrario.
	 */
	private boolean borrarVenta() {
		/** La funci�n "localizarVenta" devuelve -1 en caso de no encontrar el elemento,
		 * por lo que controlamos la excepci�n para indicar que no se ha eliminado el elemento.
		 */
		try{
			if(getVentas().size() != 0){
				int locVenta = localizarVenta();
				actualizarFichero("Ventas", getVentas().get(locVenta).getCodigo());
				getVentas().remove(locVenta);
				return true;
			}
			else{
				System.out.println("\tLista de ventas vac�a.");
				return false;
			}
				
		}
		catch(ArrayIndexOutOfBoundsException ex){
			System.out.println("\nVenta no encontrado");
			return false;
		}
	}
	
	/** 
	 * Esta funci�n se encargar� de actualizar el fichero de ventas eliminando la venta borrada por le usuario.
	 * @param fich - Indica el nombre del fichero
	 * @param codVenta - Indica la posici�n de la venta
	 * @return boolean - true si lo elimina correctamente; false en caso contrario.
	 */
	private void actualizarFichero(String fich, int codVenta) {
		//Eliminamos el fichero
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fichero = fich + "_" + sdf.format(date.getTime()) + ".txt";
		
		File f = new File(fichero);
		f.delete();
		
		//Re-escribimos el fichero con todas las ventas, menos la que tenga el mismo c�digo que la que hemos eliminado.
		if(getVentas().size() != 0)
			for (Venta venta : getVentas()) {
				if(venta.getCodigo() != codVenta)
					venta.imprimir("Venta");
			}
	}

	/** 
	 * Funci�n para listar todas Las distintas m�sicas que posee la tienda.
	 * @param void
	 * @return void
	 */
	private void listarMusica() {
		if(getMusica().size() != 0)
			for(Musica musica : getMusica()){
				System.out.println(musica.imprimir());
			}
		else
			System.out.println("\tLista de m�sica vacia\n");
	}

	/** 
	 * Funci�n para listar los clientes que posee la tienda.
	 * @param void
	 * @return void
	 */
	private void listarClientes() {
		if(getClientes().size() != 0)
			for(Cliente cliente : getClientes()){
				System.out.println(cliente.imprimir());
			}
		else
			System.out.println("\tLista de clientes vacia\n");
	}
	
	/** 
	 * Funci�n para listar las ventas de m�sica que realiza la tienda.
	 * @param void
	 * @return void
	 */
	private void listarVentas() {
		if(getVentas().size() != 0)
			for(Venta venta : getVentas()){
				System.out.println(venta.imprimir());
			}
		else
			System.out.println("\tLista de ventas vac�a\n");
	}
	
	/** 
	 * Funci�n que realizar� la impresi�n del men�.
	 * @param void
	 * @return int - Elecci�n del usuario
	 */
	private int menu(){
		int opcion = 0; 
		System.out.print("\n\nSelecciona la opci�n que quieres realizar:\n" +
				"1. A�adir m�sica\n" +
				"2. Eliminar m�sica\n" +
				"3. Listar m�sicas\n\n" +
				"4. A�adir cliente\n" +
				"5. Eliminar cliente\n" +
				"6. Listar clientes\n\n" +
				"7. A�adir venta\n" +
				"8. Eliminar venta\n" +
				"9. Listar ventas\n" +
				"0. Salir\n\n" +
				"Esperando instrucci�n: ");
		
		/** Repetir la petici�n de la opci�n mientras no est� dentro de las opciones posibles del men� */
		do{
			try{
				opcion = new Integer(read.nextLine());
				if((opcion < 0) || (opcion > 9)){
					System.out.print("\n\tDebes introducir un valor dentro de las opciones del menu.\n" +
							"Esperando instrucci�n: ");
				}
			}
			catch( NumberFormatException ex){
				System.out.print("\n\tDebes introducir un valor num�rico: ");
				opcion = -1;
			}
		}while((opcion < 0) || (opcion > 9));

		return opcion;
	}
	
	/**
	 * Esta funci�n se encarga buscar en la lista de clientes, cual es el pr�ximo c�digo que se encuentra
	 * disponible.
	 * @param void
	 * @return Integer - El c�digo para el nuevo usuario
	 */
	private Integer getCodigoCliente() {
		ArrayList<Integer> cod = new ArrayList<Integer>();
		int codigo = 0;
		
		/** Guardamos en un vector todos los c�digos de los clientes */
		for(Cliente c : getClientes())
			cod.add(new Integer(c.getCodigo()));
		
		/** Ordenamos el vector */
		Arrays.sort(cod.toArray());
		
		/** La primera posici�n que no coincida con le n�mero ser� la que se asignar� al nuevo cliente
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
	 * Esta funci�n se encarga buscar en la lista de ventas, cual es el pr�ximo c�digo que se encuentra
	 * disponible.
	 * @param void
	 * @return Integer - El c�digo para el nuevo usuario
	 */
	private Integer getCodigoVenta() {
		ArrayList<Integer> cod = new ArrayList<Integer>();
		int codigo = 0;
		
		/** Guardamos en un vector todos los c�digos de las ventas */
		for(Venta v : getVentas())
			cod.add(new Integer(v.getCodigo()));
		
		/** Ordenamos el vector */
		Arrays.sort(cod.toArray());
		
		/** La primera posici�n que no coincida con le n�mero ser� la que se asignar� al nuevo 
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
	 * M�todo para localizar un elemento de m�sica en la lista introduciendo el usuario en
	 * los datos de comparaci�n.
	 * @param void
	 * @return int - posici�n del elemento encontrado, en caso de no encontrar el elemento, devuelve -1 
	 */
	private int localizarMusica(){
		Musica comp = null;
		String album, autor;
		int formato = -1;

		/** Pedimos al usuario que introduzca los campos de comparaci�n */
		System.out.println("\tIntroduce el nombre del album: ");
		album = read.nextLine().trim();
		System.out.println("\tIntroduce el nombre del autor: ");
		autor = read.nextLine().trim();
		
		/** Pedimos al usuario el formato del elemento que desea eliminar de la lista */
		System.out.println("\tIntroduce el formato de la m�sica: \n" +
				"\t\t 1. CD\n" +
				"\t\t 2. Vinilo\n" +
				"\t\t 3. Casete");
		System.out.print("\t\tEsperando instrucci�n: ");
		System.out.println();
		
		/** Control del dato introducido por el usuario */
		do{
			try{
				formato = new Integer(read.nextLine());
				if((formato < 1) || (formato > 3)){
					System.out.print("\n\t\tDebes introducir un formato dentro de las opciones del menu.\n" +
							"\t\tEsperando instrucci�n: ");
				}
			}
			catch( NumberFormatException ex){
				System.out.print("\n\t\tDebes introducir un valor num�rico: ");
				opcion = -1;
			}
		}while((formato < 1) || (formato > 3));
		
		/** Creaci�n del objeto que compararemos con los elementos de la lista */
		switch(formato){
			//CD
			case 1:
				comp = new CD(album, autor);
				break;
			//Vinilo
			case 2:
				comp = new Vinilo(album, autor);
				break;
			//Casete
			case 3:
				comp = new Casete(album, autor);
				break;
		}
		
		/** Comparaci�n del objeto creado con los elementos de la lista:
		 *  	Si existe el elemento con el mismo formato, lo elimina.
		 *  	En caso contrario se indicar� que no est� en la lista.
		 */
		for(int i=0; i<getMusica().size(); i++){
			if(getMusica(i).equals(comp)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * M�todo para localizar un cliente en la lista de clientes introduciendo el c�digo de cliente en
	 * los datos de comparaci�n.
	 * @param void
	 * @return int - posici�n del elemento encontrado, en caso de no encontrar el elemento, devuelve -1
	 */
	private int localizarCliente() {
		boolean fallo = false;
		int codigo = -1;
		
		/** Control del c�digo introducido por el usuario */
		do{
			try{
				System.out.print("\tIntroduce el c�digo del cliente: ");
				codigo = new Integer(read.nextLine());
				fallo = false;
			}
			catch( NumberFormatException ex){
				System.out.println("\n\tDebes introducir un valor num�rico: ");
				fallo = true;
			}
		}while(fallo);
		
		for(int i=0; i<getClientes().size(); i++){
			if(getClientes().get(i).getCodigo() == codigo){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * M�todo para localizar una venta en la lista de ventas introduciendo el c�digo de ventas en
	 * los datos de comparaci�n.
	 * @param void
	 * @return int - posici�n del elemento encontrado, en caso de no encontrar el elemento, devuelve -1
	 */
	private int localizarVenta() {
		boolean fallo = false;
		int codigo = -1;
		
		/** Control del c�digo introducido por el usuario */
		do{
			try{
				System.out.print("\tIntroduce el c�digo de la venta: ");
				codigo = new Integer(read.nextLine());
				fallo = false;
			}
			catch( NumberFormatException ex){
				System.out.println("\n\tDebes introducir un valor num�rico: ");
				fallo = true;
			}
		}while(fallo);
		
		for(int i=0; i<getVentas().size(); i++){
			if(getVentas().get(i).getCodigo() == codigo){
				return i;
			}
		}
		return -1;
	}
	
	/** 
	 * Funci�n principal donde se instanciar� la tienda de m�sica y se ejecutar�n las diferentes acciones
	 * del men�.
	 * @param args - Par�metros de entrada al programa
	 * @return void
	 */
	public static void main(String[] args) {
		Tienda tiendaMusica = new Tienda();
		
		/** Muestra el menu y obtiene la elecci�n del usuario */
		do{
			tiendaMusica.setOpcion(tiendaMusica.menu());
			
			/** Selector para realizar las diferentes acciones en funci�n del menu */
			switch(tiendaMusica.getOpcion()){
				// A�adir m�sica
				case 1:
					System.out.println("\nA�adir m�sica a la tienda");
					System.out.println("-------------------------");
					if(tiendaMusica.setMusica())
						System.out.println("Musica a�adida correctamente");
					else
						System.out.println("\tError al a�adir la m�sica");
					break;
					
				// Eliminar m�sica	
				case 2: 
					System.out.println("\nEliminar m�sica de la tienda");
					System.out.println("----------------------------");
					if(tiendaMusica.borrarMusica())
						System.out.println("Musica borrada correctamente");
					else
						System.out.println("\tError al borrar la m�sica");
					break;
					
				// Listar m�sica
				case 3: 
					System.out.println("\nListar m�sica de la tienda");
					System.out.println("--------------------------");
					tiendaMusica.listarMusica();
					break;
					
				// A�adir clientes		
				case 4: 
					System.out.println("\nA�adir clientes");
					System.out.println("---------------");
					int codigoCliente = tiendaMusica.getCodigoCliente();
					if(tiendaMusica.setClientes(new Cliente(codigoCliente)))
						System.out.println("\tCliente creado correctamente con c�digo: " + codigoCliente );
					else
						System.out.println("\tError al a�adir al cliente");
					break;
					
				// Eliminar clientes
				case 5: 
					System.out.println("\nEliminar cliente");
					System.out.println("----------------");
					if(tiendaMusica.borrarCliente())
						System.out.println("\tCliente borrado correctamente");
					else
						System.out.println("\tError al borrar al cliente");
					break;
					
				// Listar clientes
				case 6: 
					System.out.println("\nListar clientes");
					System.out.println("---------------");
					tiendaMusica.listarClientes();
					break;
					
				// A�adir ventas
				case 7: 
					System.out.println("\nA�adir ventas");
					System.out.println("-------------");
					int codigoVenta = tiendaMusica.getCodigoVenta();
					if(tiendaMusica.setVentas(codigoVenta))
						System.out.println("\tVenta realizada correctamente con c�digo: " + codigoVenta);
					else
						System.out.println("\tError al realizar la venta");
					break;
					
				// Eliminar ventas
				case 8: 
					System.out.println("\nEliminar venta");
					System.out.println("--------------");
					if(tiendaMusica.borrarVenta())
						System.out.println("\tVenta borrada correctamente");
					else
						System.out.println("\tError al borrar la venta");
					break;
					
				// Listar ventas
				case 9: 
					System.out.println("\nListar ventas");
					System.out.println("-------------");
					tiendaMusica.listarVentas();
					break;
					
				// Salir
				case 0: 
					System.out.println("\tSalir\n");
					break;
			}
			System.out.println();
		}while(tiendaMusica.getOpcion() != 0);
		
		tiendaMusica.read.close();
	}
}
