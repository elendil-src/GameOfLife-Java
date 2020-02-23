package com.elendil.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import java.util.Random;


public class CellSupplierTest {

    private Random rnGenerator;
    private Random mockRnGenerator = Mockito.mock(Random.class);

    @BeforeEach
    void setup()
    {
        rnGenerator = new Random();
    }

    @Test
    public void givenZeroPercentSeed_whenCreatingCell_thenCellIsDead(){

        when(mockRnGenerator.nextInt(99)).thenReturn(0);
        CellSupplier underTest = new CellSupplier(1,0, mockRnGenerator);
        assertEquals(underTest.get(), new DeadCell(0,0));
    }

    @Test
    public void givenHundredPercentSeed_whenCreatingCell_thenCellIsLiving(){

        when(mockRnGenerator.nextInt(99)).thenReturn(1);
        CellSupplier underTest = new CellSupplier(1,100, mockRnGenerator);
        assertEquals(underTest.get(), new LivingCell(0,0));
    }

    @Test
    public void givenFiftyPercentSeed_whenCreatingCellAndRNIsGtrThanSeedAlive_thenCellIsDead(){

        when(mockRnGenerator.nextInt(99)).thenReturn(51);
        CellSupplier underTest = new CellSupplier(1,50, mockRnGenerator);
        assertEquals(underTest.get(), new DeadCell(0,0));
    }

    @Test
    public void givenFiftyPercentSeed_whenCreatingCellAndRNIsEqualsSeedAlive_thenCellIsDead(){

        when(mockRnGenerator.nextInt(99)).thenReturn(50);
        CellSupplier underTest = new CellSupplier(1,50, mockRnGenerator);
        assertEquals(underTest.get(), new DeadCell(0,0));
    }

    @Test
    public void givenHundredPercentSeed_whenCreatingCellAndRNIsLessThanSeedAlive_thenCellIsLiving(){

        when(mockRnGenerator.nextInt(99)).thenReturn(49);
        CellSupplier underTest = new CellSupplier(1,50, mockRnGenerator);
        assertEquals(underTest.get(), new LivingCell(0,0));
    }

}
