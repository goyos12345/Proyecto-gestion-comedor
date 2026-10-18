package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrarMenu extends JFrame {

	public BorrarMenu(JTable tab, DefaultTableModel modelo, gestor g, VentanaPrincipal Vp) {

		this.setTitle("Borrar menú");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		JPanel granPanNor = new JPanel();
		JPanel panOp = new JPanel();

		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");

		JLabel seguro = new JLabel("¿Está seguro de que quiere eliminar el menú?");

		this.add(granPanNor);
		granPanNor.setPreferredSize(new Dimension(600, 100));
		granPanNor.setLayout(new GridLayout(2, 1));
		granPanNor.add(seguro);

		this.add(panOp);
		panOp.setLayout(new GridLayout(2, 1));
		panOp.add(yesBut);
		panOp.add(noBut);

		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int fila = tab.getSelectedRow();

				if (fila == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione un menú para eliminar");
					return;
				}

				int filaModelo = tab.convertRowIndexToModel(fila);
				int idEliNum = Integer.parseInt(modelo.getValueAt(filaModelo, 0).toString());

				boolean eliminado = g.bajaMenu(idEliNum);

				if (eliminado) {
					g.listarMenus(modelo);
					setVisible(false);
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
}