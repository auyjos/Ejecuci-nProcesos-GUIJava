package com.ejemploui.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import com.ejemploui.controller.RelojHilo;

import javax.swing.JLabel;
import java.awt.Font;

public class MainWindow {

	private JFrame frmEjemploWindowsApp;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmEjemploWindowsApp.setVisible(true);
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
		frmEjemploWindowsApp = new JFrame();
		frmEjemploWindowsApp.setTitle("Ejemplo Windows App");
		frmEjemploWindowsApp.setBounds(100, 100, 700, 400);
		frmEjemploWindowsApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEjemploWindowsApp.getContentPane().setLayout(null);
		
						
		txtNombre = new JTextField();
		txtNombre.setBounds(140, 60, 146, 20);
		frmEjemploWindowsApp.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		AccionSaludar miAccion = new AccionSaludar(txtNombre);
		JButton btnSaludar2 = new JButton("Saludar 2");
		btnSaludar2.addActionListener(miAccion);
		btnSaludar2.setBounds(300, 59, 89, 23);
		frmEjemploWindowsApp.getContentPane().add(btnSaludar2);
		
		JLabel lblFileName = new JLabel("---");
		lblFileName.setBounds(250, 115, 398, 14);
		frmEjemploWindowsApp.getContentPane().add(lblFileName);
		
		JButton btnSaludar = new JButton("Saludar");
		btnSaludar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser ofd = new JFileChooser();
				int result = ofd.showDialog(frmEjemploWindowsApp, "Seleccionar archivo");
				if (result == JFileChooser.APPROVE_OPTION) {
					lblFileName.setText(ofd.getSelectedFile().getAbsolutePath());
				}
			}
		});
		
		
		btnSaludar.setBounds(140, 111, 89, 23);
		frmEjemploWindowsApp.getContentPane().add(btnSaludar);
		
		JLabel lblReloj = new JLabel("0:0:0");
		lblReloj.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblReloj.setBounds(570, 44, 78, 14);
		frmEjemploWindowsApp.getContentPane().add(lblReloj);
		
		RelojHilo miReloj = new RelojHilo(lblReloj);
		
		JButton btnIniciarReloj = new JButton("Iniciar Reloj");
		btnIniciarReloj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miReloj.start();
			}
		});
		
		btnIniciarReloj.setBounds(399, 43, 119, 23);
		frmEjemploWindowsApp.getContentPane().add(btnIniciarReloj);

	}
}
