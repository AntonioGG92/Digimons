import java.util.Random;

/**
 * Clase que representa un Digimon.
 */
public class Digimon {
	private String nombre;
	private int nivel;
	private int puntosAtaque;
	private int salud;
	private int dp1;
	private int dp2;

	/**
	 * Constructor para la clase Digimon.
	 * @param nombre Nombre del Digimon.
	 */
	public Digimon(String nombre) {
		this.nombre = nombre;
		this.nivel = new Random().nextInt(5) + 1;
		this.puntosAtaque = this.nivel * 5;
		this.salud = this.nivel * 10;
		this.dp1 = 10;
		this.dp2 = 10;
	}

	/**
	 * Constructor para la clase Digimon con atributos personalizados.
	 * @param nombre Nombre del Digimon.
	 * @param nivel Nivel del Digimon.
	 * @param puntosAtaque Puntos de ataque del Digimon.
	 * @param salud Salud del Digimon.
	 * @param dp1 Puntos de acción disponibles 1 del Digimon.
	 * @param dp2 Puntos de acción disponibles 2 del Digimon.
	 */
	public Digimon(String nombre, int nivel, int puntosAtaque, int salud, int dp1, int dp2) {
		this.nombre = nombre;
		this.nivel = nivel;
		this.puntosAtaque = puntosAtaque;
		this.salud = salud;
		this.dp1 = dp1;
		this.dp2 = dp2;
	}

	public String getNombre() {
		return nombre;
	}

	public int getNivel() {
		return nivel;
	}

	public int getPuntosAtaque() {
		return puntosAtaque;
	}

	public int getSalud() {
		return salud;
	}

	public int getDP1() {
		return dp1;
	}

	public int getDP2() {
		return dp2;
	}

	public void mostrarEstado() {
		System.out.println("Nombre: " + nombre);
		System.out.println("Nivel: " + nivel);
		System.out.println("Puntos de Ataque: " + puntosAtaque);
		System.out.println("Salud: " + salud);
		System.out.println("DP1: " + dp1);
		System.out.println("DP2: " + dp2);
	}

	public void recibirAtaque(int dano) {
		salud -= dano;
		if (salud < 0) {
			salud = 0;
		}
	}

	public void atacar(Digimon enemigo) {
		if (dp1 > 0) {
			System.out.println(nombre + " ataca a " + enemigo.getNombre() + " con un ataque común.");
			enemigo.recibirAtaque(puntosAtaque);
			dp1--;
			System.out.println("Causó " + puntosAtaque + " puntos de daño.");
			System.out.println("Salud de " + enemigo.getNombre() + " ahora es " + enemigo.getSalud());
			System.out.println("DP1 restante: " + dp1);
		} else {
			System.out.println(nombre + " no tiene suficientes DP para realizar el ataque común.");
		}
	}

	public void ataqueEspecial(Digimon enemigo) {
		if (dp2 >= 2) {
			System.out.println(nombre + " usa un ataque especial en " + enemigo.getNombre() + ".");
			enemigo.recibirAtaque(2 * puntosAtaque);
			dp2 -= 2;
			System.out.println("Causó " + (2 * puntosAtaque) + " puntos de daño.");
			System.out.println("Salud de " + enemigo.getNombre() + " ahora es " + enemigo.getSalud());
			System.out.println("DP2 restante: " + dp2);
		} else {
			System.out.println(nombre + " no tiene suficientes DP para realizar el ataque especial.");
		}
	}
}
