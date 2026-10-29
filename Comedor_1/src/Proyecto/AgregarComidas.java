
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgregarComidas extends JFrame {

    private final Color altaCol = new Color(155, 190, 145);
    private final Color bajaCol = new Color(205, 135, 125);

    public AgregarComidas(
            gestor g, DefaultTableModel t, VentanaPrincipal vp) {

        EstiloVentanas.configurarVentana(
                this, "Agregar comida", 540, 400);

        setResizable(false);

        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(5, 2, 15, 15));
        formulario.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 20, 30));

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

        prepCB.setOpaque(false);
        friCB.setOpaque(false);

        EstiloVentanas.estilizarCampo(nomTxt);
        EstiloVentanas.estilizarCampo(conTxt);

        spinCan.setFont(new Font("Arial", Font.PLAIN, 16));
        prepCB.setPreferredSize(new Dimension(30, 30));
        friCB.setPreferredSize(new Dimension(30, 30));

        JLabel[] etiquetas = {
            nomLab, conLab, cantLab, prepLab, friLab
        };

        for (JLabel etiqueta : etiquetas) {
            EstiloVentanas.estilizarEtiqueta(etiqueta);
            etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
        }

        formulario.add(nomLab);
        formulario.add(nomTxt);

        formulario.add(conLab);
        formulario.add(conTxt);

        formulario.add(cantLab);
        formulario.add(spinCan);

        formulario.add(prepLab);
        formulario.add(prepCB);

        formulario.add(friLab);
        formulario.add(friCB);

        JPanel botones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 20, 15));
        botones.setBackground(EstiloVentanas.FONDO);

        JButton aceptar = EstiloVentanas.crearBoton(
                "Agregar", altaCol);

        JButton cancelar = EstiloVentanas.crearBoton(
                "Cancelar", bajaCol);

        estilizarBoton(aceptar);
        estilizarBoton(cancelar);

        botones.add(aceptar);
        botones.add(cancelar);

        add(formulario, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        aceptar.addActionListener(e -> {

            String nombre = nomTxt.getText().trim();
            String contenedor = conTxt.getText().trim();
            int cantidad = (int) spinCan.getValue();

            if (nombre.isEmpty() || contenedor.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Completá el nombre y el contenedor.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                g.altaComidas(
                        nombre,
                        contenedor,
                        cantidad,
                        prepCB.isSelected(),
                        friCB.isSelected());

                g.listarComidas(t);

                JOptionPane.showMessageDialog(
                        this,
                        "La comida se agregó correctamente.",
                        "Operación exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo agregar la comida.\n"
                                + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelar.addActionListener(e -> dispose());
    }

    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.BOLD, 16));
        boton.setPreferredSize(new Dimension(145, 45));
        boton.setFocusPainted(false);
    }
}
