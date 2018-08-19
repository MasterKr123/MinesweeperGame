package modelo;

import exceptions.CreateBoardException;

public class Game {

	private boolean finish;
	private Board board;

	/**
	 * clase: Game <br>
	 * metodo: constructor <br>
	 * Este metodo inicializa los parametros del juego <br>
	 * @param width - Corresponde al ancho del tablero - width!=null && width>0 <br>
	 * @param height - Corresponde a la altura del tablero -  height!=null && height>0 <br>
	 * @param numMines - Corresponde al numero de minas que contiene el tablero - numMines!=null && numMines>0 <br>
	 * <b>post: </b> Game inicialized with the parameter <br>
	 * @throws CreateBoardException <br>
	 *         1. Si el numero de minas es mayor a la cantidad de celdas del tablero (width*height). <br>
	 */
	public Game(int width,int height, int numMines)throws CreateBoardException{
		finish = false;
		board = new Board(width, height, numMines);
	}

	/**
	 * clase: Board <br>
	 * metodo: play <br>
	 * Este metodo selecciona o marca una celda en el tablero, ademas de validar si el juego ha terminado. <br>
	 * @param row - Corresponde a la fila del tablero. row!=null && 0<row<width <br>
	 * @param column - Corresponde a la columna del tablero. column!=null && 0<column<height <br>
	 * @param action - Corresponde al ancho del tablero. action!=null && action can be ('U','M') <br>
	 * <b>pre: </b> El juego no ha terminado. <br>
	 * <b>post: </b> la celda ha sido seleccionada o marcada y el juego es verificado. <br>
	 */
	public void play(int row, int column, char action){
		board.selectedCell(row-1, column-1, action);
		finish = board.isFinished();
	}
	
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
