package modelo;

import exceptions.CreateBoardException;

public class Game {

	private boolean finish;
	private Board board;

	/**
	 * clase: Game <br>
	 * metodo: builder <br>
	 * This method initializes the game parameters. <br>
	 * @param width - Corresponds to the width of the board. width!=null && width>0 <br>
	 * @param height - Corresponds to the height of the board.  height!=null && height>0 <br>
	 * @param numMines - Corresponds to the number of mines that the board contains. numMines!=null && numMines>0 && numMines<(width*height) <br>
	 * <b>post: </b> Game inicialized with the parameters <br>
	 * @throws CreateBoardException <br>
	 *         1. If the number of mines is greater than the number of cells in the board. (width*height). <br>
	 */
	public Game(int width,int height, int numMines)throws CreateBoardException{
		finish = false;
		board = new Board(width, height, numMines);
	}

	/**
	 * clase: Board <br>
	 * metodo: play <br>
	 * This method selects or marks a cell on the board, in addition to validating if the game is over. <br>
	 * @param row - Corresponds to the board row. row!=null && 0<row<width <br>
	 * @param column - Corresponds to the board column. column!=null && 0<column<height <br>
	 * @param action - Corresponds to the action of the game. action!=null && action has to be ('U','M') <br>
	 * <b>pre: </b> The game is not over. <br>
	 * <b>post: </b> The cell has been selected or checked and the game is verified. <br>
	 */
	public void play(int row, int column, char action){
		board.selectedCell(row-1, column-1, action);
		finish = board.isFinished();
	}
	
	/**
	 * clase: Game <br>
	 * metodo: showBoard <br>
	 * This method concatenates the entire board in the current game. <br>
	 * <b>pre: </b> board!=null <br>
	 * <b>post: </b> Board chain. <br>
	 */
	public String showBoard(){
		return board.showBoard();
	}

	public Board getBoard() {
		return board;
	}

	public boolean isFinished() {
		return finish;
	}

	public void setFinish(boolean isFinish) {
		this.finish = isFinish;
	}	

}
