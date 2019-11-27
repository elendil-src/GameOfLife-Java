package com.elendil.training;

import org.junit.Assert;
import org.junit.Test;

public class TestCell {
    @Test
    public void givenLivingCell_whenOneLivingNeighbour_thenCellDIes() {
        Cell initialCell = new LivingCell(4, 5);
        Cell nextCell = initialCell.nextState(1);
        Assert.assertTrue(nextCell instanceof DeadCell);
        Assert.assertEquals(nextCell.getX(), 4);
        Assert.assertEquals(nextCell.getY(), 5);
        Assert.assertEquals(nextCell, new DeadCell(4, 5));
    }


    @Test
    public void givenLivingCell_whenComparedWithDifferent_thenEqualsFalse() {
        Assert.assertNotEquals(new LivingCell(2, 45), new LivingCell(3, 45));
    }

    @Test
    public void givenLivingCell_whenComparedWithDeadCell_thenEqualsFalse() {
        Assert.assertNotEquals(new LivingCell(2, 45), new DeadCell(2, 45));
    }

    @Test
    public void givenDifferentLivingCells_whenHashcodeCalculated_thenHashCodeDiffers() {
        Assert.assertNotEquals(new LivingCell(3, 45).hashCode(), new LivingCell(3, 44).hashCode());
    }

    @Test
    public void givenIdenticalLivingCells_whenHashcodeCalculated_thenHashcodeIsSame() {
        Assert.assertEquals(new LivingCell(3, 45).hashCode(), new LivingCell(3, 45).hashCode());
    }

    @Test
    public void givenLivingCell_whenComparedWithIdentical_thenEqualsTrue() {
        Assert.assertEquals(new LivingCell(3, 45), new LivingCell(3, 45));
    }


    @Test
    public void givenDeadCell_whenComparedWithDifferent_thenEqualsFalse() {
        Assert.assertNotEquals(new DeadCell(2, 45), new DeadCell(3, 45));
    }


    @Test
    public void givenDeadCell_whenComparedWithLivingCellSameCoord_thenEqualsFalse() {
        Assert.assertNotEquals(new DeadCell(2, 45), new LivingCell(2, 45));
    }

    @Test
    public void givenDifferentDeadCells_whenHashcodeCalculated_thenHashcodeDiffers() {
        Assert.assertNotEquals(new DeadCell(3, 45).hashCode(), new DeadCell(3, 44).hashCode());
    }

    @Test
    public void givenIdenticalDeadCells_whenHashcodeCalculated_thenHashcodeIsSame() {
        Assert.assertEquals(new DeadCell(3, 45).hashCode(), new DeadCell(3, 45).hashCode());
    }

    @Test
    public void givenDeadCell_whenComparedWithIdentical_thenEqualsTrue() {
        Assert.assertEquals(new DeadCell(3, 45), new DeadCell(3, 45));
    }

    @Test
    public void givenLivingCell_whenInvalidNeighbourCount_thenCellDiesInNextState() {
        LivingCell c = new LivingCell(1, 2);
        Assert.assertEquals(c.nextState(0), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(1), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(9), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(4), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(5), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(6), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(7), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(8), new DeadCell(c.getX(), c.getY()));
    }

    @Test
    public void givenLivingCell_whenValidNeighbourCount_thenCellLivesInNextState() {
        LivingCell c = new LivingCell(4, 5);
        Assert.assertEquals(c.nextState(2), new LivingCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(3), new LivingCell(c.getX(), c.getY()));
    }

    @Test
    public void givenDeadCell_whenInvalidNeighbourCount_thenCellDiesInNextState() {
        Cell c = new DeadCell(44, 44);
        Assert.assertEquals(c.nextState(0), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(1), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(9), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(2), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(5), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(4), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(6), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(7), new DeadCell(c.getX(), c.getY()));
        Assert.assertEquals(c.nextState(8), new DeadCell(c.getX(), c.getY()));
    }

    @Test
    public void givenDeadCell_whenValidNeighbourCount_thenCellLivesInNextState() {
        DeadCell c = new DeadCell(6, 7);
        Assert.assertEquals(c.nextState(3), new LivingCell(c.getX(), c.getY()));
    }
}
