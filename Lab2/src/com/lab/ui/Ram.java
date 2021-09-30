package com.lab.ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Ram {
// se declaran variables
	int tamañoRam;
	int tamañoRamEnBloques;
	String tipoRam;
	int espacioDisponibleBloques;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter formatterSegundos = DateTimeFormatter.ofPattern("ss");
	public ArrayList<Programa> listaPrograma = new ArrayList<Programa>();

	//método para realizar los cálculos de ram
	public Ram(String tipo, int tamaño) {
		this.tamañoRam = tamaño;
		this.tipoRam = tipo;
		this.espacioDisponibleBloques = tamaño * 1024 / 64; //se determinan los espacios disponibles
		this.tamañoRamEnBloques = tamaño * 1024 / 64; // // se determina el tamaño en bloques
	}
// método que nos permite añadir los programas al espacio de ram y disminuir el espacio disponible según el tamaño del programa
	public void addProgramaRam(Programa pro) {
		if (this.espacioDisponibleBloques >= pro.espacioBloquesRam) {
			this.listaPrograma.add(pro);
			this.espacioDisponibleBloques = this.espacioDisponibleBloques - pro.espacioBloquesRam;
		}
	}

	//método que una vez el programa ha terminado de trabajar, se debe de remover de la ram
	public void removerProgramadeRam(Programa pro, LocalDateTime tiempoActual, int index) {
		if (pro.empezoEjecucion.isBefore(tiempoActual)) {

			System.out.println(
					"Size:" + this.listaPrograma.size() + "Bloques dispoinibles: " + this.espacioDisponibleBloques);
			this.listaPrograma.remove(index);
			this.espacioDisponibleBloques = this.espacioDisponibleBloques + pro.espacioBloquesRam;
			System.out.println(
					"Size:" + this.listaPrograma.size() + "Bloques dispoinibles: " + this.espacioDisponibleBloques);
		}
	}

	
	// se crea un array list  para los programas en cola y de esta manera podemos hacerles print en texto
	public ArrayList<String> programasEnCola() {

		ArrayList<String> programasEnColaText = new ArrayList<String>();
		for (int i = 0; i < this.listaPrograma.size(); i++) {
			
			

			programasEnColaText.add(this.listaPrograma.get(i).nombre + "| Empezo: "
					+ this.listaPrograma.get(i).empezoEjecucion.format(this.formatter) + "| Duración: "
					+ this.listaPrograma.get(i).finalizoEjecucion().minusSeconds(LocalDateTime.now().getSecond()).format(this.formatterSegundos) +  "/" +  this.listaPrograma.get(i).tiempo);
		}
		return programasEnColaText;
	}

}
