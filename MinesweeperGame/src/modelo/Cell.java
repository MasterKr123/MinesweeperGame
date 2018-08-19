package modelo;

public class Cell {

	private char value;
	private boolean covered;
	private boolean marked;

	public final static char UNSELECTED_CELL = '.';
	public final static char DISABLE_CELL = '-';
	public final static char MINE_CELL = '*';
	public final static char FLAG_CELL = 'P';

	/**
	 * clase: Cell <br>
	 * metodo: builder <br>
	 * This method initializes the parameters of the cell (value, covered and marked). <br>
	 * covered and marked refer to the states of whether the cell is covered and/or flagged. <br> 
	 * @param value - Corresponds to the value of the cell. <br>
	 * <b>pre: </b> value!=null && 0<=value<=9 || value can be ('.','*','*','P'). <br>
	 * <b>post: </b> cell inicialized with the parameters, covered and non market with flag. <br>
	 */
	
	public Cell(char value){
		this.value = value;
		this.covered = true;
		this.marked = false;
	}

	public char getValue(){
		return value;	
	}

	public void setValue(char value){
		this.value = value;
	}
	
	public boolean isCovered() {
		return covered;
	}
	
	public void setCovered(boolean covered) {
		this.covered = covered;
	}
	
	public boolean isMarked() {
		return marked;
	}
	
	public void setMarked(boolean flag) {
		this.marked = flag;
	}
}
