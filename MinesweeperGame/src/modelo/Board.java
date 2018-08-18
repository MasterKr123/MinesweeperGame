package modelo;

import exceptions.CreateBoardException;

public class Board {

	private Cell[][] cells;

	private int height;
	private int width;
	private int numMines;

	private boolean isFinished;

	public final static char SELECT = 'U';
	public final static char MARKET = 'M';

	/**
	 * clase: Board <br>
	 * metodo: constructor <br>
	 * Este metodo inicializa los parametros del tablero <br>
	 * @param width - Corresponde al ancho del tablero - width!=null && width>0 <br>
	 * @param height - Corresponde a la altura del tablero -  height!=null && height>0 <br>
	 * @param numMines - Corresponde al numero de minas que contiene el tablero - numMines!=null && numMines>0 <br>
	 * <b>post: </b> board inicialized with the parameter <br>
	 */
	public Board(int width, int height, int numMines) throws CreateBoardException{

		if(numMines>(width*height)){
			throw new CreateBoardException("La cantidad de minas es mayor al tablero");
		}else{
			this.height = height;
			this.width = width;
			this.numMines = numMines;	

			cells = new Cell[width][height];
			initializeCells();

			isFinished = false;
		}
	}

	/**
	 * clase: Board <br>
	 * metodo: initializeCells <br>
	 * Este metodo inicializa los valores de las celdas en no seleccionadas <br>
	 * <b>pre: </b> cells!=null && width!=null && height!=null <br>
	 * <b>post: </b> All cells have the value unselected <br>
	 */
	private void initializeCells(){
		for(int row = 0; row < width; row++){
			for(int column = 0; column < height; column++){
				cells[row][column].setValue(Cell.UNSELECTED_CELL);
			}
		}
	}


	/**
	 * clase: Board <br>
	 * metodo: selectedCell <br>
	 * Este metodo selecciona o marca una celda, ademas de validar si el juego ha terminado <br>
	 * @param row - Corresponde a la fila del tablero - row!=null && 0<row<width <br>
	 * @param column - Corresponde a la columna del tablero - column!=null && 0<column<height <br>
	 * @param action - Corresponde al ancho del tablero - action!=null && action can be ('U','M') <br>
	 * <b>pre: </b> El juego no ha terminado <br>
	 * <b>post: </b> la celda ha sido seleccionada o marcada y el juego es verificado <br>
	 */
	public void selectedCell(int row, int column, char action){
		if(action==SELECT){
			if(cells[row][column].getValue()==Cell.MINE_CELL){
				isFinished = true;
			}else{



			}
		}else{
			cells[row][column].setValue(Cell.FLAG_CELL);
		}
	}


	/**
	 * clase: Board <br>
	 * metodo: validatePlay <br>
	 * Este metodo valida si el juego ha terminado verifiacando el numero de minas descubiertas <br>
	 * <b>pre: </b> El juego no ha terminado <br>
	 * <b>post: </b> validación del juego <br>
	 */
	public void validatePlay(){
		int uncoveredMines = 0;
		for(int row = 0; row < width && uncoveredMines<numMines; row++){
			for(int column = 0; column < height && uncoveredMines<numMines; column++){
				if(cells[row][column].getValue()==Cell.MINE_CELL){
					uncoveredMines++;
				}
			}
		}
		if(numMines==uncoveredMines){
			isFinished = true;
		}
	}

	public Cell[][] getCells() {
		return cells;
	}

	public int getHeight() {
		return height;
	}

	public int getNumMines() {
		return numMines;
	}

	public int getWidth() {
		return width;
	}

	public boolean isFinished() {
		return isFinished;
	}
}