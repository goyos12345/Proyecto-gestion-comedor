package Proyecto;

public class Objeto {
	String Nombre;
	int ID;
	String Contenedor;
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getContenedor() {
		return Contenedor;
	}
	public void setContenedor(String contenedor) {
		Contenedor = contenedor;
	}
	public Objeto(String nombre, int iD, String contenedor) {
		super();
		Nombre = nombre;
		ID = iD;
		Contenedor = contenedor;
	}
	

}
