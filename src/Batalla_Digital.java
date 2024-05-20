import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa una batalla digital entre Digimon.
 */
public class Batalla_Digital {
    private Digimon digimonEnemigo;

    /**
     * Constructor para la clase BatallaDigital.
     * Genera un Digimon enemigo aleatorio.
     */
    public Batalla_Digital() {
        String[] nombresDigimon = {"Agumon", "Gabumon", "Patamon"};
        String nombreDigimon = nombresDigimon[new Random().nextInt(nombresDigimon.length)];
        this.digimonEnemigo = new Digimon(nombreDigimon);
        System.out.println("Ha aparecido un Digimon enemigo: " + digimonEnemigo.getNombre());
        digimonEnemigo.mostrarEstado();
    }

    /**
     * Inicia una batalla entre el equipo del domador y el Digimon enemigo.
     * @param domador El domador que participa en la batalla.
     */
    public void iniciarBatalla(Domador domador) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Iniciar batalla");
            System.out.println("2. Ver equipo");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            if (opcion == 2) {
                domador.mostrarEquipo();
                continue;
            }
            if (opcion == 3) break;

            Digimon digimonAliado = domador.elegirDigimon();
            pelea(domador, digimonAliado);
        }
    }

    /**
     * Realiza una pelea entre el Digimon del domador y el Digimon enemigo.
     * @param domador El domador que participa en la batalla.
     * @param digimon El Digimon del domador que participa en la batalla.
     */
    public void pelea(Domador domador, Digimon digimon) {
        Scanner scanner = new Scanner(System.in);
        while (digimon.getSalud() > 0 && digimonEnemigo.getSalud() > 0) {
            System.out.println("Elige tu acción:");
            System.out.println("1. Ataque 1");
            System.out.println("2. Ataque 2");
            System.out.println("3. Capturar");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                int daño = digimon.ataque1();
                digimonEnemigo.reducirSalud(daño);
            } else if (opcion == 2) {
                int daño = digimon.ataque2();
                digimonEnemigo.reducirSalud(daño);
            } else if (opcion == 3) {
                if (domador.capturar(digimonEnemigo)) {
                    break;
                }
            }

            if (digimonEnemigo.getSalud() > 0) {
                // Simulación del ataque del enemigo
                int dañoEnemigo = digimonEnemigo.ataque1();
                digimon.reducirSalud(dañoEnemigo);
                System.out.println(digimonEnemigo.getNombre() + " ataca y causa " + dañoEnemigo + " puntos de daño.");
            }

            System.out.println("Estado de " + digimon.getNombre() + ":");
            digimon.mostrarEstado();
            System.out.println("Estado de " + digimonEnemigo.getNombre() + ":");
            digimonEnemigo.mostrarEstado();
        }

        if (digimon.getSalud() <= 0) {
            System.out.println(digimon.getNombre() + " ha sido derrotado.");
        } else if (digimonEnemigo.getSalud() <= 0) {
            System.out.println(digimonEnemigo.getNombre() + " ha sido derrotado.");
        }
    }
}
