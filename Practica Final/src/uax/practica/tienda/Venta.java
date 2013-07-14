package uax.practica.tienda;

import java.io.IOException;

import uax.practica.musica.Musica;
/**
 * Clase que se encarga del tratamiento de las ventas.
 * @version v0.1 	24 Junio 2013
 * @author Alberto Hidalgo
 */
public class Venta {
	// VARIABLES
	private int codigo;
	private Musica musica;
	private Cliente cliente;
	private Fichero fichero;
	private boolean fallo;
	
	// CONSTRUCTORES
	Venta(Musica musica, Cliente cliente, int codigo){
		this.musica = musica;
		this.cliente = cliente;
		this.codigo = codigo;
		
		imprimir("Ventas");
	}

	// MÉTODOS
	/** Getter and Setter */
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Musica getMusica() {
		return musica;
	}

	public void setMusica(Musica musica) {
		this.musica = musica;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Fichero getFichero() {
		return fichero;
	}

	public void setFichero(Fichero fichero) {
		this.fichero = fichero;
	}	
	
	public boolean isFallo() {
		return fallo;
	}

	public void setFallo(boolean fallo) {
		this.fallo = fallo;
	} 
	
	public String imprimir(){
		String cad = "Venta\n";
		
		cad += 	"\tMusica: " + getMusica().getAlbum() + " - " + getMusica().getAutor() + "\n" +
				"\tAsignada al cliente: " + 
					getCliente().getNombre() + " " + getCliente().getApellidos()[0] + " " + getCliente().getApellidos()[1] + "\n" +
				"\tCon código: " + getCodigo();

		return cad;
	}
	
	/**
	 * Función que se encarga de escribir en el fichero
	 * @param string
	 */
	public void imprimir(String string) {
		setFichero(new Fichero("Ventas"));
		try {
			if(getFichero().escribirArchivo(this))
				fallo = false;
			else
				fallo = true;
		} catch (IOException e) {
			fallo = false;
		}
	}

}
