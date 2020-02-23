package com.elendil.training;

import java.io.PrintStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * High level controller for Conway's Game of Life
 * https://www.conwaylife.com/wiki/Conway's_Game_of_Life
 * Automatically seeds a universe, and then displays generations sequentially when prompted by the user.
 */
public class GameOfLife {

    /**
     * Controls game
     * @param args none at present.
     */
    public static void main(String[] args) {

        int universeDimension = 8; // Default - TODO  - make command line arg
        int seedAlivePercent = 50; // Default - TODO  - make command line arg


        Random rnGenerator = new Random();
        CellSupplier cellSupplier = new CellSupplier(universeDimension, seedAlivePercent, rnGenerator);

        Universe universe = new Universe(universeDimension,cellSupplier);

        System.out.printf("Game of life: dimension=%d; seeded=%d percent.%n", universeDimension, seedAlivePercent);

        evolveUniverse(universe);
    }

    /**
     * Evolves universe, handling user input
     * @param universe universe to evolve
     */
    private static void evolveUniverse(Universe universe) {
        final String COMMAND_DESC = "'Enter' to view next generation, or anything else to quit.";

        Scanner scanner = new Scanner(System.in);
        int generationCount = 0;
        do {
            System.out.printf("Generation:%d: " + COMMAND_DESC + "\n", generationCount++);
            outputUniverse(universe, System.out);

            if (userContinue(scanner))
                universe.nextGeneration();
            else break;
        } while (true);
        System.out.println("Now exited");
    }

    private static boolean userContinue(Scanner scanner) {
        return scanner.nextLine().length() == 0;
    }

    /**
     * Outputs universe's current state to output stream. Prints as grid.
     * @param universe to be displayed
     * @param os output stream to be displayed to.
     */
    private static void outputUniverse(Universe universe, PrintStream os) {

        List<List<Cell>> universeByRowAndCol = universe.toRowAndColumn();
        universeByRowAndCol.forEach( rowList -> outputRow(os, rowList));
    }

    /**
     * Output given row.. '*' for living cell; space for dead cell.
     * @param os output stream
     * @param rowList row of cells to output
     */
    private static void outputRow(PrintStream os, List<Cell> rowList) {
        rowList.forEach(cell -> os.print((cell instanceof LivingCell ? '*' : ' ')));
        os.println();
    }
}
