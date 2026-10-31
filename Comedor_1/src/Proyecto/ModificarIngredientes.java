package Proyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
public class ModificarIngredientes extends JFrame{
	public ModificarIngredientes(JTable tab, DefaultTableModel mod, gestor g) {
		this.setTitle("Modificar proveedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// JLabels
		JLabel modLab = new JLabel("Modficando pedido");
		JLabel nomLab = new JLabel("Nombre:");
		JLabel conLab = new JLabel("Contenedor:");
		JLabel fechaLab = new JLabel("Caducidad (día/mes/año):");
		JLabel marcaLab = new JLabel("Proveedor: ");
		JLabel cantLab = new JLabel("Unidades del lote:");

		JTextField nomTxt = new JTextField(14);
		JTextField conTxt = new JTextField(14);

		JSpinner spinDia = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));

		JSpinner spinMes = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));

		JSpinner spinAnio = new JSpinner(new SpinnerNumberModel(2026, 2000, 2100, 1));

		JSpinner spinCan = new JSpinner(new SpinnerNumberModel(1, 1, 999999999, 1));

		JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

		// JComboBox
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

		// JPanels
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		JPanel panFecha = new JPanel();
		
		//Función de la base de datos
		g.TxtsIngredientes(sele, nomTxt, conTxt, spinDia, spinMes, spinAnio, comboPr, spinCan);

		// Fecha
		panFecha.add(spinDia);
		panFecha.add(spinMes);
		panFecha.add(spinAnio);

		// Agregar todo el quilombo
		this.add(pan1);
		pan1.add(modLab);

		this.add(pan2);

		pan2.add(nomLab);
		pan2.add(nomTxt);

		pan2.add(conLab);
		pan2.add(conTxt);

		pan2.add(fechaLab);
		pan2.add(panFecha);

		pan2.add(marcaLab);
		pan2.add(comboPr);

		pan2.add(cantLab);
		pan2.add(spinCan);
		pan2.setLayout(new GridLayout(5,1));
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
				String nombre=nomTxt.getText();
				String contenedor=conTxt.getText();
				int d=(int)spinDia.getValue();
				int m=(int)spinMes.getValue();
				int a=(int)spinAnio.getValue();
				int c=(int)spinCan.getValue();
				//Parte del proveedor
				String provNom = "" + comboPr.getSelectedItem();
				int IdProv = g.encontrarProveedor(0, provNom);
				System.out.println(a);
				

				if (nomTxt.getText().isEmpty() || conTxt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
				} else {
					g.ModificarIngrediente(sele, nombre, contenedor, c,d, m, a, IdProv);
					g.listarLoteIngrdientes(mod);
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
