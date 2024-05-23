import java.util.Random;
import java.util.Scanner;

/**
 * La clase BatallaDigital representa una batalla entre Digimons.
 */
public class Batalla_Digital {
    private Domador domador;

    /**
     * Constructor de la clase BatallaDigital.
     *
     * @param domador el domador que participará en la batalla.
     */
    public Batalla_Digital(Domador domador) {
        this.domador = domador;
    }
    
    /**
     * Inicia la batalla entre el Digimon del domador y un Digimon enemigo generado aleatoriamente.
     */
    public void iniciarBatalla() {
        System.out.println("¡Prepárate para la batalla!");

        while (true) {
            Digimon digimonElegido = domador.elegirDigimon();
            if (digimonElegido == null) {
                continue;
            }

            Digimon enemigo = generarDigimonAleatorio();
            System.out.println("Te enfrentas a " + enemigo.getNombre());
            System.out.println("Nivel: " + enemigo.getNivel());
            System.out.println("Salud: " + enemigo.getSalud());
            System.out.println("Puntos de Ataque: " + enemigo.getPuntosAtaque());
            System.out.println("DP1: " + enemigo.getDP1());
            System.out.println("DP2: " + enemigo.getDP2());
            
            while (true) {
                System.out.println("Elige tu acción:");
                System.out.println("1. Ataque común");
                System.out.println("2. Ataque especial");
                System.out.println("3. Capturar enemigo");

                Scanner scanner = new Scanner(System.in);
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        ataqueComun(digimonElegido, enemigo);
                        break;
                    case 2:
                        ataqueEspecial(digimonElegido, enemigo);
                        break;
                    case 3:
                        if (capturarEnemigo(enemigo)) {
                            System.out.println("¡Has capturado a " + enemigo.getNombre() + "!");
                            return;
                        } else {
                            System.out.println("No se pudo capturar al enemigo.");
                        }
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción válida.");
                        break;
                }

                if (enemigo.getSalud() <= 0) {
                    System.out.println("¡Has derrotado a " + enemigo.getNombre() + "!");
                    return;
                }

                if (digimonElegido.getSalud() <= 0) {
                    System.out.println("¡Tu Digimon ha sido derrotado!");
                    break;
                }
            }
        }
    }
    /**
     * Realiza un ataque común de un Digimon a otro.
     *
     * @param atacante el Digimon que realiza el ataque.
     * @param objetivo el Digimon que recibe el ataque.
     */
    private void ataqueComun(Digimon atacante, Digimon objetivo) {
        System.out.println(atacante.getNombre() + " ataca a " + objetivo.getNombre() + " con un ataque común.");
        int dano = atacante.getPuntosAtaque();
        objetivo.recibirAtaque(dano);
        System.out.println("Causó " + dano + " puntos de daño.");
    }

    /**
     * Realiza un ataque especial de un Digimon a otro.
     *
     * @param atacante el Digimon que realiza el ataque.
     * @param objetivo el Digimon que recibe el ataque.
     */
    private void ataqueEspecial(Digimon atacante, Digimon objetivo) {
        System.out.println(atacante.getNombre() + " usa un ataque especial en " + objetivo.getNombre() + ".");
        int dano = atacante.getPuntosAtaque() * 2;
        objetivo.recibirAtaque(dano);
        System.out.println("Causó " + dano + " puntos de daño.");
    }
    
    /**
     * Intenta capturar a un Digimon enemigo.
     *
     * @param enemigo el Digimon enemigo a capturar.
     * @return true si el Digimon fue capturado exitosamente, false en caso contrario.
     */
    private boolean capturarEnemigo(Digimon enemigo) {
        Random random = new Random();
        int intento = random.nextInt(10) + 1; // Intento de captura aleatorio entre 1 y 10
        int limiteCaptura = enemigo.getNivel() * 5; // Se determina un límite de captura basado en el nivel del enemigo

        if (intento > limiteCaptura) {
            return false; // El intento de captura falla si el número aleatorio es mayor que el límite de captura
        }

        return domador.capturar(enemigo);
    }

    /**
     * Genera un Digimon enemigo aleatorio.
     *
     * @return un objeto Digimon con atributos aleatorios.
     */
    private Digimon generarDigimonAleatorio() {
        String[] nombres = {"Agumon", "Gabumon", "Patamon", "Palmon", "Tentomon", "Gomamon", "Biyomon", "Elecmon", "Gatomon", "Veemon"};
        String nombre = nombres[new Random().nextInt(nombres.length)];
        int nivel = new Random().nextInt(5) + 1;
        int puntosAtaque = nivel * 5;
        int salud = nivel * 10;
        int dp1 = 10;
        int dp2 = 10;

        return new Digimon(nombre, nivel, puntosAtaque, salud, dp1, dp2);
    }
}