package test;

import static org.junit.Assert.*;
import modelo.Game;

import org.junit.Test;

import exceptions.CreateBoardException;

public class GameTest {

	@Test
	public void test() {
		
		
		try {
			Game game = new Game(3, 4, 2);
			assertNotEquals(game, null);
		} catch (CreateBoardException e) {
			fail();
		}
	}

}
