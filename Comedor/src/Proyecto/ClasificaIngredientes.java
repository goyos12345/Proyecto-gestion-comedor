package Proyecto;

import java.util.ArrayList;

public class ClasificaIngredientes {
	String Nombre;
	int ID;
	String Marca;
	ArrayList<Ingredientes> arrIngredientes = new ArrayList<>();
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
	public String getMarca() {
		return Marca;
	}
	public void setMarca(String marca) {
		Marca = marca;
	}
	public ArrayList<Ingredientes> getArrIngredientes() {
		return arrIngredientes;
	}
	public void setArrIngredientes(ArrayList<Ingredientes> arrIngredientes) {
		this.arrIngredientes = arrIngredientes;
	}
	public ClasificaIngredientes(String nombre, int iD, String marca, ArrayList<Ingredientes> arrIngredientes) {
		super();
		Nombre = nombre;
		ID = iD;
		Marca = marca;
		this.arrIngredientes = arrIngredientes;
	}
	

	

}
