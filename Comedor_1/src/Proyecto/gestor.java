package Proyecto;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class gestor {
	MySQL bd = new MySQL();

	public static void main(String[] args) {
		VentanaLogin ven1 = new VentanaLogin();

		ven1.setVisible(true);

	}

	public void listarLoteIngrdientes(DefaultTableModel modelo) {
		bd.listarLoteIngrdientes(modelo);

	}

	public void altaLoteIngredientes(String n, String c, int dia, int mes, int anno, int cant, int IdPro) {
		bd.altaLoteIngredientes(n, c, dia, mes, anno, cant, IdPro);

	}

	public void bajaLoteIngredientes(int s) {
		bd.bajaLoteIngredientes(s);

	}

	public void listarComidas(DefaultTableModel m) {
		bd.listarComida(m);
	}

	public void altaComidas(String n, String c, int cant, boolean pr, boolean fr) {
		bd.altaComida(n, c, cant, pr, fr);

	}

	public void BajaComidas(int s) {
		bd.bajaComidas(s);
	}

	public void ListarMenus(DefaultTableModel t) {
		bd.listarMenus(t);
	}

	public void AltaMenus(String nombre, String dias, String platos, int hora, int minuto) {
		bd.altaMenu(nombre, dias, platos, hora, minuto);
	}

	public void BajaMenus(int id) {
		bd.bajaMenu(id);
	}

	public void ListarPedidos(DefaultTableModel m) {
		bd.listarPedidos(m);
	}

	public void AltaPedidos(int dia, int mes, int anno, String ing, int IdPrv, int nro, int cant) {
		bd.AltaPedidos(dia, mes, anno, ing, IdPrv, nro, cant);
	}

	public void BajaPedidos(int s) {
		bd.bajaPedidos(s);
	}

	public void NombreProv(ArrayList<String> s) {
		bd.ListarProveedoresArreglo(s);
	}

	public int encontrarProveedor(int p, String n) {
		p = bd.encontrarIdProveedor(p, n);
		return p;
	}

	public void listarProveedores(DefaultTableModel t) {
		bd.ListarProveedores(t);
	}

	public void altaProveedores(String n, String t, String p) {
		bd.AltaProveedores(n, t, p);
	}

	public void bajaProveedores(int s) {
		bd.BajaProveedores(s);
	}

	public boolean ProvExiste(boolean p, String n) {
		bd.comprobarProveedor(p, n);
		System.out.println("g" + p);
		return p;
	}

	public void TxtsProveedor(int s, JTextField n, JTextField t) {
		bd.LabelsProveedores(s, n, t);

	}

	public void ModificarProveedor(int s, String n, String t) {
		bd.ModificarProveedores(s, n, t);
	}

	public void TxtsPedidos(int s, JSpinner dia, JSpinner mes, JSpinner año, JSpinner can, JTextField ing,
			JTextField nro, JComboBox<String> comboPr) {
		bd.LabelsPedidos(s, dia, mes, año, can, ing, nro, comboPr);
	}

	public void IdProv(String id) {
		bd.encontrarProveedorNombre(id);
	}

	public void ModificarPedido(int s, int a, int m, int d, String i, int iP, int n, int c) {
		bd.ModificarPedido(s, a, m, d, i, iP, n, c);
	}

	public void ModificarIngrediente(int id, String n, String c, int can, int dia, int mes, int anno, int idPr) {
		bd.ModificarLoteIngredientes(id, n, c, can, dia, mes, anno, idPr);
		System.out.println(anno);

	}

	public void TxtsIngredientes(int s, JTextField txtN, JTextField txtC, JSpinner dia, JSpinner mes, JSpinner año,
			JComboBox comboPr, JSpinner can) {
		bd.LabelsLoteIngredientes(s, txtN, txtC, dia, mes, año, comboPr, can);

	}

}
