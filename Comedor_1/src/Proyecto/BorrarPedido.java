package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BorrarPedido extends JFrame {

	public BorrarPedido(JTable tab, DefaultTableModel modelo, gestor g, VentanaPrincipal Vp) {

		this.setTitle("Borrar pedido");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// Elementos generales
		String idEli = "" + tab.getValueAt(tab.getSelectedRow(), 0);
		JPanel granPanNor = new JPanel();
		JPanel panOp = new JPanel();
		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");
		JLabel seguro = new JLabel("¿Está seguro de que quiere eliminar este pedido?");
		JLabel idLab = new JLabel("ID: " + idEli);

		// Se agregan elementos
		this.add(granPanNor);
		granPanNor.setPreferredSize(new Dimension(600, 100));
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
				int fila = tab.getSelectedRow();
				if (fila == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione un Elemento para elminar");
				} else {
					int idEliNum = Integer.parseInt(idEli);
					g.BajaPedidos(idEliNum);
					g.ListarPedidos(modelo);
				}
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
