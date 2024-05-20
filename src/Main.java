import java.util.Scanner;

/**
 * Clase principal que inicia el programa de batallas digitales.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre del domador: ");
        String nombreDomador = scanner.nextLine();
        Domador domador = new Domador(nombreDomador);

        Batalla_Digital batallaDigital = new Batalla_Digital();
        batallaDigital.iniciarBatalla(domador);
    }
}