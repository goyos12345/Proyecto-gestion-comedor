package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class AgregarProveedores extends JFrame {
	public AgregarProveedores(gestor g, DefaultTableModel t) {
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
			JPanel flwPan5 = new JPanel();
			JPanel flwPan6 = new JPanel();

			// JLabels
			JLabel nomLab = new JLabel("Nombre: ");
			JLabel conNom = new JLabel("Prefijo telefónico (país): +");
			JLabel caaLab = new JLabel("Teléfono: ");

			// JTextField
			JTextField nomTxt = new JTextField(14);
			JTextField telTxt = new JTextField(14);

			// JSpinners para la fecha
			SpinnerNumberModel modPais = new SpinnerNumberModel(1, 1, 998, 1);
			JSpinner spinPais = new JSpinner(modPais);

			// Agregar elementos
			granPanNor.setLayout(new GridLayout(7, 1));
			granPanNor.add(flwPan1);
			flwPan1.add(nomLab);
			flwPan1.add(nomTxt);
			flwPan1.setLayout(new FlowLayout());
			granPanNor.add(flwPan6);
			flwPan6.add(conNom);
			flwPan6.add(spinPais);
			flwPan6.setLayout(new FlowLayout());
			granPanNor.add(flwPan5);
			flwPan5.add(caaLab);
			flwPan5.add(telTxt);

			/*
			 * Lo de abajo está re bueno, es para bloquear la escritura de letras Perdono al
			 * creador de Java
			 */
			telTxt.addKeyListener(new KeyListener() {

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

			yesBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					String nombre = nomTxt.getText();
					String telefono = telTxt.getText();
					String prefijo = ""+spinPais.getValue();
					boolean a = false;
					boolean /* la historia */ seRepite = g.ProvExiste(a, nombre);
					System.out.println(seRepite);
					if (seRepite == true || nomTxt.getText().isEmpty() || telTxt.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Campo conflictivo encontrado");
					}else {
						g.altaProveedores(nombre, telefono, prefijo);
						setVisible(false);
						g.listarProveedores(t);
					}

				}

			});
			noBut.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					
				}
			});
		this.add(panOp, FlowLayout.CENTER);
		panOp.add(yesBut);
		panOp.add(noBut);
		panOp.setLayout(new GridLayout(2, 1));

	}

}
