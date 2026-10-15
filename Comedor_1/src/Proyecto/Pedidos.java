package Proyecto;

public class Pedidos {
int iD;
int fechaDia;
int fechaMes;
int fechaAño;
String ingredientes;
String proveedor;
String nroLote;
int cantidadKg;
public int getiD() {
	return iD;
}
public void setiD(int iD) {
	this.iD = iD;
}

public String getIngredientes() {
	return ingredientes;
}
public void setIngredientes(String ingredientes) {
	this.ingredientes = ingredientes;
}

public int getFechaDia() {
	return fechaDia;
}
public void setFechaDia(int fechaDia) {
	this.fechaDia = fechaDia;
}
public int getFechaMes() {
	return fechaMes;
}
public void setFechaMes(int fechaMes) {
	this.fechaMes = fechaMes;
}
public int getFechaAño() {
	return fechaAño;
}
public void setFechaAño(int fechaAño) {
	this.fechaAño = fechaAño;
}
public String getProveedor() {
	return proveedor;
}
public void setProveedor(String proveedor) {
	this.proveedor = proveedor;
}
public String getNroLote() {
	return nroLote;
}
public void setNroLote(String nroLote) {
	this.nroLote = nroLote;
}
public int getCantidadKg() {
	return cantidadKg;
}
public void setCantidadKg(int cantidadKg) {
	this.cantidadKg = cantidadKg;
}
public Pedidos(int iD, int fechaDia, int fechaMes, int fechaAño, String ingredientes, String proveedor, String nroLote,
		int cantidadKg) {
	super();
	this.iD = iD;
	this.fechaDia = fechaDia;
	this.fechaMes = fechaMes;
	this.fechaAño = fechaAño;
	this.ingredientes = ingredientes;
	this.proveedor = proveedor;
	this.nroLote = nroLote;
	this.cantidadKg = cantidadKg;
}




}
