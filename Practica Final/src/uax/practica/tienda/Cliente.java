package uax.practica.tienda;

import java.util.Scanner;

/**
 * Clase Cliente que guarda la información de los usuarios que desean adquirir música de la tienda
 * @version v0.1 	24 Junio 2013
 * @author Alberto Hidalgo
 */
public class Cliente {
	
	private String nombre;
	private String[] apellidos = new String[2];
	private Integer codigo;
	Scanner read = new Scanner(System.in);

	/** Constructores */
	Cliente(Integer codigo){
		System.out.println("\tIntroduce el nombre del cliente: ");
		setNombre(read.nextLine().trim());
		System.out.println("\tIntroduce el primer apellido: ");
		setApellidos(read.nextLine().trim(), 0);
		System.out.println("\tIntroduce el segundo apellido: ");
		setApellidos(read.nextLine().trim(), 1);
		
		/** Asignamos el código de forma incremental para todos los clientes */
		this.codigo = codigo;
	}
	
	/** Getter and Setter */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String[] getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String[] apellidos) {
		this.apellidos = apellidos;
	}
	
	public void setApellidos(String apellido, int pos){
		this.apellidos[pos] = apellido;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public boolean equals(Cliente cliente){
		if(this.getCodigo() == cliente.getCodigo())
			return true;
		return false;
	}
	
	public String imprimir(){
		String cad = "Cliente\n";
		
		cad += 	"\tNombre: " + getNombre() + "\n" +
				"\tApellidos: " + getApellidos()[0] + " " + getApellidos()[1] + "\n" +
				"\tCódigo: " + getCodigo() + "\n";
		
		return cad;
	}
}
