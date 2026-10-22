
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BorrarPedido extends JFrame {

    private final Color fondoCol = new Color(244, 235, 220);
    private final Color panSupCol = new Color(185, 154, 115);
    private final Color panInfCol = new Color(155, 121, 83);
    private final Color bajaCol = new Color(205, 135, 125);
    private final Color txtCol = new Color(51, 42, 33);

    public BorrarPedido(
            JTable tab, DefaultTableModel modelo,
            gestor g, VentanaPrincipal vp) {

        setTitle("Gestor del comedor - Eliminar pedido");
        setSize(500, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Comprobar que haya una fila seleccionada
        int filaSeleccionada = tab.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(
                    vp,
                    "Seleccioná un pedido de la tabla antes de eliminarlo.",
                    "Ningún pedido seleccionado",
                    JOptionPane.WARNING_MESSAGE
            );

            dispose();
            return;
        }

        // Convertir el índice visual al índice del modelo
        int filaModelo = tab.convertRowIndexToModel(filaSeleccionada);
        Object valorId = modelo.getValueAt(filaModelo, 0);
        String idPedido = String.valueOf(valorId);

        JPanel principal = new JPanel(new BorderLayout(10, 15));
        principal.setBackground(fondoCol);
        principal.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        setContentPane(principal);

        // Encabezado
        JLabel titulo = new JLabel(
                "Eliminar pedido", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(txtCol);
        titulo.setOpaque(true);
        titulo.setBackground(panSupCol);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));

        principal.add(titulo, BorderLayout.NORTH);

        // Información
        JPanel informacion = new JPanel(
                new GridLayout(2, 1, 5, 10));
        informacion.setBackground(fondoCol);

        JLabel pregunta = new JLabel(
                "¿Estás seguro de que querés eliminar este pedido?",
                SwingConstants.CENTER);
        pregunta.setForeground(txtCol);
        pregunta.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel idLabel = new JLabel(
                "ID del pedido: " + idPedido,
                SwingConstants.CENTER);
        idLabel.setForeground(txtCol);
        idLabel.setFont(new Font("Arial", Font.BOLD, 16));

        informacion.add(pregunta);
        informacion.add(idLabel);

        principal.add(informacion, BorderLayout.CENTER);

        // Botones
        JPanel botones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 5));
        botones.setBackground(panInfCol);

        JButton eliminarBut = new JButton("- Eliminar pedido");
        JButton cancelarBut = new JButton("Cancelar");

        eliminarBut.setBackground(bajaCol);
        eliminarBut.setForeground(new Color(85, 30, 25));

        cancelarBut.setBackground(new Color(217, 185, 143));
        cancelarBut.setForeground(txtCol);

        estilizarBoton(eliminarBut);
        estilizarBoton(cancelarBut);

        botones.add(eliminarBut);
        botones.add(cancelarBut);

        principal.add(botones, BorderLayout.SOUTH);

        // Eliminar pedido
        eliminarBut.addActionListener(e -> {

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "Esta acción no se puede deshacer.\n"
                            + "¿Querés eliminar el pedido " + idPedido + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirmacion != JOptionPane.YES_OPTION) {
                return;
            }

            try {
                int idEliNum = Integer.parseInt(idPedido);

                g.BajaPedidos(idEliNum);

                // Actualizar la tabla desde los datos del gestor
                vp.MuestraPedidos(modelo);

                JOptionPane.showMessageDialog(
                        this,
                        "El pedido con ID " + idPedido
                                + " se eliminó correctamente.",
                        "Pedido eliminado",
                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo eliminar el pedido.\n"
                                + "Detalle: " + ex.getMessage(),
                        "Error al eliminar",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        cancelarBut.addActionListener(e -> dispose());

        getRootPane().setDefaultButton(cancelarBut);
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
