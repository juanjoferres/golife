package com.feseju.android.gameoflife.tablero;

import android.util.Log;

import java.util.Random;

/**
 * Created by FESEJU on 20/04/2015.
 */
public class Tablero {

    static int filas = 0;
    static int columnas = 0;

    private static int datos[][];
    private static int vecinos[][];
    private static int nuevaGeneracion[][];

    public Tablero(int filas, int columnas) {

        this.filas = filas;
        this.columnas = columnas;

        datos = new int[filas][columnas];
        vecinos = new int[filas][columnas];
        nuevaGeneracion = new int[filas][columnas];
    }

    public static void tick() {
        /*
        Any live cell with fewer than two live neighbours dies, as if caused by under-population.
        Any live cell with two or three live neighbours lives on to the next generation.
        Any live cell with more than three live neighbours dies, as if by overcrowding.
        Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
         */
        calculaVecinos();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                //Is alive?
                if ( datos[i][j] == 1) {

                    if ( vecinos[i][j] < 2 ) {
                        nuevaGeneracion[i][j] = 0;
                    }

                    if ( vecinos[i][j] > 3 ) {
                        nuevaGeneracion[i][j] = 0;
                    }


                } else { //Is dead
                    //Si tiene tres celulas muertas alrededor esa cell vive
                    //El problemas
                    if ( vecinos[i][j] == 3 ) {
                        nuevaGeneracion[i][j] = 1;
                    }
                }
            }
        }

        nuevaGeneracionADatos();
    }

    public static void nuevaGeneracionADatos() {

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = nuevaGeneracion[i][j];
            }
        }
    }

    public static void calculaVecinos() {

        boolean inBoundsI;
        boolean inBoundsJ;
        int maxI = filas;
        int maxJ = columnas;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                vecinos[i][j] = 0;
            }
        }

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                // 0 // i-1, j-1
                inBoundsI = (i - 1 >= 0) && (i - 1 < maxI);
                inBoundsJ = (j - 1 >= 0) && (j - 1 < maxJ);

                if (inBoundsI && inBoundsJ) {
                    if ( datos[(i - 1)][(j - 1)] == 1 ){
                        vecinos[i][j] = vecinos[i][j] + 1;
                    }
                }

                // 1 // i  , j-1
                inBoundsI = (i >= 0 ) && ( i < maxI );
                inBoundsJ = (j - 1 >= 0) && (j - 1 < maxJ );

                if (inBoundsI && inBoundsJ) {
                    if ( datos[(i)][(j - 1)] == 1 ){
                        vecinos[i][j] = vecinos[i][j] + 1;
                    }
                }

                // 2 // i+1, j-1
                inBoundsI = (i + 1 >= 0 ) && ( i + 1 < maxI );
                inBoundsJ = (j - 1 >= 0 ) && ( j - 1 < maxJ );

                if (inBoundsI && inBoundsJ) {
                    if ( datos[(i + 1)][(j - 1)] == 1 ){
                        vecinos[i][j] = vecinos[i][j] + 1;
                    }
                }

                // 3 // i-1, j
                inBoundsI = (i - 1 >= 0 ) && ( i - 1 < maxI );
                inBoundsJ = (j  >= 0 ) && ( j < maxJ );

                if (inBoundsI && inBoundsJ) {
                    if ( datos[(i - 1)][(j)] == 1 ){
                        vecinos[i][j] = vecinos[i][j] + 1;
                    }
                }

                // 4 // i+1, j
                inBoundsI = (i + 1 >= 0 ) && ( i + 1 < maxI );
                inBoundsJ = (j  >= 0 ) && ( j < maxJ );

                if (inBoundsI && inBoundsJ) {
                    if ( datos[(i + 1)][(j)] == 1 ){
                        vecinos[i][j] = vecinos[i][j] + 1;
                    }
                }

                // 5 // i-1, j+1
                inBoundsI = (i - 1 >= 0 ) && ( i - 1 < maxI );
                inBoundsJ = (j + 1 >= 0 ) && ( j + 1 < maxJ );

                if (inBoundsI && inBoundsJ) {
                    if ( datos[(i - 1)][(j + 1)] == 1 ){
                        vecinos[i][j] = vecinos[i][j] + 1;
                    }
                }

                // 6 // i  , j+1
                inBoundsI = (i >= 0 ) && ( i < maxI );
                inBoundsJ = (j + 1 >= 0 ) && ( j + 1 < maxJ );

                if (inBoundsI && inBoundsJ) {
                    if ( datos[(i)][(j + 1)] == 1 ){
                        vecinos[i][j] = vecinos[i][j] + 1;
                    }
                }

                // 7 // i+1, j+1
                inBoundsI = (i + 1 >= 0 ) && ( i + 1 < maxI );
                inBoundsJ = (j + 1 >= 0 ) && ( j + 1 < maxJ );

                if (inBoundsI && inBoundsJ) {
                    if ( datos[(i + 1)][(j + 1)] == 1 ){
                        vecinos[i][j] = vecinos[i][j] + 1;
                    }
                }
            }
        }
    }

    public static void generateRandomTablero() {

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = randInt(0,1);
            }
        }
    }

    public static String tableroACadena() {
        String miTablero = "";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                miTablero = miTablero + "" + datos[i][j];
            }
            miTablero = miTablero + "\n";
        }
        return miTablero;
    }

    public static String vecinosACadena() {
        String miTablero = "";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                miTablero = miTablero + "" + vecinos[i][j];
            }
            miTablero = miTablero + "\n";
        }
        return miTablero;
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}

