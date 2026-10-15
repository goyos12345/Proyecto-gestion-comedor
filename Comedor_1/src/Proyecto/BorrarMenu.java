package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BorrarMenu extends JFrame {

	public BorrarMenu(JTable tab, DefaultTableModel modelo, ArrayList<Menu> listaMenus, VentanaPrincipal Vp) {
		this.setTitle("Borrar menú");
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
		JLabel seguro = new JLabel("¿Está seguro de que quiere eliminar el Menú?");
		JLabel idLab= new JLabel("ID: "+ tab.getSelectedRow());

		// Se agregan elementos
		this.add(granPanNor);
		granPanNor.setPreferredSize(new Dimension(600, 100));
		granPanNor.setLayout(new GridLayout(2,1));
		granPanNor.add(seguro);
		granPanNor.add(idLab);

		this.add(panOp);
		panOp.setLayout(new GridLayout(2, 1));
		panOp.add(yesBut);
		panOp.add(noBut);

		// Acá se borra el menú
		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int fila=tab.getSelectedRow();
				listaMenus.remove(fila);
				Vp.MuestraMenus(modelo);
				setVisible(false);

			}
		});

		noBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		

	}

}
