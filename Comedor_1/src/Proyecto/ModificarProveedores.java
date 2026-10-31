
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ModificarProveedores extends JFrame {

	private final Color altaCol = new Color(155, 190, 145);
	private final Color bot1Col = new Color(217, 185, 143);
	private final Color txtCol = new Color(51, 42, 33);

	public ModificarProveedores(JTable tab, DefaultTableModel mod, gestor g) {

        EstiloVentanas.configurarVentana(
                this, "Modificar proveedor", 450, 280);

        // Obtener el proveedor seleccionado
        String seleS = "" + tab.getValueAt(tab.getSelectedRow(), 0);
        int sele = Integer.parseInt(seleS);

        // Panel principal del formulario
        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(3, 2, 10, 15));
        formulario.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiquetas
        JLabel modLab = new JLabel("Modificando proveedor");
        JLabel nomLab = new JLabel("Nombre:");
        JLabel telLab = new JLabel("Teléfono:");

        EstiloVentanas.estilizarEtiqueta(modLab);
        EstiloVentanas.estilizarEtiqueta(nomLab);
        EstiloVentanas.estilizarEtiqueta(telLab);

        // Campos de texto
        JTextField nomTxt = new JTextField(14);
        JTextField telTxt = new JTextField(14);

        EstiloVentanas.estilizarCampo(nomTxt);
        EstiloVentanas.estilizarCampo(telTxt);

        // Cargar los datos actuales del proveedor
        g.TxtsProveedor(sele, nomTxt, telTxt);

        // Permitir únicamente números en el teléfono
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
                // Sin acciones adicionales
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // Sin acciones adicionales
            }
        });

        // Agregar componentes al formulario
        formulario.add(modLab);
        formulario.add(new JLabel(""));
        formulario.add(nomLab);
        formulario.add(nomTxt);
        formulario.add(telLab);
        formulario.add(telTxt);

        // Panel de botones
        JPanel botones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 10));
        botones.setBackground(EstiloVentanas.FONDO);

        JButton aceptar = EstiloVentanas.crearBoton(
                "Guardar cambios", altaCol);
        JButton cancelar = EstiloVentanas.crearBoton(
                "Cancelar", bot1Col);

        // Asegurar los colores exactos
        aceptar.setBackground(altaCol);
        cancelar.setBackground(bot1Col);

        aceptar.setForeground(txtCol);
        cancelar.setForeground(txtCol);

        botones.add(aceptar);
        botones.add(cancelar);

        // Distribución de los paneles
        add(formulario, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        // Función para modificar el proveedor
        aceptar.addActionListener(e -> {
            String nom = nomTxt.getText().trim();
            String tel = telTxt.getText().trim();

            // Comprobar campos vacíos
            if (nom.isEmpty() || tel.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Completá todos los campos.",
                        "Campos incompletos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Comprobar que el teléfono contenga solo números
            if (!tel.matches("[0-9 ]+")) {
                JOptionPane.showMessageDialog(
                        this,
                        "El teléfono debe contener solamente números y espacios.",
                        "Teléfono incorrecto",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Modificar el proveedor seleccionado
                g.ModificarProveedor(sele, nom, tel);

                // Actualizar la tabla
                g.listarProveedores(mod);

                JOptionPane.showMessageDialog(
                        this,
                        "El proveedor se modificó correctamente.",
                        "Operación exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo modificar el proveedor.\n"
                                + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cerrar sin guardar cambios
        cancelar.addActionListener(e -> dispose());
    }
}
