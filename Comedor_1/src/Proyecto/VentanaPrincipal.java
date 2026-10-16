package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {
	int id;
	Color bot1Col = new Color(217, 185, 143);
	Color panInfCol = new Color(155, 121, 83);
	Color panSupCol = new Color(185, 154, 115);
	Color bot2Col = new Color(155, 121, 83);
	Color txtCol = new Color(51, 42, 33);
	Color tableCol = new Color(246, 241, 234);
	ArrayList<LoteIngredientes> IngList = new ArrayList();
	ArrayList<Comidas> ComidasList = new ArrayList<>();
	ArrayList<Menu> listaMenus = new ArrayList<>();
	ArrayList<Pedidos> listaPedidos = new ArrayList<>();
	ArrayList<Proveedores> provs = new ArrayList<>();

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
		JButton ingBut = new JButton("Inventario");
		JButton comBut = new JButton("Comidas");
		JButton ProvBut = new JButton("Proveedores");
		JButton PedidosBut = new JButton("Pedidos");

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
		panSup.add(PedidosBut);
		panSup.setLayout(new FlowLayout(FlowLayout.LEFT));

		this.add(granPan, BorderLayout.CENTER);
		granPan.add(NadaLab);

		this.add(panInf, BorderLayout.SOUTH);
		panInf.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Colores
		this.setBackground(new Color(244, 235, 220));
		panSup.setBackground(panSupCol);
		panInf.setBackground(panInfCol);
		menuBut.setBackground(bot2Col);
		ingBut.setBackground(bot2Col);
		comBut.setBackground(bot2Col);
		ProvBut.setBackground(bot2Col);
		PedidosBut.setBackground(bot2Col);
		menuBut.setForeground(Color.white);
		ingBut.setForeground(Color.white);
		comBut.setForeground(Color.white);
		ProvBut.setForeground(Color.white);
		PedidosBut.setForeground(Color.white);

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
		PedidosBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				botonPedidos(granPan, panInf, resY, resX);

			}
		});

	}

	public void botonIngredientes(JPanel granPan, JPanel panInf, int y, int x) {
		granPan.removeAll();
		panInf.removeAll();
		// Capaz borramos lo de abajo
		// Escalado para la resolución de la pantalla de la tabla
		double resYdTabDecimal = y * 0.82; // Usar el de abajo, no este
		int resYdTab = (int) resYdTabDecimal;
		double resXdTabDecimal = x * 0.78;// Usar el de abajo, no este
		int resXdTab = (int) resXdTabDecimal;

		granPan.setLayout(new BorderLayout());
		System.out.println("Anda");

		// variable para saber el grupo actual
		this.id = -1;

		/*
		 * Creación del srcroll
		 */

		// Tabla
		String[] secciones = { "ID", "Nombre", "Vencimiento", "Contenedor", "Marca", "Cantidad" };

		DefaultTableModel modelo = new DefaultTableModel(secciones, 0);

		JTable tablita = new JTable(modelo);

		JScrollPane scrollTab = new JScrollPane(tablita);
		granPan.add(scrollTab);

		// Panel izquierdo

		// Funcion para mostrar ingredientes

		System.out.println(id);

		scrollTab.setPreferredSize(new Dimension(resXdTab, resYdTab));

		// cosos de los paneles de abajo
		panInf.removeAll();
		JButton adminIngBut = new JButton("+ Agregar");
		JButton borIngBut = new JButton("- Eliminar");
		JButton modIngBut = new JButton("~ Modificar");
		JLabel adminIngLab = new JLabel("Administrar lote ingredientes");

		panInf.add(adminIngLab);
		panInf.add(adminIngBut);
		panInf.add(borIngBut);
		panInf.add(modIngBut);

		// Colores
		adminIngBut.setBackground(bot1Col);
		borIngBut.setBackground(bot1Col);
		modIngBut.setBackground(bot1Col);
		adminIngBut.setForeground(txtCol);
		borIngBut.setForeground(txtCol);
		modIngBut.setForeground(txtCol);
		tablita.setBackground(tableCol);

		adminIngLab.setForeground(Color.WHITE);

		panInf.repaint();
		granPan.repaint();

		granPan.revalidate();
		panInf.revalidate();

		// Funciones
		muestraIngredientes(modelo);

		// Action Listeners
		adminIngBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarIngredientes venIng = new AgregarIngredientes(IngList, VentanaPrincipal.this, modelo);
				venIng.setVisible(true);

			}
		});

		borIngBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BorrarIngredientes venIng = new BorrarIngredientes(tablita, modelo, IngList, VentanaPrincipal.this);
				venIng.setVisible(true);

			}
		});

	}

	public void muestraIngredientes(DefaultTableModel t) {
		t.setRowCount(0);
		for (LoteIngredientes com : IngList) {
			String Caducidad = com.getCaducidadDia() + "/" + com.getCaducidadMes() + "/" + com.getCaducidadYear();// Se
																													// crea
																													// la
																													// palabra
																													// de
																													// la
																													// fecha

			t.addRow(new Object[] { com.getID(), com.getNombre(), Caducidad, com.getContenedor(), com.getMarca(),
					com.getCantidad() });

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

		granPan.setLayout(new BorderLayout());

		String[] secciones = { "ID", "Nombre", "Ingredientees", "Preparado", "Frizado", "Contenedor" };

		DefaultTableModel modelo = new DefaultTableModel(secciones, 0);

		JTable tablita = new JTable(modelo);
		JScrollPane scrollTab = new JScrollPane(tablita);

		granPan.add(scrollTab);
		scrollTab.setPreferredSize(new Dimension(resXdTab, resYdTab));

		// Los botones de abajo
		JButton adminComBut = new JButton("+ Agregar");
		JButton borComBut = new JButton("- Eliminar");
		JButton modComBut = new JButton("~ Modificar");
		JLabel adminComLab = new JLabel("Administrar comidas");

		panInf.add(adminComLab);
		panInf.add(adminComBut);
		panInf.add(borComBut);
		panInf.add(modComBut);

		// Colores
		adminComBut.setBackground(bot1Col);
		borComBut.setBackground(bot1Col);
		modComBut.setBackground(bot1Col);
		adminComLab.setForeground(Color.WHITE);
		adminComBut.setForeground(txtCol);
		borComBut.setForeground(txtCol);
		modComBut.setForeground(txtCol);
		tablita.setBackground(tableCol);

		MuestraComidas(modelo);
		adminComBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarComidas ven = new AgregarComidas(ComidasList, modelo, VentanaPrincipal.this);
				ven.setVisible(true);

			}
		});
		borComBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BorrarComidas venCom = new BorrarComidas(tablita, modelo, ComidasList, VentanaPrincipal.this);
				venCom.setVisible(true);
			}
		});

		panInf.repaint();
		panInf.revalidate();
		granPan.repaint();
		granPan.revalidate();

	}

	public void MuestraComidas(DefaultTableModel t) {
		t.setRowCount(0);
		for (Comidas com : ComidasList) {
			String ingredientes = "";
			int i = 0;
			for (String a : com.getIngredientes()) {
				ingredientes += com.getIngredientes().get(i) + ", ";
				i++;
			}
			String prep;
			if (com.isPreparado() == false) {
				prep = "No";
			} else {
				prep = "Sí";
			}
			String friz;
			if (com.isFrizado() == false) {
				friz = "No";
			} else {
				friz = "Sí";
			}
			System.out.println(ingredientes);
			t.addRow(new Object[] { com.getID(), com.getNombre(), ingredientes, prep, friz, com.getContenedor() });

		}

	}

	public void MuestraMenus(DefaultTableModel t) {
		t.setRowCount(0);

		for (Menu men : listaMenus) {
			int i = 0;
			String Horario = men.getHorarioHr() + ":" + men.getHorarioMin();
			String dias = "";
			for (String a : men.getDias()) {
				dias += men.getDias().get(i) + ", ";
				i++;
			}
			i = 0;
			String platos = "";
			for (String a : men.getPlatos()) {

				platos += men.getPlatos().get(i) + ", ";
				i++;

			}
			t.addRow(new Object[] { men.getID(), men.getNombre(), dias, platos, Horario });
		}
	}

	public void botonMenu(JPanel granPan, JPanel panInf, int y, int x) {
		granPan.removeAll();

		// Escalado para la resolución de la pantalla de la tabla
		double resYdTabDecimal = y * 0.82; // Usar el de abajo, no este
		int resYdTab = (int) resYdTabDecimal;
		double resXdTabDecimal = x * 0.99;// Usar el de abajo, no este
		int resXdTab = (int) resXdTabDecimal;

		granPan.setLayout(new BorderLayout());

		granPan.setLayout(new BorderLayout());
		System.out.println("Anda");

		// Creación de los paneles del medio
		JPanel panIngDerecha = new JPanel();

		// Tabla
		String[] secciones = { "ID", "Nombre", "Días", "Platos", "Horario" };

		DefaultTableModel modelo = new DefaultTableModel(secciones, 0);

		JTable tabla = new JTable(modelo);
		JScrollPane scrollTab = new JScrollPane(tabla);

		// Panel de la derecha

		granPan.add(panIngDerecha, BorderLayout.CENTER);
		panIngDerecha.add(scrollTab);
		scrollTab.setPreferredSize(new Dimension(resXdTab, resYdTab));

		// cosos de los paneles de abajo
		panInf.removeAll();
		JButton addMenBut = new JButton("+ Agregar");
		JButton borMenBut = new JButton("- Eliminar");
		JButton modMenBut = new JButton("~ Modificar");
		JLabel adminMenLab = new JLabel("Administrar menús");
		panInf.add(adminMenLab);
		panInf.add(addMenBut);
		panInf.add(borMenBut);
		panInf.add(modMenBut);

		// Colores
		addMenBut.setBackground(bot1Col);
		borMenBut.setBackground(bot1Col);
		modMenBut.setBackground(bot1Col);
		adminMenLab.setForeground(Color.WHITE);
		addMenBut.setForeground(txtCol);
		borMenBut.setForeground(txtCol);
		modMenBut.setForeground(txtCol);
		tabla.setBackground(tableCol);

		panInf.repaint();
		granPan.repaint();
		granPan.revalidate();

		// Action Listeners

		addMenBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AgregarMenus ven = new AgregarMenus(1, listaMenus, modelo, VentanaPrincipal.this);

				ven.setVisible(true);
			}
		});

		borMenBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				BorrarMenu borVn = new BorrarMenu(tabla, modelo, listaMenus, VentanaPrincipal.this);
				borVn.setVisible(true);
			}
		});
	}

	public void botonProveedores(JPanel granPan, JPanel panInf, int y, int x) {
		granPan.removeAll();

		granPan.setLayout(new BorderLayout());

		// Escalado para la resolución de la pantalla de la tabla
		double resYdTabDecimal = y * 0.82; // Usar el de abajo, no este
		int resYdTab = (int) resYdTabDecimal;
		double resXdTabDecimal = x * 1.75;// Usar el de abajo, no este
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

		// botones de los paneles de abajo
		panInf.removeAll();
		JButton addMenBut = new JButton("+ Agregar");
		JButton eliProBut = new JButton("- Eliminar");
		JButton modProBut = new JButton("~ Modificar");
		JLabel adminMenLab = new JLabel("Administrar proveedores");
		panInf.add(adminMenLab);
		panInf.add(addMenBut);
		panInf.add(eliProBut);
		panInf.add(modProBut);

		// Colores
		addMenBut.setBackground(bot1Col);
		eliProBut.setBackground(bot1Col);
		modProBut.setBackground(bot1Col);
		adminMenLab.setForeground(Color.WHITE);
		addMenBut.setForeground(txtCol);
		eliProBut.setForeground(txtCol);
		modProBut.setForeground(txtCol);
		tabla.setBackground(tableCol);

		panInf.repaint();
		granPan.repaint();
		granPan.revalidate();

		// Action Listeners

		addMenBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int op = 1;
				AdministrarProveedores venGI1 = new AdministrarProveedores(op, provs, modelo, VentanaPrincipal.this);
				venGI1.setVisible(true);
			}
		});

	}

	public void agregaFilasProv(DefaultTableModel t) {
		t.setRowCount(0);
		for (Proveedores p : provs) {
			String tele = "+" + p.getNumRegional() + " " + p.getNumero();
			t.addRow(new Object[] { p.getID(), p.getNombre(), tele });

		}

	}

	public void botonPedidos(JPanel granPan, JPanel panInf, int y, int x) {
		granPan.removeAll();
		panInf.removeAll();
		// Escalado para la resolución de la pantalla de la tabla
		double resYdTabDecimal = y * 0.82; // Usar el de abajo, no este
		int resYdTab = (int) resYdTabDecimal;
		double resXdTabDecimal = x * 0.99;// Usar el de abajo, no este
		int resXdTab = (int) resXdTabDecimal;

		granPan.setLayout(new BorderLayout());

		String[] secciones = { "ID", "Fecha", "Ingrediente", "Proveedor", "Nro Lote", "Cantidad" };

		DefaultTableModel modelo = new DefaultTableModel(secciones, 0);

		JTable tablita = new JTable(modelo);
		JScrollPane scrollTab = new JScrollPane(tablita);

		granPan.add(scrollTab);
		scrollTab.setPreferredSize(new Dimension(resXdTab, resYdTab));

		// Botones y Labels de los paneles de abajo
		panInf.removeAll();
		JButton adminPedBut = new JButton("+ Agregar");
		JButton borPedBut = new JButton("- Eliminar");
		JButton modPedBut = new JButton("~ Modificar");
		JLabel adminPedLab = new JLabel("Administrar pedidos");

		panInf.add(adminPedLab);
		panInf.add(adminPedBut);
		panInf.add(borPedBut);
		panInf.add(modPedBut);

		// Colores

		adminPedLab.setForeground(Color.WHITE);
		adminPedBut.setBackground(bot1Col);
		borPedBut.setBackground(bot1Col);
		modPedBut.setBackground(bot1Col);
		tablita.setBackground(tableCol);
		adminPedBut.setForeground(txtCol);
		borPedBut.setForeground(txtCol);
		modPedBut.setForeground(txtCol);

		panInf.repaint();
		granPan.repaint();
		granPan.revalidate();

		MuestraPedidos(modelo);
		adminPedBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AdministrarPedidos ven = new AdministrarPedidos(1, listaPedidos, modelo, VentanaPrincipal.this);
				ven.setVisible(true);

			}
		});

		panInf.repaint();
		panInf.revalidate();
		granPan.repaint();
		granPan.revalidate();

	}

	public void MuestraPedidos(DefaultTableModel t) {
		t.setRowCount(0);

		for (Pedidos ped : listaPedidos) {
			String fecha = ped.getFechaDia() + "/" + ped.getFechaMes() + "/" + ped.fechaAño;
			t.addRow(new Object[] { ped.getiD(), fecha, ped.getIngredientes(), ped.getProveedor(), ped.getNroLote(),
					ped.getCantidadKg() });
		}
	}

}
