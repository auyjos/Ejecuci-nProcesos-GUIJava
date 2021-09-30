/*/José Auyón 201579
Programación Orientada a Objetos
Septiembre 27, 2021/*/

package com.lab.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JTextPane;
import java.awt.Panel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import java.time.LocalDateTime;
import javax.swing.AbstractListModel;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

public class MainWindow {

	private JFrame frmWindowmain;
	public JList listCola;
	public JList listColaRam;
	public JProgressBar ramDisponible;
	public JLabel ramTextDisponible;
	public JLabel hora;
	private JRadioButton ramCuatro;
	private JRadioButton ramTreintaDos;
	private JRadioButton ramSesentaCuatro;
	private JRadioButton ramOcho;
	private JRadioButton ramDieciseis;
	
	public int ramSize = 16;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					MainWindow window = new MainWindow();
					window.frmWindowmain.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWindowmain = new JFrame();
		frmWindowmain.setTitle("Utilizaci\u00F3n de Recursos");
		frmWindowmain.setBounds(100, 100, 770, 837);
		frmWindowmain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWindowmain.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
			
		
		//Se crea un Jpanel para todos los componentes gráficos
		JPanel panel = new JPanel();
		frmWindowmain.getContentPane().add(panel);
		panel.setLayout(null);

		
		
		
		// Se crean los componentes gráficos para que vayan dentro del Jpanel
		this.listCola = new JList();
		listCola.setBounds(28, 12, 293, 262);
		panel.add(this.listCola);

		this.listColaRam = new JList();
		this.listColaRam.setBounds(385, 11, 344, 263);
		panel.add(this.listColaRam);
		
		this.ramDisponible = new JProgressBar();
		this.ramDisponible.setStringPainted(true);
		this.ramDisponible.setBounds(10, 316, 719, 72);
		panel.add(this.ramDisponible);
		
		this.ramTextDisponible = new JLabel("");
		this.ramTextDisponible.setBounds(583, 298, 146, 14);
		panel.add(this.ramTextDisponible);
		
		this.hora = new JLabel("New label");
		this.hora.setBounds(10, 285, 119, 28);
		panel.add(this.hora);
		
		
	    ActionListener sliceActionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	          AbstractButton aButton = (AbstractButton) actionEvent.getSource();
	          System.out.println("Selected: " + aButton.getText());
	          ramSize =  Integer.parseInt(aButton.getText().split(" ")[0]);
	        }
	      };
		
	      
	      //Se crean botones para poder asignarles acciones
		ramOcho = new JRadioButton("8 GB");
		ramOcho.setBounds(63, 504, 109, 23);
		ramOcho.addActionListener(sliceActionListener);
		panel.add(ramOcho);
		
		ramDieciseis = new JRadioButton("16 GB ");
		ramDieciseis.setBounds(63, 540, 109, 23);
		ramDieciseis.addActionListener(sliceActionListener);
		panel.add(ramDieciseis);
		
		ramCuatro = new JRadioButton("4 GB");
		ramCuatro.setBounds(63, 454, 109, 23);
		ramCuatro.addActionListener(sliceActionListener);
		panel.add(ramCuatro);
		
		ramTreintaDos = new JRadioButton("32 GB");
		ramTreintaDos.setBounds(63, 574, 109, 23);
		ramTreintaDos.addActionListener(sliceActionListener);
		panel.add(ramTreintaDos);
		
		ramSesentaCuatro = new JRadioButton("64 GB");
		ramSesentaCuatro.setBounds(63, 621, 109, 23);
		ramSesentaCuatro.addActionListener(sliceActionListener);
		panel.add(ramSesentaCuatro);
		
		//Se hace una agrupación de los botones para que no puedan ser todos presionados al mismo tiempo y asignarle a la agrupación una acción
		ButtonGroup btnGrp = new ButtonGroup();
		btnGrp.add(ramOcho);
		btnGrp.add(ramDieciseis);
		btnGrp.add(ramCuatro);
		btnGrp.add(ramTreintaDos);
		btnGrp.add(ramSesentaCuatro);		
		
		JButton btnSimulacion = new JButton("Empezar Simulacion");
		
		MainWindow self = this;
		btnSimulacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ramOcho.setEnabled(false);
				ramDieciseis.setEnabled(false);
				ramCuatro.setEnabled(false);
				ramTreintaDos.setEnabled(false);
				ramSesentaCuatro.setEnabled(false);
				
				btnSimulacion.setEnabled(false);
				
				
				SimulacionThread simulacionThread = new SimulacionThread(self);
				simulacionThread.start();
			}
		});
		btnSimulacion.setBounds(341, 751, 187, 23);
		panel.add(btnSimulacion);
		
	}
	}

