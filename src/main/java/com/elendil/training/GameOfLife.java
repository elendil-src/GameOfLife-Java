package com.elendil.training;

import java.io.PrintStream;
import java.util.List;
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

        int universeDimension = 8; // Default
        int seedAlivePercent = 50; // Default

        Universe universe = new Universe(universeDimension, seedAlivePercent);

        boolean doNext = true;
        System.out.printf("Game of life: dimension=%d; seeded=%d percent.\n" +
                        "Enter 'Enter' to view next generation, or anything else to quit.\n",
                universeDimension, seedAlivePercent);

        Scanner scanner = new Scanner(System.in);
        int generationCount = 0;
        do {
            System.out.printf("Generation:%d\n", generationCount++);
            outputUniverse(universe, System.out);

            String input = scanner.nextLine();
            if (input.length() != 0)
                doNext = false;
            else
                universe.nextGeneration();
        } while (doNext);
        System.out.println("Now exited");
    }

    /**
     * Outputs universe's current state to output stream.
     * @param universe to be displayed
     * @param os output stream to be displayed to.
     */
    private static void outputUniverse(Universe universe, PrintStream os) {

        List<List<Cell>> universeByRowAndCol = universe.toRowAndColumn();

        universeByRowAndCol.forEach(
                rowList ->
                {
                    rowList.forEach(cell -> os.print((cell instanceof LivingCell ? '*' : ' ')));
                    os.println();
                }
        );
    }
}
