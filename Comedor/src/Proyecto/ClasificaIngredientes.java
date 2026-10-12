package Proyecto;

import java.util.ArrayList;

public class ClasificaIngredientes {
	String Nombre;
	int ID;
	String Marca;
	ArrayList<LoteIngredientes> arrIngredientes = new ArrayList<>();
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
	public ArrayList<LoteIngredientes> getArrIngredientes() {
		return arrIngredientes;
	}
	public void setArrIngredientes(ArrayList<LoteIngredientes> arrIngredientes) {
		this.arrIngredientes = arrIngredientes;
	}
	public ClasificaIngredientes(String nombre, int iD, String marca, ArrayList<LoteIngredientes> arrIngredientes) {
		super();
		Nombre = nombre;
		ID = iD;
		Marca = marca;
		this.arrIngredientes = arrIngredientes;
	}
	

	

}
