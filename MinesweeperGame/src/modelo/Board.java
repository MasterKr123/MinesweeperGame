package modelo;

import java.util.Random;

import exceptions.CreateBoardException;

public class Board {

	private Cell[][] cells;

	private int height;
	private int width;
	private int numMines;

	private boolean finished;
	private boolean correct;

	public final static char SELECT = 'U';
	public final static char MARK = 'M';

	/**
	 * clase: Board <br>
	 * metodo: builder <br>
	 * This method initializes the parameters of the board and initializes the cells. <br>
	 * @param width - Corresponds to the width of the board. width!=null && width>0 <br>
	 * @param height - Corresponds to the height of the board.  height!=null && height>0 <br>
	 * @param numMines - Corresponds to the number of mines contained in the board. numMines!=null && numMines>0 <br>
	 * <b> post: </b> board inicialized with the parameters. <br>
	 * @throws CreateBoardException <br>
	 *         1. If the number of mines is greater than the number of cells in the board. (width*height). <br>
	 */
	public Board(int width, int height, int numMines) throws CreateBoardException{

		if(numMines>(width*height)){
			throw new CreateBoardException("The number of mines is greater than the board.");
		}else{
			this.width = width;
			this.height = height;
			this.numMines = numMines;	

			cells = new Cell[height][width];
			initializeCells();

			finished = false;
			correct = false;
		}
	}

	/**
	 * clase: Board <br>
	 * metodo: initializeCells <br>
	 * This method initializes the values ​​of the cells with random mines and counting of adjacent mines. <br>
	 * <b>pre: </b> cells!=null && width!=null && height!=null <br>
	 * <b>post: </b> The board is initialized with the number of mines and the count around the cells. <br>
	 */
	private void initializeCells(){
		fillBoardWithMines();
		fillCellsWithAround();
	}

	
	/**
	 * clase: Board <br>
	 * metodo: fillBoardWithMines <br>
	 * This method initializes the values ​​of the cells with random mines. <br>
	 * <b>pre: </b> cells!=null && numMines!=null <br>
	 * <b>post: </b> The board is initialized with the number of random mines. <br>
	 */
	private void fillBoardWithMines(){
		int mines = 0;
		int rowRandom = 0;
		int columnRandom = 0;

		Random r = new Random();
		while(mines<numMines){
			rowRandom = r.nextInt(height);
			columnRandom = r.nextInt(width);

			if(cells[rowRandom][columnRandom] == null){
				cells[rowRandom][columnRandom] = new Cell(Cell.MINE_CELL);
				mines++;
			}
		}
	}

	/**
	 * clase: Board <br>
	 * metodo: fillCellsWithAround <br>
	 * This method initializes the values ​​of the cells with the mine count around. <br>
	 * <b>pre: </b> cells!=null && the board contains the random mines. <br>
	 * <b>post: </b> The board is initialized with the count of the mines around the cell. <br>
	 */
	private void fillCellsWithAround(){
		for(int row = 0; row < height; row++){
			for(int column = 0; column < width; column++){
				if(cells[row][column]==null){
					int minesAround = 0;
					if(row-1>=0 && column-1>=0 && cells[row-1][column-1]!=null && cells[row-1][column-1].getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(row-1>=0 && cells[row-1][column]!=null && cells[row-1][column].getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(row-1>=0 && column+1<width && cells[row-1][column+1]!=null && cells[row-1][column+1].getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(column-1>=0 && cells[row][column-1]!=null && cells[row][column-1].getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(column+1<width && cells[row][column+1]!=null && cells[row][column+1].getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(row+1<height && column-1>=0 && cells[row+1][column-1]!=null && cells[row+1][column-1].getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(row+1<height && cells[row+1][column]!=null && cells[row+1][column].getValue()==Cell.MINE_CELL){
						minesAround++;
					}
					if(row+1<height && column+1<width && cells[row+1][column+1]!=null && cells[row+1][column+1].getValue()==Cell.MINE_CELL){
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
	 * This method selects or marks a cell, in addition to validating if the game is over. <br>
	 * @param row - Corresponds to the row of the board. row!=null && 0<row<width <br>
	 * @param column - Corresponds to the board column. column!=null && 0<column<height <br>
	 * @param action - Corresponds to the action of the game. action!=null && action can be ('U','M') <br>
	 * <b>pre: </b> The game is not over. <br>
	 * <b>post: </b> The cell has been selected or checked and the game is verified. <br>
	 */
	public void selectedCell(int row, int column, char action){
		Cell cell = cells[row][column]; 

		if(action==SELECT){
			cell.setCovered(false);
			cell.setMarked(false);
			if(cell.getValue() == Cell.MINE_CELL){
				finished = true;
			}else if(cell.getValue() == Cell.DISABLE_CELL){
				findOthersDisableCells(row, column);
			}
		}else if(action==MARK){
			if(cell.isMarked()){
				cell.setMarked(false);
			}else{
				cell.setMarked(true);
			}
		}

		if(!finished){
			validatePlay();	
		}
	}


	/**
	 * clase: Board <br>
	 * metodo: findOthersDisableCells <br>
	 * This method recursively searches for deactivated cells around. <br>
	 * @param row - Corresponds to the row of the board. row!=null && 0<row<width <br>
	 * @param column - Corresponds to the board column. column!=null && 0<column<height <br>
	 * <b>post: </b> The cells deactivated around will become visible. <br>
	 */
	public void findOthersDisableCells(int row, int column){
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
	 * This method validates if the game has ended by verifying the number of discovered mines. <br>
	 * <b>pre: </b> The game is not over. <br>
	 * <b>post: </b> Game validation. <br>
	 */
	public void validatePlay(){
		int discoverMines = 0;
		int nonDiscover = 0;

		for(int row = 0; row < height && nonDiscover==0; row++){
			for(int column = 0; column < width && nonDiscover==0; column++){
				Cell cell = cells[row][column];
				if(cell.isCovered() || cell.isMarked()){
					if(cell.getValue()==Cell.MINE_CELL){
						discoverMines++;
					}else{
						nonDiscover++;
					}
				}
			}
		}
		if(nonDiscover==0 && numMines==discoverMines){
			correct = true;
			finished = true;
		}
	}

	/**
	 * clase: Board <br>
	 * metodo: showBoard <br>
	 * This method concatenates the entire board in the current game. <br>
	 * <b>pre: </b> cells!=null <br>
	 * <b>post: </b> Board chain. <br>
	 */
	public String showBoard(){
		String board = "";
		for(int row = 0; row < height; row++){
			for(int column = 0; column < width; column++){
				Cell cell = cells[row][column];
				if(cell.isCovered()){
					if(cell.isMarked()){
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
	 * This method concatenates the entire board in the finished game. <br>
	 * <b>pre: </b> cells!=null <br>
	 * <b>post: </b> Full board chain. <br>
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
		return finished;
	}
	
	public boolean isCorrect() {
		return correct;
	}
	
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
}