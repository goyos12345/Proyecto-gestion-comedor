package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SelectorABMLIngredientes extends JFrame {
	public SelectorABMLIngredientes(JButton b, ArrayList<ClasificaIngredientes> arrClase, int id, DefaultTableModel t, JTable tab) {
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
				AdministrarIngredientes ven = new AdministrarIngredientes(op, arrClase, id, t,
						SelectorABMLIngredientes.this, tab);
				ven.setVisible(true);

			}
		});
		ElimiBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int op = 2;
				AdministrarIngredientes ven = new AdministrarIngredientes(op, arrClase, id, t,
						SelectorABMLIngredientes.this, tab);
				ven.setVisible(true);

			}
		});
		ModifBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int op = 3;
				AdministrarIngredientes ven = new AdministrarIngredientes(op, arrClase, id, t,
						SelectorABMLIngredientes.this, tab );
				ven.setVisible(true);

			}
		});

	}

}