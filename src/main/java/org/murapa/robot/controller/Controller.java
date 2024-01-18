package org.murapa.robot.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import org.murapa.robot.model.*;
import org.murapa.robot.view.*;

import java.util.ArrayList;

/**
 * Clase que controla la ejecucion del programa
 *
 */
public class Controller {

    boolean isTraceEnabled = false;

    View view = new View();
    Logic logic;
    Building building;

    String inputFilePath;
    String outputFilePath;

    /**
     * Metodo que ejecuta el programa
     *
     * @param args Argumentos de la linea de comandos
     */
    public void execute(String args[]) {

        // Leemos los argumentos de la linea de comandos
        this.parseArgs(args);

        // Creamos el StringBuilder con los datos de entrada, si no hay fichero de
        // entrada, se leen de la entrada estandar
        StringBuilder inputData = new StringBuilder();

        if (inputFilePath != null) {
            try {
                File inputFile = new File(inputFilePath);
                // Leemos el fichero de entrada
                // Snippet de:
                // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
                Scanner sc = new Scanner(inputFile);
                while (sc.hasNextLine())
                    inputData.append(sc.nextLine() + "\n");
                sc.close();
            } catch (FileNotFoundException e) {
                view.showError("No se ha encontrado el fichero de entrada");
                System.exit(1);
            }
        } else {
            inputData.append(view.readUserInput());
        }

        // Parseamos los datos de entrada
        this.parseInput(inputData.toString());

        if (isTraceEnabled) {
            // Creamos el algoritmo con traza
            view.showInfo("Ejecutando algoritmo con traza");
            logic = new BackTrackTraceLogic(building);
        } else {
            // Creamos el algoritmo sin traza
            view.showInfo("Ejecutando algoritmo sin traza");
            logic = new BackTrackLogic(building);
        }

        // Ejecutamos el algoritmo
        String output = logic.solve();

        if (outputFilePath != null) {
            // Escribimos en el fichero de salida
            File outputFile = new File(outputFilePath);
            try {
                // Snippet de https://www.w3schools.com/java/java_files_create.asp
                outputFile.createNewFile();
                FileWriter writer = new FileWriter(outputFile);
                writer.write(output);
                writer.close();
            } catch (Exception e) {
                view.showError("No se ha podido crear el fichero de salida");
                System.exit(1);
            }
        }
        // Escribimos en la salida estandar
        System.out.println(output);

    }

    /**
     * Metodo que devuelve si esta activada la traza
     *
     * @return boolean isTraceEnabled
     */
    public boolean getisTraceEnabled() {
        return isTraceEnabled;
    }

    /**
     * Metodo que activa o desactiva la traza
     *
     * @param isTraceEnabled
     */
    public void setisTraceEnabled(boolean isTraceEnabled) {
        this.isTraceEnabled = isTraceEnabled;
    }

    /**
     * Metodo que parsea los argumentos de la linea de comandos
     *
     */
    private void parseArgs(String[] args) {

        // Creamos una copia de los argumentos, pero en un ArrayList.
        // El motivo de esto es para ir borrando los argumentos que ya hemos procesado
        // Los dos ultimos argumentos son los ficheros de entrada y salida.
        ArrayList<String> argsList = new ArrayList<String>();

        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-t":
                        isTraceEnabled = true;
                        break;
                    case "-h":
                        view.showHelp();
                        System.exit(0);
                    default:
                        argsList.add(args[i]);
                        break;
                }
            }
        }

        // Si hay mas de dos argumentos, es que hay fichero de entrada y salida
        if (argsList.size() > 1) {
            inputFilePath = argsList.get(argsList.size() - 2);
            outputFilePath = argsList.get(argsList.size() - 1);
        }

        // Si hay un solo argumento, es que es el fichero de entrada
        if (argsList.size() == 1) {
            inputFilePath = argsList.get(argsList.size() - 1);
        }

        // Si no hay argumentos, es que no hay fichero de entrada ni salida
        if (argsList.size() == 0) {
            inputFilePath = null;
            outputFilePath = null;
        }
    }

    /**
     * Metodo que parsea los datos de entrada del usuario, tomados por la View
     *
     */
    private void parseInput(String inputData) {
        // Separamos las lineas en un array de strings
        String[] lines = inputData.split("\n");
        int rows = Integer.parseInt(lines[0]);
        int columns = Integer.parseInt(lines[1]);
        StringBuilder map = new StringBuilder();
        for (int i = 2; i < lines.length; i++) {
            map.append(lines[i] + "\n");
        }
        // Creamos el edificio
        this.building = new Building(rows, columns, map.toString());

        // Parseamos la cantidad a cambiar
    }

}
