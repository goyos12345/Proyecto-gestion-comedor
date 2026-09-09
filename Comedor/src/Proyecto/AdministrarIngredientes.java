package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministrarIngredientes extends JFrame {
	public AdministrarIngredientes(int op) {
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
		granPanNor.setPreferredSize(new Dimension(600, 100));

		// Esto es por si decide agregar un ingredientte
		if (op == 1) {
			this.setTitle("Agregar ingrediente");
			// Jpanels
			JPanel flwPan1 = new JPanel();
			JPanel flwPan2 = new JPanel();
			JPanel flwPan3 = new JPanel();
			JPanel flwPan4 = new JPanel();
			JPanel flwPan5 = new JPanel();

			// JLabels
			JLabel nomLab = new JLabel("Nombre: ");
			JLabel caaLab = new JLabel("Caducidad");
			JLabel marLab = new JLabel("Día: ");
			JLabel tiGluLab = new JLabel("Mes");
			JLabel lacLab = new JLabel("Año");

			// JTextField
			JTextField nomTxt = new JTextField(14);

			// JSpinners para la fecha
			SpinnerNumberModel modDia = new SpinnerNumberModel(1, 1, 31, 1);
			SpinnerNumberModel modMes = new SpinnerNumberModel(1, 1, 12, 1);
			SpinnerNumberModel modAño = new SpinnerNumberModel(2000, 2000, 2100, 1);
			JSpinner spinDia = new JSpinner(modDia);
			JSpinner spinMes = new JSpinner(modMes);
			JSpinner spinAño = new JSpinner(modAño);

			// Agregar elementos
			granPanNor.setLayout(new GridLayout(5, 1));
			granPanNor.add(flwPan1);
			flwPan1.add(nomLab);
			flwPan1.add(nomTxt);
			flwPan1.setLayout(new FlowLayout());
			granPanNor.add(flwPan5);
			flwPan5.add(caaLab);
			granPanNor.add(flwPan2);
			flwPan2.add(marLab);
			flwPan2.add(spinDia);
			flwPan2.setLayout(new FlowLayout());
			granPanNor.add(flwPan3);
			flwPan3.add(tiGluLab);
			flwPan3.add(spinMes);
			flwPan3.setLayout(new FlowLayout());
			granPanNor.add(flwPan4);
			flwPan4.add(lacLab);
			flwPan4.add(spinAño);

		} else if (op == 2) {
			this.setTitle("Eliminar ingrediente");

			JLabel ElIdLab = new JLabel("Ingrese el ID del ingrediente para poder eliminarlo");
			JTextField ElIdTxt = new JTextField(7);
			granPanNor.setLayout(new FlowLayout());
			granPanNor.add(ElIdLab);
			granPanNor.add(ElIdTxt);

		} else if (op == 3) {
			this.setTitle("Modificar ingrediente");
			JLabel modIdLab = new JLabel("Ingrese el ID del ingrediente que quiere modificar");
			JTextField modIdTxt = new JTextField(7);
			granPanNor.setLayout(new FlowLayout());
			granPanNor.add(modIdLab);
			granPanNor.add(modIdTxt);

		}

		this.add(panOp, FlowLayout.CENTER);
		panOp.add(yesBut);
		panOp.add(noBut);
		panOp.setLayout(new GridLayout(2, 1));

		// Funciones de los botones de aceptar y cancelar
		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (op != 3) {
					setVisible(false);
				} else {
					granPanNor.removeAll();

					JLabel selLab = new JLabel("Seleccione el atributo que quiere modificar");
					String[] selArr = { "", "Nombre", "Fecha de caducidad" };
					JComboBox selCBox = new JComboBox(selArr);
					granPanNor.add(selLab);
					granPanNor.add(selCBox);

					// Lo elimino así puedo ejecutar la función para saber qué elegir
					panOp.remove(yesBut);
					panOp.remove(noBut);
					JButton queYesBut = new JButton("Aceptar");

					// Lo de abajo es lo de agregar y refrescar
					panOp.add(queYesBut);
					panOp.add(noBut);
					panOp.repaint();
					panOp.revalidate();
					granPanNor.repaint();
					granPanNor.revalidate();

					queYesBut.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int index = selCBox.getSelectedIndex();
							System.out.println(index);
							FunQueModificar(granPanNor, panOp, index);

						}
					});
					// Esta parte sirve para conseguir que quiere modificar el usuario

				}

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