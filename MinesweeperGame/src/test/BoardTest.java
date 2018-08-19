package test;

import static org.junit.Assert.*;

import modelo.Board;
import modelo.Cell;

import org.junit.Test;

import exceptions.CreateBoardException;

public class BoardTest {

	@Test
	public void testBoard() {
		try {
			Board board = new Board(3, 4, 2);
			assertNotEquals(board, null);
		} catch (CreateBoardException e) {
			fail();
		}	
	}

	@Test
	public void testFillBoardWithMines(){
		try {
			Board board = new Board(3, 4, 2);
			int numMines = 0;

			Cell[][] cells = board.getCells();
			for(int row = 0; row < board.getHeight(); row++){
				for(int column = 0; column < board.getWidth(); column++){
					if(cells[row][column].getValue()==Cell.MINE_CELL){
						numMines++;
					}
				}
			}

			assertEquals(numMines,2);

		} catch (CreateBoardException e) {
			fail();
		}	
	}

	@Test
	public void testFillCellsWithAround(){
		try {
			Board board = new Board(2, 2, 1);
			Cell[][] cells = board.getCells();

			if(cells[0][0].getValue()==Cell.MINE_CELL){
				assertEquals(cells[0][1].getValue(), '1');
				assertEquals(cells[1][1].getValue(), '1');
				assertEquals(cells[1][0].getValue(), '1');
			} else if(cells[0][1].getValue()==Cell.MINE_CELL){
				assertEquals(cells[0][0].getValue(), '1');
				assertEquals(cells[1][1].getValue(), '1');
				assertEquals(cells[1][0].getValue(), '1');
			} else if(cells[1][0].getValue()==Cell.MINE_CELL){
				assertEquals(cells[0][1].getValue(), '1');
				assertEquals(cells[1][1].getValue(), '1');
				assertEquals(cells[0][0].getValue(), '1');
			} else{
				assertEquals(cells[0][1].getValue(), '1');
				assertEquals(cells[1][0].getValue(), '1');
				assertEquals(cells[0][0].getValue(), '1');
			}

		} catch (CreateBoardException e) {
			fail();
		}		
	}

	@Test
	public void testSelectedCell(){
		try {
			Board board = new Board(2, 2, 1);
			Cell[][] cells = board.getCells();

			board.selectedCell(1, 1, Board.MARK);
			assertEquals(true, cells[1][1].isMarked());

			board.selectedCell(0, 0, Board.SELECT);
			if(cells[0][0].getValue()== Cell.MINE_CELL){
				assertEquals(true, board.isFinished());
				assertEquals(false, cells[0][0].isCovered());
			}else{
				assertEquals(cells[0][0].isCovered(), false);
			}
		} catch (CreateBoardException e) {
			fail();
		}	
	}

	@Test
	public void testFindOthersDisableCells(){

		try {
			Board board = new Board(2, 2, 1);
			board.getCells()[0][0].setValue(Cell.DISABLE_CELL);
			board.getCells()[0][1].setValue(Cell.DISABLE_CELL);
			board.getCells()[1][0].setValue(Cell.DISABLE_CELL);
			board.getCells()[1][1].setValue(Cell.DISABLE_CELL);

			board.findOthersDisableCells(0,0);

			Cell[][] cells = board.getCells();

			assertEquals(false, cells[0][0].isCovered());
			assertEquals(false, cells[0][1].isCovered());
			assertEquals(false, cells[1][0].isCovered());
			assertEquals(false, cells[1][1].isCovered());

		} catch (CreateBoardException e) {
			fail();
		}
	}

	@Test
	public void testValidatePlay(){
		try {
			Board board = new Board(2, 2, 1);
			board.getCells()[0][0].setValue('1');
			board.getCells()[0][1].setValue(Cell.MINE_CELL);
			board.getCells()[1][0].setValue('1');
			board.getCells()[1][1].setValue('1');

			board.validatePlay();
			assertEquals(false, board.isFinished());
			
			board.getCells()[0][0].setCovered(false);
			board.getCells()[1][0].setCovered(false);
			board.getCells()[1][1].setCovered(false);
			board.validatePlay();
			assertEquals(true, board.isFinished());
			
			assertNotEquals(board, null);
		} catch (CreateBoardException e) {
			fail();
		}	
	}
	
	@Test
	public void testShowBoard(){
		try {
			Board board = new Board(2, 2, 1);
			
			String boardText = ". . \n. . \n";
			assertEquals(board.showBoard(), boardText);
			
		} catch (CreateBoardException e) {
			fail();
		}	
	}
	
	@Test
	public void testUncoveredCells(){
		try {
			Board board = new Board(2, 2, 1);
			board.getCells()[0][0].setValue('1');
			board.getCells()[0][1].setValue(Cell.MINE_CELL);
			board.getCells()[1][0].setValue('1');
			board.getCells()[1][1].setValue('1');
			
			String boardText = "1 * \n1 1 \n";
			assertEquals(board.uncoveredCells(), boardText);
			
		} catch (CreateBoardException e) {
			fail();
		}	
	}
}
