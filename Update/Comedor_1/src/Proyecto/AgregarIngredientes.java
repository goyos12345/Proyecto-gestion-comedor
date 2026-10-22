
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;

public class AgregarIngredientes extends JFrame {

    public AgregarIngredientes(gestor main, VentanaPrincipal vp,
                               DefaultTableModel m) {
        EstiloVentanas.configurarVentana(
                this, "Agregar ingrediente", 500, 380);

        JPanel formulario = EstiloVentanas.crearPanel();
        formulario.setLayout(new GridLayout(5, 2, 10, 12));
        formulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nomLab = new JLabel("Nombre:");
        JLabel conLab = new JLabel("Contenedor:");
        JLabel fechaLab = new JLabel("Caducidad (día/mes/año):");
        JLabel marcaLab = new JLabel("Marca:");
        JLabel cantLab = new JLabel("Unidades del lote:");

        JTextField nomTxt = new JTextField(14);
        JTextField conTxt = new JTextField(14);
        JTextField marTxt = new JTextField(14);

        JSpinner spinDia = new JSpinner(
                new SpinnerNumberModel(1, 1, 31, 1));
        JSpinner spinMes = new JSpinner(
                new SpinnerNumberModel(1, 1, 12, 1));
        JSpinner spinAnio = new JSpinner(
                new SpinnerNumberModel(2026, 2000, 2100, 1));
        JSpinner spinCan = new JSpinner(
                new SpinnerNumberModel(1, 1, 999999999, 1));

        JPanel fechaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        fechaPanel.setOpaque(false);
        fechaPanel.add(spinDia);
        fechaPanel.add(new JLabel("/"));
        fechaPanel.add(spinMes);
        fechaPanel.add(new JLabel("/"));
        fechaPanel.add(spinAnio);

        JTextField[] campos = {nomTxt, conTxt, marTxt};
        for (JTextField campo : campos) {
            EstiloVentanas.estilizarCampo(campo);
        }

        JLabel[] etiquetas = {nomLab, conLab, fechaLab, marcaLab, cantLab};
        for (JLabel etiqueta : etiquetas) {
            EstiloVentanas.estilizarEtiqueta(etiqueta);
        }

        formulario.add(nomLab);
        formulario.add(nomTxt);
        formulario.add(conLab);
        formulario.add(conTxt);
        formulario.add(fechaLab);
        formulario.add(fechaPanel);
        formulario.add(marcaLab);
        formulario.add(marTxt);
        formulario.add(cantLab);
        formulario.add(spinCan);

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
            String marca = marTxt.getText().trim();

            int dia = (int) spinDia.getValue();
            int mes = (int) spinMes.getValue();
            int anio = (int) spinAnio.getValue();
            int cantidad = (int) spinCan.getValue();

            if (nombre.isEmpty() || contenedor.isEmpty() || marca.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Completá todos los campos.",
                        "Campos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                LocalDate.of(anio, mes, dia);
            } catch (DateTimeException ex) {
                JOptionPane.showMessageDialog(this,
                        "La fecha de caducidad no es válida.",
                        "Fecha incorrecta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                main.altaLoteIngredientes(
                        nombre, contenedor, dia, mes, anio, cantidad, marca);

                vp.muestraIngredientes(m);

                JOptionPane.showMessageDialog(this,
                        "El lote de ingredientes se agregó correctamente.",
                        "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo agregar el lote.\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelar.addActionListener(e -> dispose());
    }
}
