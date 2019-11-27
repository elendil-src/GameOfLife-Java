package com.elendil.training;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a Universe of cells. Responsible for setting up the initial state, and triggering the next generation.
 */
class Universe {

    private Collection<Cell> universe;
    private Random rnGenerator = new Random();

    /**
     * Constructs two dimensional square universe of cells with a percentage seeded as alive.
     * The location of the living cells is randomly determined; this may result in the actual percentage of
     * seeded living cells differing from 'seedAlivePercent'.
     * @param dimension  dimension of the square
     * @param seedAlivePercent  percentage of the square that should be living cells.
     */
    Universe(int dimension, int seedAlivePercent) {
        // Create universe as collection of dimension*dimension cells. See with living cells randomly at given rate.
        universe = new HashSet<>(dimension ^ 2);
        seedUniverse(dimension, seedAlivePercent);
    }

    /**
     * Create a cell at given coordinates. Use seedAlivePercent and random number generator to determine if cell
     * is living or deead.
     * @param x column coordinate of cell
     * @param y row coordinate of cell
     * @param seedAlivePercent percentage of the square that should be living cells.
     * @return subclass of Cell.
     */
    private Cell createCell(int x, int y, int seedAlivePercent) {
        int rn = rnGenerator.nextInt(99);
        if (rn < seedAlivePercent)
            return new LivingCell(x, y);
        else
            return new DeadCell(x, y);
    }

    /**
     *  Seed the universe
     * @param dimension  dimension of the square
     * @param seedAlivePercent  percentage of the square that should be living cells.
     */
    private void seedUniverse(int dimension, int seedAlivePercent) {
        for (int x = 0; x < dimension; x++) {
            for (int y = 0; y < dimension; y++) {
                universe.add(createCell(x, y, seedAlivePercent));
            }
        }
    }

    /**
     * Return copy of universe; no guarantee about order etc.
     * @return copy of cells in universe; no guarantee about order etc.
     */
    Collection<Cell> getUniverse() {
        return new HashSet<>(universe);
    }

    /**
     * Trigger next generation of universe.
     */
    void nextGeneration() {

        Collection<Cell> nextGeneration = new HashSet<>(universe.size());

        universe.forEach(c -> nextGeneration.add(Universe.calculateNextCellState(c, universe)));
        universe = nextGeneration;
    }

    /**
     * Given a cell, identify its neighbours which are living, then delegate to cell to decide if it lives or dies.
     * @param targetCell cell that is being considered for regeneration
     * @param cellSet all other cells that may or may not be around it.
     * @return the target cell's next state.
     */
    static Cell calculateNextCellState(Cell targetCell, Collection<Cell> cellSet) {

        Collection<Cell> livingNeighbours = cellSet.stream().filter(c -> Cell.isNeighbour(targetCell, c))
                .filter(c -> c instanceof LivingCell)
                .collect(Collectors.toList());

        return targetCell.nextState(livingNeighbours.size());
    }

    /**
     * Sort universe into a two dimensional array of cells.
     * @return Current universe sorted by rows then columns
     */
    List<List<Cell>> toRowAndColumn() {

        List<List<Cell>> listOfRows = new LinkedList<>();

        universe.stream().sorted(Comparator.comparing(Cell::getY).thenComparing(Cell::getX)).forEach(c ->
                {
                    if (listOfRows.size() < c.getY() + 1)
                        listOfRows.add(c.getY(), new LinkedList<>());
                    listOfRows.get(c.getY()).add(c);
                }
        );
        return listOfRows;
    }
}
