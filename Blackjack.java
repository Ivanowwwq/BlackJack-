import java.util.Random;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        new Blackjack().iniciar();
    }

    private ListaEnlazadaBaraja baraja;
    private PilaHistorial historial;
    private ColaTurnos turnos;
    private NodoDecision arbolDealer;
    private TablaHash jugadores;

    private Jugador humano;
    private Jugador dealer;

    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        System.out.println("Bienvenido a Blackjack versión consola");
        System.out.print("Ingrese su nombre: ");

        String nombre = scanner.nextLine();

        // Inicializar estructuras
        baraja = new ListaEnlazadaBaraja();
        baraja.inicializarBaraja();
        baraja.mezclar(random);

        historial = new PilaHistorial();
        turnos = new ColaTurnos();
        arbolDealer = NodoDecision.crearArbolDealer();
        jugadores = new TablaHash(16);

        humano = new Jugador(nombre);
        dealer = new Jugador("Dealer");

        jugadores.insertar(nombre, humano);
        jugadores.insertar("Dealer", dealer);

        turnos.encolar(nombre);
        turnos.encolar("Dealer");

        repartirCartasIniciales();
        turnoHumano();

        if (humano.obtenerPuntaje() <= 21)
            turnoDealer();

        mostrarResultadosFinales();
    }

    // ===============================================
    // REPARTO INICIAL
    private void repartirCartasIniciales() {
        for (int i = 0; i < 2; i++) {
            Carta c1 = baraja.robarCarta();
            humano.recibirCarta(c1);
            historial.apilar(c1);

            Carta c2 = baraja.robarCarta();
            dealer.recibirCarta(c2);
            historial.apilar(c2);
        }
    }

    // ===============================================
    // TURNO HUMANO (INTERACTIVO)
    private void turnoHumano() {
        System.out.println("\nTurno de: " + humano.nombre);

        while (true) {
            System.out.println(humano.nombre + " tiene:");
            humano.imprimirMano();
            System.out.println("Puntaje: " + humano.obtenerPuntaje());

            if (humano.obtenerPuntaje() > 21) {
                System.out.println("Te pasaste!");
                break;
            }

            System.out.print("¿Desea otra carta? (s/n): ");
            String r = scanner.nextLine().toLowerCase();

            if (r.equals("s")) {
                Carta c = baraja.robarCarta();
                System.out.println("Recibes: " + c);
                humano.recibirCarta(c);
                historial.apilar(c);
            } else break;
        }
    }

    // ===============================================
    // TURNO DEALER (AUTOMÁTICO)
    private void turnoDealer() {
        System.out.println("\nTurno de: Dealer");
        dealer.imprimirMano();
        System.out.println("Puntaje: " + dealer.obtenerPuntaje());

        while (arbolDealer.evaluar(dealer.obtenerPuntaje())) {
            System.out.println("El dealer toma una carta...");
            Carta c = baraja.robarCarta();
            System.out.println("Dealer recibe: " + c);
            dealer.recibirCarta(c);
            historial.apilar(c);

            if (dealer.obtenerPuntaje() > 21) {
                System.out.println("El dealer se pasó!");
                break;
            }
        }

        System.out.println("El dealer se planta.");
    }


    // ===============================================
    // RESULTADOS FINALES
    private void mostrarResultadosFinales() {
        System.out.println("\n--- RESULTADOS ---");

        int ph = humano.obtenerPuntaje();
        int pd = dealer.obtenerPuntaje();

        System.out.println("\n" + humano.nombre + " tiene:");
        humano.imprimirMano();
        System.out.println("Puntaje: " + ph);

        System.out.println("\nDealer tiene:");
        dealer.imprimirMano();
        System.out.println("Puntaje: " + pd);

        System.out.println("\n--- GANADOR ---");

        if (ph > 21) {
            if (pd > 21) System.out.println("Empate, ambos se pasaron.");
            else System.out.println("Gana el Dealer.");
        } else if (pd > 21) {
            System.out.println("¡" + humano.nombre + " gana!");
        } else if (ph > pd) {
            System.out.println("¡" + humano.nombre + " gana!");
        } else if (ph < pd) {
            System.out.println("Gana el Dealer.");
        } else {
            System.out.println("Empate.");
        }
    }
}
