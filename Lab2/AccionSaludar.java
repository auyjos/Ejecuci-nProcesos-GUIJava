package com.ejemploui.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AccionSaludar implements ActionListener{
	
	private JTextField NombrePersona;
	
	public AccionSaludar(JTextField nombre) {
		NombrePersona = nombre;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JOptionPane.showMessageDialog(null, "¡Hola " + NombrePersona.getText() + " Bienvenido!", "Saludar", JOptionPane.INFORMATION_MESSAGE);
		
	}

}
