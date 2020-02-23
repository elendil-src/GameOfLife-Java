package com.elendil.training;

import java.util.Random;
import java.util.function.Supplier;

/**
 * Class to hold the state of the generation, specifically the row/col nos.
 * TODO Implementing the supplier is a bit heavy weight given that it needs state, but regard it as more readable
 * TODO than a two element array to break the immutability rule.
 */
class CellSupplier implements Supplier<Cell> {
    private final int dimension;
    private final int seedAlivePercent;
    private Random rnGenerator ;
    private int count = 0;

    /**
     * Creates living/dead cell randomly controlled by seedAlivePercent using  random number
     * @param seedAlivePercent percentage of the square that should be living cells.
     */
    CellSupplier(int dimension, int seedAlivePercent, Random rnGenerator) {
        this.dimension = dimension;
        this.seedAlivePercent = seedAlivePercent;
        this.rnGenerator = rnGenerator;
    }

    /**
     * Create a cell at given coordinates. Use seedAlivePercent and random number generator to determine if cell
     * is living or dead.
     * @param x column coordinate of cell
     * @param y row coordinate of cell
     * @return subclass of Cell.
     */
    private Cell createCell(int x, int y) {
        int rn = rnGenerator.nextInt(99);
        if (rn < seedAlivePercent)
            return new LivingCell(x, y);
        else
            return new DeadCell(x, y);
    }

    @Override
    public Cell get() {
        Cell c = createCell(count % dimension, count / dimension);
        count++;
        return c;
    }
}
