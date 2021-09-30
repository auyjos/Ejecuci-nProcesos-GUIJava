package com.lab.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SimulacionThread extends Thread {
//declaración de variables y llamada a las otras clases para poder definirlas
	private ArrayList<Programa> programaCola = new ArrayList<Programa>();
	private MainWindow window;

	
	public SimulacionThread(MainWindow window) {
		this.window = window;
	}

	public void run() {
		System.out.println("This code is running in a thread");
		this.simulacion();
	}

	//en este método llamamos a la clase ram y permitimos que se haga una lista de los programas que correr
	public void simulacion() {
		Ram ram = new Ram("DDR", this.window.ramSize);
		
		this.window.ramDisponible.setMaximum(ram.tamañoRamEnBloques + 1);
		this.window.ramDisponible.setMinimum(0);

		this.programaCola.add(new Programa("Office", 270, 1, ram));
		this.programaCola.add(new Programa("Discord", 420, 2, ram));
		this.programaCola.add(new Programa("Chrome", 12700, 3, ram));
		this.programaCola.add(new Programa("Office1", 27000, 4, ram));
		this.programaCola.add(new Programa("Discord2", 42000, 5, ram));
		this.programaCola.add(new Programa("Chrome3", 1270, 6, ram));
		this.programaCola.add(new Programa("Office4", 27000, 7, ram));
		this.programaCola.add(new Programa("Discord5", 420, 8, ram));
		this.programaCola.add(new Programa("Chrome6", 1270, 9, ram));

		System.out.println(this.programaCola);
		while (ram.listaPrograma.size() != 0 || this.programaCola.size() != 0) {
			this.procesarProgramas(ram);

			// se realiza un  try and catch para poder utilizar un thread
			try {

				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.actualizarUI(ram); // aquí usamos actualizar ui y de parámetro ram para poder actualizar los datos en la interfaz gráfica

		}

		
	}

	//en este método hacemos que los programas puedan ser procesados en la cola y luego removerlos de esta
	public void procesarProgramas(Ram ram) {

		if (programaCola.size() != 0) {
			for (int i = 0; i < programaCola.size(); i++) {
				Programa programaCargar = programaCola.get(i);
				if (esPosibleAgregarPrograma(programaCargar, ram)) {

					programaCargar.empezoEjecucion = LocalDateTime.now();
					ram.addProgramaRam(programaCargar);
					programaCola.remove(programaCargar);
				}
			}
		}

		
		//en este método permitimos que se haga una lista de los programas que se encuentran en cola y que al finalizar de correr muestren el tiempo que transcurrió hasta que terminaran
		if (ram.listaPrograma.size() != 0) {

			for (int i = 0; i < ram.listaPrograma.size(); i++) {
				Programa programaLista = ram.listaPrograma.get(i);
				LocalDateTime horaActual = LocalDateTime.now();
				System.out.println("Tiempo Actual: " + horaActual + "  Tiempo Programa finalizacion: "
						+ programaLista.finalizoEjecucion() + " isBefore: "
						+ programaLista.finalizoEjecucion().isBefore(horaActual));
				if (programaLista.finalizoEjecucion().isBefore(horaActual)) {
					ram.removerProgramadeRam(programaLista, horaActual, i);
				}

			}

		}

	}

	
	// en este método realizamos la impresión de los programas en cola y el espacio disponible en la ram
	public void actualizarUI(Ram ram) {

		System.out.println("Bloques disponibles" + ram.espacioDisponibleBloques);
		System.out.println("Programas en Cola");
		ArrayList<String> cola = Programa.getProgramasTextList(programaCola);

		System.out.println(cola.toString());
		this.window.listCola.setListData(cola.toArray());

		System.out.println("Programas en Ram");
		ArrayList<String> colaEnRam = ram.programasEnCola();
		System.out.println(colaEnRam.toString());
		this.window.listColaRam.setListData(colaEnRam.toArray());
		
		//ram
		System.out.println(ram.espacioDisponibleBloques);
		System.out.println( ram.tamañoRamEnBloques);
		window.hora.setText(LocalDateTime.now().format(ram.formatter));
		this.window.ramDisponible.setValue(ram.espacioDisponibleBloques);
		this.window.ramTextDisponible.setText(ram.espacioDisponibleBloques + "/" + ram.tamañoRamEnBloques + " bloques");
	}

	public boolean esPosibleAgregarPrograma(Programa programa, Ram ram) {
		return ram.espacioDisponibleBloques >= programa.espacioBloquesRam;
	}

}