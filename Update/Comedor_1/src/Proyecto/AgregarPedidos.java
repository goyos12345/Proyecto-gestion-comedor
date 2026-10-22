
package Proyecto;

import javax.swing.*;
import java.awt.*;

public class AgregarPedidos extends JFrame {

    private final Color fondoCol = new Color(244, 235, 220);
    private final Color panSupCol = new Color(185, 154, 115);
    private final Color panInfCol = new Color(155, 121, 83);
    private final Color altaCol = new Color(155, 190, 145);
    private final Color txtCol = new Color(51, 42, 33);

    public AgregarPedidos(
            int op, gestor g, javax.swing.table.DefaultTableModel t,
            VentanaPrincipal vp) {

        setTitle("Gestor del comedor - Agregar pedido");
        setSize(650, 430);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBackground(fondoCol);
        principal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setContentPane(principal);

        // Encabezado
        JLabel titulo = new JLabel("Registrar nuevo pedido", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(txtCol);
        titulo.setOpaque(true);
        titulo.setBackground(panSupCol);
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 5, 12, 5));

        principal.add(titulo, BorderLayout.NORTH);

        // Formulario
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBackground(fondoCol);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fecha
        SpinnerNumberModel modDia = new SpinnerNumberModel(1, 1, 31, 1);
        SpinnerNumberModel modMes = new SpinnerNumberModel(1, 1, 12, 1);
        SpinnerNumberModel modAnio =
                new SpinnerNumberModel(2026, 2000, 2100, 1);

        JSpinner spinDia = new JSpinner(modDia);
        JSpinner spinMes = new JSpinner(modMes);
        JSpinner spinAnio = new JSpinner(modAnio);

        JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        fechaPanel.setBackground(fondoCol);
        fechaPanel.add(new JLabel("Día"));
        fechaPanel.add(spinDia);
        fechaPanel.add(new JLabel("Mes"));
        fechaPanel.add(spinMes);
        fechaPanel.add(new JLabel("Año"));
        fechaPanel.add(spinAnio);

        JTextField ingredientesTxt = new JTextField(20);
        JTextField nroLoteTxt = new JTextField(20);

        SpinnerNumberModel modCan =
                new SpinnerNumberModel(1, 1, 999999999, 1);
        JSpinner spinCan = new JSpinner(modCan);

        // Lista de proveedores
        java.util.ArrayList<String> listPr = new java.util.ArrayList<>();
        g.NombreProv(listPr);

        DefaultComboBoxModel<String> modeloProv =
                new DefaultComboBoxModel<>();

        for (String proveedor : listPr) {
            modeloProv.addElement(proveedor);
        }

        JComboBox<String> comboPr = new JComboBox<>(modeloProv);
        comboPr.setPreferredSize(new Dimension(240, 28));

        agregarCampo(formulario, gbc, 0, "Fecha del pedido:", fechaPanel);
        agregarCampo(formulario, gbc, 1, "Ingrediente:", ingredientesTxt);
        agregarCampo(formulario, gbc, 2, "Proveedor:", comboPr);
        agregarCampo(formulario, gbc, 3, "Número de lote:", nroLoteTxt);
        agregarCampo(formulario, gbc, 4, "Cantidad (kg):", spinCan);

        principal.add(formulario, BorderLayout.CENTER);

        // Botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        botones.setBackground(panInfCol);

        JButton aceptarBut = new JButton("+ Registrar pedido");
        JButton cancelarBut = new JButton("Cancelar");

        aceptarBut.setBackground(altaCol);
        aceptarBut.setForeground(new Color(35, 65, 32));

        cancelarBut.setBackground(new Color(217, 185, 143));
        cancelarBut.setForeground(txtCol);

        estilizarBoton(aceptarBut);
        estilizarBoton(cancelarBut);

        botones.add(aceptarBut);
        botones.add(cancelarBut);

        principal.add(botones, BorderLayout.SOUTH);

        // Registrar pedido
        aceptarBut.addActionListener(e -> {

            String ingrediente = ingredientesTxt.getText().trim();
            String loteTexto = nroLoteTxt.getText().trim();
            String proveedor = (String) comboPr.getSelectedItem();

            if (ingrediente.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ingresá el nombre del ingrediente.",
                        "Campo obligatorio",
                        JOptionPane.WARNING_MESSAGE
                );
                ingredientesTxt.requestFocus();
                return;
            }

            if (loteTexto.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ingresá el número de lote.",
                        "Campo obligatorio",
                        JOptionPane.WARNING_MESSAGE
                );
                nroLoteTxt.requestFocus();
                return;
            }

            if (!loteTexto.matches("\\d+")) {
                JOptionPane.showMessageDialog(
                        this,
                        "El número de lote debe contener solamente números.",
                        "Dato inválido",
                        JOptionPane.WARNING_MESSAGE
                );
                nroLoteTxt.requestFocus();
                return;
            }

            int nroLote;

            try {
                nroLote = Integer.parseInt(loteTexto);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "El número de lote es demasiado grande.",
                        "Dato inválido",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (proveedor == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Primero debe existir al menos un proveedor.",
                        "Sin proveedores",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int dia = (int) spinDia.getValue();
            int mes = (int) spinMes.getValue();
            int anio = (int) spinAnio.getValue();
            int cantidad = (int) spinCan.getValue();

            // Validar que la fecha exista
            java.time.LocalDate fecha;

            try {
                fecha = java.time.LocalDate.of(anio, mes, dia);
            } catch (java.time.DateTimeException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "La fecha ingresada no existe.",
                        "Fecha inválida",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int idProveedor = g.encontrarProveedor(0, proveedor);

            try {
                g.AltaPedidos(
                        fecha.getDayOfMonth(),
                        fecha.getMonthValue(),
                        fecha.getYear(),
                        ingrediente,
                        idProveedor,
                        nroLote,
                        cantidad
                );

                vp.MuestraPedidos(t);

                JOptionPane.showMessageDialog(
                        this,
                        "El pedido se agregó correctamente.",
                        "Pedido registrado",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo registrar el pedido.\n"
                                + "Detalle: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        cancelarBut.addActionListener(e -> dispose());

        getRootPane().setDefaultButton(aceptarBut);
    }

    private void agregarCampo(
            JPanel panel, GridBagConstraints gbc, int fila,
            String texto, Component campo) {

        gbc.gridy = fila;
        gbc.gridx = 0;
        gbc.weightx = 0;

        JLabel etiqueta = new JLabel(texto);
        etiqueta.setForeground(txtCol);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 14));

        panel.add(etiqueta, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(campo, gbc);
    }

    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(120, 105, 85)),
                BorderFactory.createEmptyBorder(7, 12, 7, 12)
        ));
    }
    
    private void estilizarLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(51, 42, 33));
    }
}
