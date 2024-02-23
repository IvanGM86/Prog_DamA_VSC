import motor3R.TresEnRaya;

import java.util.Scanner;

public class InterfaceConsola {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("¡Bienvenido al juego de Tres en Raya! \n");

        System.out.println("Elige el caracter que te representará :");

        char jugador = sc.nextLine().trim().charAt(0);

        System.out.println("¿Cuál será el de la máquina?");

        char maquina = sc.nextLine().trim().charAt(0);

        TresEnRaya juego = new TresEnRaya(jugador, maquina);

        jugarTresEnRaya(juego, sc);

        sc.close();

    }

    public static void jugarTresEnRaya(TresEnRaya juego, Scanner sc) {

        boolean jugando = true;

        while (jugando) {

            imprimirTablero(juego.getTablero());

            while (!juego.hayGanador() && !juego.tableroLleno()) {

                System.out.println("Turno del jugador. Elija fila (1-3): ");

                int fila = sc.nextInt();

                System.out.println("Elija columna (1-3): ");

                int columna = sc.nextInt();

                if (juego.rellenarCasilla(fila, columna, juego.getJugador())) {

                    imprimirTablero(juego.getTablero());

                    if (!juego.hayGanador() && !juego.tableroLleno()) {

                        System.out.println("Turno de la máquina:");

                        juego.turnoMaquina();

                        imprimirTablero(juego.getTablero());
                    }

                } else {

                    System.out.println("La casilla seleccionada no es válida");

                }
            }

            if (juego.hayGanador()) {

                System.out.println("¡Felicidades! ¡Has ganado!\n");

            } else {

                System.out.println("¡Empate! El juego ha terminado.\n");

            }

            System.out.println("¿Quieres jugar de nuevo? (s/n): ");

            String respuesta = sc.next();

            if (!respuesta.equalsIgnoreCase("s")) {

                jugando = false;
            }

            juego.prepararNueva();

        }

    }

    public static void imprimirTablero(char[][] tablero) {

        System.out.println();

        for (char[] fila : tablero) {
            for (char c : fila) {
                System.out.print("| " + c + " ");
            }
            System.out.println("|\n");
        }
        System.out.println("\n");

    }

}
