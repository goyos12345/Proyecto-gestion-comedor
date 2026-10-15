package Proyecto;

public class Proveedores {
	int ID;
	String nombre;
	int numRegional;
	int numero;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumRegional() {
		return numRegional;
	}
	public void setNumRegional(int numRegional) {
		this.numRegional = numRegional;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Proveedores(int iD, String nombre, int numRegional, int numero) {
		ID = iD;
		this.nombre = nombre;
		this.numRegional = numRegional;
		this.numero = numero;
	}
	
	
}
