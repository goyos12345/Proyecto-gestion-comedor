package Proyecto;

public class Ingredientes extends Objeto{
	int caducidadDia;
	int caducidadMes;
	int caducidadYear;
	public int getCaducidadDia() {
		return caducidadDia;
	}
	public void setCaducidadDia(int caducidadDia) {
		this.caducidadDia = caducidadDia;
	}
	public int getCaducidadMes() {
		return caducidadMes;
	}
	public void setCaducidadMes(int caducidadMes) {
		this.caducidadMes = caducidadMes;
	}
	public int getCaducidadYear() {
		return caducidadYear;
	}
	public void setCaducidadYear(int caducidadYear) {
		this.caducidadYear = caducidadYear;
	}
	public Ingredientes(String nombre, int iD, String contenedor, int caducidadDia, int caducidadMes,
			int caducidadYear) {
		super(nombre, iD, contenedor);
		this.caducidadDia = caducidadDia;
		this.caducidadMes = caducidadMes;
		this.caducidadYear = caducidadYear;
	}
	

}
