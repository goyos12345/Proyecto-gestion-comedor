
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AgregarProveedores extends JFrame {

    private final Color altaCol = new Color(155, 190, 145);
    private final Color bajaCol = new Color(205, 135, 125);

    public AgregarProveedores(gestor g, DefaultTableModel t) {

        EstiloVentanas.configurarVentana(
                this, "Agregar proveedor", 540, 350);

        setResizable(false);

        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(3, 2, 15, 18));
        formulario.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 20, 30));

        JLabel nomLab = new JLabel("Nombre:");
        JLabel prefLab = new JLabel("Prefijo telefónico (+):");
        JLabel telLab = new JLabel("Teléfono:");

        JLabel[] etiquetas = {nomLab, prefLab, telLab};

        for (JLabel etiqueta : etiquetas) {
            EstiloVentanas.estilizarEtiqueta(etiqueta);
            etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
        }

        JTextField nomTxt = new JTextField(14);
        JTextField telTxt = new JTextField(14);

        EstiloVentanas.estilizarCampo(nomTxt);
        EstiloVentanas.estilizarCampo(telTxt);

        nomTxt.setFont(new Font("Arial", Font.PLAIN, 16));
        telTxt.setFont(new Font("Arial", Font.PLAIN, 16));

        nomTxt.setPreferredSize(new Dimension(250, 38));
        telTxt.setPreferredSize(new Dimension(250, 38));

        JSpinner spinPais = new JSpinner(
                new SpinnerNumberModel(1, 1, 998, 1));

        spinPais.setFont(new Font("Arial", Font.PLAIN, 16));
        spinPais.setPreferredSize(new Dimension(100, 38));

        formulario.add(nomLab);
        formulario.add(nomTxt);

        formulario.add(prefLab);
        formulario.add(spinPais);

        formulario.add(telLab);
        formulario.add(telTxt);

        // Bloquear letras en el teléfono
        telTxt.addKeyListener(new KeyListener() {

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
            String telefono = telTxt.getText().trim();
            String prefijo = ""+spinPais.getValue();

            if (nombre.isEmpty() || telefono.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Completá el nombre y el teléfono.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!telefono.matches("[0-9]+")) {
                JOptionPane.showMessageDialog(
                        this,
                        "El teléfono debe contener solamente números.",
                        "Teléfono incorrecto",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                boolean seRepite = g.ProvExiste(false, nombre);

                if (seRepite) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ya existe un proveedor con ese nombre.",
                            "Proveedor duplicado",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                g.altaProveedores(nombre, telefono, prefijo);
                g.listarProveedores(t);

                JOptionPane.showMessageDialog(
                        this,
                        "El proveedor se agregó correctamente.",
                        "Operación exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo agregar el proveedor.\n"
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
