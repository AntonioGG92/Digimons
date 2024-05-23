import java.util.Scanner;

/**
 * La clase Main es el punto de entrada del juego de Batalla Digital.
 */
public class Main {
    /**
     * El método principal que inicia el juego.
     *
     * @param args argumentos de la línea de comandos (no se utilizan).
     */
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Introducción y nombre del domador
        System.out.println("¡Bienvenido al Mundo Digital!");
        System.out.println("Introduce el nombre del domador:");
        String nombreDomador = scanner.nextLine();
        
     // Creación del domador y asignación del Digimon inicial
        Domador domador = new Domador(nombreDomador);
        System.out.println("¡Hola, " + nombreDomador + "!");
        System.out.println("Has capturado a tu primer Digimon: " + domador.getEquipo().get(0).getNombre() + "!");
        
        // Creación de la batalla digital
        Batalla_Digital batalla = new Batalla_Digital(domador);
        
     // Bucle principal del juego
        boolean salir = false;
        while (!salir) {
            System.out.println("\n¿Qué te gustaría hacer ahora?");
            System.out.println("1. Iniciar batalla");
            System.out.println("2. Ver equipo");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea
            switch (opcion) {
            case 1:
                batalla.iniciarBatalla();
                if (domador.haCapturadoIconicos()) {
                    System.out.println("¡Felicidades! Has capturado a los tres Digimon icónicos: Agumon, Gabumon y Patamon.");
                    salir = true;
                }
                break;
            case 2:
                System.out.println("Estos son los Digimon en tu equipo:");
                domador.mostrarEquipo();
                break;
            case 3:
                System.out.println("¡Hasta luego, " + nombreDomador + "! ¡Vuelve pronto!");
                salir = true;
                break;
            default:
                System.out.println("Opción no válida, por favor intenta de nuevo.");
            }
        }

        scanner.close();
        System.out.println("Fin del juego.");
	}
}