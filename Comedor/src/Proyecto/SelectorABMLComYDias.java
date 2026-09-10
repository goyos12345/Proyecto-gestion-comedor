package Proyecto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectorABMLComYDias extends JFrame {
	public SelectorABMLComYDias() {
		System.out.println("Funciona");
		this.setTitle("Gestor del comedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.setLayout(new FlowLayout());

		JPanel granPanNor = new JPanel();
		JPanel panOp = new JPanel();
		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");
		JLabel modIdLab = new JLabel("Ingrese el ID del menú al que le quiere agregar o eliminar Días o comidas");
		JTextField modIdTxt = new JTextField(7);
		this.add(granPanNor);
		granPanNor.setLayout(new FlowLayout());
		granPanNor.add(modIdLab);
		granPanNor.add(modIdTxt);

		this.add(panOp);
		panOp.add(yesBut);
		panOp.add(noBut);
		panOp.setLayout(new GridLayout(2, 1));

		// Funciones de los botones de aceptar y cancelar
		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				granPanNor.removeAll();

				JLabel selLab = new JLabel("Seleccione que quiere agregar o modificar");
				String[] selArr = { "", "Comida", "Día" };
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
			this.setSize(400, 400);
			p.removeAll();
			JPanel elimiPan = new JPanel();
			p.setLayout(new GridLayout(2, 2));
			JButton elimiBut = new JButton("Eliminar todas las comidas");
			JButton ingBut = new JButton("Almacenar comida");
			JLabel marLab = new JLabel("Ingrese la comida deseada");
			JTextField marTxt = new JTextField(15);
			elimiPan.add(elimiBut);
			p.add(marLab);
			p.add(marTxt);
			p.add(ingBut);
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
			this.setSize(400, 400);
			p.removeAll();
			JPanel elimiPan = new JPanel();
			p.setLayout(new GridLayout(2, 2));
			JButton elimiBut = new JButton("Eliminar todos los días");
			JButton ingBut = new JButton("Almacenar día");
			JLabel marLab = new JLabel("Ingrese el día deseado");
			JTextField marTxt = new JTextField(15);
			elimiPan.add(elimiBut);
			p.add(marLab);
			p.add(marTxt);
			p.add(ingBut);
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
