package com.elendil.training;

import java.util.Objects;

/**
 * Represents a living cell, is a subclass of Cell.
 */
public class LivingCell extends Cell {

    /**
     * Constructs living cell
     * @param x column coordinate
     * @param y row coordinate
     */
    LivingCell(int x, int y) {
        super(x, y);
    }

    /**
     * Implements Conway's Game Of Live rule for living cells.
     */
    @Override
    public Cell nextState(int livingNeighbourCount) {
        if (livingNeighbourCount == 2 || livingNeighbourCount == 3)
            return new LivingCell(this.getX(), this.getY());
        else
            return new DeadCell(this.getX(), this.getY());
    }

    /**
     * Implements equals functionality for object - equality determined by coordinates in universe.
     */
    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (!(obj instanceof LivingCell)) return false;

        LivingCell other = (LivingCell) obj;
        return (this.getX() == other.getX() && this.getY() == other.getY());
    }

    /**
     * Implements hashcode functionality so cells that are equal will also have the same hashcode.
     * @return integer that is unique for this instance of this class and any equal to it.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getClass());
    }
}
