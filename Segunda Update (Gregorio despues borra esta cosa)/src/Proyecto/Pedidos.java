package Proyecto;

public class Pedidos {
int iD;
String fecha;
String ingredientes;
int nroLote;
String cantidadKg;
public int getiD() {
	return iD;
}
public void setiD(int iD) {
	this.iD = iD;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public String getIngredientes() {
	return ingredientes;
}
public void setIngredientes(String ingredientes) {
	this.ingredientes = ingredientes;
}
public int getNroLote() {
	return nroLote;
}
public void setNroLote(int nroLote) {
	this.nroLote = nroLote;
}
public String getCantidadKg() {
	return cantidadKg;
}
public void setCantidadKg(String cantidadKg) {
	this.cantidadKg = cantidadKg;
}
public Pedidos(int iD, String fecha, String ingredientes, int nroLote, String cantidadKg) {
	super();
	this.iD = iD;
	this.fecha = fecha;
	this.ingredientes = ingredientes;
	this.nroLote = nroLote;
	this.cantidadKg = cantidadKg;
}


}
