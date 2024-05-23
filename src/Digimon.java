import java.util.Random;

public class Digimon {
    private String nombre;
    private int nivel;
    private int puntosAtaque;
    private int salud;
    private int experiencia;
    private int dp1;
    private int dp2;

    public Digimon(String nombre) {
        this.nombre = nombre;
        this.nivel = (int) (Math.random() * 5) + 1;
        this.puntosAtaque = 5 * nivel;
        this.salud = 10 * nivel;
        this.experiencia = 0;
        this.dp1 = 10;
        this.dp2 = 10;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getSalud() {
        return salud;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void reducirSalud(int daño) {
        this.salud -= daño;
        System.out.println(nombre + " recibe " + daño + " puntos de daño.");
        if (salud <= 0) {
            System.out.println(nombre + " ha sido derrotado.");
        }
    }

    public void incrementarExperiencia(int exp) {
        this.experiencia += exp;
        System.out.println(nombre + " gana " + exp + " puntos de experiencia.");
        if (experiencia >= 100) {
            subirNivel();
        }
    }

    public int ataque1() {
        if (dp1 > 0) {
            dp1--;
            System.out.println(nombre + " usa Ataque 1 y causa " + puntosAtaque + " puntos de daño.");
            return puntosAtaque;
        } else {
            System.out.println(nombre + " no tiene suficientes DP1 para usar Ataque 1.");
            return 0;
        }
    }

    public int ataque2() {
        if (dp2 > 1) {
            dp2 -= 2;
            System.out.println(nombre + " usa Ataque 2 y causa " + (2 * puntosAtaque) + " puntos de daño.");
            return 2 * puntosAtaque;
        } else {
            System.out.println(nombre + " no tiene suficientes DP2 para usar Ataque 2.");
            return 0;
        }
    }

    private void subirNivel() {
        System.out.println(nombre + " ha subido de nivel!");
        nivel++;
        puntosAtaque = 5 * nivel;
        salud = 10 * nivel;
        experiencia = 0;
    }

    public void mostrarEstado() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Nivel: " + nivel);
        System.out.println("Puntos de Ataque: " + puntosAtaque);
        System.out.println("Salud: " + salud);
        System.out.println("Experiencia: " + experiencia);
        System.out.println("DP1: " + dp1);
        System.out.println("DP2: " + dp2);
    }
}
