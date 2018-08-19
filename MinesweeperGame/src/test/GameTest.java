package test;

import static org.junit.Assert.*;
import modelo.Board;
import modelo.Game;

import org.junit.Test;

import exceptions.CreateBoardException;

public class GameTest {

	@Test
	public void testGame() {
		try {
			Game game = new Game(3, 4, 2);
			assertNotEquals(game, null);
		} catch (CreateBoardException e) {
			fail();
		}
	}
	
	
	@Test
	public void testPlay(){
		try {
			Game game = new Game(2, 2, 4);
			game.play(1, 1, Board.SELECT);
			
			assertEquals(true, game.isFinished());
			
		} catch (CreateBoardException e) {
			fail();
		}
	}
	
	@Test
	public void testShowBoard(){
		try {
			Game game = new Game(2, 2, 1);
			
			String boardText = ". . \n. . \n";
			assertEquals(boardText, game.showBoard());
			
		} catch (CreateBoardException e) {
			fail();
		}
	}

}
