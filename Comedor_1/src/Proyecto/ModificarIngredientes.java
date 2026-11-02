package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ModificarIngredientes extends JFrame {

    private final Color altaCol = new Color(155, 190, 145);
    private final Color bot1Col = new Color(217, 185, 143);
    private final Color txtCol = new Color(51, 42, 33);

    public ModificarIngredientes(JTable tab, DefaultTableModel mod, gestor g) {

        EstiloVentanas.configurarVentana(
                this, "Modificar ingrediente", 600, 400);

        // Obtener el ingrediente seleccionado
        String seleS = "" + tab.getValueAt(tab.getSelectedRow(), 0);
        int sele = Integer.parseInt(seleS);

        // Panel principal del formulario
        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(6, 2, 10, 12));
        formulario.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiquetas
        JLabel modLab = new JLabel("Modificando ingrediente");
        JLabel nomLab = new JLabel("Nombre:");
        JLabel conLab = new JLabel("Contenedor:");
        JLabel fechaLab = new JLabel("Caducidad:");
        JLabel marcaLab = new JLabel("Proveedor:");
        JLabel cantLab = new JLabel("Unidades del lote:");

        EstiloVentanas.estilizarEtiqueta(modLab);
        EstiloVentanas.estilizarEtiqueta(nomLab);
        EstiloVentanas.estilizarEtiqueta(conLab);
        EstiloVentanas.estilizarEtiqueta(fechaLab);
        EstiloVentanas.estilizarEtiqueta(marcaLab);
        EstiloVentanas.estilizarEtiqueta(cantLab);

        // Campos de texto
        JTextField nomTxt = new JTextField(14);
        JTextField conTxt = new JTextField(14);

        EstiloVentanas.estilizarCampo(nomTxt);
        EstiloVentanas.estilizarCampo(conTxt);

        // Spinners de fecha y cantidad
        JSpinner spinDia = new JSpinner(
                new SpinnerNumberModel(1, 1, 31, 1));

        JSpinner spinMes = new JSpinner(
                new SpinnerNumberModel(1, 1, 12, 1));

        JSpinner spinAnio = new JSpinner(
                new SpinnerNumberModel(2026, 2000, 2100, 1));

        JSpinner spinCan = new JSpinner(
                new SpinnerNumberModel(1, 1, 999999999, 1));

        // Panel de fecha
        JPanel panFecha = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 5, 0));

        panFecha.setOpaque(false);

        JLabel diaLab = new JLabel("Día");
        JLabel mesLab = new JLabel("Mes");
        JLabel anioLab = new JLabel("Año");

        EstiloVentanas.estilizarEtiqueta(diaLab);
        EstiloVentanas.estilizarEtiqueta(mesLab);
        EstiloVentanas.estilizarEtiqueta(anioLab);

        panFecha.add(diaLab);
        panFecha.add(spinDia);
        panFecha.add(mesLab);
        panFecha.add(spinMes);
        panFecha.add(anioLab);
        panFecha.add(spinAnio);

        // ComboBox de proveedores
        ArrayList<String> listPr = new ArrayList<>();
        g.NombreProv(listPr);

        DefaultComboBoxModel<String> modelo =
                new DefaultComboBoxModel<>();

        for (String s : listPr) {
            modelo.addElement(s);
        }

        JComboBox<String> comboPr = new JComboBox<>(modelo);

        // Cargar datos actuales del ingrediente
        g.TxtsIngredientes(
                sele, nomTxt, conTxt, spinDia,
                spinMes, spinAnio, comboPr, spinCan);

        // Agregar componentes al formulario
        formulario.add(modLab);
        formulario.add(new JLabel(""));

        formulario.add(nomLab);
        formulario.add(nomTxt);

        formulario.add(conLab);
        formulario.add(conTxt);

        formulario.add(fechaLab);
        formulario.add(panFecha);

        formulario.add(marcaLab);
        formulario.add(comboPr);

        formulario.add(cantLab);
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

        // Distribución de los paneles
        add(formulario, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        // Función para modificar el ingrediente
        aceptar.addActionListener(e -> {

            String nombre = nomTxt.getText().trim();
            String contenedor = conTxt.getText().trim();

            int d = (int) spinDia.getValue();
            int m = (int) spinMes.getValue();
            int a = (int) spinAnio.getValue();
            int c = (int) spinCan.getValue();

            // Comprobar campos vacíos
            if (nombre.isEmpty() || contenedor.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Completá todos los campos.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {

                // Obtener proveedor seleccionado
                String provNom = "" + comboPr.getSelectedItem();
                int IdProv = g.encontrarProveedor(0, provNom);

                // Modificar ingrediente
                g.ModificarIngrediente(
                        sele, nombre, contenedor,
                        c, d, m, a, IdProv);

                // Actualizar tabla
                g.listarLoteIngrdientes(mod);

                JOptionPane.showMessageDialog(
                        this,
                        "El ingrediente se modificó correctamente.",
                        "Operación exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo modificar el ingrediente.\n"
                                + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cerrar sin guardar cambios
        cancelar.addActionListener(e -> dispose());
    }
}
