package Proyecto;

import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectorABMLComidas extends JFrame{
	public SelectorABMLComidas(JButton b) {
		this.setTitle("Panel de administración");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(b);
		this.setResizable(false);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Jpanel
		JPanel pan1 = new JPanel();

		// JButtons
		JButton AgregBut = new JButton("+");
		JButton ElimiBut = new JButton("-");
		JButton ModifBut = new JButton("~");

		// JLabels
		JLabel AgregLab = new JLabel("Agregar");
		JLabel ElimiLab = new JLabel("Eliminar");
		JLabel ModifLab = new JLabel("Modificar");

		// Agregar los elementos
		this.add(pan1);
		pan1.setLayout(new GridLayout(3, 2));
		pan1.add(AgregBut);
		pan1.add(AgregLab);
		pan1.add(ElimiBut);
		pan1.add(ElimiLab);
		pan1.add(ModifBut);
		pan1.add(ModifLab);

		AgregBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int op = 1;
				AbrirVentana(op);

			}
		});
		ElimiBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int op = 2;
				AbrirVentana(op);

			}
		});
		ModifBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int op = 3;
				AbrirVentana(op);

			}
		});

	}

	public void AbrirVentana(int op) {
		AdministrarComidas ven = new AdministrarComidas(op);
		ven.setVisible(true);
	}
	

}
