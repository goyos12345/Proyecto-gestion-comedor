package Proyecto;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BorrarProveedores extends JFrame{
	public BorrarProveedores(JTable tab, DefaultTableModel modelo, gestor g) {
		this.setTitle("Borrar menú");
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
		JLabel seguro = new JLabel("¿Está seguro de que quiere eliminar el Lote de ingredientes?");
		JLabel idLab= new JLabel("ID: "+ idEli);

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
				int fila=tab.getSelectedRow();
				if (fila==-1) {
					JOptionPane.showMessageDialog(null, "Seleccione un Elemento para elminar");
				}else {
					int idEliNum= Integer.parseInt(idEli);
					g.bajaProveedores(idEliNum);
					g.listarProveedores(modelo);
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
