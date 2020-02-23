package com.elendil.training;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a Universe of cells. Responsible for setting up the initial state, and triggering the
 * next generation.
 */
class Universe {

    private List<Cell> universeOfCells;
    private Supplier<Cell> cellSupplier;
    private final int dimension;

    /**
     * Constructs two dimensional square universe of cells with a percentage seeded as alive.
     * The location of the living cells is randomly determined; this may result in the actual percentage of
     * seeded living cells differing from 'seedAlivePercent'.
     * @param dimension  dimension of the square
     * @param cellSupplier  supplier of cells, controlling percentage of the initial universe that should be living cells.
     */
    Universe(int dimension, Supplier<Cell> cellSupplier) {
        this.cellSupplier = cellSupplier;
        this.dimension = dimension;

        // Create universe as collection of dimension*dimension cells. Seed with living cells randomly at given rate.
        universeOfCells = new ArrayList<>(dimension ^ 2);
        seedUniverse();
    }


    /**
     *  Seed the universe
     */
    private void seedUniverse() {

        this.universeOfCells = Stream.generate(cellSupplier)
                        .takeWhile( c -> c.getY() < dimension && c.getX() < dimension)
                        .collect(Collectors.toList());
    }

    /**
     * Return copy of universe; no guarantee about order etc.
     * @return immutable set of (mutable) cells in universe; no guarantee about order etc.
     */
    List<Cell> getUniverseOfCells() {
        return Collections.unmodifiableList(universeOfCells);
    }

    /**
     * Trigger next generation of universe.
     */
    void nextGeneration() {

        List<Cell> nextGeneration = new ArrayList<>(universeOfCells.size());

        universeOfCells.forEach(c -> nextGeneration.add(Universe.calculateNextCellState(c, universeOfCells)));
        universeOfCells = nextGeneration;
    }

    /**
     * Given a cell, identify its neighbours which are living, then delegate to cell to decide if it lives or dies.
     * @param targetCell cell that is being considered for regeneration
     * @param cellSet all other cells that may or may not be around it.
     * @return the target cell's next state.
     */
    static Cell calculateNextCellState(Cell targetCell, Collection<Cell> cellSet) {

        Collection<Cell> livingNeighbours = cellSet.stream().filter(c -> c.isNeighbour(targetCell))
                .filter(c -> c instanceof LivingCell)
                .collect(Collectors.toList());

        return targetCell.nextState(livingNeighbours.size());
    }

    /**
     * Sort universe into a two dimensional array of cells.
     * @return  copy of two dimensional array of universe sorted by rows then columns
     */
    List<List<Cell>> toRowAndColumn() {

        List<List<Cell>> listOfRows = new LinkedList<>();

        universeOfCells.stream().sorted(Comparator.comparing(Cell::getY).thenComparing(Cell::getX)).forEach(c ->
                {
                    if (listOfRows.size() < c.getY() + 1)
                        listOfRows.add(c.getY(), new LinkedList<>());
                    listOfRows.get(c.getY()).add(c);
                }
        );
        return listOfRows;
    }
}