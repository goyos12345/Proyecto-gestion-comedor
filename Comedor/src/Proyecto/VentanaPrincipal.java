package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
	public VentanaPrincipal() {
		this.setTitle("Calculadora");
		this.setSize(1366, 688);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setLayout(new BorderLayout());

		// Creación de Jpanels generales
		JPanel panSup = new JPanel();
		JPanel granPan = new JPanel();
		JPanel panInf = new JPanel();

		// Creación de elementos del Jpanel superior

		// lupa
		ImageIcon Lupaimg = new ImageIcon("lupa.png");
		Image imagenEscalada = Lupaimg.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		ImageIcon imaF = new ImageIcon(imagenEscalada);
		JLabel conima1 = new JLabel(imaF);

		JTextField busTxt = new JTextField(15);
		JButton menuBut = new JButton("Menú");
		JButton ingBut = new JButton("Ingredientes");
		JButton comBut = new JButton("Comidas");
		JButton ProvBut = new JButton("Proveedores");

		// Creación de elementos del gran panel del medip
		JLabel NadaLab = new JLabel("Seleccione una de las opciones de arriba");

		// Creación de elementos del panel inferior

		this.add(panSup, BorderLayout.NORTH);
		panSup.add(busTxt);
		panSup.add(conima1);
		conima1.setToolTipText("");
		panSup.add(menuBut);
		panSup.add(ingBut);
		panSup.add(comBut);
		panSup.add(ProvBut);
		panSup.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.add(granPan, BorderLayout.CENTER);
		granPan.add(NadaLab);

		this.add(panInf, BorderLayout.SOUTH);
		panInf.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Funciones de los botones de arriba
		ingBut.addActionListener(new ActionListener(

		) {

			@Override
			public void actionPerformed(ActionEvent e) {
				granPan.removeAll();
				granPan.setLayout(new FlowLayout(FlowLayout.LEFT));
				System.out.println("Anda");

				// Creación de los paneles del medio
				JPanel panIngDerecha = new JPanel();
				JPanel panIngIzquierda = new JPanel();

				// Los PlaceHolders hay que borrarlos
				JButton PlaceHolder1 = new JButton("PlaceHolder");
				JButton PlaceHolder2 = new JButton("PlaceHolder");
				JButton PlaceHolder3 = new JButton("PlaceHolder");
				JButton PlaceHolder4 = new JButton("PlaceHolder");

				// Creación del srcoreaas
				

				// Tabla
				String[] secciones = { "ID", "Nombres", "Días", "Limitaciónes aptas", "Platos", "Horario" };
				DefaultTableModel modelo = new DefaultTableModel(secciones, 40);
				modelo.addRow(new Object[] { "1", "Menú 1", "Lunes", "Intolerante al gluten", "Milanesa", "17:30" });

				JTable tablita = new JTable(modelo);
				JScrollPane scroll = new JScrollPane(tablita);
				
				
				//Panel izquierdo
				granPan.add(panIngIzquierda);
				PlaceHolder1.setPreferredSize(new Dimension(100, 100));
				panIngIzquierda.add(PlaceHolder1);
				panIngIzquierda.add(PlaceHolder2);
				panIngIzquierda.add(PlaceHolder3);
				panIngIzquierda.add(PlaceHolder4);

				panIngIzquierda.setLayout(new BoxLayout(panIngIzquierda, BoxLayout.Y_AXIS));

				panIngIzquierda.repaint();
				panIngIzquierda.revalidate();
				
				//Panel de la derecha
				granPan.add(panIngDerecha);
				panIngDerecha.add(scroll);
				scroll.setPreferredSize(new Dimension(1150,700));

				// cosos de los paneles de abajo
				panInf.removeAll();
				JButton adminIngBut = new JButton("+");
				JLabel adminIngLab = new JLabel("Administrar ingredientes");
				JButton adminGruIngBut = new JButton("+");
				JLabel admGruIngLab = new JLabel("Administrar grupo de ingredientes");
				panInf.add(adminIngBut);
				panInf.add(adminIngLab);
				panInf.add(adminGruIngBut);
				panInf.add(admGruIngLab);
				
				
				panInf.repaint();

				granPan.repaint();

			}
		});

	}

}
