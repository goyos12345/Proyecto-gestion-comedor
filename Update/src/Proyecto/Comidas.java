package Proyecto;

import java.util.ArrayList;

public class Comidas extends Objeto {
	ArrayList<String> Ingredientes =  new ArrayList<>();
	boolean Preparado;
	boolean Frizado;
	
	public ArrayList<String> getIngredientes() {
		return Ingredientes;
	}
	public void setIngredientes(ArrayList<String> ingredientes) {
		Ingredientes = ingredientes;
	}
	public boolean isPreparado() {
		return Preparado;
	}
	public void setPreparado(boolean preparado) {
		Preparado = preparado;
	}
	public boolean isFrizado() {
		return Frizado;
	}
	public void setFrizado(boolean frizado) {
		Frizado = frizado;
	}
	public Comidas(String nombre, int iD, String contenedor, ArrayList<String> ingredientes, boolean preparado,
			boolean frizado) {
		super(nombre, iD, contenedor);
		Ingredientes = ingredientes;
		Preparado = preparado;
		Frizado = frizado;
	}
	
	
	
	

}
