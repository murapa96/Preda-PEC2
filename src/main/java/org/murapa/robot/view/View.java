package org.murapa.robot.view;

import java.util.Scanner;
import org.murapa.robot.model.Building;


/**
 * Clase View
 * 
 * Esta clase se encarga de mostrar los mensajes por pantalla y de leer la entrada del usuario
 * 
 */
public class View {

    /**
     * Muestra un mensaje de error
     * 
     * @param message Mensaje a mostrar
     */
    public void showError(String message) {
        System.out.printf("!ERROR: %s\n", message);
    }

    /**
     * Muestra un mensaje de información
     * 
     * @param message Mensaje a mostrar
     */
    public void showInfo(String message) {
        System.out.printf("*INFO: %s\n", message);
    }

    /**
     * Muestra un mensaje de resultado
     * 
     * @param result Mensaje a mostrar
     */
    public void showResult(String result) {
        System.out.printf("RESULTADO: %s\n", result);
    }

    /**
     * Lee la entrada del usuario y devuelve un objeto Building
     * 
     */
    public String readUserInput() {
        // Declaramos el scanner
        Scanner scanner = new Scanner(System.in);

        showInfo("Introduce el número de filas que tiene tu mapa: ");
        String firstLine = scanner.nextLine();

        showInfo("Introduce el número de columnas que tiene tu mapa: ");
        String secondLine = scanner.nextLine();

        int rows = Integer.parseInt(firstLine);

        String map = "";

        for (int i = 0; i < rows; i++) {
            showInfo("Introduce la fila " + (i + 1) + " de tu mapa: ");
            String line = scanner.nextLine();
            map += line + "\n";
        }

        // Cerramos el scanner
        scanner.close();

        return firstLine + "\n" + secondLine + "\n" + map;
    }

    /**
     * Muestra la ayuda
     */
    public void showHelp() {
        System.out.println("ROBOT");
        System.out.println("SINTAXIS: robot [-t][-h] [fichero entrada] [fichero salida]");
        System.out.println("-t \t Traza el algoritmo");
        System.out.println("-h \t Muestra esta ayuda");
        System.out.println("[fichero entrada] \t Fichero de entrada");
        System.out.println("[fichero salida] \t Fichero de salida");
    }

}
