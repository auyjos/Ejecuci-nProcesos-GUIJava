package com.lab.ui;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;  
import java.lang.*;


// declaraci�n de variables
public class Programa {
	String nombre;
	int espacioOriginal; 
	 int espacioBloquesRam;
	 LocalDateTime empezoEjecucion;
	 int tiempo;
	
	 
	 // m�todo que nos permite establecer variables con los diferentes par�metros y de esta manera poder realizar los par�metros para ram
	public Programa(String nombre, int espacio, int tiempo, Ram ram) {
		this.nombre = nombre;
		this.espacioOriginal= espacio;
		this.tiempo= tiempo;
		this.espacioBloquesRam= (int) Math.ceil(espacio/64);
		
		if(this.espacioBloquesRam > ram.tama�oRamEnBloques) {
			this.espacioBloquesRam = ram.tama�oRamEnBloques;
		}
		
		System.out.println("Bloques en Ram: " + this.espacioBloquesRam);
		
	}
	
	//m�todo que nos permite sumar el tiempo desde que se ejecut� el programa hasta que termin�
	public LocalDateTime finalizoEjecucion() {
		return (this.empezoEjecucion.plusSeconds(this.tiempo));
		
	}
	
	//m�todo que permite realizar un arraylist para poder conseguir los programas que est�n en lista y poder imprimirlos como un string
	public static  ArrayList<String> getProgramasTextList(ArrayList<Programa> listaPrograma) {
		
		ArrayList<String> listaProgramaText = new ArrayList<String>();
		
		
		 for (int i = 0; i <listaPrograma.size(); i++) 
		 {
			 
			 listaProgramaText.add(listaPrograma.get(i).nombre + " - tama�o: "  + listaPrograma.get(i).espacioBloquesRam);
		  
		}
		 
		return listaProgramaText;

	}
	
}
