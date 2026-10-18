package Proyecto;

import javax.swing.table.DefaultTableModel;

public class gestor {
	MySQL bd = new MySQL();

	public static void main(String[] args) {
		VentanaPrincipal ven1 = new VentanaPrincipal();

		ven1.setVisible(true);

	}

	public void listarLoteIngrdientes(DefaultTableModel modelo) {
		bd.listarLoteIngrdientes(modelo);

	}

	public void altaLoteIngredientes(String n, String c, int dia, int mes, int anno, int cant, String m) {
		bd.altaLoteIngredientes(n, c, dia, mes, anno, cant, m);

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

}
