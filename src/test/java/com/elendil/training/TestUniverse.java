package com.elendil.training;

//import org.junit.Assert;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.*;

public class TestUniverse {

    private CellSupplier supplier = mock(CellSupplier.class);


    @Test
    public void givenUniverseSetup_whenDimOne_thenUniverseSizeIsOne() {

        when(supplier.get()).thenReturn(new LivingCell(0,0))
                            .thenReturn(new LivingCell(1,0));

        Universe universe = new Universe(1, supplier);

        Collection<Cell> resultUniverse = universe.getUniverseOfCells();
        assertTrue((resultUniverse.size() == 1));
    }


    @Test
    public void givenUniverseSetup_whenDimTwo_thenUniverseSizeIsFour() {

        when(supplier.get()).thenReturn(new LivingCell(0,0))
                .thenReturn(new LivingCell(1,0))
                .thenReturn(new LivingCell(0,1))
                .thenReturn(new LivingCell(1,1))
                .thenReturn(new LivingCell(0,2));

        Universe universe = new Universe(2, supplier);

        Collection<Cell> resultUniverse = universe.getUniverseOfCells();
        assertTrue((resultUniverse.size() == 4));
    }


@Test
public void givenUniverseSetup_whenDimThree_thenUniverseSizeIsNine() {

    when(supplier.get()).thenReturn(new LivingCell(0,0))
            .thenReturn(new LivingCell(1,0))
            .thenReturn(new LivingCell(2,0))
            .thenReturn(new LivingCell(0,1))
            .thenReturn(new LivingCell(1,1))
            .thenReturn(new LivingCell(2,1))
            .thenReturn(new LivingCell(0,2))
            .thenReturn(new LivingCell(1,2))
            .thenReturn(new LivingCell(2,2))
            .thenReturn(new LivingCell(0,3));

    Universe universe = new Universe(3, supplier);

    Collection<Cell> resultUniverse = universe.getUniverseOfCells();
    assertTrue((resultUniverse.size() == 9));
}


    @Test
    public void givenUniverseSetup_when0PercentSeedAndDimOne_thenUniverse0PercentAlive() {

        CellSupplier supplier = new CellSupplier(1,0, new Random());
        Universe universe = new Universe(1, supplier);

        Collection<Cell> resultUniverse = universe.getUniverseOfCells();
        assertTrue(resultUniverse.stream().allMatch(c -> c instanceof DeadCell));
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

        //TODO - investigate if mocking can help here.

        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new DeadCell(1, 1));
    }

    @Test
    public void givenLivingCellInUniverse_whenOneNeighbours_thenCellDies() {
        Collection<Cell> neighbouringCells = new HashSet<>(8);
        Cell[] c = {new LivingCell(0, 0), new DeadCell(1, 0), new DeadCell(2, 0),
                new DeadCell(0, 1), new DeadCell(2, 1),
                new DeadCell(0, 2), new DeadCell(1, 2), new DeadCell(2, 2)
        };
        neighbouringCells.addAll(Arrays.asList(c));

        //TODO - investigate if mocking can help here.

        Cell initialCell = new LivingCell(1, 1);
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new DeadCell(1, 1));
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
        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new DeadCell(1, 1));
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
        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new LivingCell(1, 1));
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
        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new LivingCell(1, 1));
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
        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new LivingCell(1, 1));
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
        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new DeadCell(1, 1));
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
        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new DeadCell(4, 4));
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

        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new LivingCell(4, 4));
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

        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new LivingCell(initialCell.getX(), initialCell.getY()));
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

        //TODO - investigate if mocking can help here.
        Cell nextCell = Universe.calculateNextCellState(initialCell, neighbouringCells);
        assertEquals(nextCell, new LivingCell(initialCell.getX(), initialCell.getY()));
    }

    @Test
    public void givenUniverse_whenCellIsAdjacent_thenIdentifyAsNeighbour() {

        //TODO - investigate if mocking can help here.
        assertTrue(new LivingCell(1, 1).isNeighbour( new LivingCell(0, 0)));
        assertTrue(new DeadCell(1, 1).isNeighbour( new DeadCell(0, 1)));
        assertTrue(new LivingCell(1, 1).isNeighbour( new DeadCell(0, 2)));
        assertTrue(new DeadCell(1, 1).isNeighbour( new LivingCell(1, 0)));
        assertTrue(new LivingCell(1, 1).isNeighbour( new LivingCell(1, 2)));
        assertTrue(new LivingCell(1, 1).isNeighbour( new LivingCell(2, 0)));
        assertTrue(new LivingCell(1, 1).isNeighbour( new LivingCell(2, 1)));
        assertTrue(new LivingCell(1, 1).isNeighbour( new LivingCell(2, 2)));
        assertTrue(new LivingCell(3, 3).isNeighbour( new LivingCell(2, 2)));
        assertTrue(new DeadCell(3, 3).isNeighbour( new DeadCell(2, 3)));
        assertTrue(new LivingCell(3, 3).isNeighbour( new LivingCell(3, 2)));
        assertTrue(new LivingCell(3, 3).isNeighbour( new LivingCell(3, 4)));
        assertTrue(new DeadCell(0, 0).isNeighbour( new DeadCell(0, 1)));
        assertTrue(new LivingCell(0, 0).isNeighbour( new LivingCell(1, 1)));
        assertTrue(new LivingCell(0, 0).isNeighbour( new LivingCell(1, 0)));
    }

    @Test
    public void givenUniverse_whenCellIsSameCoord_thenIdentifyAsNotNeighbour() {
        //TODO - investigate if mocking can help here.
        assertFalse(new LivingCell(1, 1).isNeighbour(new LivingCell(1, 1)));
        assertFalse(new LivingCell(0, 0).isNeighbour( new LivingCell(0, 0)));
        assertFalse(new LivingCell(9, 9).isNeighbour( new LivingCell(9, 9)));
    }

    @Test
    public void givenUniverse_whenCellIsNotAdjacent_thenIdentifyAsNotNeighbour() {
        //TODO - investigate if mocking can help here.

        assertFalse(new LivingCell(1, 1).isNeighbour( new LivingCell(3, 1)));
        assertFalse(new LivingCell(1, 1).isNeighbour( new LivingCell(0, 3)));
        assertFalse(new LivingCell(1, 1).isNeighbour( new LivingCell(3, 3)));
        assertFalse(new LivingCell(0, 0).isNeighbour( new LivingCell(2, 2)));
        assertFalse(new LivingCell(0, 6).isNeighbour( new LivingCell(4, 6)));
    }

    @Test
    public void givenUniverse_whenDimensionIsOne_thenReturnsOneRowAndOneColumn() {
        when(supplier.get()).thenReturn(new LivingCell(0,0))
                .thenReturn(new LivingCell(1,0));

        Universe universe = new Universe(1, supplier);
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();

        assertEquals(rowAndCol.get(0).get(0), new LivingCell(0, 0));
        assertTrue(rowAndCol.size() == 1 && rowAndCol.get(0).size() == 1);
    }


    @Test
    public void givenUniverse_whenDimensionIsTwo_thenReturnsTwoRowAndTwoColumn() {

        when(supplier.get()).thenReturn(new DeadCell(0,0))
                .thenReturn(new DeadCell(1,0))
                .thenReturn(new DeadCell(0,1))
                .thenReturn(new DeadCell(1,1))
                .thenReturn(new DeadCell(0,2));

        Universe universe = new Universe(2, supplier);
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();

        assertEquals(rowAndCol.get(0).get(0), new DeadCell(0, 0));
        assertEquals(rowAndCol.get(0).get(1), new DeadCell(1, 0));
        assertEquals(rowAndCol.get(1).get(0), new DeadCell(0, 1));
        assertEquals(rowAndCol.get(1).get(1), new DeadCell(1, 1));
        assertTrue(rowAndCol.size() == 2 && rowAndCol.get(0).size() == 2);
    }



    @Test
    public void givenOneByOneFullAliveUniverse_whenNextGeneration_thenAllDead()
    {
        CellSupplier supplier = new CellSupplier(1, 0, new Random());

        Universe universe = new Universe(1, supplier);
        universe.nextGeneration();
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();
        assertTrue(rowAndCol.get(0).get(0) instanceof DeadCell);
    }

    @Test
    public void givenTwoByTwoDeadUniverse_whenNextGeneration_thenAllAlive()
    {
        CellSupplier supplier = new CellSupplier(2, 100, new Random());
        Universe universe = new Universe(2, supplier);

        universe.nextGeneration();
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();
        assertTrue(rowAndCol.get(0).get(0) instanceof LivingCell);
        assertTrue(rowAndCol.get(0).get(1) instanceof LivingCell);
        assertTrue(rowAndCol.get(1).get(0) instanceof LivingCell);
        assertTrue(rowAndCol.get(1).get(1) instanceof LivingCell);
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
        CellSupplier supplier = new CellSupplier(3, 100, new Random());
        Universe universe = new Universe(3, supplier);

        universe.nextGeneration();
        List<List<Cell>> rowAndCol = universe.toRowAndColumn();

        assertTrue(rowAndCol.get(0).get(0) instanceof LivingCell);
        assertTrue(rowAndCol.get(0).get(2) instanceof LivingCell);
        assertTrue(rowAndCol.get(2).get(0) instanceof LivingCell);
        assertTrue(rowAndCol.get(2).get(2) instanceof LivingCell);

        assertTrue(rowAndCol.get(0).get(1) instanceof DeadCell);
        assertTrue(rowAndCol.get(1).get(0) instanceof DeadCell);
        assertTrue(rowAndCol.get(1).get(1) instanceof DeadCell);
        assertTrue(rowAndCol.get(1).get(2) instanceof DeadCell);
        assertTrue(rowAndCol.get(2).get(1) instanceof DeadCell);
    }

}
