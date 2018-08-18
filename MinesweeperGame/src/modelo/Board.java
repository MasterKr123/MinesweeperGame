package modelo;

import java.util.Random;

import exceptions.CreateBoardException;

public class Board {

	private Cell[][] cells;

	private int height;
	private int width;
	private int numMines;

	private boolean finish;

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
			this.width = width;
			this.height = height;
			this.numMines = numMines;	

			cells = new Cell[height][width];
			initializeCells();

			finish = false;
		}
	}

	


	/**
	 * clase: Board <br>
	 * metodo: initializeCells <br>
	 * Este metodo inicializa los valores de las celdas con minas aleatorias y conteo de minas alrededor<br>
	 * <b>pre: </b> cells!=null && width!=null && height!=null <br>
	 * <b>post: </b> All cells have the value unselected <br>
	 */
	private void initializeCells(){

		int mines = 0;
		int rowRandom = 0;
		int columnRandom = 0;

		Random r = new Random();
		while(mines<numMines){
			rowRandom = r.nextInt(height-1);
			columnRandom = r.nextInt(width-1);

			if(cells[rowRandom][columnRandom]==null){
				cells[rowRandom][columnRandom] = new Cell(Cell.MINE_CELL);
				mines++;
			}
		}

		for(int row = 0; row < height; row++){
			for(int column = 0; column < width; column++){
				if(cells[row][column]==null){

					int minesAround = 0;

					Cell cellOne = null;
					Cell cellTwo = null;
					Cell cellThree = null;
					Cell cellFour = null;
					Cell cellFive = null;
					Cell cellSix = null;
					Cell cellSeven = null;
					Cell cellEight = null;

					if(row-1>=0 && column-1>=0){
						cellOne = cells[row-1][column-1];
					}
					if(row-1>=0){
						cellTwo = cells[row-1][column];
					}
					if(row-1>=0 && column+1<width){
						cellThree = cells[row-1][column+1];
					}
					if(column-1>=0){
						cellFour = cells[row][column-1];
					}
					if(column+1<width){
						cellFive = cells[row][column+1];
					}
					if(row+1<height && column-1>=0){
						cellSix = cells[row+1][column-1];
					}
					if(row+1<height){
						cellSeven = cells[row+1][column];
					}
					if(row+1<height && column+1<width){
						cellEight = cells[row+1][column+1];
					}

					if(cellOne!=null && cellOne.getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(cellTwo!=null && cellTwo.getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(cellThree!=null && cellThree.getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(cellFour!=null && cellFour.getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(cellFive!=null && cellFive.getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(cellSix!=null && cellSix.getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(cellSeven!=null && cellSeven.getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(cellEight!=null && cellEight.getValue()==Cell.MINE_CELL){
						minesAround++;
					}


					if(minesAround==0){
						cells[row][column] = new Cell(Cell.DISABLE_CELL);
					}else{
						cells[row][column] = new Cell((char)(minesAround+'0'));	
					}
				}
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
		Cell cell = cells[row][column]; 

		if(action==SELECT){
			cell.setCovered(false);
			cell.setFlag(false);
			if(cell.getValue() == Cell.MINE_CELL){
				finish = true;
			}else if(cell.getValue() == Cell.DISABLE_CELL){
				findOthersDisableCells(row, column);
			}
		}else if(action==MARKET){
			if(cell.isFlag()){
				cell.setFlag(false);
			}else{
				cell.setFlag(true);
			}
		}
		
		if(!finish){
			validatePlay();	
		}
	}

	
	/**
	 * clase: Board <br>
	 * metodo: findOthersDisableCells <br>
	 * Este metodo busca recursivamente las celdas desactivadas alrededor <br>
	 * @param row - Corresponde a la fila del tablero - row!=null && 0<row<width <br>
	 * @param column - Corresponde a la columna del tablero - column!=null && 0<column<height <br>
	 * <b>post: </b> las celdas desactivadas alrededor se haran visibles <br>
	 */
	private void findOthersDisableCells(int row, int column){
		Cell cell = cells[row][column]; 

		if(cell.getValue() == Cell.DISABLE_CELL){	
			cells[row][column].setCovered(false);

			if(row-1>=0 && column-1>=0 && cells[row-1][column-1].isCovered()){
				findOthersDisableCells(row-1,column-1);
			}
			if(row-1>=0 && cells[row-1][column].isCovered()){
				findOthersDisableCells(row-1,column);
			}
			if(row-1>=0 && column+1<width && cells[row-1][column+1].isCovered()){
				findOthersDisableCells(row-1,column+1);
			}
			if(column-1>=0 && cells[row][column-1].isCovered()){
				findOthersDisableCells(row,column-1);
			}
			if(column+1<width && cells[row][column+1].isCovered()){
				findOthersDisableCells(row,column+1);
			}
			if(row+1<height && column-1>=0 && cells[row+1][column-1].isCovered()){
				findOthersDisableCells(row+1,column-1);
			}
			if(row+1<height && cells[row+1][column].isCovered()){
				findOthersDisableCells(row+1,column);
			}
			if(row+1<height && column+1<width && cells[row+1][column+1].isCovered()){
				findOthersDisableCells(row+1,column+1);
			}
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
		int discoverMines = 0;
		int nonDiscover = 0;
		
		for(int row = 0; row < height && nonDiscover==0; row++){
			for(int column = 0; column < width && nonDiscover==0; column++){
				Cell cell = cells[row][column];
				if(cell.isCovered() || cell.isFlag()){
					if(cell.getValue()==Cell.MINE_CELL){
						discoverMines++;
					}else{
						nonDiscover++;
					}
				}
			}
		}
		if(nonDiscover==0 && numMines==discoverMines){
			finish = true;
		}
	}

	/**
	 * clase: Board <br>
	 * metodo: showBoard <br>
	 * Este metodo concatena todo el tablero en el juego actual <br>
	 * <b>post: </b> cadena del tablero <br>
	 */
	public String showBoard(){
		String board = "";
		for(int row = 0; row < height; row++){
			for(int column = 0; column < width; column++){
				Cell cell = cells[row][column];
				if(cell.isCovered()){

					if(cell.isFlag()){
						board += Cell.FLAG_CELL + " ";
					}else{
						board += Cell.UNSELECTED_CELL +" ";
					}

				}else{
					board += cell.getValue()+ " ";
				}
			}
			board += "\n";
		}
		return board;
	}

	/**
	 * clase: Board <br>
	 * metodo: uncoveredCells <br>
	 * Este metodo concatena todo el tablero en el juego terminado <br>
	 * <b>post: </b> cadena del tablero completo <br>
	 */
	public String uncoveredCells(){
		String board = "";
		for(int row = 0; row < height; row++){
			for(int column = 0; column < width; column++){
				board += cells[row][column].getValue()+ " ";
			}
			board += "\n";
		}
		return board;
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
		return finish;
	}
}