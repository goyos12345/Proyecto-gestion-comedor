
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

    // Exactamente los colores de VentanaPrincipal
    private final Color altaCol = new Color(155, 190, 145);
    private final Color bajaCol = new Color(205, 135, 125);

    public AgregarMenus(int op, DefaultTableModel t, VentanaPrincipal vp) {

        EstiloVentanas.configurarVentana(
                this,
                op == 1 ? "Agregar menú" : "Modificar menú",
                850, 570);

        setResizable(false);

        if (op != 1) {
            mostrarAvisoModificacionNoDisponible();
            return;
        }

        JPanel principal = EstiloVentanas.crearPanel();
        principal.setLayout(new BorderLayout(15, 15));
        principal.setBorder(
                BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel formulario = new JPanel(new GridLayout(4, 1, 10, 15));
        formulario.setOpaque(false);

        // Nombre del menú
        JPanel nombrePanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 5));
        nombrePanel.setOpaque(false);

        JLabel nombreLabel = new JLabel("Nombre del menú:");
        estilizarEtiqueta(nombreLabel);

        JTextField nombreTxt = new JTextField(20);
        EstiloVentanas.estilizarCampo(nombreTxt);
        nombreTxt.setFont(new Font("Arial", Font.PLAIN, 17));
        nombreTxt.setPreferredSize(new Dimension(260, 35));

        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombreTxt);

        // Días del menú
        JPanel diasPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 8, 5));
        diasPanel.setOpaque(false);

        JLabel diasLabel = new JLabel("Días:");
        estilizarEtiqueta(diasLabel);
        diasPanel.add(diasLabel);

        JToggleButton[] botonesDias = new JToggleButton[7];

        for (int i = 0; i < nombresDias.length; i++) {
            final int indice = i;

            botonesDias[i] = new JToggleButton(nombresDias[i]);
            botonesDias[i].setFont(new Font("Arial", Font.BOLD, 14));
            botonesDias[i].setPreferredSize(new Dimension(95, 38));
            botonesDias[i].setBackground(EstiloVentanas.BOTON);
            botonesDias[i].setForeground(EstiloVentanas.TEXTO);
            botonesDias[i].setFocusPainted(false);

            botonesDias[i].addActionListener(e -> {
                dias[indice] = botonesDias[indice].isSelected();

                botonesDias[indice].setBackground(
                        dias[indice]
                                ? EstiloVentanas.MARRON
                                : EstiloVentanas.BOTON);
            });

            diasPanel.add(botonesDias[i]);
        }

        // Agregar platos
        JPanel platosPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 5));
        platosPanel.setOpaque(false);

        JLabel platosLabel = new JLabel("Plato:");
        estilizarEtiqueta(platosLabel);

        JTextField platoTxt = new JTextField(18);
        EstiloVentanas.estilizarCampo(platoTxt);
        platoTxt.setFont(new Font("Arial", Font.PLAIN, 17));
        platoTxt.setPreferredSize(new Dimension(230, 35));

        JButton agregarPlato = EstiloVentanas.crearBoton(
                "Agregar plato", EstiloVentanas.BOTON);
        estilizarBoton(agregarPlato);

        JLabel listaPlatos = new JLabel("Platos agregados: ninguno");
        listaPlatos.setFont(new Font("Arial", Font.BOLD, 16));
        listaPlatos.setForeground(EstiloVentanas.TEXTO);

        agregarPlato.addActionListener(e -> {
            String plato = platoTxt.getText().trim();

            if (plato.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ingresá el nombre del plato.",
                        "Campo incompleto",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            arrComidas.add(plato);

            listaPlatos.setText(
                    "<html><body style='width: 600px'>"
                    + "Platos agregados: "
                    + String.join(", ", arrComidas)
                    + "</body></html>");

            platoTxt.setText("");
        });

        platosPanel.add(platosLabel);
        platosPanel.add(platoTxt);
        platosPanel.add(agregarPlato);

        // Horario
        JPanel horarioPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 12, 5));
        horarioPanel.setOpaque(false);

        JLabel horarioLabel = new JLabel("Horario:");
        JLabel horaLabel = new JLabel("Hora:");
        JLabel minutoLabel = new JLabel("Minuto:");

        estilizarEtiqueta(horarioLabel);
        estilizarEtiqueta(horaLabel);
        estilizarEtiqueta(minutoLabel);

        JSpinner spinHr = new JSpinner(
                new SpinnerNumberModel(0, 0, 23, 1));

        JSpinner spinMin = new JSpinner(
                new SpinnerNumberModel(0, 0, 59, 1));

        spinHr.setFont(new Font("Arial", Font.PLAIN, 17));
        spinMin.setFont(new Font("Arial", Font.PLAIN, 17));

        spinHr.setPreferredSize(new Dimension(70, 35));
        spinMin.setPreferredSize(new Dimension(70, 35));

        horarioPanel.add(horarioLabel);
        horarioPanel.add(horaLabel);
        horarioPanel.add(spinHr);
        horarioPanel.add(minutoLabel);
        horarioPanel.add(spinMin);

        formulario.add(nombrePanel);
        formulario.add(diasPanel);
        formulario.add(platosPanel);
        formulario.add(horarioPanel);

        // Lista de platos agregados
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setOpaque(false);
        panelLista.setBorder(
                BorderFactory.createEmptyBorder(10, 5, 10, 5));
        panelLista.add(listaPlatos, BorderLayout.CENTER);

        // Botones inferiores: verde y rojo de VentanaPrincipal
        JPanel botones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 20, 15));
        botones.setBackground(EstiloVentanas.FONDO);

        JButton aceptar = EstiloVentanas.crearBoton(
                "Guardar menú", altaCol);

        JButton cancelar = EstiloVentanas.crearBoton(
                "Cancelar", bajaCol);

        estilizarBoton(aceptar);
        estilizarBoton(cancelar);

        botones.add(aceptar);
        botones.add(cancelar);

        principal.add(formulario, BorderLayout.CENTER);
        principal.add(panelLista, BorderLayout.SOUTH);

        add(principal, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        // Guardar menú
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
                JOptionPane.showMessageDialog(
                        this,
                        "Ingresá el nombre del menú.",
                        "Campo incompleto",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (diasSeleccionados.length() == 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccioná al menos un día.",
                        "Faltan días",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (arrComidas.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Agregá al menos un plato al menú.",
                        "Faltan platos",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                mysql1.altaMenu(
                        nombre,
                        diasSeleccionados.toString(),
                        platos,
                        (int) spinHr.getValue(),
                        (int) spinMin.getValue());

                mysql1.listarMenus(t);

                JOptionPane.showMessageDialog(
                        this,
                        "El menú se agregó correctamente.",
                        "Operación exitosa",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo agregar el menú.\n"
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

    private void estilizarEtiqueta(JLabel etiqueta) {
        EstiloVentanas.estilizarEtiqueta(etiqueta);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void mostrarAvisoModificacionNoDisponible() {
        setLayout(new BorderLayout(10, 10));

        JLabel aviso = new JLabel(
                "La modificación de menús todavía no está implementada.",
                SwingConstants.CENTER);

        estilizarEtiqueta(aviso);

        JButton cerrar = EstiloVentanas.crearBoton(
                "Cerrar", EstiloVentanas.BOTON);
        estilizarBoton(cerrar);

        cerrar.addActionListener(e -> dispose());

        add(aviso, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(EstiloVentanas.FONDO);
        panelBoton.add(cerrar);

        add(panelBoton, BorderLayout.SOUTH);
    }
}
