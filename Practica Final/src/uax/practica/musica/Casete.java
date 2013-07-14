package uax.practica.musica;


/**
 * Clase Casete que hereda de Música y añade la propiedad: número de canciones por ambas caras. 
 * @version v0.1 	25 Junio 2013
 * @author Alberto Hidalgo
 */
public class Casete extends Musica {
	private int numCancionesA;
	private int numCancionesB;
	
	/** Constructores */
	public Casete(){
		super();
		boolean fallo = false;
		do{
//			try{
//				System.out.println("\tIntroduce el número de canciones de la cara A: ");
//				setNumCancionesA(read.nextInt());
//				
//				System.out.println("\tIntroduce el número de canciones de la cara B: ");
//				setNumCancionesB(read.nextInt());
//				fallo = false;
//			}
//			catch(NumberFormatException ex){
//				System.out.println("\tDebe introducir un número correcto:");
//				fallo = true;
//			}
			try{
				System.out.println("\tIntroduce el número de canciones de la cara A: ");
				setNumCancionesA(Integer.parseInt(read.nextLine()));
				System.out.println("\tIntroduce el número de canciones de la cara B: ");
				setNumCancionesB(Integer.parseInt(read.nextLine()));

				fallo = false;
			}
			catch(IllegalArgumentException ex){
				System.out.println("\tDebe introducir un número correcto");
				fallo = true;
			}
		}while(fallo);
		
		
	}
	
	public Casete(String album, String autor){
		super(album, autor);
	}
	
	/** Getter and Setter */
	public int getNumCancionesA() {
		return numCancionesA;
	}
	
	public void setNumCancionesA(int numcancionesA) {
		this.numCancionesA = numcancionesA;
	}
	
	public int getNumCancionesB() {
		return numCancionesB;
	}
	
	public void setNumCancionesB(int numcancionesB) {
		this.numCancionesB = numcancionesB;
	}
	
	/** Funciones */
	public String imprimir(){
		return super.imprimir() + 	"\tCanciones cara A: " + getNumCancionesA() + "\n" +
									"\tCanciones cara B: " + getNumCancionesB() + "\n" +
									"\tTipo de formato: Casete\n";
	}
	
	public boolean equals(Musica m){
		if(m instanceof Casete){
			if( getAlbum().equals(m.getAlbum()) && getAutor().equals(m.getAutor()))
				return true;
		}

		return false;
	}

}
