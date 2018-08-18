package test;

import static org.junit.Assert.*;
import modelo.Cell;

import org.junit.Test;

public class CellTest {

	@Test
	public void test() {
		Cell cell = new Cell(Cell.DISABLE_CELL);
		assertEquals(cell.getValue(), Cell.DISABLE_CELL);
		
		cell.setValue(Cell.MINE_CELL);
		assertEquals(cell, Cell.MINE_CELL);
		
		cell.setValue(Cell.FLAG_CELL);
		assertEquals(cell.getValue(), Cell.FLAG_CELL);
		
		cell.setValue(Cell.UNSELECTED_CELL);
		assertEquals(cell.getValue(), Cell.UNSELECTED_CELL);
	}

}
