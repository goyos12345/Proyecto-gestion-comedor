
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AgregarProveedores extends JFrame {

    public AgregarProveedores(gestor g, DefaultTableModel t) {
        EstiloVentanas.configurarVentana(
                this, "Agregar proveedor", 450, 280);

        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(3, 2, 10, 15));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomLab = new JLabel("Nombre:");
        JLabel prefLab = new JLabel("Prefijo telefónico (+):");
        JLabel telLab = new JLabel("Teléfono:");

        JTextField nomTxt = new JTextField(14);
        JTextField telTxt = new JTextField(14);
        JSpinner spinPais = new JSpinner(
                new SpinnerNumberModel(1, 1, 998, 1));

        EstiloVentanas.estilizarEtiqueta(nomLab);
        EstiloVentanas.estilizarEtiqueta(prefLab);
        EstiloVentanas.estilizarEtiqueta(telLab);
        EstiloVentanas.estilizarCampo(nomTxt);
        EstiloVentanas.estilizarCampo(telTxt);

        formulario.add(nomLab);
        formulario.add(nomTxt);
        formulario.add(prefLab);
        formulario.add(spinPais);
        formulario.add(telLab);
        formulario.add(telTxt);

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
            String telefono = telTxt.getText().trim();
            String prefijo = "+" + spinPais.getValue();

            if (nombre.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Completá el nombre y el teléfono.",
                        "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!telefono.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(this,
                        "El teléfono debe contener solamente números.",
                        "Teléfono incorrecto", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                boolean repetido = g.ProvExiste(false, nombre);

                if (repetido) {
                    JOptionPane.showMessageDialog(this,
                            "Ya existe un proveedor con ese nombre.",
                            "Proveedor duplicado", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                g.altaProveedores(nombre, telefono, prefijo);
                g.listarProveedores(t);

                JOptionPane.showMessageDialog(this,
                        "El proveedor se agregó correctamente.",
                        "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo agregar el proveedor.\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelar.addActionListener(e -> dispose());
    }
}
