import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase que representa una batalla digital entre Digimon.
 */
public class Batalla_Digital {
    private List<Digimon> digimonEnemigos;

    /**
     * Constructor para la clase BatallaDigital.
     * Genera una lista de Digimon enemigos aleatorios.
     */
    public Batalla_Digital() {
        digimonEnemigos = new ArrayList<>();
        generarEnemigos();
        System.out.println("Ha aparecido un grupo de Digimon enemigos:");
        for (Digimon digimon : digimonEnemigos) {
            digimon.mostrarEstado();
        }
    }

    /**
     * Genera una lista de Digimon enemigos aleatorios.
     */
    private void generarEnemigos() {
        String[] nombresDigimon = {"Agumon", "Gabumon", "Patamon"};
        int cantidadEnemigos = new Random().nextInt(3) + 1; // Genera entre 1 y 3 enemigos
        for (int i = 0; i < cantidadEnemigos; i++) {
            String nombreDigimon = nombresDigimon[new Random().nextInt(nombresDigimon.length)];
            Digimon digimon = new Digimon(nombreDigimon);
            digimonEnemigos.add(digimon);
        }
    }

    /**
     * Inicia una batalla entre el equipo del domador y los Digimon enemigos.
     * @param domador El domador que participa en la batalla.
     */
    public void iniciarBatalla(Domador domador) {
        Scanner scanner = new Scanner(System.in);
        boolean continuarJugando = true;

        while (continuarJugando && !domador.getEquipo().isEmpty()) {
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

            if (digimonAliado.getSalud() <= 0) {
                System.out.println("Tu Digimon ha sido derrotado.");
                System.out.println("¿Quieres jugar de nuevo? (s/n)");
                scanner.nextLine(); // Limpiar el buffer del scanner
                String respuesta = scanner.nextLine();
                if (!respuesta.equalsIgnoreCase("s")) {
                    continuarJugando = false;
                }
            }
        }
    }

    /**
     * Realiza una pelea entre el Digimon del domador y los Digimon enemigos.
     * @param domador El domador que participa en la batalla.
     * @param digimon El Digimon del domador que participa en la batalla.
     */
    public void pelea(Domador domador, Digimon digimon) {
        Scanner scanner = new Scanner(System.in);
        while (digimon.getSalud() > 0 && hayEnemigosVivos()) {
            System.out.println("Elige tu acción:");
            System.out.println("1. Ataque 1");
            System.out.println("2. Ataque 2");
            System.out.println("3. Capturar");
            int opcion = scanner.nextInt();

            if (opcion == 1) {
                int daño = digimon.ataque1();
                if (hayEnemigosVivos()) {
                    for (Digimon enemigo : digimonEnemigos) {
                        if (enemigo.getSalud() > 0) {
                            enemigo.reducirSalud(daño);
                        }
                    }
                }
            } else if (opcion == 2) {
                int daño = digimon.ataque2();
                if (hayEnemigosVivos()) {
                    for (Digimon enemigo : digimonEnemigos) {
                        if (enemigo.getSalud() > 0) {
                            enemigo.reducirSalud(daño);
                        }
                    }
                }
            } else if (opcion == 3) {
                if (domador.capturar(digimonEnemigos.get(0))) {
                    break;
                }
            }

            for (Digimon enemigo : digimonEnemigos) {
                if (enemigo.getSalud() > 0) {
                    int dañoEnemigo = enemigo.ataque1();
                    digimon.reducirSalud(dañoEnemigo);
                }
            }

            System.out.println("Estado de " + digimon.getNombre() + ":");
            digimon.mostrarEstado();
            System.out.println("Estado de los enemigos:");
            for (Digimon enemigo : digimonEnemigos) {
                enemigo.mostrarEstado();
            }
        }

        if (digimon.getSalud() <= 0) {
            System.out.println(digimon.getNombre() + " ha sido derrotado.");
        } else if (!hayEnemigosVivos()) {
            System.out.println("Todos los enemigos han sido derrotados. ¡Has ganado la batalla!");
        }
    }

    /**
     * Verifica si todavía hay enemigos vivos en la batalla.
     * @return true si hay al menos un enemigo vivo, false en caso contrario.
     */
    private boolean hayEnemigosVivos() {
        for (Digimon enemigo : digimonEnemigos) {
            if (enemigo.getSalud() > 0) {
                return true;
            }
        }
        return false;
    }
}
