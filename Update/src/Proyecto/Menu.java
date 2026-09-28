package Proyecto;

public class Menu {
	
	int ID;
	String nombre;
	String dias;
	String platos;
	String horario;
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
	public String getDias() {
		return dias;
	}
	public void setDias(String dias) {
		this.dias = dias;
	}
	public String getPlatos() {
		return platos;
	}
	public void setPlatos(String platos) {
		this.platos = platos;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public Menu(int iD, String nombre, String dias, String platos, String horario) {
		super();
		ID = iD;
		this.nombre = nombre;
		this.dias = dias;
		this.platos = platos;
		this.horario = horario;
	}
	
	
}
