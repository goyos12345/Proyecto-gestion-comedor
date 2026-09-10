package Proyecto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdministrarComidas extends JFrame {
	public AdministrarComidas(int op) {
		this.setTitle("Gestor del comedor");
		this.setSize(600, 400);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new FlowLayout());

		// Elementos generales
		JPanel granPanNor = new JPanel();
		JPanel superGranPanNor = new JPanel();
		JPanel panOp = new JPanel();
		JButton yesBut = new JButton("Aceptar");
		JButton noBut = new JButton("Cancelar");

		this.add(superGranPanNor);

		superGranPanNor.add(granPanNor);

		// Esto es por si decide agregar un grupo
		if (op == 1) {
			this.setSize(900, 400);
			superGranPanNor.setLayout(new GridLayout(1, 2));
			this.setTitle("Agregar comida");
			// Jpanels
			// flw=flow
			JPanel flwPan1 = new JPanel();
			JPanel flwPan2 = new JPanel();
			JPanel flwPan3 = new JPanel();

			// un granPanel masz
			JPanel granPanEst = new JPanel();

			// JLabels
			JLabel nomLab = new JLabel("Nombre: ");
			JLabel ingLab = new JLabel("Ingredientes: ");
			JLabel conLab = new JLabel("Contenedor");
			JLabel almIngLab = new JLabel("Acá aparecerán los ingredientes agregados: \n");

			// JTextField
			JTextField nomTxt = new JTextField(14);
			JTextField ingTxt = new JTextField(14);
			JTextField conTxt = new JTextField(14);

			// Jbutons
			JButton ingBut = new JButton("Almacenar Ingrediente");

			// Array
			String[] arrIngs = new String[100];
			int EspacioArr = 0;

			// Agregar elementos
			granPanNor.setLayout(new GridLayout(3, 1));
			granPanNor.add(flwPan1);
			flwPan1.add(nomLab);
			flwPan1.add(nomTxt);
			flwPan1.setLayout(new FlowLayout(FlowLayout.LEFT));
			granPanNor.add(flwPan2);
			flwPan2.add(ingLab);
			flwPan2.add(ingTxt);
			flwPan2.add(ingBut);
			flwPan2.setLayout(new FlowLayout(FlowLayout.LEFT));
			granPanNor.add(flwPan3);
			flwPan3.add(conLab);
			flwPan3.add(conTxt);
			flwPan3.setLayout(new FlowLayout(FlowLayout.LEFT));
			superGranPanNor.add(granPanEst);
			granPanEst.add(almIngLab);

			ingBut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					arrIngs[EspacioArr] = ingTxt.getText();
					almIngLab.setText(almIngLab.getText() + arrIngs[EspacioArr] + ", \n");
					AumentarValor(EspacioArr);

				}

			});

		} else if (op == 2) {
			this.setTitle("Eliminar grupo de comidas");

			JLabel ElIdLab = new JLabel("Ingrese el ID del grupo de ingredientes para poder eliminarlo");
			JTextField ElIdTxt = new JTextField(7);
			granPanNor.setLayout(new FlowLayout());
			granPanNor.add(ElIdLab);
			granPanNor.add(ElIdTxt);

		} else if (op == 3) {
			this.setTitle("Modificar comidas");
			JLabel modIdLab = new JLabel("Ingrese el ID de la comida quiere modificar");
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

				if (op != 3) {
					setVisible(false);
				} else {
					granPanNor.removeAll();

					JLabel selLab = new JLabel("Seleccione el atributo que quiere modificar");
					String[] selArr = { "", "Nombre", "Ingredientes", "Preparado", "Frizado", "Contenedor" };
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
			this.setSize(400, 400);
			p.removeAll();
			JPanel elimiPan=new JPanel();
			p.setLayout(new GridLayout(2,2));
			JButton elimiBut=new JButton("Eliminar todos los ingredientes");
			JButton ingBut = new JButton("Almacenar Ingrediente");
			JLabel marLab = new JLabel("Ingrese el ingrediente deseado");
			JTextField marTxt = new JTextField(15);
			elimiPan.add(elimiBut);
			p.add(marLab);
			p.add(marTxt);
			p.add(ingBut);
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
		}else if (i==3) {
				p.removeAll();
				JLabel nomLab = new JLabel("Ingrese si está preparado");
				JCheckBox prepCB=new JCheckBox();
				p.add(nomLab);
				p.add(prepCB);
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
		} else if (i==4) {
			p.removeAll();
			JLabel nomLab = new JLabel("Ingrese si está frizado");
			JCheckBox prepCB=new JCheckBox();
			p.add(nomLab);
			p.add(prepCB);
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
		}else if (i==5) {
			p.removeAll();
			JLabel nomLab = new JLabel("Ingrese el contenedor deseado");
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
			
		}
		p.revalidate();

	}

	public void AumentarValor(int i) {
		i++;
	}

}
