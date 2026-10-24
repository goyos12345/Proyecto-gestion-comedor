package Proyecto;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MySQL {

	private final String DB_URL = "jdbc:mysql://localhost:3306/comedor?useSSL=false";

	private final String DB_USER = "root";

	private final String DB_PASS = "";

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}

	public void listarLoteIngrdientes(DefaultTableModel modelo) {

		String sentencia = "SELECT ID, Nombre, Contenedor, Cantidad, Vencimiento, Marca FROM lote_ingredientes;";

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentencia);

			ResultSet rs = declaracionSQL1.executeQuery();

			modelo.setRowCount(0); // Borra cont tabla

			while (rs.next()) {

				String idIng = rs.getString(1);

				String nombre = rs.getString(2);

				String contenedor = rs.getString(3);

				String cantidad = rs.getString(4);

				String vencimiento = rs.getString(5);

				String marca = rs.getString(6);

				Object[] fila = { idIng, nombre, vencimiento, contenedor, marca, cantidad };

				modelo.addRow(fila);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}

	public void altaLoteIngredientes(String n, String c, int dia, int mes, int anno, int cant, String m) {
		String diaStr = "" + dia;
		String mesStr = "" + mes;
		if (diaStr.length() < 2) {
			diaStr = "0" + dia;
		}
		if (mesStr.length() < 2) {
			mesStr = "0" + mes;
		}

		String sentencia = "INSERT INTO Lote_Ingredientes (Nombre, Contenedor, Cantidad, Vencimiento, Marca) VALUES ('"
				+ n + "', '" + c + "'," + cant + "," + "'" + anno + "-" + mesStr + "-" + diaStr + "'" + ",'" + m
				+ "');";

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}

	public void bajaLoteIngredientes(int s) {
		String sentencia = "delete from lote_Ingredientes where ID=" + s + ";";
		System.out.println(sentencia);

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}

	public void listarComida(DefaultTableModel modelo) {

		String sentencia = "SELECT ID, Nombre, Contenedor, Cantidad, freezado, preparado FROM comida;";

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentencia);

			ResultSet rs = declaracionSQL1.executeQuery();

			modelo.setRowCount(0); // Borra cont tabla

			while (rs.next()) {
				String id = rs.getString(1);

				String nombre = rs.getString(2);

				String contenedor = rs.getString(3);

				String cantidad = rs.getString(4);

				String freezado = rs.getString(5);

				String preparado = rs.getString(6);

				String prep = "";
				String friz = "";
				if (freezado.equals("1")) {
					friz = "Sí";
				} else {
					friz = "No";
				}
				if (preparado.equals("1")) {
					prep = "Sí";
				} else {
					prep = "No";
				}

				Object[] fila = { id, nombre, friz, prep, contenedor, cantidad };

				modelo.addRow(fila);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}

	public void altaComida(String n, String c, int cant, boolean pr, boolean fr) {

		String sentencia = "INSERT INTO comida (Nombre, Contenedor, Cantidad, freezado, preparado) VALUES ('" + n
				+ "','" + c + "'," + cant + "," + pr + "," + fr + ");";

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}

	public void bajaComidas(int s) {
		String sentencia = "delete from comida where ID=" + s + ";";
		System.out.println(sentencia);

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}

	public void listarMenus(DefaultTableModel modelo) {
		String sentencia = "SELECT ID, Nombre, Dias, Platos, Hora, Minuto FROM menus";

		try (Connection conexion = getConnection();

				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
				ResultSet rs = declaracionSQL.executeQuery()) {

			modelo.setRowCount(0);

			while (rs.next()) {
				String horario = rs.getInt("Hora") + ":" + rs.getInt("Minuto");
				Object[] fila = { rs.getInt("ID"), rs.getString("Nombre"), rs.getString("Dias"), rs.getString("Platos"),
						horario };

				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al listar menús: " + ex.getMessage());
		}
	}

	public void altaMenu(String nombre, String dias, String platos, int hora, int minuto) {

		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/comedor?useSSL=false", "root", "");

			String sql = "INSERT INTO menus (Nombre, Dias, Platos, Hora, Minuto) " + "VALUES (?, ?, ?, ?, ?)";

			PreparedStatement ps = cn.prepareStatement(sql);

			ps.setString(1, nombre);
			ps.setString(2, dias);
			ps.setString(3, platos);
			ps.setInt(4, hora);
			ps.setInt(5, minuto);

			ps.executeUpdate();

			ps.close();
			cn.close();

		} catch (SQLException e) {
			System.out.println("Error al agregar menú: " + e.getMessage());
		}
	}

	public boolean bajaMenu(int id) {
		String sentencia = "DELETE FROM menus WHERE ID = ?";

		try (Connection conexion = getConnection();
				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia)) {

			declaracionSQL.setInt(1, id);

			int filas = declaracionSQL.executeUpdate();

			if (filas == 0) {
				JOptionPane.showMessageDialog(null, "No existe un menú con ese ID.");
				return false;
			}

			return true;

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al eliminar menú: " + ex.getMessage());
			return false;
		}
	}

	public void listarPedidos(DefaultTableModel modelo) {
		String sentencia = "SELECT ID, Fecha, Ingrediente, IdProveedor, NroLote, Cantidad FROM pedidos";

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentencia);

			ResultSet rs = declaracionSQL1.executeQuery();

			modelo.setRowCount(0); // Borra cont tabla

			while (rs.next()) {
				String id = rs.getString(1);

				String fecha = rs.getString(2);

				String ingrediente = rs.getString(3);

				String IDProveedor = rs.getString(4);

				String nroLote = rs.getString(5);

				String cantidad = rs.getString(6);

				String sentProv = "SELECT Nombre FROM proveedor where ID=" + IDProveedor + ";";
				String provNombre = "";
				try {
					Connection conexion2 = getConnection();

					PreparedStatement declaracionSQL2 = conexion2.prepareStatement(sentProv);

					ResultSet rs2 = declaracionSQL2.executeQuery();
					while (rs2.next()) {
						provNombre = rs2.getString(1);
					}

				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}

				Object[] fila = { id, fecha, ingrediente, provNombre, nroLote, cantidad };

				modelo.addRow(fila);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}

	public void ListarProveedoresArreglo(ArrayList<String> provs) {
		String sentProv = "SELECT Nombre FROM proveedor;";
		try {
			Connection conexion2 = getConnection();

			PreparedStatement declaracionSQL2 = conexion2.prepareStatement(sentProv);

			ResultSet rs2 = declaracionSQL2.executeQuery();
			while (rs2.next()) {
				String provNom = rs2.getString(1);
				provs.add(provNom);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
	}

	public void AltaPedidos(int dia, int mes, int anno, String ing, int IdPrv, int nro, int cant) {
		String diaStr = "" + dia;
		String mesStr = "" + mes;
		if (diaStr.length() < 2) {
			diaStr = "0" + dia;
		}
		if (mesStr.length() < 2) {
			mesStr = "0" + mes;
		}
		String sentencia = "INSERT INTO pedidos (fecha, Ingrediente, IdProveedor, nroLote, Cantidad) VALUES('" + anno
				+ "-" + mes + "-" + dia + "','" + ing + "'," + IdPrv + "," + nro + "," + cant + ");";
		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentencia);

			declaracionSQL1.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}
	}

	public void bajaPedidos(int s) {
		String sentencia = "delete from pedidos where ID=" + s + ";";
		System.out.println(sentencia);

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}
	}

	public boolean comprobarProveedor(boolean p, String n) {
		String sentProv = "SELECT Nombre FROM proveedor;";
		try {
			Connection conexion2 = getConnection();

			PreparedStatement declaracionSQL2 = conexion2.prepareStatement(sentProv);

			ResultSet rs2 = declaracionSQL2.executeQuery();
			while (rs2.next()) {
				String provNom = rs2.getString(1);
				if (provNom == n) {
					p = true;
				}
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
		System.out.println("S" + p);
		return p;

	}

	public int encontrarIdProveedor(int p, String nombre) {
		String sql = "SELECT ID FROM proveedor WHERE Nombre = ?";
		try (Connection conexion = getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

			ps.setString(1, nombre);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					p = rs.getInt("ID");
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
		}
		System.out.println("sql" + p);
		return p;

	}

	public void AltaProveedores(String n, String t, String p) {

		String sentencia = "INSERT INTO proveedor (Nombre, teléfono) VALUES('" + n + "','" + p + " " + t + "');";
		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentencia);

			declaracionSQL1.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}
	}

	public void ListarProveedores(DefaultTableModel modelo) {
		String sentencia = "SELECT ID, Nombre, teléfono FROM proveedor";

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentencia);

			ResultSet rs = declaracionSQL1.executeQuery();

			modelo.setRowCount(0); // Borra cont tabla

			while (rs.next()) {
				String id = rs.getString(1);

				String nombre = rs.getString(2);

				String teléfono = "+"+rs.getString(3);

				Object[] fila = { id, nombre, teléfono };

				modelo.addRow(fila);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}
	}

	public void BajaProveedores(int s) {
		String sentencia = "delete from proveedor where ID=" + s + ";";
		System.out.println(sentencia);

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}
	}

	public void LabelsProveedores(int s, JTextField nomTxt, JTextField telTxt) {
		String sentencia = "Select Nombre, teléfono from proveedor where ID="+ s+";";
		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentencia);

			ResultSet rs = declaracionSQL1.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString(1);

				String teléfono = rs.getString(2);

				nomTxt.setText(nombre);
				telTxt.setText(teléfono);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}
	public void ModificarProveedores(int s, String n, String t) {
		String sentencia ="UPDATE proveedor SET Nombre='"+n+"', teléfono='"+t+"' WHERE ID="+s+";";
		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL1 = conexion.prepareStatement(sentencia);

			declaracionSQL1.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}
		
		
	}
}
