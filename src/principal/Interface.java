package principal;

import buscaminas.Buscaminas;

import java.util.Scanner;

public class Interface {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int filas, columnas;
        String dificultad;
        boolean juegoTerminado;
        boolean jugarDeNuevo = true;

        while (jugarDeNuevo) {

            presentarJuego();

            System.out.println("Selecciona el tamaño del tablero");
            System.out.println("Empezaremos por las filas: ");

            do {

                filas = sc.nextInt();

                if (filas < 6) {
                    System.out.println("El número de filas debe ser mayor o igual a 6.");
                    System.out.println("Vuelve a indicarnos el numero de filas: ");
                }
            } while (filas < 6);

            System.out.println("Ahora elige el número de columnas: ");

            do {
                columnas = sc.nextInt();

                if (columnas < 6) {
                    System.out.println("El número de columnas debe ser mayor o igual a 6.");
                    System.out.println("Vuelve a indicarnos el numero de filas: ");
                }
            } while (columnas < 6);

            sc.nextLine();

            do {

                System.out.println("""
                        Perfecto... Ahora necesitamos que elijas el nivel de dificultad:
                        \t - Fácil: prefiona -> F
                        \t - Medio: presiona -> M
                        \t - Dificil: presiona -> D
                        """);

                dificultad = sc.nextLine().trim().toUpperCase();

                if (dificultad.equals("F") || dificultad.equals("M") || dificultad.equals("D")) {

                    System.out.println("\n\n¡¡Muy bien,... QUE EMPIEZCE EL JUEGO!!\n\n");

                } else

                    System.out.println("Opción de dificultad no válida. Por favor, elige entre F, M o D.");

            } while (!(dificultad.equals("F") || dificultad.equals("M") || dificultad.equals("D")));

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }

            Buscaminas juego = new Buscaminas(filas, columnas);

            juego.establecerDificultad(dificultad);

            do {

                System.out.println("\n=====================================================");

                System.out.println("Elije fila: (1-" + juego.getFil() + ")");

                filas = sc.nextInt();

                System.out.println("Elije columna: (1-" + juego.getCol() + ")");

                columnas = sc.nextInt();

                juegoTerminado = juego.destaparCasilla(filas, columnas);

                if (juegoTerminado) {

                    System.out.println("¡BOOM! ..... TE HAS DESINTEGRADO.");
                    juego.destaparTodo();
                    juego.imprimirTablero();

                } else if (juego.juegoCompleto()) {

                    System.out.println("¡FELICIDADES , LO HAS CONSEGUIDO!\n");
                    juego.imprimirTablero();

                } else {
                    System.out.println("\n==========...SIGUE ASI..==========\n");
                    juego.imprimirTablero();
                }

            } while (!juego.juegoCompleto() && !juegoTerminado);

            System.out.println("\n¿Quieres jugar de nuevo? (Sí: 's' / No: 'n')");
            char opcion = sc.next().charAt(0);
            if (opcion == 'n' || opcion == 'N') {
                jugarDeNuevo = false;
            }
        }

        System.out.println("¡Gracias por jugar al Buscaminas!");

    }

    public static void presentarJuego() {
        System.out.println("¡Bienvenido al juego del Buscaminas!");
        System.out.println("======================================== \n\n");
        System.out.println("Instrucciones:\n");
        System.out.println(
                "- Selecciona  el tamaño del tablero \n (se recomienda que sea cuadrado y un mínimo de 6X6 aunque tienes cierta libertad para elegir)");
        System.out.println("- Selecciona el nivel de dificultad...");
        System.out.println("- Selecciona una casilla para destaparla.");
        System.out.println("- Si destapas una casilla con una mina, pierdes.");
        System.out.println("- Si destapas una casilla sin mina, se mostrará el número de minas adyacentes.");
        System.out.println("- El juego termina cuando todas las casillas sin minas están destapadas.\n\n");
        System.out.println("\t¡Buena suerte!\n\n");
    }

}
