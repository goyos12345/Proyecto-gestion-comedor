package Proyecto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
public class ModificarComidas extends JFrame{
	public ModificarComidas(JTable tab, DefaultTableModel mod, gestor g) {
		this.setTitle("Modificar proveedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// JLabels
		JLabel modLab = new JLabel("Modficando comida");
		JLabel nomLab = new JLabel("Nombre:");
        JLabel conLab = new JLabel("Contenedor:");
        JLabel cantLab = new JLabel("Cantidad:");
        JLabel prepLab = new JLabel("Está preparada:");
        JLabel friLab = new JLabel("Está frizada:");

        JTextField nomTxt = new JTextField(14);
        JTextField conTxt = new JTextField(14);

        JSpinner spinCan = new JSpinner(
                new SpinnerNumberModel(1, 1, 999999999, 1));

        JCheckBox prepCB = new JCheckBox();
        JCheckBox friCB = new JCheckBox();

		// Valor de la tabla
		String seleS = "" + tab.getValueAt(tab.getSelectedRow(), 0);
		int sele = Integer.parseInt(seleS);

		// JPanels
		JPanel pan1 = new JPanel();
		JPanel pan2 = new JPanel();
		
		//Función de la base de datos
		g.TxtsComidas(sele, nomTxt, prepCB, friCB, conTxt, spinCan);
		
		//Agrear los páneles
		this.add(pan1);
		this.add(pan2);
		//PanFormulario
		pan2.setLayout(new GridLayout(5,2));
		pan2.add(nomLab);
		pan2.add(nomTxt);
		pan2.add(conLab);
		pan2.add(conTxt);
		pan2.add(cantLab);
		pan2.add(spinCan);
		pan2.add(prepLab);
		pan2.add(prepCB);
		pan2.add(friLab);
		pan2.add(friCB);
		

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
				int cantidad=(int)spinCan.getValue();
				boolean preparado=prepCB.isSelected();
				boolean frizado=friCB.isSelected();
				

				if (nomTxt.getText().isEmpty() || conTxt.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Complete todos los campos");
				} else {
					g.ModificarComidas(sele, nombre, contenedor, cantidad, frizado, preparado);
					g.listarComidas(mod);
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
