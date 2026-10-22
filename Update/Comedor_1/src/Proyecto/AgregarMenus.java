
package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AgregarMenus extends JFrame {

    private final boolean[] dias = new boolean[7];
    private final String[] nombresDias = {
        "Lunes", "Martes", "Miércoles", "Jueves",
        "Viernes", "Sábado", "Domingo"
    };

    private final ArrayList<String> arrComidas = new ArrayList<>();
    private final MySQL mysql1 = new MySQL();

    public AgregarMenus(int op, DefaultTableModel t, VentanaPrincipal vp) {
        EstiloVentanas.configurarVentana(
                this, op == 1 ? "Agregar menú" : "Modificar menú",
                760, 500);

        if (op != 1) {
            mostrarAvisoModificacionNoDisponible();
            return;
        }

        JPanel principal = EstiloVentanas.crearPanel();
        principal.setLayout(new BorderLayout(10, 10));
        principal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel formulario = new JPanel(new GridLayout(4, 1, 5, 8));
        formulario.setOpaque(false);

        JPanel nombrePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        nombrePanel.setOpaque(false);
        JLabel nombreLabel = new JLabel("Nombre del menú:");
        EstiloVentanas.estilizarEtiqueta(nombreLabel);
        JTextField nombreTxt = new JTextField(20);
        EstiloVentanas.estilizarCampo(nombreTxt);
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreTxt);

        JPanel diasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        diasPanel.setOpaque(false);
        JLabel diasLabel = new JLabel("Días:");
        EstiloVentanas.estilizarEtiqueta(diasLabel);
        diasPanel.add(diasLabel);

        JToggleButton[] botonesDias = new JToggleButton[7];

        for (int i = 0; i < nombresDias.length; i++) {
            final int indice = i;
            botonesDias[i] = new JToggleButton(nombresDias[i]);
            botonesDias[i].setBackground(EstiloVentanas.BOTON);
            botonesDias[i].setForeground(EstiloVentanas.TEXTO);
            botonesDias[i].addActionListener(e -> {
                dias[indice] = botonesDias[indice].isSelected();
                botonesDias[indice].setBackground(
                        dias[indice] ? EstiloVentanas.MARRON
                                      : EstiloVentanas.BOTON);
            });
            diasPanel.add(botonesDias[i]);
        }

        JPanel platosPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        platosPanel.setOpaque(false);
        JLabel platosLabel = new JLabel("Plato:");
        EstiloVentanas.estilizarEtiqueta(platosLabel);
        JTextField platoTxt = new JTextField(18);
        EstiloVentanas.estilizarCampo(platoTxt);
        JButton agregarPlato = EstiloVentanas.crearBoton(
                "Agregar plato", EstiloVentanas.BOTON);

        JLabel listaPlatos = new JLabel("Platos agregados: ninguno");

        agregarPlato.addActionListener(e -> {
            String plato = platoTxt.getText().trim();

            if (plato.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingresá el nombre del plato.",
                        "Campo incompleto", JOptionPane.WARNING_MESSAGE);
                return;
            }

            arrComidas.add(plato);
            listaPlatos.setText(
                    "<html><body style='width: 450px'>Platos agregados: "
                    + String.join(", ", arrComidas) + "</body></html>");
            platoTxt.setText("");
        });

        platosPanel.add(platosLabel);
        platosPanel.add(platoTxt);
        platosPanel.add(agregarPlato);

        JPanel horarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        horarioPanel.setOpaque(false);
        JLabel horarioLabel = new JLabel("Horario:");
        JLabel horaLabel = new JLabel("Hora:");
        JLabel minutoLabel = new JLabel("Minuto:");

        EstiloVentanas.estilizarEtiqueta(horarioLabel);
        EstiloVentanas.estilizarEtiqueta(horaLabel);
        EstiloVentanas.estilizarEtiqueta(minutoLabel);

        JSpinner spinHr = new JSpinner(
                new SpinnerNumberModel(0, 0, 23, 1));
        JSpinner spinMin = new JSpinner(
                new SpinnerNumberModel(0, 0, 59, 1));

        horarioPanel.add(horarioLabel);
        horarioPanel.add(horaLabel);
        horarioPanel.add(spinHr);
        horarioPanel.add(minutoLabel);
        horarioPanel.add(spinMin);

        formulario.add(nombrePanel);
        formulario.add(diasPanel);
        formulario.add(platosPanel);
        formulario.add(horarioPanel);

        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setOpaque(false);
        panelLista.add(listaPlatos, BorderLayout.NORTH);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        botones.setBackground(EstiloVentanas.FONDO);

        JButton aceptar = EstiloVentanas.crearBoton(
                "Guardar menú", EstiloVentanas.VERDE);
        JButton cancelar = EstiloVentanas.crearBoton(
                "Cancelar", EstiloVentanas.BOTON);

        botones.add(aceptar);
        botones.add(cancelar);

        principal.add(formulario, BorderLayout.CENTER);
        principal.add(panelLista, BorderLayout.SOUTH);

        add(principal, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        aceptar.addActionListener(e -> {
            String nombre = nombreTxt.getText().trim();
            String platos = String.join(", ", arrComidas);

            StringBuilder diasSeleccionados = new StringBuilder();

            for (int i = 0; i < dias.length; i++) {
                if (dias[i]) {
                    if (diasSeleccionados.length() > 0) {
                        diasSeleccionados.append(", ");
                    }
                    diasSeleccionados.append(nombresDias[i]);
                }
            }

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingresá el nombre del menú.",
                        "Campo incompleto", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (diasSeleccionados.length() == 0) {
                JOptionPane.showMessageDialog(this,
                        "Seleccioná al menos un día.",
                        "Faltan días", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (arrComidas.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Agregá al menos un plato al menú.",
                        "Faltan platos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                mysql1.altaMenu(nombre, diasSeleccionados.toString(),
                        platos, (int) spinHr.getValue(),
                        (int) spinMin.getValue());

                mysql1.listarMenus(t);

                JOptionPane.showMessageDialog(this,
                        "El menú se agregó correctamente.",
                        "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo agregar el menú.\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelar.addActionListener(e -> dispose());
    }

    private void mostrarAvisoModificacionNoDisponible() {
        setLayout(new BorderLayout(10, 10));

        JLabel aviso = new JLabel(
                "La modificación de menús todavía no está implementada.",
                SwingConstants.CENTER);

        EstiloVentanas.estilizarEtiqueta(aviso);

        JButton cerrar = EstiloVentanas.crearBoton(
                "Cerrar", EstiloVentanas.BOTON);

        cerrar.addActionListener(e -> dispose());

        add(aviso, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(EstiloVentanas.FONDO);
        panelBoton.add(cerrar);
        add(panelBoton, BorderLayout.SOUTH);
    }
}
