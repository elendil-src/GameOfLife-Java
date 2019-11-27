package com.elendil.training;

/**
 * Abstract class representing a cell. It has coordinates (so its neighbours can be determined) and rules
 * about its next state.
 */
public abstract class Cell {
    private int x;
    private int y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    /**
     * Calculate next state based on current state and number of living neighbours, as per Conway's Game Of Life.
     * @param livingNeighbourCount number of living, immediate neighbours (including diagonals)
     * @return subclass of  Cell representing next state.
     */
    abstract public Cell nextState(int livingNeighbourCount);

    /**
     * Determins if a potential neighbour is an immediate neighbour of the given cell. Has to cope with corners/edges etc.
     * @param me current cell being determined
     * @param potentialNeighbour possible nighbour cell
     * @return true if immediate nieghbour, false if not.
     */
    static boolean isNeighbour(Cell me, Cell potentialNeighbour) {
        int x = me.getX();
        int y = me.getY();

        return (potentialNeighbour.getX() == (x - 1) || potentialNeighbour.getX() == x || potentialNeighbour.getX() == (x + 1))
                && (potentialNeighbour.getY() == (y - 1) || potentialNeighbour.getY() == y || potentialNeighbour.getY() == (y + 1))
                && !(potentialNeighbour.getX() == x && potentialNeighbour.getY() == y);
    }
}
