import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Domador {
    private String nombre;
    private List<Digimon> equipo;

    public Domador(String nombre) {
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
        capturarDigimonInicial();
    }

    private void capturarDigimonInicial() {
        String[] nombresDigimon = {"Agumon", "Gabumon", "Patamon"};
        String nombreDigimon = nombresDigimon[new Random().nextInt(nombresDigimon.length)];
        Digimon digimon = new Digimon(nombreDigimon);
        equipo.add(digimon);
        System.out.println("Has capturado a " + digimon.getNombre() + " como tu Digimon inicial.");
    }

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

    public Digimon elegirDigimon() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige un Digimon de tu equipo:");
        for (int i = 0; i < equipo.size(); i++) {
            System.out.println((i + 1) + ". " + equipo.get(i).getNombre());
        }
        int eleccion = scanner.nextInt() - 1;
        return equipo.get(eleccion);
    }

    public void mostrarEquipo() {
        System.out.println("Equipo de " + nombre + ":");
        for (Digimon digimon : equipo) {
            digimon.mostrarEstado();
        }
    }

    public List<Digimon> getEquipo() {
        return equipo;
    }
}
