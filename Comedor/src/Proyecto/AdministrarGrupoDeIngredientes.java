package Proyecto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdministrarGrupoDeIngredientes extends JFrame {
	public AdministrarGrupoDeIngredientes(int op, ArrayList<ClasificaIngredientes> arrClase, int ID, JPanel p,
			DefaultTableModel t, VentanaPrincipal v, SelectorABMLGrupoIngredientes sv) {
		this.setTitle("Gestor del comedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// Elementos generales
		JPanel granPanNor = new JPanel();
		JPanel panOp = new JPanel();
		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");

		this.add(granPanNor);
		granPanNor.setPreferredSize(new Dimension(600, 100));

		// Esto es por si decide agregar un grupo
		if (op == 1) {
			this.setTitle("Agregar grupo de ingredientes");
			// Jpanels
			// flw=flow
			JPanel flwPan1 = new JPanel();
			JPanel flwPan2 = new JPanel();

			// JLabels
			JLabel nomLab = new JLabel("Nombre: ");
			JLabel marLab = new JLabel("Marca: ");
			// JTextField
			JTextField nomTxt = new JTextField(14);
			JTextField marTxt = new JTextField(14);

			// Agregar elementos
			granPanNor.setLayout(new GridLayout(2, 1));
			granPanNor.add(flwPan1);
			flwPan1.add(nomLab);
			flwPan1.add(nomTxt);
			flwPan1.setLayout(new FlowLayout());
			granPanNor.add(flwPan2);
			flwPan2.add(marLab);
			flwPan2.add(marTxt);
			flwPan2.setLayout(new FlowLayout());

			// Action listener para agregar los botones
			yesBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int id = 0;
					boolean repetido;

					do {
						repetido = false;

						for (ClasificaIngredientes clase : arrClase) {
							if (clase.getID() == id) {
								repetido = true;
								id++;
								break;
							}
						}

					} while (repetido);

					ArrayList<Ingredientes> TemporalIng = new ArrayList<>();
					Ingredientes ing = new Ingredientes(null, -1, null, 0, 0, 0);
					TemporalIng.add(ing);

					ClasificaIngredientes objClase = new ClasificaIngredientes(nomTxt.getText(), id, marTxt.getText(),
							TemporalIng);
					arrClase.add(objClase);
					setVisible(false);
					sv.setVisible(false);

					v.muestraBotonesIngredientes(p, t);

				}
			});

		} else if (op == 2) {
			this.setTitle("Eliminar grupo de ingredientes");

			JLabel ElIdLab = new JLabel("Se eliminará el grupo de ingredientes actual con el ID: " + ID);
			JLabel ConfLab = new JLabel("Afirmo que si quiero eliminar este grupo de ingredientes");
			JCheckBox EliCBox = new JCheckBox();
			granPanNor.setLayout(new FlowLayout());
			granPanNor.add(ElIdLab);
			granPanNor.add(ConfLab);
			granPanNor.add(EliCBox);

			yesBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					boolean opEliminar = EliCBox.isSelected();
					if (opEliminar == false) {
						JOptionPane.showMessageDialog(null, "Indique si quiere eliminar el Grupo de Ingredientes");
					} else {
						if (ID == -1) {
							JOptionPane.showMessageDialog(null,
									"Seleccione un Grupo de Ingredientes válido para Eliminar");
						} else {
							arrClase.remove(ID);
						}
						setVisible(false);
						sv.setVisible(false);
						v.muestraBotonesIngredientes(p, t);

					}

				}
			});

		} else if (op == 3) {
			this.setTitle("Modificar grupo de ingredientes");
			JLabel modIdLab = new JLabel("Ingrese el ID del grupo que quiere modificar");
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

				if (op == 3) {
					granPanNor.removeAll();

					JLabel selLab = new JLabel("Seleccione el atributo que quiere modificar");
					String[] selArr = { "", "Nombre", "Marca" };
					JComboBox selCBox = new JComboBox(selArr);
					granPanNor.add(selLab);
					granPanNor.add(selCBox);

					// Lo elimino así puedo ejecutar la función para saber qué elegir
					panOp.remove(yesBut);
					panOp.remove(noBut);
					JButton queYesBut = new JButton("Aceptar");

					// Lo de abajo es lo de agregar y refrescar
					panOp.add(queYesBut);
					panOp.add(noBut);
					panOp.repaint();
					panOp.revalidate();
					granPanNor.repaint();
					granPanNor.revalidate();

					queYesBut.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int index = selCBox.getSelectedIndex();
							System.out.println(index);
							FunQueModificar(granPanNor, panOp, index);

						}
					});
					// Esta parte sirve para conseguir que quiere modificar el usuario

				}

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
			p.removeAll();
			JLabel marLab = new JLabel("Ingrese la marca deseada");
			JTextField marTxt = new JTextField(15);
			p.add(marLab);
			p.add(marTxt);
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
		}
		p.revalidate();

	}

}
