package org.murapa.robot.model;

/**
 * Clase que representa el edificio
 *
 */
public class Building {

    int rows;
    int columns;

    Tiles map[][];
    boolean visited[][];

    /***
     * Constructor
     * 
     * @param rows    Número de filas
     * @param columns Número de columnas
     */

    public Building(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Tiles[rows][columns];
        this.visited = new boolean[rows][columns];

    }

    /***
     * Constructor
     * 
     * @param rows    Número de filas
     * @param columns Número de columnas
     * @param map     Mapa, en formato String
     */
    public Building(int rows, int columns, String map) {
        this.rows = rows;
        this.columns = columns;
        this.map = new Tiles[rows][columns];
        this.visited = new boolean[rows][columns];

        this.setMap(map);
    }

    /* SETTERS */

    /**
     * Establece el numero de filas
     * 
     * @param rows Número de filas
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Establece el número de columnas
     * 
     * @param columns Número de columnas
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Establece el valor de una casilla
     * 
     * @param x     Coordenada x
     * @param y     Coordenada y
     * @param value Valor de la casilla
     */
    public void setVisited(int x, int y, boolean value) {
        if (x < 0 || x >= this.rows || y < 0 || y >= this.columns) {
            throw new IllegalArgumentException("Las coordenadas no están dentro del mapa");
        }
        this.visited[x][y] = value;
    }

    /**
     * Establece el mapa
     * 
     * @param map Mapa, en formato String
     */
    public void setMap(String map) {
        String[] lines = map.split("\n");

        if (lines.length != this.rows) {
            throw new IllegalArgumentException("El número de filas no es correcto");
        }

        for (int i = 0; i < lines.length; i++) {
            String[] rowTiles = lines[i].split(" ");

            if (rowTiles.length != this.columns) {
                throw new IllegalArgumentException("El número de columnas no es correcto");
            }

            for (int j = 0; j < rowTiles.length; j++) {
                char c = rowTiles[j].charAt(0);

                if (Tiles.getTile(c) == null) {
                    throw new IllegalArgumentException("El caracter " + c + " no es un tipo de casilla conocido");
                }

                this.map[i][j] = Tiles.getTile(c);
                this.visited[i][j] = false;
            }
        }
    }

    /**
     * Establece el valor de una casilla determinada del mapa
     * 
     * @param x    Coordenada x
     * @param y    Coordenada y
     * @param tile Tipo de casilla
     */
    public void setTile(int x, int y, Tiles tile) {
        if (x < 0 || x >= this.rows || y < 0 || y >= this.columns) {
            throw new IllegalArgumentException("Las coordenadas no están dentro del mapa");
        }
        this.map[x][y] = tile;
    }

    /* GETTERS */

    /**
     * Devuelve el mapa, en formato String
     * 
     * @return Mapa, en formato String
     */
    public String getMap() {
        String map = "";

        for (int i = 0; i < this.rows; i++) {
            String line = "";

            for (int j = 0; j < this.columns; j++) {
                line += this.map[i][j];
            }

            map += line + "\n";
        }

        return map;
    }

    /**
     * Devuelve el mapa, en formato Tiles[][]
     * 
     * @return Mapa, en formato Tiles[][]
     */
    public Tiles[][] getMapTiles() {
        return this.map;
    }

    /**
     * Devuelve el número de filas
     * 
     * @return Número de filas
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * Devuelve el número de columnas
     * 
     * @return Número de columnas
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * Devuelve el valor de una casilla
     * 
     * @param x Coordenada x
     * @param y Coordenada y
     * @return Valor de la casilla
     */
    public boolean isVisited(int x, int y) {
        if (x < 0 || x >= this.rows || y < 0 || y >= this.columns) {
            throw new IllegalArgumentException("Las coordenadas no están dentro del mapa");
        }
        return this.visited[x][y];
    }

    /**
     * Devuelve el valor de una casilla
     * 
     * @param x Coordenada x
     * @param y Coordenada y
     * @return Valor de la casilla
     */

    public Tiles getTile(int x, int y) {
        if (x < 0 || x >= this.rows || y < 0 || y >= this.columns) {
            throw new IllegalArgumentException("Las coordenadas no están dentro del mapa");
        }
        return this.map[x][y];
    };

    /**
     * Prepara el edificio para ser impreso por pantalla.
     * 
     * 
     * @return Edificio formateado para ser impreso por pantalla
     */

    @Override
    public String toString() {
        String building = "";

        building += this.rows + "\n";
        building += this.columns + "\n\n";

        building += this.getMap();

        return building;
    }

}
