package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ModificarProveedores extends JFrame {
	public ModificarProveedores(JTable tab, DefaultTableModel mod, gestor g) {
		this.setTitle("Modificar proveedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// JLabels
		JLabel modLab = new JLabel("Modficando proveedor");
		JLabel nomLab = new JLabel("Nombre: "); 
		JLabel telLab = new JLabel("Teléfono:                                                                             +");

		// JTexxxts
		JTextField nomTxt = new JTextField();
		JTextField telTxt = new JTextField();
		String seleS = "" + tab.getValueAt(tab.getSelectedRow(), 0);
		int sele = Integer.parseInt(seleS);

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

		g.TxtsProveedor(sele, nomTxt, telTxt);

		// JPanels
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();

		this.add(pan1);
		pan1.add(modLab);

		this.add(pan2);
		pan2.setLayout(new GridLayout(2, 2));
		pan2.add(nomLab);
		pan2.add(nomTxt);
		pan2.add(telLab);
		pan2.add(telTxt);

		this.setLayout(new GridLayout(3, 1));
		
		//Colores 
		pan1.setBackground(EstiloVentanas.FONDO);
		pan2.setBackground(EstiloVentanas.FONDO);

		// Tema de los botones de abajo
		JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		botones.setBackground(EstiloVentanas.FONDO);

		JButton aceptar = EstiloVentanas.crearBoton("Aceptar", EstiloVentanas.VERDE);
		JButton cancelar = EstiloVentanas.crearBoton("Cancelar", EstiloVentanas.BOTON);

		botones.add(aceptar);
		botones.add(cancelar);

		add(botones, BorderLayout.SOUTH);

		// Funciones
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String nom = nomTxt.getText();
				String tel = telTxt.getText();

				if (nomTxt.getText().isEmpty() || telTxt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
				} else {
					g.ModificarProveedor(sele, nom, tel);
					g.listarProveedores(mod);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Elemento modificado");

				}

			}
		});
		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

	}
}
