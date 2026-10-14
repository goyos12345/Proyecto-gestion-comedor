package Proyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Proyecto.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class BorrarComidas extends JFrame{
	public BorrarComidas(JTable tab, DefaultTableModel modelo, ArrayList<Comidas> listaComidas, VentanaPrincipal Vp) {
		this.setTitle("Borrar comida");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// Elementos generales
		JPanel granPanNor = new JPanel();
		JPanel panOp = new JPanel();
		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");
		JLabel rellenoLab= new JLabel("                                                                                                                           ");//Por alguna razón, si se borra este JLabel no se ve lo que se va a borrar
		JLabel seguro = new JLabel("¿Está seguro de que quiere eliminar esta Comida?");
		JLabel idLab= new JLabel("ID: "+ tab.getSelectedRow());

		// Se agregan elementos
		this.add(granPanNor);
		granPanNor.setLayout(new GridLayout(3,1));
		granPanNor.add(seguro);
		granPanNor.add(idLab);
		granPanNor.add(rellenoLab);

		this.add(panOp);
		panOp.setLayout(new GridLayout(2, 1));
		panOp.add(yesBut);
		panOp.add(noBut);

		// Acá se borra el menú
		yesBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int fila=tab.getSelectedRow();
				listaComidas.remove(fila);
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
