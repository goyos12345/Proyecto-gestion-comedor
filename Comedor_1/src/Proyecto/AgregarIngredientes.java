
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

public class AgregarIngredientes extends JFrame {

	private final Color altaCol = new Color(155, 190, 145);
	private final Color bajaCol = new Color(205, 135, 125);

	public AgregarIngredientes(gestor main, VentanaPrincipal vp, DefaultTableModel m) {

		EstiloVentanas.configurarVentana(this, "Agregar ingrediente", 540, 430);

		setResizable(false);

		JPanel formulario = EstiloVentanas.crearPanel();
		formulario.setLayout(new GridLayout(5, 2, 15, 15));
		formulario.setBorder(BorderFactory.createEmptyBorder(25, 30, 20, 30));

		JLabel nomLab = new JLabel("Nombre:");
		JLabel conLab = new JLabel("Contenedor:");
		JLabel fechaLab = new JLabel("Caducidad (día/mes/año):");
		JLabel marcaLab = new JLabel("Proveedor: ");
		JLabel cantLab = new JLabel("Unidades del lote:");

		JTextField nomTxt = new JTextField(14);
		JTextField conTxt = new JTextField(14);
		JTextField marTxt = new JTextField(14);

		JSpinner spinDia = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));

		JSpinner spinMes = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));

		JSpinner spinAnio = new JSpinner(new SpinnerNumberModel(2026, 2000, 2100, 1));

		JSpinner spinCan = new JSpinner(new SpinnerNumberModel(1, 1, 999999999, 1));

		JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

		// JComboBox
		ArrayList<String> listPr = new ArrayList<>();
		main.NombreProv(listPr);

		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
		for (String s : listPr) {
			modelo.addElement(s);
		}
		JComboBox<String> comboPr = new JComboBox<>(modelo);
		
		fechaPanel.setOpaque(false);

		fechaPanel.add(spinDia);
		fechaPanel.add(new JLabel("/"));
		fechaPanel.add(spinMes);
		fechaPanel.add(new JLabel("/"));
		fechaPanel.add(spinAnio);

		JTextField[] campos = { nomTxt, conTxt, marTxt };

		for (JTextField campo : campos) {
			EstiloVentanas.estilizarCampo(campo);
		}

		JSpinner[] spinners = { spinDia, spinMes, spinAnio, spinCan };

		for (JSpinner spinner : spinners) {
			spinner.setFont(new Font("Arial", Font.PLAIN, 16));
		}

		JLabel[] etiquetas = { nomLab, conLab, fechaLab, marcaLab, cantLab };

		for (JLabel etiqueta : etiquetas) {
			EstiloVentanas.estilizarEtiqueta(etiqueta);
			etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
		}

		fechaPanel.setPreferredSize(new Dimension(220, 35));

		formulario.add(nomLab);
		formulario.add(nomTxt);

		formulario.add(conLab);
		formulario.add(conTxt);

		formulario.add(fechaLab);
		formulario.add(fechaPanel);

		formulario.add(marcaLab);
		formulario.add(comboPr);

		formulario.add(cantLab);
		formulario.add(spinCan);

		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
		botones.setBackground(EstiloVentanas.FONDO);

		JButton aceptar = EstiloVentanas.crearBoton("Agregar", altaCol);

		JButton cancelar = EstiloVentanas.crearBoton("Cancelar", bajaCol);

		estilizarBoton(aceptar);
		estilizarBoton(cancelar);

		botones.add(aceptar);
		botones.add(cancelar);

		add(formulario, BorderLayout.CENTER);
		add(botones, BorderLayout.SOUTH);

		aceptar.addActionListener(e -> {

			String n = nomTxt.getText();
			String c = conTxt.getText();
			String pro = (String) comboPr.getSelectedItem();
			int dia = (int) spinDia.getValue();
			int mes = (int) spinMes.getValue();
			int año = (int) spinAnio.getValue();
			int cant = (int) spinCan.getValue();
			int IdPro = main.encontrarProveedor(0, pro);

			if (n.isEmpty() || c.isEmpty()) {

				JOptionPane.showMessageDialog(this, "Completá todos los campos.", "Campos incompletos",
						JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				main.altaLoteIngredientes(n, c, dia, mes, año, cant, IdPro);

				vp.muestraIngredientes(m);
				setVisible(false);
			}
		});

		
	}

	private void estilizarBoton(JButton boton) {
		boton.setFont(new Font("Arial", Font.BOLD, 16));
		boton.setPreferredSize(new Dimension(145, 45));
		boton.setFocusPainted(false);
	}
}
