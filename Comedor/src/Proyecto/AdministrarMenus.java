package Proyecto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdministrarMenus extends JFrame{
	public AdministrarMenus(int op) {
		this.setTitle("Gestor del comedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// Elementos generales
		JPanel granPanNor = new JPanel();
		JPanel superGranPanNor = new JPanel();
		JPanel panOp = new JPanel();
		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");

		this.add(superGranPanNor);

		superGranPanNor.add(granPanNor);

		// Esto es por si decide agregar un grupo
		if (op == 1) {
			this.setSize(900, 400);
			JPanel flwPan1 = new JPanel();
			JPanel flwPan2 = new JPanel();
			JPanel flwPan3 = new JPanel();
			JPanel flwPan5 = new JPanel();

			// JLabels
			JLabel nomLab = new JLabel("Nombre: ");
			JLabel caaLab = new JLabel("Horario");
			JLabel marLab = new JLabel("Hora: ");
			JLabel tiGluLab = new JLabel("Minuto: ");

			// JTextField
			JTextField nomTxt = new JTextField(14);

			// JSpinners para la fecha
			SpinnerNumberModel modHr = new SpinnerNumberModel(1, 1, 24, 1);
			SpinnerNumberModel modMin = new SpinnerNumberModel(1, 1, 60, 1);
			JSpinner spinDia = new JSpinner(modHr);
			JSpinner spinMes = new JSpinner(modMin);

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
			

		} else if (op == 2) {
			this.setTitle("Eliminar menú");

			JLabel ElIdLab = new JLabel("Ingrese el ID del menú para poder eliminarlo");
			JTextField ElIdTxt = new JTextField(7);
			granPanNor.setLayout(new FlowLayout());
			granPanNor.add(ElIdLab);
			granPanNor.add(ElIdTxt);

		} else if (op == 3) {
			this.setTitle("Modificar comidas");
			JLabel modIdLab = new JLabel("Ingrese el ID del menú quiere modificar");
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
					String[] selArr = { "", "Nombre", "Horario" };
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
			this.setSize(400, 400);
			p.removeAll();
			JPanel hrPan1=new JPanel();
			JPanel hrPan2=new JPanel();
			SpinnerNumberModel modHr = new SpinnerNumberModel(1, 1, 24, 1);
			SpinnerNumberModel modMin = new SpinnerNumberModel(1, 1, 60, 1);
			JSpinner spinDia = new JSpinner(modHr);
			JSpinner spinMes = new JSpinner(modMin);
			JLabel caaLab = new JLabel("Ingrese el horario deseado");
			JLabel marLab = new JLabel("Hora: ");
			JLabel tiGluLab = new JLabel("Minuto: ");
			p.setLayout(new GridLayout(3,1));
			p.add(caaLab);
			p.add(hrPan1);
			hrPan1.add(marLab);
			hrPan1.add(spinDia);
			p.add(hrPan2);
			hrPan2.add(tiGluLab);
			hrPan2.add(spinMes);
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

	public void AumentarValor(int i) {
		i++;
	}
}
