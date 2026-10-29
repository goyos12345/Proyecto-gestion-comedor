
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BorrarMenu extends JFrame {

    private final Color bajaCol = new Color(205, 135, 125);

    public BorrarMenu(
            JTable tab, DefaultTableModel modelo,
            gestor g, VentanaPrincipal vp) {

        EstiloVentanas.configurarVentana(
                this, "Eliminar menú", 540, 270);

        int fila = tab.getSelectedRow();
        int id = -1;

        if (fila != -1) {
            int filaModelo = tab.convertRowIndexToModel(fila);
            id = Integer.parseInt(
                    modelo.getValueAt(filaModelo, 0).toString());
        }

        final int idSeleccionado = id;

        JPanel contenido = EstiloVentanas.crearPanel();
        contenido.setLayout(new BorderLayout(10, 15));
        contenido.setBorder(
                BorderFactory.createEmptyBorder(25, 25, 20, 25));

        JLabel mensaje = new JLabel(
                idSeleccionado == -1
                        ? "No hay ningún menú seleccionado."
                        : "¿Querés eliminar el menú con ID "
                                + idSeleccionado + "?",
                SwingConstants.CENTER);

        EstiloVentanas.estilizarEtiqueta(mensaje);
        mensaje.setFont(new Font("Arial", Font.BOLD, 18));

        contenido.add(mensaje, BorderLayout.CENTER);

        JPanel botones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 20, 15));
        botones.setBackground(EstiloVentanas.FONDO);

        JButton aceptar = EstiloVentanas.crearBoton(
                "Eliminar", bajaCol);

        JButton cancelar = EstiloVentanas.crearBoton(
                "Cancelar", bajaCol);

        estilizarBoton(aceptar);
        estilizarBoton(cancelar);

        aceptar.setEnabled(idSeleccionado != -1);

        botones.add(aceptar);
        botones.add(cancelar);

        add(contenido, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        aceptar.addActionListener(e -> {

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "Esta acción no se puede deshacer.\n¿Continuar?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            try {
                g.BajaMenus(idSeleccionado);
                g.ListarMenus(modelo);

                JOptionPane.showMessageDialog(
                        this,
                        "El menú se eliminó correctamente.",
                        "Operación exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo eliminar el menú.\n"
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
