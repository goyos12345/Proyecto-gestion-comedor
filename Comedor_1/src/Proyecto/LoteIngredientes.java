package Proyecto;

public class LoteIngredientes extends Objeto{
	int caducidadDia;
	int caducidadMes;
	int caducidadYear;
	String marca;
	int cantidad;
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
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public LoteIngredientes(String nombre, int iD, String contenedor, int caducidadDia, int caducidadMes, int caducidadYear,
			String marca, int cantidad) {
		super(nombre, iD, contenedor);
		this.caducidadDia = caducidadDia;
		this.caducidadMes = caducidadMes;
		this.caducidadYear = caducidadYear;
		this.marca = marca;
		this.cantidad = cantidad;
	}
	
	

}
