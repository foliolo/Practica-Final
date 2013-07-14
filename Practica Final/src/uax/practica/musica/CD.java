package uax.practica.musica;


/**
 * Clase CD que hereda de Música y añade la propiedad: número de canciones. 
 * @version v0.1 	25 Junio 2013
 * @author Alberto Hidalgo
 */
public class CD extends Musica {
	private int numCanciones;
	
	public CD(){
		super();
		boolean fallo = false;
		do{
			try{
				System.out.println("\tIntroduce el número de canciones: ");
				setNumCanciones(Integer.parseInt(read.nextLine()));

				fallo = false;
			}
			catch(IllegalArgumentException ex){
				System.out.println("\tDebe introducir un número correcto");
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
