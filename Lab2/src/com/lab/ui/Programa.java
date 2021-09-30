package com.lab.ui;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;  
import java.lang.*;


// declaración de variables
public class Programa {
	String nombre;
	int espacioOriginal; 
	 int espacioBloquesRam;
	 LocalDateTime empezoEjecucion;
	 int tiempo;
	
	 
	 // método que nos permite establecer variables con los diferentes parámetros y de esta manera poder realizar los parámetros para ram
	public Programa(String nombre, int espacio, int tiempo, Ram ram) {
		this.nombre = nombre;
		this.espacioOriginal= espacio;
		this.tiempo= tiempo;
		this.espacioBloquesRam= (int) Math.ceil(espacio/64);
		
		if(this.espacioBloquesRam > ram.tamañoRamEnBloques) {
			this.espacioBloquesRam = ram.tamañoRamEnBloques;
		}
		
		System.out.println("Bloques en Ram: " + this.espacioBloquesRam);
		
	}
	
	//método que nos permite sumar el tiempo desde que se ejecutó el programa hasta que terminó
	public LocalDateTime finalizoEjecucion() {
		return (this.empezoEjecucion.plusSeconds(this.tiempo));
		
	}
	
	//método que permite realizar un arraylist para poder conseguir los programas que están en lista y poder imprimirlos como un string
	public static  ArrayList<String> getProgramasTextList(ArrayList<Programa> listaPrograma) {
		
		ArrayList<String> listaProgramaText = new ArrayList<String>();
		
		
		 for (int i = 0; i <listaPrograma.size(); i++) 
		 {
			 
			 listaProgramaText.add(listaPrograma.get(i).nombre + " - tamaño: "  + listaPrograma.get(i).espacioBloquesRam);
		  
		}
		 
		return listaProgramaText;

	}
	
}
