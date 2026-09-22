package Proyecto;

import javax.swing.*;
import java.awt.*; 
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Proveedores extends JFrame {

    //⬩➤ Comp importantes
     JTable tablaProveedores;
     DefaultTableModel modeloTabla;
     JButton btnAdminProveedores;

    public Proveedores() {
        //⬩➤ Confi de la ventana principal
        setTitle("Sistema de Gestión - Proveedores");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //⬩➤ Creación de la Tabla 
        String[] columnas = {"ID", "Nombre", "Teléfono"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        //⬩➤ Datos de prueba
        modeloTabla.addRow(new Object[]{"1", "Vacas del Este", "54934561233"});

        tablaProveedores = new JTable(modeloTabla);
        tablaProveedores.setRowHeight(30);
        tablaProveedores.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        
        JScrollPane scrollTabla = new JScrollPane(tablaProveedores);
        add(scrollTabla, BorderLayout.CENTER);

        //⬩➤ Panel Inferior con el Botón "+ Administrar proveedores"
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnAdminProveedores = new JButton("+ Administrar proveedores");
        btnAdminProveedores.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnAdminProveedores.setFocusPainted(false);

        panelInferior.add(btnAdminProveedores);
        add(panelInferior, BorderLayout.SOUTH);

        //⬩➤ Para abrir el menú de admin
        btnAdminProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDialogoAdministración();
            }
        });
    }
    
    private void abrirDialogoAdministración() {
        JDialog dialog = new JDialog(this, "Administrar Proveedores", true);
        dialog.setSize(450, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        //⬩➤ Botones "Agregar", "Eliminar", "Modificar"
        JPanel panelMenuLateral = new JPanel(new GridLayout(3, 1, 5, 5));
        panelMenuLateral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnAgregar = crearBotonMenu("➤ Agregar");
        JButton btnEliminar = crearBotonMenu("➤ Eliminar");
        JButton btnModificar = crearBotonMenu("➤ Modificar");

        panelMenuLateral.add(btnAgregar);
        panelMenuLateral.add(btnEliminar);
        panelMenuLateral.add(btnModificar);

        JPanel panelContenido = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) panelContenido.getLayout();

        //⬩➤ Agregar Proveedor:
        JPanel panelAgregar = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtNombreAdd = new JTextField(15);
        JTextField txtTelAdd = new JTextField(15);
        JButton btnAceptarAdd = new JButton("Aceptar");
        JButton btnCancelarAdd = new JButton("Cancelar");

        gbc.gridx = 0; gbc.gridy = 0; panelAgregar.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; panelAgregar.add(txtNombreAdd, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panelAgregar.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1; panelAgregar.add(txtTelAdd, gbc);

        JPanel panelBtnsAdd = new JPanel();
        panelBtnsAdd.add(btnAceptarAdd);
        panelBtnsAdd.add(btnCancelarAdd);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelAgregar.add(panelBtnsAdd, gbc);

        //⬩➤ Eliminar Proveedor:
        
        JPanel panelEliminar = new JPanel(new GridBagLayout());
        JTextField txtIdDel = new JTextField(8);
        JButton btnAceptarDel = new JButton("Aceptar");
        JButton btnCancelarDel = new JButton("Cancelar");

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 0; 
        panelEliminar.add(new JLabel("Ingrese el ID del proveedor para eliminar sus datos: "), gbc);
        gbc.gridx = 1; panelEliminar.add(txtIdDel, gbc);

        JPanel panelBtnsDel = new JPanel();
        panelBtnsDel.add(btnAceptarDel);
        panelBtnsDel.add(btnCancelarDel);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panelEliminar.add(panelBtnsDel, gbc);

        //⬩➤ Modificar Proveedor: Pedir ID

        JPanel panelModPaso1 = new JPanel(new GridBagLayout());
        JTextField txtIdMod = new JTextField(8);
        JButton btnAceptarModPaso1 = new JButton("Aceptar");
        JButton btnCancelarModPaso1 = new JButton("Cancelar");

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 0; 
        panelModPaso1.add(new JLabel("Ingrese el ID del proveedor para modificar sus datos: "), gbc);
        gbc.gridx = 1; panelModPaso1.add(txtIdMod, gbc);

        JPanel panelBtnsMod1 = new JPanel();
        panelBtnsMod1.add(btnAceptarModPaso1);
        panelBtnsMod1.add(btnCancelarModPaso1);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panelModPaso1.add(panelBtnsMod1, gbc);

        //⬩➤ Qué modificar

        JPanel panelModPaso2 = new JPanel(new GridBagLayout());
        JComboBox<String> comboCampoMod = new JComboBox<>(new String[]{"Nombre", "Teléfono"});
        JButton btnAceptarModPaso2 = new JButton("Aceptar");
        JButton btnCancelarModPaso2 = new JButton("Cancelar");

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 0;
        panelModPaso2.add(new JLabel("Ingrese el dato que desea modificar: "), gbc);
        gbc.gridx = 1; panelModPaso2.add(comboCampoMod, gbc);

        JPanel panelBtnsMod2 = new JPanel();
        panelBtnsMod2.add(btnAceptarModPaso2);
        panelBtnsMod2.add(btnCancelarModPaso2);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panelModPaso2.add(panelBtnsMod2, gbc);

        //⬩➤ Ingresar el nuevo dato

        JPanel panelModPaso3 = new JPanel(new GridBagLayout());
        JLabel lblTipoDatoMod = new JLabel("Ingrese el nuevo dato:");
        JTextField txtNuevoValorMod = new JTextField(15);
        JButton btnAceptarModPaso3 = new JButton("Aceptar");
        JButton btnCancelarModPaso3 = new JButton("Cancelar");

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 0;
        panelModPaso3.add(lblTipoDatoMod, gbc);
        gbc.gridx = 1; panelModPaso3.add(txtNuevoValorMod, gbc);

        JPanel panelBtnsMod3 = new JPanel();
        panelBtnsMod3.add(btnAceptarModPaso3);
        panelBtnsMod3.add(btnCancelarModPaso3);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        panelModPaso3.add(panelBtnsMod3, gbc);

        //⬩➤ Agregar todas las pantallas
        panelContenido.add(panelAgregar, "AGREGAR");
        panelContenido.add(panelEliminar, "ELIMINAR");
        panelContenido.add(panelModPaso1, "MOD_PASO1");
        panelContenido.add(panelModPaso2, "MOD_PASO2");
        panelContenido.add(panelModPaso3, "MOD_PASO3");

        dialog.add(panelMenuLateral, BorderLayout.WEST);
        dialog.add(panelContenido, BorderLayout.CENTER);

        //⬩➤ Cambios entre pestañas del menú lateral
        btnAgregar.addActionListener(e -> cardLayout.show(panelContenido, "AGREGAR"));
        btnEliminar.addActionListener(e -> cardLayout.show(panelContenido, "ELIMINAR"));
        btnModificar.addActionListener(e -> cardLayout.show(panelContenido, "MOD_PASO1"));

        //⬩➤ Agregar proveedor
        btnAceptarAdd.addActionListener(e -> {
            String nombre = txtNombreAdd.getText().trim();
            String telefono = txtTelAdd.getText().trim();

            if (!nombre.isEmpty() && !telefono.isEmpty()) {
                int nuevoId = modeloTabla.getRowCount() + 1;
                modeloTabla.addRow(new Object[]{String.valueOf(nuevoId), nombre, telefono});
                JOptionPane.showMessageDialog(dialog, "Proveedor agregado exitosamente.");
                txtNombreAdd.setText("");
                txtTelAdd.setText("");
            } else {
                JOptionPane.showMessageDialog(dialog, "Debe completar ambos campos .", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // ⬩➤ Eliminar proveedor
        btnAceptarDel.addActionListener(e -> {
            String idBuscado = txtIdDel.getText().trim();
            int filaEncontrada = buscarFilaPorId(idBuscado);

            if (filaEncontrada != -1) {
                modeloTabla.removeRow(filaEncontrada);
                JOptionPane.showMessageDialog(dialog, "Proveedor eliminado correctamente.");
                txtIdDel.setText("");
            } else {
                JOptionPane.showMessageDialog(dialog, "No se encontró ningún proveedor con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //⬩➤ Variables temporales para el flujo de modificación
         int[] filaAModificar = {-1};
         String[] campoAModificar = {""};

        // ⬩➤ Modificación: ID
        btnAceptarModPaso1.addActionListener(e -> {
            String idBuscado = txtIdMod.getText().trim();
            int fila = buscarFilaPorId(idBuscado);

            if (fila != -1) {
                filaAModificar[0] = fila;
                cardLayout.show(panelContenido, "MOD_PASO2");
            } else {
                JOptionPane.showMessageDialog(dialog, "El ID ingresado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //⬩➤ Qué modificar
        btnAceptarModPaso2.addActionListener(e -> {
            campoAModificar[0] = (String) comboCampoMod.getSelectedItem();
            lblTipoDatoMod.setText("Ingrese el nuevo " + campoAModificar[0].toLowerCase() + ":");
            txtNuevoValorMod.setText("");
            cardLayout.show(panelContenido, "MOD_PASO3");
        });

        //⬩➤ Guardar el valor nuevo
        btnAceptarModPaso3.addActionListener(e -> {
            String nuevoValor = txtNuevoValorMod.getText().trim();

            if (!nuevoValor.isEmpty()) {
                int columna = campoAModificar[0].equalsIgnoreCase("Nombre") ? 1 : 2;
                modeloTabla.setValueAt(nuevoValor, filaAModificar[0], columna);

                JOptionPane.showMessageDialog(dialog, "Dato actualizado con éxito.");
                txtIdMod.setText("");
                cardLayout.show(panelContenido, "MOD_PASO1");
            } else {
                JOptionPane.showMessageDialog(dialog, "Ingrese un valor válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //⬩➤ Eventos de cancelar
        ActionListener cerrarDialogo = e -> dialog.dispose();
        btnCancelarAdd.addActionListener(cerrarDialogo);
        btnCancelarDel.addActionListener(cerrarDialogo);
        btnCancelarModPaso1.addActionListener(cerrarDialogo);
        btnCancelarModPaso2.addActionListener(cerrarDialogo);
        btnCancelarModPaso3.addActionListener(cerrarDialogo);

        dialog.setVisible(true);
    }

    //⬩➤ Método auxiliar para buscar la fila según el ID
     int buscarFilaPorId(String id) {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (modeloTabla.getValueAt(i, 0).toString().equals(id)) {
                return i;
            }
        }
        return -1;
    }
 
     JButton crearBotonMenu(String texto) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Proveedores().setVisible(true);
        });
    }
}