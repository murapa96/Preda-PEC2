package org.murapa.robot.model;

/**
 * Clase para representar un elemento de la solución
 * 
 * Un elemento de la solución es una casilla del edificio, representado como
 * unas coordenadas x e y, y un tipo de casilla
 */
public class RouteItem {
    int x;
    int y;

    Tiles tile;

    /**
     * Constructor
     * 
     * @param x    Coordenada x
     * @param y    Coordenada y
     * @param tile Tipo de casilla
     */

    public RouteItem(int x, int y, Tiles tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    /* GETTERS */

    /**
     * Devuelve la coordenada x
     * 
     * @return
     */
    public int getX() {
        return x;
    };

    /**
     * Devuelve la coordenada y
     * 
     * @return
     */

    public int getY() {
        return y;
    };

    /**
     * Devuelve el tipo de casilla
     * 
     * @return
     */

    public Tiles getTile() {
        return tile;
    };

    /* SETTERS */

    /**
     * Establece la coordenada x
     * 
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    };

    /**
     * Establece la coordenada y
     * 
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    };

    /**
     * Establece el tipo de casilla
     * 
     * @param tile
     */
    public void setTile(Tiles tile) {
        this.tile = tile;
    };

    /**
     * Devuelve el elemento de la solución en formato String
     */
    @Override
    public String toString() {
        return String.format("(%d, %d)", x+1, y+1);
    }

}
