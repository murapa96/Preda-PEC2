package org.murapa.robot;

import org.murapa.robot.controller.Controller;


public class Main {
    public static void main(String[] args) {
        //Creamos un nuevo controlador y le pasamos los argumentos.
        Controller controller = new Controller();
        controller.execute(args);
    }
}