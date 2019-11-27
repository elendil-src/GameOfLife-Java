package com.elendil.training;

import java.util.Objects;

/**
 * Implements DeadCell as subclass of cell
 */
public class DeadCell extends Cell {

    /**
     * Constructs instance of class
     * @param x column no
     * @param y row no
     */
    DeadCell(int x, int y) {
        super(x, y);
    }

    /**
     * Implements Conway's GameOfLife rules for a dead cell.
     */
    @Override
    public Cell nextState(int livingNeighbourCount) {
        if (livingNeighbourCount == 3)
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

        if (!(obj instanceof DeadCell)) return false;

        DeadCell other = (DeadCell) obj;
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
