package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ModificarPedidos extends JFrame {
	public ModificarPedidos(JTable tab, DefaultTableModel mod, gestor g) {
		this.setTitle("Modificar proveedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// JLabels
		JLabel modLab = new JLabel("Modficando pedido");
		JLabel ingLab = new JLabel("Insumo: ");
		JLabel nroLab = new JLabel("Número de lote: ");
		JLabel proLab = new JLabel("Proveedor: ");
		JLabel fecLab = new JLabel("Fecha: ");
		JLabel diaLab = new JLabel("Dia");
		JLabel mesLab = new JLabel("Mes");
		JLabel añoLab = new JLabel("Año");
		JLabel canLab = new JLabel("Cantidad: ");

		// JTexxxts
		JTextField ingTxt = new JTextField();
		JTextField nroTxt = new JTextField();

		// JSpinners
		SpinnerNumberModel modDia = new SpinnerNumberModel(1, 1, 31, 1);
		SpinnerNumberModel modMes = new SpinnerNumberModel(1, 1, 12, 1);
		SpinnerNumberModel modAño = new SpinnerNumberModel(2000, 2000, 2100, 1);
		SpinnerNumberModel modcan = new SpinnerNumberModel(1, 1, 999999999, 1);
		JSpinner spinDia = new JSpinner(modDia);
		JSpinner spinMes = new JSpinner(modMes);
		JSpinner spinAño = new JSpinner(modAño);
		JSpinner spinCan = new JSpinner(modcan);

		// ComoboBox
		ArrayList<String> listPr = new ArrayList<>();
		g.NombreProv(listPr);

		DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
		for (String s : listPr) {
			modelo.addElement(s);
		}
		JComboBox<String> comboPr = new JComboBox<>(modelo);

		// Valor de la tabla
		String seleS = "" + tab.getValueAt(tab.getSelectedRow(), 0);
		int sele = Integer.parseInt(seleS);

		nroTxt.addKeyListener(new KeyListener() {

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
		
			g.TxtsPedidos(sele, spinDia, spinMes, spinAño, spinCan, ingTxt, nroTxt, comboPr);
		
		

		// JPanels
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel panFecha = new JPanel();

		// Fecha
		panFecha.add(diaLab);
		panFecha.add(spinDia);
		panFecha.add(mesLab);
		panFecha.add(spinMes);
		panFecha.add(añoLab);
		panFecha.add(spinAño);

		// Agregar todo el quilombo
		this.add(pan1);
		pan1.add(modLab);

		this.add(pan2);
		pan2.setLayout(new GridLayout(5, 2));
		pan2.add(ingLab);
		pan2.add(ingTxt);

		pan2.add(nroLab);
		pan2.add(nroTxt);

		pan2.add(proLab);
		pan2.add(comboPr);

		pan2.add(fecLab);
		pan2.add(panFecha);

		pan2.add(canLab);
		pan2.add(spinCan);
		this.setLayout(new GridLayout(3, 1));

		// Colores
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
				String ing = ingTxt.getText();

				String nro = nroTxt.getText();
				int nroLote = Integer.parseInt(nro);

				String provNom = "" + comboPr.getSelectedItem();
				int IdProv = g.encontrarProveedor(0, provNom);
				System.out.println(IdProv);

				int dia = (int) spinDia.getValue();
				int mes = (int) spinMes.getValue();
				int año = (int) spinAño.getValue();

				int can = (int) spinCan.getValue();

				if (ingTxt.getText().isEmpty() || nroTxt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
				} else {
					g.ModificarPedido(sele, año, mes, dia, ing, IdProv, nroLote, can);
					g.ListarPedidos(mod);
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
