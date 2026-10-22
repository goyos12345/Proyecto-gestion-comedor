
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdministrarProveedores extends JFrame {

    public AdministrarProveedores(int op, ArrayList<Proveedores> arrClase,
                                  DefaultTableModel t, VentanaPrincipal vP) {
        EstiloVentanas.configurarVentana(
                this, "Administrar proveedores", 450, 280);

        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(3, 2, 10, 15));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        botones.setBackground(EstiloVentanas.FONDO);

        JButton aceptar = EstiloVentanas.crearBoton(
                "Agregar", EstiloVentanas.VERDE);
        JButton cancelar = EstiloVentanas.crearBoton(
                "Cancelar", EstiloVentanas.BOTON);

        if (op != 1) {
            JLabel aviso = new JLabel(
                    "Esta operación todavía no está implementada.",
                    SwingConstants.CENTER);

            EstiloVentanas.estilizarEtiqueta(aviso);
            add(aviso, BorderLayout.CENTER);

            cancelar.setText("Cerrar");
            botones.add(cancelar);
            add(botones, BorderLayout.SOUTH);

            cancelar.addActionListener(e -> dispose());
            return;
        }

        JLabel nomLab = new JLabel("Nombre:");
        JLabel prefLab = new JLabel("Prefijo telefónico:");
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

        botones.add(aceptar);
        botones.add(cancelar);

        add(formulario, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        aceptar.addActionListener(e -> {
            String nombre = nomTxt.getText().trim();
            String telefono = telTxt.getText().trim();

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

            for (Proveedores proveedor : arrClase) {
                if (proveedor.getNombre().equalsIgnoreCase(nombre)) {
                    JOptionPane.showMessageDialog(this,
                            "Ya existe un proveedor con ese nombre.",
                            "Proveedor duplicado", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            int id = 0;
            boolean repetido;

            do {
                repetido = false;

                for (Proveedores proveedor : arrClase) {
                    if (proveedor.getID() == id) {
                        repetido = true;
                        id++;
                        break;
                    }
                }
            } while (repetido);

            try {
                Proveedores prov = new Proveedores(
                        id, nombre, (int) spinPais.getValue(), telefono);

                arrClase.add(prov);
                vP.agregaFilasProv(t);

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
