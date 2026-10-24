
package Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {

    // Colores de tu VentanaPrincipal
    Color panSupCol = new Color(185, 154, 115);
    Color panInfCol = new Color(155, 121, 83);
    Color bot1Col = new Color(217, 185, 143);
    Color txtCol = new Color(51, 42, 33);
    Color fondoCol = new Color(244, 235, 220);

    // ACA PONES EL USUARIO Y LA CONTRASEÑA PERMITIDOS
    private final String usuarioPermitido = "admin";
    private final String contrasenaPermitida = "1234";

    public VentanaLogin() {

        setTitle("Gestor del comedor - Iniciar sesión");
        setSize(600, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(fondoCol);

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(panSupCol);
        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel titulo = new JLabel("Gestor del comedor");
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setForeground(txtCol);

        panelSuperior.add(titulo);

        // Panel central: usuario y contraseña
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(fondoCol);
        panelCentral.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setForeground(txtCol);

        JTextField usuarioTxt = new JTextField(20);
        JPasswordField contrasenaTxt = new JPasswordField(20);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        contrasenaLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Fila 1: usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentral.add(usuarioLabel, gbc);

        gbc.gridx = 1;
        panelCentral.add(usuarioTxt, gbc);

        // Fila 2: contraseña
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCentral.add(contrasenaLabel, gbc);

        gbc.gridx = 1;
        panelCentral.add(contrasenaTxt, gbc);

        // Panel inferior: botón
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(panInfCol);

        JButton ingresarBut = new JButton("Ingresar");
        ingresarBut.setFont(new Font("Arial", Font.BOLD, 16));
        ingresarBut.setPreferredSize(new Dimension(150, 40));
        ingresarBut.setBackground(bot1Col);
        ingresarBut.setForeground(txtCol);
        ingresarBut.setFocusPainted(false);

        panelInferior.add(ingresarBut);

        // Agregar los paneles a la ventana
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Acción del botón Ingresar
        ingresarBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String usuario = usuarioTxt.getText();
                String contrasena =
                        new String(contrasenaTxt.getPassword());

                if (usuario.equals(usuarioPermitido)
                        && contrasena.equals(contrasenaPermitida)) {

                    JOptionPane.showMessageDialog(
                            VentanaLogin.this,
                            "Inicio de sesión correcto."
                    );

                    // Abrir la ventana principal
                    VentanaPrincipal ventana = new VentanaPrincipal();
                    ventana.setVisible(true);

                    // Cerrar la ventana de login
                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            VentanaLogin.this,
                            "Usuario o contraseña incorrectos.",
                            "Error de inicio de sesión",
                            JOptionPane.ERROR_MESSAGE
                    );

                    contrasenaTxt.setText("");
                    contrasenaTxt.requestFocus();
                }
            }
        });

        // Permitir ingresar pulsando Enter
        getRootPane().setDefaultButton(ingresarBut);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                VentanaLogin ventana = new VentanaLogin();
                ventana.setVisible(true);
            }
        });
    }
}
