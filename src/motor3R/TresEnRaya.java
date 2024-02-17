package motor3R;

import java.util.Random;

/**
 * Clase que representa el juego de Tres en Raya.
 */
public class TresEnRaya {

    private char[][] tablero;
    private final char JUGADOR;
    private final char MAQUINA;

    /**
     * Constructor de la clase TresEnRaya.
     * Permite crear una instancia del juego con un tablero vacío.
     * 
     * @param jugador Carácter que representa al jugador humano en el juego.
     * @param maquina Carácter que representa a la máquina en el juego.
     */
    public TresEnRaya(char jugador, char maquina) {

        JUGADOR = jugador;
        MAQUINA = maquina;
        tablero = new char[3][3];
        inicializarTablero();

    }

    /**
     * Obtiene el carácter que representa al jugador humano.
     * 
     * @return Carácter que representa al jugador humano.
     */
    public char getJugador() {

        return JUGADOR;

    }

    private void inicializarTablero() {

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '-';
            }

        }
    }

    /**
     * Imprime el estado actual del tablero en la consola.
     */
    public void imprimirTablero() {

        System.out.println();

        for (char[] fila : tablero) {
            for (char c : fila) {
                System.out.print("| " + c + " ");
            }
            System.out.println("|\n");
        }
        System.out.println("\n");

    }

    /**
     * Rellena una casilla del tablero con el símbolo del jugador o de la máquina.
     * 
     * @param fila    Fila en la que se desea colocar el símbolo.
     * @param columna Columna en la que se desea colocar el jugador.
     * @param jugador Caracter del jugador que se colocará en la casilla.
     * @return true si la casilla se ha rellenado con éxito, false si la casilla no
     *         es válida.
     */
    public boolean rellenarCasilla(int fila, int columna, char jugador) {

        if (validarPosicion(fila, columna)) {

            tablero[fila - 1][columna - 1] = jugador;

            return true;

        } else {

            return false;
        }
    }

    /**
     * Realiza el turno de la máquina, colocando su símbolo (caracter) en una
     * casilla vacía válida. *
     */
    public void turnoMaquina() {

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }

        Random random = new Random();

        int fila, columna;

        do {

            fila = random.nextInt(3) + 1;

            columna = random.nextInt(3) + 1;

        } while (tablero[fila - 1][columna - 1] != '-');

        tablero[fila - 1][columna - 1] = MAQUINA;
    }

    /**
     * Verifica si hay un ganador en el juego, revisando filas, columnas y
     * diagonales.
     * 
     * @return true si hay un ganador, false en caso contrario.
     */
    public boolean hayGanador() {

        return revisarFilas() || revisarColumnas() || revisarDiagonales();

    }

    private boolean revisarFilas() {

        for (int i = 0; i < 3; i++) {

            if (tablero[i][0] != '-' && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2]) {

                return true;
            }
        }

        return false;
    }

    private boolean revisarColumnas() {

        for (int j = 0; j < 3; j++) {

            if (tablero[0][j] != '-' && tablero[0][j] == tablero[1][j] && tablero[1][j] == tablero[2][j]) {

                return true;
            }
        }

        return false;

    }

    private boolean revisarDiagonales() {

        return (tablero[0][0] != '-' && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2]) ||
                (tablero[0][2] != '-' && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0]);

    }

    /**
     * Verifica si el tablero está completamente lleno, es decir, no quedan casillas
     * vacías.
     * 
     * @return true si el tablero está lleno, false en caso contrario.
     */
    public boolean tableroLleno() {

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                if (tablero[i][j] == '-') {

                    return false;
                }
            }
        }

        return true;
    }

    private boolean validarPosicion(int fila, int col) {

        if (fila > 0 && fila < 4 && col > 0 && col < 4) {

            if (tablero[fila - 1][col - 1] == '-')

                return true;

        }

        return false;

    }

    /**
     * Prepara un nuevo juego reiniciando el tablero.
     */
    public void prepararNueva() {

        inicializarTablero();

    }
}
