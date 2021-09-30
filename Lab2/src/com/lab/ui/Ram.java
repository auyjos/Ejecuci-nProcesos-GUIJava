package com.lab.ui;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Ram {
// se declaran variables
	int tama�oRam;
	int tama�oRamEnBloques;
	String tipoRam;
	int espacioDisponibleBloques;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	DateTimeFormatter formatterSegundos = DateTimeFormatter.ofPattern("ss");
	public ArrayList<Programa> listaPrograma = new ArrayList<Programa>();

	//m�todo para realizar los c�lculos de ram
	public Ram(String tipo, int tama�o) {
		this.tama�oRam = tama�o;
		this.tipoRam = tipo;
		this.espacioDisponibleBloques = tama�o * 1024 / 64; //se determinan los espacios disponibles
		this.tama�oRamEnBloques = tama�o * 1024 / 64; // // se determina el tama�o en bloques
	}
// m�todo que nos permite a�adir los programas al espacio de ram y disminuir el espacio disponible seg�n el tama�o del programa
	public void addProgramaRam(Programa pro) {
		if (this.espacioDisponibleBloques >= pro.espacioBloquesRam) {
			this.listaPrograma.add(pro);
			this.espacioDisponibleBloques = this.espacioDisponibleBloques - pro.espacioBloquesRam;
		}
	}

	//m�todo que una vez el programa ha terminado de trabajar, se debe de remover de la ram
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
					+ this.listaPrograma.get(i).empezoEjecucion.format(this.formatter) + "| Duraci�n: "
					+ this.listaPrograma.get(i).finalizoEjecucion().minusSeconds(LocalDateTime.now().getSecond()).format(this.formatterSegundos) +  "/" +  this.listaPrograma.get(i).tiempo);
		}
		return programasEnColaText;
	}

}
