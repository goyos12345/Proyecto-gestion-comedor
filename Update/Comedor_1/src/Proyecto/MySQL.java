package Proyecto;

import java.sql.*;

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

	public void listarMenus(DefaultTableModel modelo) {
		String sentencia = "SELECT ID, Nombre, Dias, Platos, Hora, Minuto FROM menus";

		try (Connection conexion = getConnection();

				PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);
				ResultSet rs = declaracionSQL.executeQuery()) {

			modelo.setRowCount(0);

			while (rs.next()) {

				Object[] fila = { rs.getInt("ID"), rs.getString("Nombre"), rs.getString("Dias"), rs.getString("Platos"),
						rs.getInt("Hora"), rs.getInt("Minuto") };

				modelo.addRow(fila);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al listar menús: " + ex.getMessage());
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

				String vencimiento = rs.getString(5);

				String marca = rs.getString(6);

				Object[] fila = { id, nombre, vencimiento, contenedor, marca, cantidad };

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
		System.out.println(sentencia);

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

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

}
