package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ModificarComidas extends JFrame {

    private final Color altaCol = new Color(155, 190, 145);
    private final Color bot1Col = new Color(217, 185, 143);
    private final Color txtCol = new Color(51, 42, 33);

    public ModificarComidas(JTable tab, DefaultTableModel mod, gestor g) {

        EstiloVentanas.configurarVentana(
                this, "Modificar comida", 450, 350);

        // Obtener la comida seleccionada
        String seleS = "" + tab.getValueAt(tab.getSelectedRow(), 0);
        int sele = Integer.parseInt(seleS);

        // Panel principal del formulario
        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(6, 2, 10, 12));
        formulario.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiquetas
        JLabel modLab = new JLabel("Modificando comida");
        JLabel nomLab = new JLabel("Nombre:");
        JLabel conLab = new JLabel("Contenedor:");
        JLabel cantLab = new JLabel("Cantidad:");
        JLabel prepLab = new JLabel("Está preparada:");
        JLabel friLab = new JLabel("Está frizada:");

        EstiloVentanas.estilizarEtiqueta(modLab);
        EstiloVentanas.estilizarEtiqueta(nomLab);
        EstiloVentanas.estilizarEtiqueta(conLab);
        EstiloVentanas.estilizarEtiqueta(cantLab);
        EstiloVentanas.estilizarEtiqueta(prepLab);
        EstiloVentanas.estilizarEtiqueta(friLab);

        // Campos de texto
        JTextField nomTxt = new JTextField(14);
        JTextField conTxt = new JTextField(14);

        EstiloVentanas.estilizarCampo(nomTxt);
        EstiloVentanas.estilizarCampo(conTxt);

        // Spinner de cantidad
        JSpinner spinCan = new JSpinner(
                new SpinnerNumberModel(1, 1, 999999999, 1));

        // CheckBoxes
        JCheckBox prepCB = new JCheckBox();
        JCheckBox friCB = new JCheckBox();

        prepCB.setOpaque(false);
        friCB.setOpaque(false);

        // Cargar datos actuales de la comida
        g.TxtsComidas(
                sele, nomTxt, prepCB, friCB, conTxt, spinCan);

        // Agregar componentes al formulario
        formulario.add(modLab);
        formulario.add(new JLabel(""));

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

        // Función para modificar la comida
        aceptar.addActionListener(e -> {

            String nombre = nomTxt.getText().trim();
            String contenedor = conTxt.getText().trim();

            int cantidad = (int) spinCan.getValue();

            boolean preparado = prepCB.isSelected();
            boolean frizado = friCB.isSelected();

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

                // Modificar comida
                g.ModificarComidas(
                        sele, nombre, contenedor,
                        cantidad, frizado, preparado);

                // Actualizar tabla
                g.listarComidas(mod);

                JOptionPane.showMessageDialog(this,"La comida se modificó correctamente.",
                        "Operación exitosa",JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,"No se pudo modificar la comida.\n"+
                ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
            }
        });

        // Cerrar sin guardar cambios
        cancelar.addActionListener(e -> dispose());
    }
}
