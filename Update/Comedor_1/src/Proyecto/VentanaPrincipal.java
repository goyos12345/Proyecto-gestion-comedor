
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaPrincipal extends JFrame {

    int id;

    gestor main = new gestor();

    // Paleta original
    Color bot1Col = new Color(217, 185, 143);
    Color panInfCol = new Color(155, 121, 83);
    Color panSupCol = new Color(185, 154, 115);
    Color bot2Col = new Color(155, 121, 83);
    Color txtCol = new Color(51, 42, 33);
    Color tableCol = new Color(246, 241, 234);
    Color fondoCol = new Color(244, 235, 220);

    // Colores para las acciones
    Color altaCol = new Color(155, 190, 145);       // Verde suave
    Color bajaCol = new Color(205, 135, 125);       // Rojo suave
    Color modificarCol = new Color(145, 175, 195);  // Azul suave

    ArrayList<LoteIngredientes> IngList = new ArrayList<>();
    ArrayList<Comidas> ComidasList = new ArrayList<>();
    ArrayList<Menu> listaMenus = new ArrayList<>();
    ArrayList<Pedidos> listaPedidos = new ArrayList<>();
    ArrayList<Proveedores> provs = new ArrayList<>();

    public VentanaPrincipal() {

        setTitle("Gestor del comedor");
        setSize(1366, 688);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        Dimension resolucion = Toolkit.getDefaultToolkit().getScreenSize();
        int resY = (int) resolucion.getHeight();
        int resX = (int) resolucion.getWidth();

        // Paneles generales
        JPanel panSup = new JPanel(new BorderLayout());
        JPanel granPan = new JPanel();
        JPanel panInf = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Elementos de la barra superior
        ImageIcon lupaImg = new ImageIcon("lupa.png");
        Image imagenEscalada = lupaImg.getImage()
                .getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon imaF = new ImageIcon(imagenEscalada);
        JLabel conima1 = new JLabel(imaF);

        JTextField busTxt = new JTextField(15);

        JButton menuBut = new JButton("Menú");
        JButton ingBut = new JButton("Inventario");
        JButton comBut = new JButton("Comidas");
        JButton ProvBut = new JButton("Proveedores");
        JButton PedidosBut = new JButton("Pedidos");

        // Botón para volver al login
        JButton inicioBut = new JButton("⌂");
        inicioBut.setFont(new Font("Arial", Font.BOLD, 26));
        inicioBut.setToolTipText("Cerrar sesión y volver al login");
        inicioBut.setPreferredSize(new Dimension(50, 35));

        // Estilo de navegación
        estilizarNavegacion(menuBut);
        estilizarNavegacion(ingBut);
        estilizarNavegacion(comBut);
        estilizarNavegacion(ProvBut);
        estilizarNavegacion(PedidosBut);
        estilizarNavegacion(inicioBut);

        // Panel izquierdo de la barra superior
        JPanel botonesIzquierda = new JPanel(
                new FlowLayout(FlowLayout.LEFT));

        botonesIzquierda.setBackground(panSupCol);
        botonesIzquierda.add(busTxt);
        botonesIzquierda.add(conima1);
        botonesIzquierda.add(menuBut);
        botonesIzquierda.add(ingBut);
        botonesIzquierda.add(comBut);
        botonesIzquierda.add(ProvBut);
        botonesIzquierda.add(PedidosBut);

        panSup.setBackground(panSupCol);
        panSup.add(botonesIzquierda, BorderLayout.WEST);
        panSup.add(inicioBut, BorderLayout.EAST);

        // Panel central
        JLabel NadaLab = new JLabel(
                "Seleccione una de las opciones de arriba");

        granPan.setBackground(fondoCol);
        granPan.add(NadaLab);

        // Colores de los paneles
        setBackground(fondoCol);
        panInf.setBackground(panInfCol);

        add(panSup, BorderLayout.NORTH);
        add(granPan, BorderLayout.CENTER);
        add(panInf, BorderLayout.SOUTH);

        // Botón Inventario
        ingBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonIngredientes(granPan, panInf, resY, resX);
            }
        });

        // Botón Comidas
        comBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonComidas(granPan, panInf, resY, resX);
            }
        });

        // Botón Menú
        menuBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonMenu(granPan, panInf, resY, resX);
            }
        });

        // Botón Proveedores
        ProvBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonProveedores(granPan, panInf, resX, resY);
            }
        });

        // Botón Pedidos
        PedidosBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonPedidos(granPan, panInf, resY, resX);
            }
        });

        // Cerrar sesión y volver al login
        inicioBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int opcion = JOptionPane.showConfirmDialog(
                        VentanaPrincipal.this,
                        "¿Desea cerrar la sesión y volver al login?",
                        "Cerrar sesión",
                        JOptionPane.YES_NO_OPTION
                );

                if (opcion == JOptionPane.YES_OPTION) {
                    VentanaLogin login = new VentanaLogin();
                    login.setVisible(true);
                    VentanaPrincipal.this.dispose();
                }
            }
        });
    }

    // =====================================================
    // MÉTODOS DE ESTILO
    // =====================================================

    // Botones superiores de navegación
    private void estilizarNavegacion(JButton boton) {
        boton.setBackground(bot2Col);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(130, 100, 70)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        boton.setOpaque(true);
    }

    // Botón para dar de alta o agregar
    private void estilizarAlta(JButton boton) {
        boton.setBackground(altaCol);
        boton.setForeground(new Color(35, 65, 32));
        estilizarAccion(boton);
    }

    // Botón para dar de baja o eliminar
    private void estilizarBaja(JButton boton) {
        boton.setBackground(bajaCol);
        boton.setForeground(new Color(85, 30, 25));
        estilizarAccion(boton);
    }

    // Botón para modificar
    private void estilizarModificar(JButton boton) {
        boton.setBackground(modificarCol);
        boton.setForeground(new Color(30, 50, 65));
        estilizarAccion(boton);
    }

    // Estilo común para todos los botones inferiores
    private void estilizarAccion(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        new Color(120, 105, 85)),
                BorderFactory.createEmptyBorder(7, 12, 7, 12)
        ));
    }

    // Actualizar los paneles después de cambiar su contenido
    private void actualizarPaneles(JPanel granPan, JPanel panInf) {
        granPan.repaint();
        granPan.revalidate();

        panInf.repaint();
        panInf.revalidate();
    }

    // =====================================================
    // INVENTARIO / INGREDIENTES
    // =====================================================

    public void botonIngredientes(
            JPanel granPan, JPanel panInf, int y, int x) {

        granPan.removeAll();
        panInf.removeAll();
        granPan.setLayout(new BorderLayout());
        granPan.setBackground(fondoCol);

        this.id = -1;

        String[] secciones = {
                "ID", "Nombre", "Vencimiento",
                "Contenedor", "Marca", "Cantidad"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(secciones, 0);

        JTable tablita = new JTable(modelo);
        tablita.setBackground(tableCol);
        tablita.setFillsViewportHeight(true);

        JScrollPane scrollTab = new JScrollPane(tablita);
        granPan.add(scrollTab, BorderLayout.CENTER);

        // Botones inferiores
        JLabel adminIngLab =
                new JLabel("Administrar lote de ingredientes");

        JButton adminIngBut = new JButton("+ Dar de alta");
        JButton borIngBut = new JButton("- Dar de baja");
        JButton modIngBut = new JButton("✎ Modificar");

        panInf.add(adminIngLab);
        panInf.add(adminIngBut);
        panInf.add(borIngBut);
        panInf.add(modIngBut);

        adminIngLab.setForeground(Color.WHITE);
        estilizarAlta(adminIngBut);
        estilizarBaja(borIngBut);
        estilizarModificar(modIngBut);

        muestraIngredientes(modelo);

        adminIngBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarIngredientes venIng =
                        new AgregarIngredientes(
                                main, VentanaPrincipal.this, modelo);
                venIng.setVisible(true);
            }
        });

        borIngBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarIngredientes venIng =
                        new BorrarIngredientes(
                                tablita, modelo, main,
                                VentanaPrincipal.this);
                venIng.setVisible(true);
            }
        });

        modIngBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        VentanaPrincipal.this,
                        "La función de modificar ingredientes todavía "
                        + "no está conectada.",
                        "Modificar ingredientes",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        actualizarPaneles(granPan, panInf);
    }

    public void muestraIngredientes(DefaultTableModel t) {
        main.listarLoteIngrdientes(t);
    }

    // =====================================================
    // COMIDAS
    // =====================================================

    public void botonComidas(
            JPanel granPan, JPanel panInf, int y, int x) {

        granPan.removeAll();
        panInf.removeAll();
        granPan.setLayout(new BorderLayout());
        granPan.setBackground(fondoCol);

        String[] secciones = {
                "ID", "Nombre", "Preparado",
                "Frizado", "Contenedor", "Cantidad"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(secciones, 0);

        JTable tablita = new JTable(modelo);
        tablita.setBackground(tableCol);
        tablita.setFillsViewportHeight(true);

        JScrollPane scrollTab = new JScrollPane(tablita);
        granPan.add(scrollTab, BorderLayout.CENTER);

        JLabel adminComLab = new JLabel("Administrar comidas");

        JButton adminComBut = new JButton("+ Dar de alta");
        JButton borComBut = new JButton("- Dar de baja");
        JButton modComBut = new JButton("✎ Modificar");

        panInf.add(adminComLab);
        panInf.add(adminComBut);
        panInf.add(borComBut);
        panInf.add(modComBut);

        adminComLab.setForeground(Color.WHITE);
        estilizarAlta(adminComBut);
        estilizarBaja(borComBut);
        estilizarModificar(modComBut);

        MuestraComidas(modelo);

        adminComBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarComidas ven =
                        new AgregarComidas(
                                main, modelo, VentanaPrincipal.this);
                ven.setVisible(true);
            }
        });

        borComBut.addActionListener(e -> {
            if (tablita.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(
                        VentanaPrincipal.this,
                        "Seleccioná una comida de la tabla antes de eliminarla.",
                        "Ninguna comida seleccionada",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            BorrarComidas ventana = new BorrarComidas(
                    tablita, modelo, main, VentanaPrincipal.this);
            ventana.setVisible(true);
        });

        modComBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        VentanaPrincipal.this,
                        "La función de modificar comidas todavía "
                        + "no está conectada.",
                        "Modificar comidas",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        actualizarPaneles(granPan, panInf);
    }

    public void MuestraComidas(DefaultTableModel t) {
        main.listarComidas(t);
    }

    // =====================================================
    // MENÚS
    // =====================================================

    public void MuestraMenus(DefaultTableModel t) {
        main.ListarMenus(t);
    }

    public void botonMenu(
            JPanel granPan, JPanel panInf, int y, int x) {

        granPan.removeAll();
        panInf.removeAll();
        granPan.setLayout(new BorderLayout());
        granPan.setBackground(fondoCol);

        String[] secciones = {
                "ID", "Nombre", "Días", "Platos", "Horario"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(secciones, 0);

        JTable tabla = new JTable(modelo);
        tabla.setBackground(tableCol);
        tabla.setFillsViewportHeight(true);

        JScrollPane scrollTab = new JScrollPane(tabla);
        granPan.add(scrollTab, BorderLayout.CENTER);

        MuestraMenus(modelo);

        JLabel adminMenLab = new JLabel("Administrar menús");

        JButton addMenBut = new JButton("+ Dar de alta");
        JButton borMenBut = new JButton("- Dar de baja");
        JButton modMenBut = new JButton("✎ Modificar");

        panInf.add(adminMenLab);
        panInf.add(addMenBut);
        panInf.add(borMenBut);
        panInf.add(modMenBut);

        adminMenLab.setForeground(Color.WHITE);
        estilizarAlta(addMenBut);
        estilizarBaja(borMenBut);
        estilizarModificar(modMenBut);

        addMenBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarMenus ven =
                        new AgregarMenus(1, modelo,
                                VentanaPrincipal.this);
                ven.setVisible(true);
            }
        });

        borMenBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarMenu borVn =
                        new BorrarMenu(
                                tabla, modelo, main,
                                VentanaPrincipal.this);
                borVn.setVisible(true);
            }
        });

        modMenBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        VentanaPrincipal.this,
                        "La función de modificar menús todavía "
                        + "no está conectada.",
                        "Modificar menús",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        actualizarPaneles(granPan, panInf);
    }

    // =====================================================
    // PROVEEDORES
    // =====================================================

    public void botonProveedores(
            JPanel granPan, JPanel panInf, int y, int x) {

        granPan.removeAll();
        panInf.removeAll();
        granPan.setLayout(new BorderLayout());
        granPan.setBackground(fondoCol);

        String[] secciones = {
                "ID", "Nombre", "Teléfono"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(secciones, 0);

        JTable tabla = new JTable(modelo);
        tabla.setBackground(tableCol);
        tabla.setFillsViewportHeight(true);

        JScrollPane scrollTab = new JScrollPane(tabla);
        granPan.add(scrollTab, BorderLayout.CENTER);

        agregaFilasProv(modelo);

        JLabel adminMenLab =
                new JLabel("Administrar proveedores");

        JButton addMenBut = new JButton("+ Dar de alta");
        JButton eliProBut = new JButton("- Dar de baja");
        JButton modProBut = new JButton("✎ Modificar");

        panInf.add(adminMenLab);
        panInf.add(addMenBut);
        panInf.add(eliProBut);
        panInf.add(modProBut);

        adminMenLab.setForeground(Color.WHITE);
        estilizarAlta(addMenBut);
        estilizarBaja(eliProBut);
        estilizarModificar(modProBut);

        eliProBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarProveedores ven =
                        new BorrarProveedores(tabla, modelo, main);
                ven.setVisible(true);
            }
        });

        addMenBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarProveedores venGI1 =
                        new AgregarProveedores(main, modelo);
                venGI1.setVisible(true);
            }
        });

        modProBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        VentanaPrincipal.this,
                        "La función de modificar proveedores todavía "
                        + "no está conectada.",
                        "Modificar proveedores",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        actualizarPaneles(granPan, panInf);
    }

    public void agregaFilasProv(DefaultTableModel t) {
        main.listarProveedores(t);
    }

    // =====================================================
    // PEDIDOS
    // =====================================================

    public void botonPedidos(
            JPanel granPan, JPanel panInf, int y, int x) {

        granPan.removeAll();
        panInf.removeAll();
        granPan.setLayout(new BorderLayout());
        granPan.setBackground(fondoCol);

        String[] secciones = {
                "ID", "Fecha", "Ingrediente",
                "Proveedor", "Nro Lote", "Cantidad"
        };

        DefaultTableModel modelo =
                new DefaultTableModel(secciones, 0);

        JTable tablita = new JTable(modelo);
        tablita.setBackground(tableCol);
        tablita.setFillsViewportHeight(true);

        JScrollPane scrollTab = new JScrollPane(tablita);
        granPan.add(scrollTab, BorderLayout.CENTER);

        JLabel adminPedLab = new JLabel("Administrar pedidos");

        JButton adminPedBut = new JButton("+ Dar de alta");
        JButton borPedBut = new JButton("- Dar de baja");
        JButton modPedBut = new JButton("✎ Modificar");

        panInf.add(adminPedLab);
        panInf.add(adminPedBut);
        panInf.add(borPedBut);
        panInf.add(modPedBut);

        adminPedLab.setForeground(Color.WHITE);
        estilizarAlta(adminPedBut);
        estilizarBaja(borPedBut);
        estilizarModificar(modPedBut);

        MuestraPedidos(modelo);

        adminPedBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarPedidos ven =
                        new AgregarPedidos(
                                1, main, modelo,
                                VentanaPrincipal.this);
                ven.setVisible(true);
            }
        });


		borPedBut.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		
		        if (tablita.getSelectedRow() == -1) {
		            JOptionPane.showMessageDialog(
		                    VentanaPrincipal.this,
		                    "Seleccioná un pedido de la tabla antes de eliminarlo.",
		                    "Ningún pedido seleccionado",
		                    JOptionPane.WARNING_MESSAGE
		            );
		            return;
		        }
		
		        BorrarPedido ven = new BorrarPedido(
		                tablita, modelo, main, VentanaPrincipal.this
		        );
		
		        ven.setVisible(true);
		    }
		});


        modPedBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        VentanaPrincipal.this,
                        "La función de modificar pedidos todavía "
                        + "no está conectada.",
                        "Modificar pedidos",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        actualizarPaneles(granPan, panInf);
    }

    public void MuestraPedidos(DefaultTableModel t) {
        t.setRowCount(0);
        main.ListarPedidos(t);
    }
}
