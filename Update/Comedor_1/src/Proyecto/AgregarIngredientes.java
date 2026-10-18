package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarIngredientes extends JFrame {
	public AgregarIngredientes(gestor main, VentanaPrincipal vp, DefaultTableModel m) {
		this.setTitle("Gestor del comedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// Elementos generales
		JPanel granPanNor = new JPanel();
		JPanel panOp = new JPanel();
		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");

		this.add(granPanNor);
		granPanNor.setPreferredSize(new Dimension(600, 200));

		// Esto es por si decide agregar un ingredientte
		this.setTitle("Agregar ingrediente");
		// Jpanels
		JPanel flwPan1 = new JPanel();
		JPanel flwPan2 = new JPanel();
		JPanel flwPan3 = new JPanel();
		JPanel flwPan4 = new JPanel();
		JPanel flwPan5 = new JPanel();
		JPanel flwPan6 = new JPanel();
		JPanel flwPan7 = new JPanel();

		// JLabels
		JLabel nomLab = new JLabel("Nombre: ");
		JLabel conNom = new JLabel("Contenedor: ");
		JLabel caaLab = new JLabel("Caducidad: ");
		JLabel marLab = new JLabel("Día: ");
		JLabel tiGluLab = new JLabel("Mes: ");
		JLabel lacLab = new JLabel("Año: ");
		JLabel marcaLab = new JLabel("Marca: ");
		JLabel canLab = new JLabel("Ingrese las unidades del lote: ");

		// JTextField
		JTextField nomTxt = new JTextField(14);
		JTextField conTxt = new JTextField(14);
		JTextField marTxt = new JTextField(14);

		// JSpinners para la fecha
		SpinnerNumberModel modDia = new SpinnerNumberModel(1, 1, 31, 1);
		SpinnerNumberModel modMes = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel modAño = new SpinnerNumberModel(2026, 2000, 2100, 1);
		SpinnerNumberModel modcan = new SpinnerNumberModel(1, 1, 999999999, 1);
		JSpinner spinDia = new JSpinner(modDia);
		JSpinner spinMes = new JSpinner(modMes);
		JSpinner spinAño = new JSpinner(modAño);
		JSpinner spinCan = new JSpinner(modcan);

		// Agregar elementos

		granPanNor.setLayout(new GridLayout(7, 1));
		// Nombre
		granPanNor.add(flwPan1);
		flwPan1.add(nomLab);
		flwPan1.add(nomTxt);
		flwPan1.setLayout(new FlowLayout());
		// Contenedor
		granPanNor.add(flwPan6);
		flwPan6.add(conNom);
		flwPan6.add(conTxt);
		flwPan6.setLayout(new FlowLayout());
		granPanNor.add(flwPan5);
		flwPan5.add(caaLab);
		// Panel de caducidad
		granPanNor.add(flwPan2);
		flwPan2.add(marLab);
		flwPan2.add(spinDia);
		flwPan2.add(tiGluLab);
		flwPan2.add(spinMes);
		flwPan2.add(lacLab);
		flwPan2.add(spinAño);
		flwPan2.setLayout(new FlowLayout());

		granPanNor.add(flwPan3);
		flwPan3.add(marcaLab);
		flwPan3.add(marTxt);

		flwPan3.setLayout(new FlowLayout());
		granPanNor.add(flwPan4);

		flwPan4.setLayout(new FlowLayout());
		granPanNor.add(flwPan7);
		flwPan7.add(canLab);
		flwPan7.add(spinCan);
		flwPan7.setLayout(new FlowLayout());

		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String n = nomTxt.getText();
				String c = conTxt.getText();
				String mar = marTxt.getText();
				int dia = (int) spinDia.getValue();
				int mes = (int) spinMes.getValue();
				int año = (int) spinAño.getValue();
				int cant = (int) spinCan.getValue();

				if (nomTxt.getText().isEmpty() || conTxt.getText().isEmpty() || marTxt.getText().isEmpty()
						|| (int) spinCan.getValue() == 0) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
				} else {
					main.altaLoteIngredientes(n, c, dia, mes, año, cant, mar);

					vp.muestraIngredientes(m);
					setVisible(false);
				}
				// Se llama a la función del gestor

			}

		});

		this.add(panOp, FlowLayout.CENTER);
		panOp.add(yesBut);
		panOp.add(noBut);
		panOp.setLayout(new GridLayout(2, 1));

		// Si ven código comentado, no lo borrren
		// -------------------------------------------------------------

		// Funciones de los botones de aceptar y cancelar
		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * if (op == 3) { granPanNor.removeAll();
				 * 
				 * JLabel selLab = new JLabel("Seleccione el atributo que quiere modificar");
				 * String[] selArr = { "", "Nombre", "Fecha de caducidad" }; JComboBox selCBox =
				 * new JComboBox(selArr); granPanNor.add(selLab); granPanNor.add(selCBox);
				 * 
				 * // Lo elimino así puedo ejecutar la función para saber qué elegir
				 * panOp.remove(yesBut); panOp.remove(noBut); JButton queYesBut = new
				 * JButton("Aceptar");
				 * 
				 * // Lo de abajo es lo de agregar y refrescar panOp.add(queYesBut);
				 * panOp.add(noBut); panOp.repaint(); panOp.revalidate(); granPanNor.repaint();
				 * granPanNor.revalidate();
				 * 
				 * queYesBut.addActionListener(new ActionListener() {
				 * 
				 * @Override public void actionPerformed(ActionEvent e) { int index =
				 * selCBox.getSelectedIndex(); System.out.println(index);
				 * FunQueModificar(granPanNor, panOp, index);
				 * 
				 * } }); // Esta parte sirve para conseguir que quiere modificar el usuario
				 * 
				 * }
				 */

			}
		});
		noBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

	}

	public void FunQueModificar(JPanel p, JPanel c, int i) {
		if (i == 0) {
			JLabel errorLab = new JLabel(
					"                     		Seleccione una opcion correcta		                    ");
			errorLab.setForeground(Color.red);
			p.add(errorLab);

		} else if (i == 1) {
			p.removeAll();
			JLabel nomLab = new JLabel("Ingrese el nombre deseado");
			JTextField nomTxt = new JTextField(15);
			p.add(nomLab);
			p.add(nomTxt);
			p.repaint();
			JButton yesBut = new JButton("Aceptar");
			JButton noBut = new JButton("Cancelar");
			c.removeAll();
			c.add(yesBut);
			c.add(noBut);
			yesBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);

				}
			});
			noBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);

				}
			});
			c.repaint();
			c.revalidate();

		} else if (i == 2) {
			p.removeAll();
			JPanel flwPan2 = new JPanel();
			JPanel flwPan3 = new JPanel();
			JPanel flwPan4 = new JPanel();
			JLabel pedFechLab = new JLabel("Ingrese la fecha deseada");
			JLabel marLab = new JLabel("Día: ");
			JLabel tiGluLab = new JLabel("Mes");
			JLabel lacLab = new JLabel("Año");
			SpinnerNumberModel modDia = new SpinnerNumberModel(1, 1, 31, 1);
			SpinnerNumberModel modMes = new SpinnerNumberModel(1, 1, 12, 1);
			SpinnerNumberModel modAño = new SpinnerNumberModel(2000, 2000, 2100, 1);
			JSpinner spinDia = new JSpinner(modDia);
			JSpinner spinMes = new JSpinner(modMes);
			JSpinner spinAño = new JSpinner(modAño);
			p.add(pedFechLab);
			p.add(flwPan2);
			flwPan2.add(marLab);
			flwPan2.add(spinDia);
			flwPan2.setLayout(new FlowLayout());
			p.add(flwPan3);
			flwPan3.add(tiGluLab);
			flwPan3.add(spinMes);
			flwPan3.setLayout(new FlowLayout());
			p.add(flwPan4);
			flwPan4.add(lacLab);
			flwPan4.add(spinAño);
			p.repaint();
			JButton yesBut = new JButton("Aceptar");
			JButton noBut = new JButton("Cancelar");
			c.removeAll();
			c.add(yesBut);
			c.add(noBut);
			yesBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);

				}
			});
			noBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);

				}
			});
			c.repaint();
			c.revalidate();
		}
		p.revalidate();

	}

}