package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
	public VentanaPrincipal() {
		this.setTitle("Gestor del comedor");
		this.setSize(1366, 688);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setLayout(new BorderLayout());

		// Lo de abajo no lo vimos, maximiza la ventana
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Creación de Jpanels generales
		JPanel panSup = new JPanel();
		JPanel granPan = new JPanel();
		JPanel panInf = new JPanel();

		// Creación de elementos del Jpanel superior
		// lupa
		ImageIcon Lupaimg = new ImageIcon("lupa.png");
		//Lo de anajo tampoco lo vimos, es para lo del tamañp (lo puso Grego)
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
				botonIngredientes(granPan, panInf);

			}
		});

	}

	public void botonIngredientes(JPanel granPan, JPanel panInf) {
		granPan.removeAll();
		granPan.setLayout(new BorderLayout());
		System.out.println("Anda");

		// Creación de los paneles del medio
		JPanel panIngDerecha = new JPanel();
		JPanel panIngIzquierda = new JPanel();

		/*
		 * Creación del srcroll (de los grupos de ingredientes) de mierda hecho por
		 * hijos de la remil puta que les parió forros de mierda hay que matar al
		 * creador de java prefiero programar en bedrock yo no lo descargo porque ya lo
		 * tengo
		 */

		// Tabla
		String[] secciones = { "ID", "Nombre", "Vencimiento", "Contenedor" };

		DefaultTableModel modelo = new DefaultTableModel(secciones, 0);
		agregaFilas(modelo);

		JTable tablita = new JTable(modelo);
		JScrollPane scrollTab = new JScrollPane(tablita);

		// Panel izquierdo

		granPan.add(panIngIzquierda, BorderLayout.WEST);
		agregaBotones(panIngIzquierda);

		panIngIzquierda.setLayout(new BoxLayout(panIngIzquierda, BoxLayout.Y_AXIS));

		agregaBotones(panIngIzquierda);

		JScrollPane scrollIng = new JScrollPane(panIngIzquierda);
		scrollIng.setPreferredSize(new Dimension(300, 900));
		scrollIng.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		granPan.add(scrollIng, BorderLayout.WEST);
		granPan.add(panIngDerecha, BorderLayout.CENTER);

		// Panel de la derecha
		granPan.add(panIngDerecha);
		panIngDerecha.add(scrollTab);
		scrollTab.setPreferredSize(new Dimension(1500, 900));

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

		panIngIzquierda.repaint();
		panInf.repaint();
		granPan.repaint();
		panIngIzquierda.revalidate();
		granPan.revalidate();
		
		
		//Action Listeners
		adminGruIngBut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectorABMLGrupoIngredientes venGI1=new SelectorABMLGrupoIngredientes(adminGruIngBut);
				
				venGI1.setVisible(true);
			}
		});

	}

	public void agregaBotones(JPanel p) {

		JButton butArr[] = new JButton[1000];

		for (int i = 0; i < butArr.length; i++) {
			butArr[i] = new JButton("PlaceHolder   ID:"+i);
			p.add(butArr[i]);
		}

	}

	public void agregaFilas(DefaultTableModel t) {

		for (int i = 0; i < 1000; i++) {
			int r = i + 1;
			t.addRow(new Object[] { i, "Tomate" + r, "26/10/3845", "Heladera derecha" });

		}

	}

}
