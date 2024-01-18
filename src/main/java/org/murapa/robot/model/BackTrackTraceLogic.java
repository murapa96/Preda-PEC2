package org.murapa.robot.model;

import java.util.ArrayList;

public class BackTrackTraceLogic implements Logic {

    // Trace
    StringBuilder trace = new StringBuilder();
    Building building;

    /**
     * Constructor
     * 
     */
    public BackTrackTraceLogic() {

    }

    /**
     * Constructor
     * 
     * @param building Edificio donde buscar el tornillo
     */
    public BackTrackTraceLogic(Building building) {
        this.building = building;
    }

    public String getTrace() {
        return trace.toString();
    }

    @Override
    public String solve() {
        ArrayList<RouteItem> solution = new ArrayList<RouteItem>();
        StringBuilder returnString = new StringBuilder();
        // Iniciamos posicion inicial en 0,0
        RouteItem pos = new RouteItem(0, 0, this.building.getTile(0, 0));
        // Guardamos microsegundos antes
        long startTime = System.nanoTime();
        // Buscamos el tornillo
        boolean found = searchScrew(pos, solution);
        // Guardamos microsegundos después
        long endTime = System.nanoTime();
        // Calculamos el tiempo en milisegundos
        long timeElapsed = (endTime - startTime) / 1000000;
        returnString.append("/********* EMPIEZA TRAZA *********/\n");
        returnString.append(trace.toString());
        returnString.append("/********* TERMINA TRAZA *********/\n");
        returnString.append("Tiempo: " + timeElapsed + "ms\n");

        if (found) {
            // Recorremos la solución
            for (int i = 0; i < solution.size(); i++) {
                // Añadimos a la traza
                returnString.append(solution.get(i)+"\n");
            }
        } else {
            // Si no hemos encontrado la solución
            returnString.append("No se encontró un camino hasta el tornillo");
        }
        // Añadimos el tiempo
        return returnString.toString();
    }

    /**
     * Devuelve el edificio
     * 
     * @return
     */
    public Building getBuilding() {
        return building;
    }

    /**
     * Establece el edificio
     * 
     * @param building
     */
    public void setBuilding(Building building) {
        this.building = building;
    }

    private boolean searchScrew(RouteItem pos, ArrayList<RouteItem> solution) {
        boolean found = false;
        trace.append("?TRAZA: Buscando en: " + pos + "\n");
        this.building.setVisited(pos.getX(), pos.getY(), true);
        if (pos.getTile() == Tiles.TORNILLO) {
            trace.append("?TRAZA: Encontrado en: " + pos + "\n");
            found = true;
            solution.add(pos);
        } else {

            ArrayList<RouteItem> routes = routes(pos);
            found = false;
            while (!found && routes.size() > 0) {
                RouteItem next = routes.remove(0);
                if (!this.building.isVisited(next.getX(), next.getY())) {
                    found = searchScrew(next, solution);
                }
            }
            if (found) {
                trace.append("?TRAZA: Añadido a la solución: " + pos + "\n");
                solution.add(pos);
            }
        }
        return found;

    }

    private ArrayList<RouteItem> routes(RouteItem pos) {
        trace.append("?TRAZA: Calculando rutas para: " + pos + "\n");
        ArrayList<RouteItem> routes = new ArrayList<RouteItem>();
        if (pos.getX() + 1 < this.building.getRows()) {
            if (this.building.getTile(pos.getX() + 1, pos.getY()) != Tiles.ESTRECHO) {
                trace.append("?TRAZA: Ruta encontrada: " + pos + " -> "
                        + new RouteItem(pos.getX() + 1, pos.getY(), this.building.getTile(pos.getX() + 1, pos.getY()))
                        + "\n");
                routes.add(
                        new RouteItem(pos.getX() + 1, pos.getY(), this.building.getTile(pos.getX() + 1, pos.getY())));

            }
        }

        if (pos.getX() - 1 >= 0) {
            if (this.building.getTile(pos.getX() - 1, pos.getY()) != Tiles.ESTRECHO) {
                trace.append("?TRAZA: Ruta encontrada: " + pos + " -> "
                        + new RouteItem(pos.getX() - 1, pos.getY(), this.building.getTile(pos.getX() - 1, pos.getY()))
                        + "\n");
                routes.add(
                        new RouteItem(pos.getX() - 1, pos.getY(), this.building.getTile(pos.getX() - 1, pos.getY())));

            }
        }

        if (pos.getY() + 1 < this.building.getColumns()) {
            if (this.building.getTile(pos.getX(), pos.getY() + 1) != Tiles.ESTRECHO) {
                trace.append("?TRAZA: Ruta encontrada: " + pos + " -> "
                        + new RouteItem(pos.getX(), pos.getY() + 1, this.building.getTile(pos.getX(), pos.getY() + 1))
                        + "\n");
                routes.add(
                        new RouteItem(pos.getX(), pos.getY() + 1, this.building.getTile(pos.getX(), pos.getY() + 1)));

            }
        }

        if (pos.getY() - 1 >= 0) {
            if (this.building.getTile(pos.getX(), pos.getY() - 1) != Tiles.ESTRECHO) {
                trace.append("?TRAZA: Ruta encontrada: " + pos + " -> "
                        + new RouteItem(pos.getX(), pos.getY() - 1, this.building.getTile(pos.getX(), pos.getY() - 1))
                        + "\n");
                routes.add(
                        new RouteItem(pos.getX(), pos.getY() - 1, this.building.getTile(pos.getX(), pos.getY() - 1)));

            }
        }

        return routes;
    }
}
