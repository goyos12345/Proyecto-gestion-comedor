package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;

public class AgregarPedidos extends JFrame {

	public AgregarPedidos(int op, gestor g, DefaultTableModel t, VentanaPrincipal vp) {

		this.setTitle("Gestor del comedor");
		this.setSize(700, 400);
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
		JPanel flwPan6 = new JPanel();

		// JLabels

		JLabel fechaLab = new JLabel("Fecha: ");
		JLabel ingredientesLab = new JLabel("Ingrediente: ");
		JLabel nroLoteLab = new JLabel("Nro. de lote: ");
		JLabel cantidadKgLab = new JLabel("Cantidad Kg: ");
		JLabel diaLab = new JLabel("Día");
		JLabel mesLab = new JLabel("Mes");
		JLabel añoLab = new JLabel("Año");
		JLabel provLab = new JLabel("Proveedor: ");

		// JSpinners
		SpinnerNumberModel modDia = new SpinnerNumberModel(1, 1, 31, 1);
		SpinnerNumberModel modMes = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel modAño = new SpinnerNumberModel(2000, 2000, 2100, 1);
		SpinnerNumberModel modcan = new SpinnerNumberModel(1, 1, 999999999, 1);
		JSpinner spinDia = new JSpinner(modDia);
		JSpinner spinMes = new JSpinner(modMes);
		JSpinner spinAño = new JSpinner(modAño);
		JSpinner spinCan = new JSpinner(modcan);

		// JTextField
		JTextField ingredientesTxt = new JTextField(14);
		JTextField nroLoteTxt = new JTextField(14);
		
		nroLoteTxt.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLetter(c)) {
					e.consume();
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// JComboBox
		ArrayList<String> listPr = new ArrayList<>();
		g.NombreProv(listPr);

		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
		for (String s : listPr) {
		    modelo.addElement(s);
		}
		JComboBox<String> comboPr = new JComboBox<>(modelo);

		// Agregar elementos
		granPanNor.setLayout(new GridLayout(4, 1));

		granPanNor.add(flwPan1);

		flwPan1.setLayout(new FlowLayout(FlowLayout.LEFT));
		flwPan1.add(fechaLab);
		flwPan1.add(diaLab);
		flwPan1.add(spinDia);
		flwPan1.add(mesLab);
		flwPan1.add(spinMes);
		flwPan1.add(añoLab);
		flwPan1.add(spinAño);

		granPanNor.add(flwPan2);

		flwPan2.setLayout(new FlowLayout(FlowLayout.LEFT));
		flwPan2.add(ingredientesLab);
		flwPan2.add(ingredientesTxt);

	
		

		granPanNor.add(flwPan6);
		flwPan6.add(provLab);
		flwPan6.add(comboPr);

		granPanNor.add(flwPan3);
		flwPan3.setLayout(new FlowLayout(FlowLayout.LEFT));
		flwPan3.add(nroLoteLab);
		flwPan3.add(nroLoteTxt);

		granPanNor.add(flwPan4);
		flwPan4.setLayout(new FlowLayout(FlowLayout.LEFT));
		flwPan4.add(cantidadKgLab);
		flwPan4.add(spinCan);

		// Función del botón Aceptar

		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String nomProv= (String) comboPr.getSelectedItem();
				int idr=g.encontrarProveedor(0, nomProv);
			
				
				System.out.println(idr);
				int dia=(int)spinDia.getValue();
				int mes=(int)spinMes.getValue();
				int año=(int)spinAño.getValue();
				int cant=(int)spinCan.getValue();
				String ing=ingredientesTxt.getText();
				int nroLote= Integer.parseInt(nroLoteTxt.getText());
				g.AltaPedidos(dia, mes, año, ing, idr, nroLote, cant);
	
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
