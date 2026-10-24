
package Proyecto;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EstiloVentanas {

    public static final Color FONDO = new Color(244, 235, 220);
    public static final Color PANEL = new Color(185, 154, 115);
    public static final Color BOTON = new Color(217, 185, 143);
    public static final Color MARRON = new Color(155, 121, 83);
    public static final Color TEXTO = new Color(51, 42, 33);
    public static final Color VERDE = new Color(90, 145, 90);
    public static final Color ROJO = new Color(190, 80, 75);

    public static void configurarVentana(JFrame ventana, String titulo,
                                         int ancho, int alto) {
        ventana.setTitle(titulo);
        ventana.setSize(ancho, alto);
        ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.getContentPane().setBackground(FONDO);
    }

    public static JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(PANEL);
        return panel;
    }

    public static JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(TEXTO);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        return boton;
    }

    public static void estilizarEtiqueta(JLabel etiqueta) {
        etiqueta.setForeground(TEXTO);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 13));
    }

    public static void estilizarCampo(JTextField campo) {
        campo.setBackground(new Color(246, 241, 234));
        campo.setForeground(TEXTO);
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
    }
}
