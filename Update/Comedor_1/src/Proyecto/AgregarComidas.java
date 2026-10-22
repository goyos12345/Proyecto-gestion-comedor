
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgregarComidas extends JFrame {

    public AgregarComidas(gestor g, DefaultTableModel t, VentanaPrincipal vp) {
        EstiloVentanas.configurarVentana(this, "Agregar comida", 450, 350);

        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(5, 2, 10, 12));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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

        JLabel[] etiquetas = {nomLab, conLab, cantLab, prepLab, friLab};
        for (JLabel etiqueta : etiquetas) {
            EstiloVentanas.estilizarEtiqueta(etiqueta);
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

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botones.setBackground(EstiloVentanas.FONDO);

        JButton aceptar = EstiloVentanas.crearBoton(
                "Agregar", EstiloVentanas.VERDE);
        JButton cancelar = EstiloVentanas.crearBoton(
                "Cancelar", EstiloVentanas.BOTON);

        botones.add(aceptar);
        botones.add(cancelar);

        add(formulario, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        aceptar.addActionListener(e -> {
            String nombre = nomTxt.getText().trim();
            String contenedor = conTxt.getText().trim();
            int cantidad = (int) spinCan.getValue();

            if (nombre.isEmpty() || contenedor.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Completá el nombre y el contenedor.",
                        "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                g.altaComidas(nombre, contenedor, cantidad,
                        prepCB.isSelected(), friCB.isSelected());

                g.listarComidas(t);

                JOptionPane.showMessageDialog(this,
                        "La comida se agregó correctamente.",
                        "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo agregar la comida.\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelar.addActionListener(e -> dispose());
    }
}
