package org.murapa.robot.model;

/**
 * Enumerado para los tipos de casillas
 * 
 * LIBRE: Libre
 * ESTRECHO: Paso Estrecho (Sirve de obstaculo al robot)
 * TORNILLO: Tornillo (Objetivo)
 */
public enum Tiles {
    LIBRE, ESTRECHO, TORNILLO;

    /**
     * Devuelve el tipo de casilla a partir de un caracter
     * 
     * @param c
     * @return
     */
    public static Tiles getTile(char c) {
        switch (c) {
            case 'L':
                return Tiles.LIBRE;
            case 'E':
                return Tiles.ESTRECHO;
            case 'T':
                return Tiles.TORNILLO;
            default:
                return null;
        }
    }

    /**
     * Devuelve el caracter a partir de un tipo de casilla
     * 
     * @param t
     * @return
     */
    public static char getChar(Tiles t) {
        switch (t) {
            case LIBRE:
                return 'L';
            case ESTRECHO:
                return 'E';
            case TORNILLO:
                return 'T';
            default:
                return ' ';
        }
    }

}
