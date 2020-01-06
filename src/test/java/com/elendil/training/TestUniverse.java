package com.elendil.training;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TestUniverse {

    @Test
    public void givenUniverseSetup_whenDimOne_thenUniverseSizeIsOne() {
        Universe universe = new Universe(1, 0);
        Collection<Cell> resultUniverse = universe.getUniverse();
        Assert.assertTrue((resultUniverse.size() == 1));
    }

    @Test
    public void givenUniverseSetup_whenDimTwo_thenUniverseSizeIsFour() {
        Universe universe = new Universe(2, 0);
        Collection<Cell> resultUniverse = universe.getUniverse();
        Assert.assertTrue((resultUniverse.size() == 4));
    }

    @Test
    public void givenUniverseSetup_whenDimThree_thenUniverseSizeIsNine() {
        Universe universe = new Universe(3, 0);
        Collection<Cell> resultUniverse = universe.getUniverse();
        Assert.assertTrue((resultUniverse.size() == 9));
    }

    @Test
    public void givenUniverseSetup_when0PercentSeedAndDimOne_thenUniverse0PercentAlive() {
        Universe universe = new Universe(1, 0);
        Collection<Cell> resultUniverse = universe.getUniverse();
        Assert.assertTrue(resultUniverse.stream().allMatch(c -> c instanceof DeadCell));
    }

    @Test
    public void givenUniverseSetup_when100PercentSeedAndDimOne_thenUniverse100PercentAlive() {
        Universe universe = new Universe(1, 100);
        Collection<Cell> resultUniverse = universe.getUniverse();
        Assert.assertTrue(resultUniverse.stream().allMatch(c -> c instanceof LivingCell));
    }

    @Test
    public void givenUniverseSetup_when100PercentSeedAndDimNine_thenUniverse100PercentAlive() {
        Universe universe = new Universe(9, 100);
        Set<Cell> resultUniverse = universe.getUniverse();
        Assert.assertTrue(resultUniverse.stream().allMatch(c -> c instanceof LivingCell));
    }

    @Test
    public void givenLivingCellInUniverse_whenZeroNeighbours_thenCellDies() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new DeadCell(0, 0), new DeadCell(1, 0), new DeadCell(2, 0),
                new DeadCell(0, 1),                         new DeadCell(2, 1),
                new DeadCell(0, 2), new DeadCell(1, 2), new DeadCell(2, 2)
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell initialCell = new LivingCell(1, 1);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new DeadCell(1, 1));
    }

    @Test
    public void givenLivingCellInUniverse_whenOneNeighbours_thenCellDies() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new LivingCell(0, 0), new DeadCell(1, 0), new DeadCell(2, 0),
                new DeadCell(0, 1), new DeadCell(2, 1),
                new DeadCell(0, 2), new DeadCell(1, 2), new DeadCell(2, 2)
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell initialCell = new LivingCell(1, 1);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new DeadCell(1, 1));
    }

    @Test
    public void givenLivingCellInUniverse_whenFourNeighbours_thenCellDies() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new LivingCell(0, 0), new LivingCell(1, 0), new DeadCell(2, 0),
                new DeadCell(0, 1), new DeadCell(2, 1),
                new DeadCell(0, 2), new LivingCell(1, 2), new LivingCell(2, 2)
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell initialCell = new LivingCell(1, 1);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new DeadCell(1, 1));
    }

    @Test
    public void givenLivingCellInUniverse_whenTwoNeighbours_thenCellLives() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new LivingCell(0, 0), new LivingCell(1, 0), new DeadCell(2, 0),
                new DeadCell(0, 1), new DeadCell(2, 1),
                new DeadCell(0, 2), new DeadCell(1, 2), new DeadCell(2, 2)
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell initialCell = new LivingCell(1, 1);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new LivingCell(1, 1));
    }

    @Test
    public void givenLivingCellInUniverse_whenThreeNeighbours_thenCellLives() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new LivingCell(0, 0), new LivingCell(1, 0), new LivingCell(2, 0),
                new DeadCell(0, 1), new DeadCell(2, 1),
                new DeadCell(0, 2), new DeadCell(1, 2), new DeadCell(2, 2)
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell initialCell = new LivingCell(1, 1);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new LivingCell(1, 1));
    }

    @Test
    public void givenDeadCellInUniverse_whenThreeLivingNeighbours_thenCellLives() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new LivingCell(0, 0), new LivingCell(1, 0), new LivingCell(2, 0),
                new DeadCell(0, 1), new DeadCell(2, 1),
                new DeadCell(0, 2), new DeadCell(1, 2), new DeadCell(2, 2)
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell initialCell = new DeadCell(1, 1);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new LivingCell(1, 1));
    }

    @Test
    public void givenDeadCellInUniverse_whenTwoLivingNeighbours_thenCellDies() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new LivingCell(0, 0), new DeadCell(1, 0), new LivingCell(2, 0),
                new DeadCell(0, 1), new DeadCell(2, 1),
                new DeadCell(0, 2), new DeadCell(1, 2), new DeadCell(2, 2)
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell initialCell = new DeadCell(1, 1);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new DeadCell(1, 1));
    }

    @Test
    public void givenDeadCellInUniverse_whenFourLivingNeighbours_thenCellDies() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new LivingCell(3, 3), new LivingCell(3, 4), new LivingCell(3, 5),
                new DeadCell(4, 3), new DeadCell(4, 5),
                new DeadCell(5, 3), new DeadCell(5, 4), new LivingCell(5, 5),
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell initialCell = new DeadCell(4, 4);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new DeadCell(4, 4));
    }

    @Test
    public void givenDeadCellInWiderUniverse_whenThreeNeighbours_thenCellLives() {
        Collection<Cell> neighbouringCells = new HashSet<>(16);
        Cell initialCell = new DeadCell(4, 4);
        Cell[] c = {new LivingCell(3, 3), new LivingCell(3, 4), new LivingCell(3, 5), new LivingCell(3, 6),
                new DeadCell(4, 3), initialCell, new DeadCell(4, 5), new LivingCell(4, 6),
                new DeadCell(5, 3), new DeadCell(5, 4), new DeadCell(5, 5), new LivingCell(5, 6),
                new DeadCell(6, 3), new DeadCell(6, 4), new LivingCell(6, 5), new LivingCell(7, 6),
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new LivingCell(4, 4));
    }

    @Test
    public void givenLivingCellInWiderUniverse_whenThreeNeighbours_thenCellLives() {
        Collection<Cell> neighbouringCells = new HashSet<>(16);
        Cell initialCell = new DeadCell(4, 4);
        Cell[] c = {new LivingCell(3, 3), new LivingCell(3, 4), new LivingCell(3, 5), new LivingCell(3, 6),
                new DeadCell(4, 3), initialCell, new DeadCell(4, 5), new LivingCell(4, 6),
                new DeadCell(5, 3), new DeadCell(5, 4), new DeadCell(5, 5), new LivingCell(5, 6),
                new DeadCell(6, 3), new DeadCell(6, 4), new LivingCell(6, 5), new LivingCell(7, 6),
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new LivingCell(initialCell.getX(), initialCell.getY()));
    }

    @Test
    public void givenLivingCellInWiderUniverse_whenTwoNeighbours_thenCellLives() {
        Collection<Cell> neighbouringCells = new HashSet<>(16);
        Cell initialCell = new LivingCell(4, 4);
        Cell[] c = {new DeadCell(3, 3), new LivingCell(3, 4), new LivingCell(3, 5), new LivingCell(3, 6),
                new DeadCell(4, 3), initialCell, new DeadCell(4, 5), new LivingCell(4, 6),
                new DeadCell(5, 3), new DeadCell(5, 4), new DeadCell(5, 5), new LivingCell(5, 6),
                new DeadCell(6, 3), new DeadCell(6, 4), new LivingCell(6, 5), new LivingCell(7, 6),
        };
        neighbouringCells.addAll(Arrays.asList(c));

        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        Assert.assertEquals(nextCell, new LivingCell(initialCell.getX(), initialCell.getY()));
    }

    @Test
    public void givenUniverse_whenCellIsAdjacent_thenIdentifyAsNeighbour() {
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(0, 0)));
        Assert.assertTrue(Cell.isNeighbour(new DeadCell(1, 1), new DeadCell(0, 1)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(1, 1), new DeadCell(0, 2)));
        Assert.assertTrue(Cell.isNeighbour(new DeadCell(1, 1), new LivingCell(1, 0)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(1, 2)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(2, 0)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(2, 1)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(2, 2)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(3, 3), new LivingCell(2, 2)));
        Assert.assertTrue(Cell.isNeighbour(new DeadCell(3, 3), new DeadCell(2, 3)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(3, 3), new LivingCell(3, 2)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(3, 3), new LivingCell(3, 4)));
        Assert.assertTrue(Cell.isNeighbour(new DeadCell(0, 0), new DeadCell(0, 1)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(0, 0), new LivingCell(1, 1)));
        Assert.assertTrue(Cell.isNeighbour(new LivingCell(0, 0), new LivingCell(1, 0)));
    }

    @Test
    public void givenUniverse_whenCellIsSameCoord_thenIdentifyAsNotNeighbour() {
        Assert.assertFalse(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(1, 1)));
        Assert.assertFalse(Cell.isNeighbour(new LivingCell(0, 0), new LivingCell(0, 0)));
        Assert.assertFalse(Cell.isNeighbour(new LivingCell(9, 9), new LivingCell(9, 9)));
    }

    @Test
    public void givenUniverse_whenCellIsNotAdjacent_thenIdentifyAsNotNeighbour() {

        Assert.assertFalse(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(3, 1)));
        Assert.assertFalse(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(0, 3)));
        Assert.assertFalse(Cell.isNeighbour(new LivingCell(1, 1), new LivingCell(3, 3)));
        Assert.assertFalse(Cell.isNeighbour(new LivingCell(0, 0), new LivingCell(2, 2)));
        Assert.assertFalse(Cell.isNeighbour(new LivingCell(0, 6), new LivingCell(4, 6)));
    }

    @Test
    public void givenUniverse_whenDimensionIsOne_thenReturnsOneRowAndOneColumn() {
        Universe universe = new Universe(1, 100);
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();
        Assert.assertEquals(rowAndCol.get(0).get(0), new LivingCell(0, 0));
        Assert.assertTrue(rowAndCol.size() == 1 && rowAndCol.get(0).size() == 1);
    }

    @Test
    public void givenUniverse_whenDimensionIsTwo_thenReturnsTwoRowAndTwoColumn() {
        Universe universe = new Universe(2, 0);
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();
        Assert.assertEquals(rowAndCol.get(0).get(0), new DeadCell(0, 0));
        Assert.assertEquals(rowAndCol.get(0).get(1), new DeadCell(1, 0));
        Assert.assertEquals(rowAndCol.get(1).get(0), new DeadCell(0, 1));
        Assert.assertEquals(rowAndCol.get(1).get(1), new DeadCell(1, 1));
        Assert.assertTrue(rowAndCol.size() == 2 && rowAndCol.get(0).size() == 2);
    }

    @Test
    public void givenUniverse_whenDimensionIsTen_thenReturnsTenRowAndTenColumn() {
        Universe universe = new Universe(10, 0);
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();
        Assert.assertTrue(rowAndCol.size() == 10 && rowAndCol.get(0).size() == 10);
    }

    @Test
    public void givenOneByOneFullAliveUniverse_whenNextGeneration_thenAllDead()
    {
        Universe universe = new Universe(1, 0);
        universe.nextGeneration();
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();
        Assert.assertTrue(rowAndCol.get(0).get(0) instanceof DeadCell);
    }

    @Test
    public void givenTwoByTwoDeadUniverse_whenNextGeneration_thenAllAlive()
    {
        Universe universe = new Universe(2, 100);
        universe.nextGeneration();
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();
        Assert.assertTrue(rowAndCol.get(0).get(0) instanceof LivingCell);
        Assert.assertTrue(rowAndCol.get(0).get(1) instanceof LivingCell);
        Assert.assertTrue(rowAndCol.get(1).get(0) instanceof LivingCell);
        Assert.assertTrue(rowAndCol.get(1).get(1) instanceof LivingCell);
    }

    @Test
    public void givenThreeByThreeAliveUniverse_whenNextGeneration_thenFourAlive()
    {
        //  A A A
        //  A A A
        //  A A A
        //   next generation
        //  A D A
        //  D D D
        //  A D A
        Universe universe = new Universe(3, 100);
        universe.nextGeneration();
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();

        Assert.assertTrue(rowAndCol.get(0).get(0) instanceof LivingCell);
        Assert.assertTrue(rowAndCol.get(0).get(2) instanceof LivingCell);
        Assert.assertTrue(rowAndCol.get(2).get(0) instanceof LivingCell);
        Assert.assertTrue(rowAndCol.get(2).get(2) instanceof LivingCell);

        Assert.assertTrue(rowAndCol.get(0).get(1) instanceof DeadCell);
        Assert.assertTrue(rowAndCol.get(1).get(0) instanceof DeadCell);
        Assert.assertTrue(rowAndCol.get(1).get(1) instanceof DeadCell);
        Assert.assertTrue(rowAndCol.get(1).get(2) instanceof DeadCell);
        Assert.assertTrue(rowAndCol.get(2).get(1) instanceof DeadCell);
    }
}
