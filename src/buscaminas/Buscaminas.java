package buscaminas;

import java.util.Random;

/**
 * La clase Buscaminas representa el juego del buscaminas.
 * Permite crear un tablero, destapar casillas,
 * establecer la dificultad del juego y determinar si el juego ha terminado.
 */
public class Buscaminas {

    private int fil;
    private int col;
    private final int[][] valores;
    private final boolean[][] destapado;
    private int numMinas;

    /**
     * Crea una instancia de Buscaminas con el tamaño especificado.
     *
     * @param filas    El número de filas del tablero.
     * @param columnas El número de columnas del tablero.
     */
    public Buscaminas(int filas, int columnas) {

        setFil(filas);
        setCol(columnas);

        valores = new int[filas][columnas];
        destapado = new boolean[filas][columnas];

        inicializarTablero();

        imprimirTablero();
    }

    /**
     * Obtiene el número de filas del tablero.
     *
     * @return El número de filas del tablero.
     */
    public int getFil() {
        return fil;
    }

    /**
     * Obtiene el número de columnas del tablero.
     *
     * @return El número de columnas del tablero.
     */
    public int getCol() {
        return col;
    }

    /**
     * Establece el número de columnas del tablero.
     * Si el número de columnas es menor que 6, se muestra un mensaje de
     * advertencia.
     *
     * @param col El número de columnas del tablero.
     */
    public void setCol(int col) {

        if (col < 6)
            System.out.println("Recuerda -> mayor o igual a 6");

        else
            this.col = col;
    }

    /**
     * Establece el número de filas del tablero.
     * Si el número de filas es menor que 6, se muestra un mensaje de advertencia.
     *
     * @param fil El número de filas del tablero.
     */
    public void setFil(int fil) {

        if (fil < 6)
            System.out.println("Recuerda -> mayor o igual a 6");

        else
            this.fil = fil;
    }

    private void inicializarTablero() {

        for (int i = 0; i < valores.length; i++) {
            for (int j = 0; j < valores[0].length; j++) {
                valores[i][j] = 0;
                destapado[i][j] = false;
            }
        }
    }

    /**
     * Destapa la casilla en la fila y columna especificadas por el jugador.
     * Si la casilla contiene una mina, el juego termina.
     * Si la casilla está vacía, se destapan las casillas adyacentes.
     *
     * @param filaJugador    La fila de la casilla a destapar.
     * @param columnaJugador La columna de la casilla a destapar.
     * @return true si el jugador ha destapado una casilla con una mina, de lo
     *         contrario false.
     */
    public boolean destaparCasilla(int filaJugador, int columnaJugador) {

        int fila = filaJugador - 1;
        int columna = columnaJugador - 1;

        if (fila >= 0 && fila < valores.length && columna >= 0 && columna < valores[0].length
                && !destapado[fila][columna]) {

            destapado[fila][columna] = true;

            if (valores[fila][columna] == 9)
                return true;

            if (valores[fila][columna] == 0) {

                for (int f = Math.max(0, fila - 1); f < Math.min(valores.length, fila + 2); f++) {
                    for (int c = Math.max(0, columna - 1); c < Math.min(valores[0].length, columna + 2); c++) {

                        destaparCasilla(f + 1, c + 1);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Imprime el tablero de juego en la consola.
     * Cada casilla destapada muestra su valor, mientras que las casillas no
     * destapadas se muestran como 'T'.
     * Las casillas vacías se muestran con un fondo de color verde, las casillas con
     * el valor 1 con un fondo amarillo,
     * las casillas con el valor 2 con un fondo magenta y las casillas con minas con
     * un fondo rojo.
     */
    public void imprimirTablero() {
        for (int i = 0; i < valores.length; i++) {
            for (int j = 0; j < valores[0].length; j++) {

                if (destapado[i][j]) {

                    switch (valores[i][j]) {
                        case 0:
                            System.out.print("\u001B[42m" + " " + valores[i][j] + " " + "\u001B[0m");
                            break;
                        case 1:
                            System.out.print("\u001B[43m" + " " + valores[i][j] + " " + "\u001B[0m");
                            break;
                        case 2:
                            System.out.print("\u001B[45m" + " " + valores[i][j] + " " + "\u001B[0m");
                            break;
                        case 9:
                            System.out.print("\u001B[41m" + " " + valores[i][j] + " " + "\u001B[0m");
                            break;
                    }

                } else
                    System.out.print(" T ");

            }
            System.out.println();
        }
    }

    /**
     * Establece la dificultad del juego según la opción especificada por el
     * jugador.
     * La dificultad determina el número de minas en el tablero.
     *
     * @param dificultad La dificultad del juego: "F" para fácil, "M" para medio,
     *                   "D" para difícil.
     */
    public void establecerDificultad(String dificultad) {

        switch (dificultad) {

            case "F":

                numMinas = (int) (0.1 * fil * col);
                colocaMinas(numMinas);
                rellenarTablero();
                break;
            case "M":

                numMinas = (int) (0.2 * fil * col);
                colocaMinas(numMinas);
                rellenarTablero();
                break;

            case "D":

                numMinas = (int) (0.3 * fil * col);
                colocaMinas(numMinas);
                rellenarTablero();
                break;

            default:
                System.out.println("Opcion incorrecta");

        }
    }

    private void colocaMinas(int num) {

        Random rd = new Random();
        int minasColocadas = 0;

        while (minasColocadas < num) {
            int fila = rd.nextInt(fil);
            int columna = rd.nextInt(col);

            if (valores[fila][columna] != 9) {

                valores[fila][columna] = 9;
                minasColocadas++;
            }
        }
    }

    /**
     * Destapa todas las casillas del tablero, revelando su contenido.
     */
    public void destaparTodo() {

        for (int i = 0; i < valores.length; i++) {
            for (int j = 0; j < valores[0].length; j++) {
                destapado[i][j] = true;
            }
        }
    }

    private void rellenarTablero() {

        for (int i = 0; i < valores.length; i++) {
            for (int j = 0; j < valores[i].length; j++) {

                if (valores[i][j] != 9) {

                    for (int f = Math.max(0, i - 1); f < Math.min(valores.length, i + 2); f++) {
                        for (int c = Math.max(0, j - 1); c < Math.min(valores[f].length, j + 2); c++) {

                            if (!(f == i && c == j) && valores[f][c] == 9) {

                                valores[i][j]++;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Verifica si el juego ha sido completado sin destapar una mina.
     *
     * @return true si el juego ha sido completado, de lo contrario false.
     */
    public boolean juegoCompleto() {

        int totalCasillasSin = (fil * col) - numMinas;
        int casillasDestapadas = 0;
        for (int i = 0; i < valores.length; i++) {
            for (int j = 0; j < valores[i].length; j++) {
                if (destapado[i][j] && valores[i][j] != 9) {
                    casillasDestapadas++;
                }
            }
        }

        return casillasDestapadas == totalCasillasSin;
    }
}