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

		// Maximizar la ventana
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Lo de abajo sirve para conseguir la resolución de la pantalla
		Dimension resolucion = Toolkit.getDefaultToolkit().getScreenSize();
		int resY = (int) resolucion.getHeight();
		int resX = (int) resolucion.getWidth();

		// Creación de Jpanels generales
		JPanel panSup = new JPanel();
		JPanel granPan = new JPanel();
		JPanel panInf = new JPanel();

		// Creación de elementos del Jpanel superior
		// lupa
		ImageIcon Lupaimg = new ImageIcon("lupa.png");
		// Lo de anajo tampoco lo vimos, es para lo del tamañp (lo puso Grego)
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

		// Colores
		this.setBackground(new Color(244, 235, 220));
		panSup.setBackground(new Color(116, 72, 58));
		panInf.setBackground(new Color(116, 72, 58));
		menuBut.setBackground(new Color(171, 106, 64));
		ingBut.setBackground(new Color(171, 106, 64));
		comBut.setBackground(new Color(171, 106, 64));
		ProvBut.setBackground(new Color(171, 106, 64));
		menuBut.setForeground(Color.white);
		ingBut.setForeground(Color.white);
		comBut.setForeground(Color.white);
		ProvBut.setForeground(Color.white);
		
		// Funciones de los botones de arriba
		ingBut.addActionListener(new ActionListener(

		) {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonIngredientes(granPan, panInf, resY, resX);

			}
		});
		comBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonComidas(granPan, panInf, resY, resX);

			}
		});
		menuBut.addActionListener(new ActionListener(

		) {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonMenu(granPan, panInf, resY, resX);

			}
		});

		ProvBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonProveedores(granPan, panInf, resX, resY);
			}
		});

	}

	public void botonIngredientes(JPanel granPan, JPanel panInf, int y, int x) {
		granPan.removeAll();
		panInf.removeAll();

		// Escalado para la resolución de la pantalla de la tabla
		double resYdTabDecimal = y * 0.82; // Usar el de abajo, no este
		int resYdTab = (int) resYdTabDecimal;
		double resXdTabDecimal = x * 0.78;// Usar el de abajo, no este
		int resXdTab = (int) resXdTabDecimal;

		// Escalado para la resolución de la pantalla de los botones de los ingredientes
		double resYdIngDecimal = y * 0.83;// Usar el de abajo, no este
		int resYdIng = (int) resYdIngDecimal;
		double resXdIngDecimal = x * 0.15;// Usar el de abajo, no este
		int resXdIng = (int) resXdIngDecimal;

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
		scrollIng.setPreferredSize(new Dimension(resXdIng, resYdIng));
		scrollIng.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		granPan.add(scrollIng, BorderLayout.WEST);
		granPan.add(panIngDerecha, BorderLayout.CENTER);

		// Panel de la derecha

		granPan.add(panIngDerecha);
		panIngDerecha.add(scrollTab);
		scrollTab.setPreferredSize(new Dimension(resXdTab, resYdTab));

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
		panInf.revalidate();

		// Action Listeners
		adminGruIngBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectorABMLGrupoIngredientes venGI1 = new SelectorABMLGrupoIngredientes(adminGruIngBut);
				venGI1.setVisible(true);
			}
		});
		adminIngBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectorABMLIngredientes venGI1 = new SelectorABMLIngredientes(adminIngBut);
				venGI1.setVisible(true);

			}
		});

	}

	public void agregaBotones(JPanel p) {

		JButton butArr[] = new JButton[1000];

		for (int i = 0; i < butArr.length; i++) {
			butArr[i] = new JButton("PlaceHolder   ID:" + i);
			p.add(butArr[i]);
			butArr[i].setBackground(new Color(171, 107, 64));
		}

	}

	public void agregaFilas(DefaultTableModel t) {

		for (int i = 0; i < 1000; i++) {
			int r = i + 1;
			t.addRow(new Object[] { i, "Tomate" + r, "26/10/3845", "Heladera derecha" });

		}

	}

	public void botonComidas(JPanel granPan, JPanel panInf, int y, int x) {
		granPan.removeAll();
		panInf.removeAll();
		// Escalado para la resolución de la pantalla de la tabla
		double resYdTabDecimal = y * 0.82; // Usar el de abajo, no este
		int resYdTab = (int) resYdTabDecimal;
		double resXdTabDecimal = x * 0.99;// Usar el de abajo, no este
		int resXdTab = (int) resXdTabDecimal;

		String[] secciones = { "ID", "Nombre", "Ingredientees", "Preparado", "Frizado", "Contenedor" };

		DefaultTableModel modelo = new DefaultTableModel(secciones, 0);

		JTable tablita = new JTable(modelo);
		JScrollPane scrollTab = new JScrollPane(tablita);

		granPan.add(scrollTab);
		scrollTab.setPreferredSize(new Dimension(resXdTab, resYdTab));

		// Los botones de abajo
		JButton adminComBut = new JButton("+");
		JLabel adminComLab = new JLabel("Administrar comidas");
		panInf.add(adminComBut);
		panInf.add(adminComLab);

		for (int i = 0; i < 1000; i++) {
			int r = i + 1;
			modelo.addRow(new Object[] { i, "milanesa" + r, "carne, pan mollido", "No", "Sí", "Heladera izquierda" });

		}

		adminComBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectorABMLComidas ven = new SelectorABMLComidas(adminComBut);
				ven.setVisible(true);

			}
		});

		panInf.repaint();
		panInf.revalidate();
		granPan.repaint();
		granPan.revalidate();

	}

	public void botonMenu(JPanel granPan, JPanel panInf, int y, int x) {
		granPan.removeAll();

		// Escalado para la resolución de la pantalla de la tabla
		double resYdTabDecimal = y * 0.82; // Usar el de abajo, no este
		int resYdTab = (int) resYdTabDecimal;
		double resXdTabDecimal = x * 0.99;// Usar el de abajo, no este
		int resXdTab = (int) resXdTabDecimal;

		granPan.setLayout(new BorderLayout());
		System.out.println("Anda");

		// Creación de los paneles del medio
		JPanel panIngDerecha = new JPanel();

		// Tabla
		String[] secciones = { "ID", "Nombre", "Días", "Platos", "Horario" };

		DefaultTableModel modelo = new DefaultTableModel(secciones, 0);
		agregaFilasMen(modelo);

		JTable tabla = new JTable(modelo);
		JScrollPane scrollTab = new JScrollPane(tabla);

		// Panel de la derecha

		granPan.add(panIngDerecha, BorderLayout.CENTER);
		panIngDerecha.add(scrollTab);
		scrollTab.setPreferredSize(new Dimension(resXdTab, resYdTab));

		// cosos de los paneles de abajo
		panInf.removeAll();
		JButton adminMenBut = new JButton("+");
		JLabel adminMenLab = new JLabel("Administrar menús");
		JButton adminComBut = new JButton("+");
		JLabel admComLab = new JLabel("Agregar comidas o días al menú");
		panInf.add(adminMenBut);
		panInf.add(adminMenLab);
		panInf.add(adminComBut);
		panInf.add(admComLab);

		panInf.repaint();
		granPan.repaint();
		granPan.revalidate();

		// Action Listeners

		adminMenBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectorABMLMenu venGI1 = new SelectorABMLMenu(adminMenBut);

				venGI1.setVisible(true);
			}
		});

		adminComBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectorABMLComYDias venGI1 = new SelectorABMLComYDias();

				venGI1.setVisible(true);
			}
		});

	}

	public void botonProveedores(JPanel granPan, JPanel panInf, int y, int x) {
		granPan.removeAll();

		// Escalado para la resolución de la pantalla de la tabla
		double resYdTabDecimal = y * 0.82; // Usar el de abajo, no este
		int resYdTab = (int) resYdTabDecimal;
		double resXdTabDecimal = x * 0.99;// Usar el de abajo, no este
		int resXdTab = (int) resXdTabDecimal;

		granPan.setLayout(new BorderLayout());
		System.out.println("Anda");

		// Creación de los paneles del medio
		JPanel panIngDerecha = new JPanel();

		// Tabla
		String[] secciones = { "ID", "Nombre", "Teléfono" };

		DefaultTableModel modelo = new DefaultTableModel(secciones, 0);
		agregaFilasProv(modelo);

		JTable tabla = new JTable(modelo);
		JScrollPane scrollTab = new JScrollPane(tabla);

		// Panel de la derecha

		granPan.add(panIngDerecha, BorderLayout.CENTER);
		panIngDerecha.add(scrollTab);
		scrollTab.setPreferredSize(new Dimension(resXdTab, resYdTab));

		// cosos de los paneles de abajo
		panInf.removeAll();
		JButton adminMenBut = new JButton("+");
		JLabel adminMenLab = new JLabel("Administrar proveedores");
		panInf.add(adminMenBut);
		panInf.add(adminMenLab);
		
		panInf.repaint();
		granPan.repaint();
		granPan.revalidate();

		// Action Listeners

		adminMenBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SelectorABMLMenu venGI1 = new SelectorABMLMenu(adminMenBut);

				venGI1.setVisible(true);
			}
		});

	}

	public void agregaBotonesIng(JPanel p) {

		JButton butArr[] = new JButton[1000];

		for (int i = 0; i < butArr.length; i++) {
			butArr[i] = new JButton("PlaceHolder   ID:" + i);
			p.add(butArr[i]);
		}

	}

	public void agregaFilasIng(DefaultTableModel t) {

		for (int i = 0; i < 1000; i++) {
			int r = i + 1;
			t.addRow(new Object[] { i, "Tomate" + r, "26/10/3845", "Heladera derecha" });
		}

	}

	public void agregaFilasMen(DefaultTableModel t) {

		for (int i = 0; i < 1000; i++) {
			int r = i + 1;
			t.addRow(new Object[] { i, "Menú" + r, "Lunes", "Milanesa", "12:45" });

			
		}

		
	}
	public void agregaFilasProv(DefaultTableModel t) {
		
		
		
	}
	
	
	

}
