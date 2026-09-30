package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdministrarPedidos extends JFrame {

	public AdministrarPedidos(int op, ArrayList<Pedidos> listaPedidos, DefaultTableModel t, VentanaPrincipal vp) {

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

		// Paneles para organizar los datos

		JPanel flwPan1 = new JPanel();
		JPanel flwPan2 = new JPanel();
		JPanel flwPan3 = new JPanel();
		JPanel flwPan4 = new JPanel();
		JPanel flwPan5 = new JPanel();

		// JLabels

		JLabel fechaLab = new JLabel("Fecha: ");
		JLabel ingredientesLab = new JLabel("Ingredientes: ");
		JLabel nroLoteLab = new JLabel("Nro. de lote: ");
		JLabel cantidadKgLab = new JLabel("Cantidad Kg: ");

		// JTextField

		JTextField fechaTxt = new JTextField(14);
		JTextField ingredientesTxt = new JTextField(14);
		JTextField nroLoteTxt = new JTextField(14);
		JTextField cantidadKgTxt = new JTextField(14);

		// Agregar elementos

		granPanNor.setLayout(new GridLayout(4, 1));

		granPanNor.add(flwPan1);

		flwPan1.setLayout(new FlowLayout(FlowLayout.LEFT));
		flwPan1.add(fechaLab);
		flwPan1.add(fechaTxt);

		granPanNor.add(flwPan2);

		flwPan2.setLayout(new FlowLayout(FlowLayout.LEFT));
		flwPan2.add(ingredientesLab);
		flwPan2.add(ingredientesTxt);

		granPanNor.add(flwPan3);

		flwPan3.setLayout(new FlowLayout(FlowLayout.LEFT));
		flwPan3.add(nroLoteLab);
		flwPan3.add(nroLoteTxt);

		granPanNor.add(flwPan4);

		flwPan4.setLayout(new FlowLayout(FlowLayout.LEFT));
		flwPan4.add(cantidadKgLab);
		flwPan4.add(cantidadKgTxt);

		// Función del botón Aceptar

		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int id = 0;
				boolean repetido;

				do {

					repetido = false;

					for (Pedidos clase : listaPedidos) {

						if (clase.getiD() == id) {

							repetido = true;
							id++;
							break;

						}

					}

				} while (repetido);

				int nroLote = Integer.parseInt(nroLoteTxt.getText());

				Pedidos objPedido = new Pedidos(
						id,
						fechaTxt.getText(),
						ingredientesTxt.getText(),
						nroLote,
						cantidadKgTxt.getText()
				);

				listaPedidos.add(objPedido);

				vp.MuestraPedidos(t);

				setVisible(false);

			}

		});

		// Agregar botones

		this.add(panOp, FlowLayout.CENTER);

		panOp.add(yesBut);
		panOp.add(noBut);

		panOp.setLayout(new GridLayout(2, 1));

		// Función del botón Cancelar

		noBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);

			}

		});

	}

}
