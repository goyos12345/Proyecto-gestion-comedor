package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AgregarMenus extends JFrame {
	// Estas variables sirven para saber si los botones de los días han sido
	// presionados
	boolean lun = false;
	boolean mar = false;
	boolean mie = false;
	boolean jue = false;
	boolean vie = false;
	boolean sab = false;
	boolean dom = false;
	ArrayList<String> arrComidas = new ArrayList<>();
	boolean siBorrar2 = false;

	MySQL mysql1 = new MySQL();

	public AgregarMenus(int op, DefaultTableModel t, VentanaPrincipal vp) {
		this.setTitle("Gestor del comedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// Elementos generales
		JPanel granPanNor = new JPanel();
		JPanel comidasPan = new JPanel();
		JPanel superGranPanNor = new JPanel();
		JPanel panOp = new JPanel();
		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");

		this.add(superGranPanNor);

		superGranPanNor.add(granPanNor);
		superGranPanNor.add(comidasPan);

		// Esto es por si decide agregar un grupo
		if (op == 1) {
			this.setSize(900, 400);

			JPanel flwPan1 = new JPanel();
			JPanel flwPan2 = new JPanel();
			JPanel flwPan3 = new JPanel();
			JPanel flwPan5 = new JPanel();

			// JLabels
			JLabel nomLab = new JLabel("Nombre: ");
			JLabel platosLab = new JLabel("Platos: ");
			JLabel dias = new JLabel("Días: ");
			JLabel caaLab = new JLabel("Horario: ");
			JLabel marLab = new JLabel("Hora: ");
			JLabel tiGluLab = new JLabel("Minuto: ");
			JLabel addComLab = new JLabel("Platos agregados: ");

			// JTextField
			JTextField nomTxt = new JTextField(14);
			JTextField platostxt = new JTextField(14);

			// JSpinners para la fecha
			SpinnerNumberModel modHr = new SpinnerNumberModel(0, 0, 23, 1);
			SpinnerNumberModel modMin = new SpinnerNumberModel(0, 0, 59, 1);
			JSpinner spinHr = new JSpinner(modHr);
			JSpinner spinMin = new JSpinner(modMin);

			// JButtons
			JButton lunes = new JButton("Lunes");
			JButton martes = new JButton("Martes");
			JButton mierco = new JButton("Miércoles");
			JButton jueves = new JButton("Jueves");
			JButton vierne = new JButton("Viernes");
			JButton sabado = new JButton("Sábado");
			JButton doming = new JButton("Domingo");
			JButton platoBut = new JButton("Agregar plato");

			// Colores para los JButtons
			EseColor(lunes);
			EseColor(martes);
			EseColor(mierco);
			EseColor(jueves);
			EseColor(vierne);
			EseColor(sabado);
			EseColor(doming);
			// Funciones para los JButtons
			lunes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (lun == false) {
						lun = true;
						OtroColor(lunes);
					} else {
						lun = false;
						EseColor(lunes);
					}

				}
			});
			martes.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (mar == false) {
						mar = true;
						OtroColor(martes);
					} else {
						mar = false;
						EseColor(martes);
					}

				}
			});
			mierco.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (mie == false) {
						mie = true;
						OtroColor(mierco);
					} else {
						mie = false;
						EseColor(mierco);
					}

				}
			});
			jueves.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (jue == false) {
						jue = true;
						OtroColor(jueves);
					} else {
						jue = false;
						EseColor(jueves);
					}

				}
			});
			vierne.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (vie == false) {
						vie = true;
						OtroColor(vierne);
					} else {
						vie = false;
						EseColor(vierne);
					}

				}
			});
			sabado.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (sab == false) {
						sab = true;
						OtroColor(sabado);
					} else {
						sab = false;
						EseColor(sabado);
					}

				}
			});
			doming.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (dom == false) {
						dom = true;
						OtroColor(doming);
					} else {
						dom = false;
						EseColor(doming);
					}

				}
			});
			// El JButton de abajo es de las comidas
			platoBut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					String plato = platostxt.getText().trim();
					if (plato.isEmpty()) {
						JOptionPane.showMessageDialog(AgregarMenus.this, "Ingrese el nombre del plato");
						return;
					}
					arrComidas.add(plato);
					addComLab.setText("Platos agregados: " + String.join(", ", arrComidas));
					platostxt.setText("");
				}
			});

			// Agregar elementos
			granPanNor.setLayout(new GridLayout(4, 1));

			granPanNor.add(flwPan1);
			flwPan1.setLayout(new FlowLayout(FlowLayout.LEFT));
			flwPan1.add(nomLab);
			flwPan1.add(nomTxt);

			granPanNor.add(flwPan5);
			flwPan5.setLayout(new FlowLayout(FlowLayout.LEFT));
			flwPan5.add(dias);
			flwPan5.add(lunes);
			flwPan5.add(martes);
			flwPan5.add(mierco);
			flwPan5.add(jueves);
			flwPan5.add(vierne);
			flwPan5.add(sabado);
			flwPan5.add(doming);

			granPanNor.add(flwPan2);
			flwPan2.setLayout(new FlowLayout(FlowLayout.LEFT));
			flwPan2.add(platosLab);
			flwPan2.add(platostxt);
			flwPan2.add(platoBut);

			granPanNor.add(flwPan3);
			flwPan3.setLayout(new FlowLayout(FlowLayout.LEFT));
			flwPan3.add(caaLab);
			flwPan3.add(marLab);
			flwPan3.add(spinHr);
			flwPan3.add(tiGluLab);
			flwPan3.add(spinMin);

			comidasPan.add(addComLab);
			comidasPan.setPreferredSize(new Dimension(250, 125));

			yesBut.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					String diasSeleccionados = "";
					if (lun)
						diasSeleccionados += "Lunes, ";
					if (mar)
						diasSeleccionados += "Martes, ";
					if (mie)
						diasSeleccionados += "Miércoles, ";
					if (jue)
						diasSeleccionados += "Jueves, ";
					if (vie)
						diasSeleccionados += "Viernes, ";
					if (sab)
						diasSeleccionados += "Sábado, ";
					if (dom)
						diasSeleccionados += "Domingo, ";

					if (!diasSeleccionados.isEmpty()) {
						diasSeleccionados = diasSeleccionados.substring(0, diasSeleccionados.length() - 2);
					}

					String nombre = nomTxt.getText();
					String platos = String.join(", ", arrComidas);

					int hora = (int) spinHr.getValue();
					int minuto = (int) spinMin.getValue();

					mysql1.altaMenu(nombre, diasSeleccionados, platos, hora, minuto);

					// Actualizar la tabla
					mysql1.listarMenus(t);

					setVisible(false);
				}
			});

		} else if (op == 3) {
			this.setTitle("Modificar comidas");
			JLabel modIdLab = new JLabel("Ingrese el ID del menú quiere modificar");
			JTextField modIdTxt = new JTextField(7);
			granPanNor.setLayout(new FlowLayout());
			granPanNor.add(modIdLab);
			granPanNor.add(modIdTxt);

		}

		this.add(panOp, FlowLayout.CENTER);
		panOp.add(yesBut);
		panOp.add(noBut);
		panOp.setLayout(new GridLayout(2, 1));

		// Funciones de los botones de aceptar y cancelar

		yesBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});

		noBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

	}

	public void FunQueModificar(JPanel p, JPanel c, int i) {
		if (i == 0) {
			JLabel errorLab = new JLabel(
					"                     		Seleccione una opcion correcta		                    ");
			errorLab.setForeground(Color.red);
			p.add(errorLab);

		} else if (i == 1) {

			p.removeAll();
			JLabel nomLab = new JLabel("Ingrese el nombre deseado");
			JTextField nomTxt = new JTextField(15);
			p.add(nomLab);
			p.add(nomTxt);
			p.repaint();
			JButton yesBut = new JButton("Aceptar");
			JButton noBut = new JButton("Cancelar");
			c.removeAll();
			c.add(yesBut);
			c.add(noBut);
			yesBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});

			noBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);

				}
			});
			c.repaint();
			c.revalidate();

		} else if (i == 2) {

			this.setSize(400, 400);
			p.removeAll();

			JPanel hrPan1 = new JPanel();
			JPanel hrPan2 = new JPanel();

			SpinnerNumberModel modHr = new SpinnerNumberModel(1, 1, 24, 1);
			SpinnerNumberModel modMin = new SpinnerNumberModel(1, 1, 60, 1);

			JSpinner spinDia = new JSpinner(modHr);
			JSpinner spinMes = new JSpinner(modMin);
			JLabel caaLab = new JLabel("Ingrese el horario deseado");
			JLabel dias = new JLabel("Días: ");
			JLabel platosLab = new JLabel("Platos: ");
			JLabel marLab = new JLabel("Hora: ");
			JLabel tiGluLab = new JLabel("Minuto: ");

			p.setLayout(new GridLayout(3, 1));
			p.add(caaLab);
			p.add(hrPan1);
			hrPan1.add(marLab);
			hrPan1.add(spinDia);

			p.add(hrPan2);
			hrPan2.add(tiGluLab);
			hrPan2.add(spinMes);

			JButton yesBut = new JButton("Aceptar");
			JButton noBut = new JButton("Cancelar");

			c.removeAll();
			c.add(yesBut);
			c.add(noBut);

			yesBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});

			noBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);

				}
			});

			c.repaint();
			c.revalidate();
		}

		p.revalidate();

	}

	public void EseColor(JButton b) {
		b.setBackground(new Color(171, 107, 64));
	}

	public void OtroColor(JButton b) {
		b.setBackground(new Color(122, 76, 45));
	}

}
