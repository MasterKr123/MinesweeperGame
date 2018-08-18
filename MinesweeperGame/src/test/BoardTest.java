package test;

import static org.junit.Assert.*;
import modelo.Board;

import org.junit.Test;

import exceptions.CreateBoardException;

public class BoardTest {

	@Test
	public void test() {
		
		try {
			Board board = new Board(3, 4, 2);
			assertNotEquals(board, null);
		} catch (CreateBoardException e) {
			fail();
		}	
	}
}
