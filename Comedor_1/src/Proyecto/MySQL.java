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
				
				String cantidad =rs.getString(4);
				
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
		String diaStr=""+dia;
		String mesStr=""+mes;
		if (diaStr.length()<2) {
			diaStr="0"+ dia;
		}
		if (mesStr.length()<2) {
			mesStr="0"+ mes;
		}
		
		String sentencia = "INSERT INTO Lote_Ingredientes (Nombre, Contenedor, Cantidad, Vencimiento, Marca) VALUES ('"+n+ "', '"+ c+"'," +cant+","+ "'"+anno + "-"+ mesStr + "-" + diaStr+"'"+",'"+ m+"');";
		System.out.println(sentencia);

		try {

			Connection conexion = getConnection();

			PreparedStatement declaracionSQL = conexion.prepareStatement(sentencia);

			declaracionSQL.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}
	public void bajaLoteIngredientes(int s) {
		String sentencia = "delete from lote_Ingredientes where ID="+s+ ";" ;
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
				
				String cantidad =rs.getString(4);
				
				String vencimiento = rs.getString(5);
				
				String marca = rs.getString(6);

				Object[] fila = { id, nombre, vencimiento, contenedor, marca, cantidad };

				modelo.addRow(fila);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());

		}

	}
	
}
