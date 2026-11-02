package Proyecto;

import java.util.ArrayList;

public class Menu {

	int ID;
	String nombre;
	ArrayList<String> Dias = new ArrayList<>();
	ArrayList<String> Platos = new ArrayList<>();
	int horarioMin;
	int horarioHr;

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

	public ArrayList<String> getDias() {
		return Dias;
	}

	public void setDias(ArrayList<String> dias) {
		Dias = dias;
	}

	public ArrayList<String> getPlatos() {
		return Platos;
	}

	public void setPlatos(ArrayList<String> platos) {
		Platos = platos;
	}

	public int getHorarioMin() {
		return horarioMin;
	}

	public void setHorarioMin(int horarioMin) {
		this.horarioMin = horarioMin;
	}

	public int getHorarioHr() {
		return horarioHr;
	}

	public void setHorarioHr(int horarioHr) {
		this.horarioHr = horarioHr;
	}

	public Menu(int iD, String nombre, ArrayList<String> dias, ArrayList<String> platos, int horarioMin,
			int horarioHr) {
		super();
		ID = iD;
		this.nombre = nombre;
		Dias = dias;
		Platos = platos;
		this.horarioMin = horarioMin;
		this.horarioHr = horarioHr;
	}

	

}
