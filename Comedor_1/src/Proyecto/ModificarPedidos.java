package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class ModificarPedidos extends JFrame {

    private final Color altaCol = new Color(155, 190, 145);
    private final Color bot1Col = new Color(217, 185, 143);
    private final Color txtCol = new Color(51, 42, 33);

    public ModificarPedidos(JTable tab, DefaultTableModel mod, gestor g) {

        EstiloVentanas.configurarVentana(
                this, "Modificar pedido", 600, 400);

        // Obtener el pedido seleccionado
        String seleS = "" + tab.getValueAt(tab.getSelectedRow(), 0);
        int sele = Integer.parseInt(seleS);

        // Panel principal
        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(6, 2, 10, 12));
        formulario.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiquetas
        JLabel modLab = new JLabel("Modificando pedido");
        JLabel ingLab = new JLabel("Insumo:");
        JLabel nroLab = new JLabel("Número de lote:");
        JLabel proLab = new JLabel("Proveedor:");
        JLabel fecLab = new JLabel("Fecha:");
        JLabel canLab = new JLabel("Cantidad:");

        EstiloVentanas.estilizarEtiqueta(modLab);
        EstiloVentanas.estilizarEtiqueta(ingLab);
        EstiloVentanas.estilizarEtiqueta(nroLab);
        EstiloVentanas.estilizarEtiqueta(proLab);
        EstiloVentanas.estilizarEtiqueta(fecLab);
        EstiloVentanas.estilizarEtiqueta(canLab);

        // Campos de texto
        JTextField ingTxt = new JTextField(14);
        JTextField nroTxt = new JTextField(14);

        EstiloVentanas.estilizarCampo(ingTxt);
        EstiloVentanas.estilizarCampo(nroTxt);

        // Permitir únicamente números en el lote
        nroTxt.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Character.isDigit(c)
                        && !Character.isISOControl(c)) {
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        // Spinners de fecha y cantidad
        SpinnerNumberModel modDia =
                new SpinnerNumberModel(1, 1, 31, 1);

        SpinnerNumberModel modMes =
                new SpinnerNumberModel(1, 1, 12, 1);

        SpinnerNumberModel modAño =
                new SpinnerNumberModel(2000, 2000, 2100, 1);

        SpinnerNumberModel modCan =
                new SpinnerNumberModel(1, 1, 999999999, 1);

        JSpinner spinDia = new JSpinner(modDia);
        JSpinner spinMes = new JSpinner(modMes);
        JSpinner spinAño = new JSpinner(modAño);
        JSpinner spinCan = new JSpinner(modCan);

        // Panel de fecha
        JPanel panFecha = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 5, 0));

        panFecha.setOpaque(false);

        JLabel diaLab = new JLabel("Día");
        JLabel mesLab = new JLabel("Mes");
        JLabel añoLab = new JLabel("Año");

        EstiloVentanas.estilizarEtiqueta(diaLab);
        EstiloVentanas.estilizarEtiqueta(mesLab);
        EstiloVentanas.estilizarEtiqueta(añoLab);

        panFecha.add(diaLab);
        panFecha.add(spinDia);
        panFecha.add(mesLab);
        panFecha.add(spinMes);
        panFecha.add(añoLab);
        panFecha.add(spinAño);

        // ComboBox de proveedores
        ArrayList<String> listPr = new ArrayList<>();
        g.NombreProv(listPr);

        DefaultComboBoxModel<String> modelo =
                new DefaultComboBoxModel<>();

        for (String s : listPr) {
            modelo.addElement(s);
        }

        JComboBox<String> comboPr = new JComboBox<>(modelo);

        // Cargar datos actuales del pedido
        g.TxtsPedidos(
                sele, spinDia, spinMes, spinAño,
                spinCan, ingTxt, nroTxt, comboPr);

        // Agregar componentes al formulario
        formulario.add(modLab);
        formulario.add(new JLabel(""));

        formulario.add(ingLab);
        formulario.add(ingTxt);

        formulario.add(nroLab);
        formulario.add(nroTxt);

        formulario.add(proLab);
        formulario.add(comboPr);

        formulario.add(fecLab);
        formulario.add(panFecha);

        formulario.add(canLab);
        formulario.add(spinCan);

        // Panel de botones
        JPanel botones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 10));

        botones.setBackground(EstiloVentanas.FONDO);

        JButton aceptar = EstiloVentanas.crearBoton(
                "Guardar cambios", altaCol);

        JButton cancelar = EstiloVentanas.crearBoton(
                "Cancelar", bot1Col);

        // Asegurar colores exactos
        aceptar.setBackground(altaCol);
        cancelar.setBackground(bot1Col);

        aceptar.setForeground(txtCol);
        cancelar.setForeground(txtCol);

        botones.add(aceptar);
        botones.add(cancelar);

        // Distribución de paneles
        add(formulario, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        // Función para modificar el pedido
        aceptar.addActionListener(e -> {

            String ing = ingTxt.getText().trim();
            String nro = nroTxt.getText().trim();

            // Comprobar campos vacíos
            if (ing.isEmpty() || nro.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Completá todos los campos.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {

                int nroLote = Integer.parseInt(nro);

                String provNom = "" + comboPr.getSelectedItem();
                int IdProv = g.encontrarProveedor(0, provNom);

                int dia = (int) spinDia.getValue();
                int mes = (int) spinMes.getValue();
                int año = (int) spinAño.getValue();

                int can = (int) spinCan.getValue();

                // Modificar pedido
                g.ModificarPedido(
                        sele, año, mes, dia,
                        ing, IdProv, nroLote, can);

                // Actualizar tabla
                g.ListarPedidos(mod);

                JOptionPane.showMessageDialog(
                        this,
                        "El pedido se modificó correctamente.",
                        "Operación exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "El número de lote no es válido.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo modificar el pedido.\n"
                                + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cerrar sin guardar cambios
        cancelar.addActionListener(e -> dispose());
    }
}
