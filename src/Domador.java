import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa a un Domador de Digimon.
 * Un Domador tiene un nombre y un equipo de Digimon.
 */
public class Domador {
	private String nombre;
	private List<Digimon> equipo;

	/**
	 * Constructor para la clase Domador.
	 * @param nombre Nombre del Domador.
	 */
	public Domador(String nombre) {
		this.nombre = nombre;
		this.equipo = new ArrayList<>();
		capturarDigimonInicial();
	}

	/**
	 * Captura un Digimon inicial al azar y lo añade al equipo.
	 */
	private void capturarDigimonInicial() {
		String[] nombresDigimon = {"Agumon", "Gabumon", "Patamon"};
		String nombreDigimon = nombresDigimon[new Random().nextInt(nombresDigimon.length)];
		Digimon digimon = new Digimon(nombreDigimon);
		equipo.add(digimon);
		System.out.println("Has capturado a " + digimon.getNombre() + " como tu Digimon inicial.");
		System.out.println("Nivel: " + digimon.getNivel());
		System.out.println("Salud: " + digimon.getSalud());
		System.out.println("Puntos de Ataque: " + digimon.getPuntosAtaque());
		System.out.println("DP1: " + digimon.getDP1());
		System.out.println("DP2: " + digimon.getDP2());
	}

	/**
	 * Intenta capturar un Digimon si tiene 20 puntos menos de salud.
	 * @param digimon El Digimon a capturar.
	 * @return true si el Digimon fue capturado, false en caso contrario.
	 */
	public boolean capturar(Digimon digimon) {
		if (digimon.getSalud() <= (10 * digimon.getNivel() - 20)) {
			equipo.add(digimon);
			System.out.println(digimon.getNombre() + " se ha unido a su equipo.");
			return true;
		} else {
			System.out.println("No se puede unir, la salud de " + digimon.getNombre() + " es demasiado alta.");
			return false;
		}
	}

	/**
	 * Elige un Digimon del equipo.
	 * @return El Digimon elegido.
	 */
	public Digimon elegirDigimon() {
		if (equipo.isEmpty()) {
			System.out.println("No tienes ningún Digimon en tu equipo.");
			return null;
		}

		System.out.println("Elige un Digimon de tu equipo:");
		for (int i = 0; i < equipo.size(); i++) {
			System.out.println((i + 1) + ". " + equipo.get(i).getNombre());
		}

		Scanner scanner = new Scanner(System.in);
		int eleccion = scanner.nextInt() - 1;

		if (eleccion < 0 || eleccion >= equipo.size()) {
			System.out.println("Opción inválida. Por favor, elige un número dentro del rango.");
			return null;
		}

		return equipo.get(eleccion);
	}

	/**
	 * Muestra el equipo de Digimon del domador.
	 */
	public void mostrarEquipo() {
		System.out.println("Equipo de " + nombre + ":");
		for (Digimon digimon : equipo) {
			digimon.mostrarEstado();
		}
	}

	/**
	 * Devuelve el equipo de Digimon del domador.
	 * @return La lista de Digimon del equipo.
	 */
	public List<Digimon> getEquipo() {
		return equipo;
	}

	/**
	 * Verifica si el domador ha capturado los tres Digimon icónicos.
	 * @return true si se han capturado los tres Digimon, false en caso contrario.
	 */
	public boolean haCapturadoIconicos() {
		boolean tieneAgumon = false;
		boolean tieneGabumon = false;
		boolean tienePatamon = false;

		for (Digimon digimon : equipo) {
			if (digimon.getNombre().equals("Agumon")) {
				tieneAgumon = true;
			} else if (digimon.getNombre().equals("Gabumon")) {
				tieneGabumon = true;
			} else if (digimon.getNombre().equals("Patamon")) {
				tienePatamon = true;
			}
		}
		return tieneAgumon && tieneGabumon && tienePatamon;
	}
}