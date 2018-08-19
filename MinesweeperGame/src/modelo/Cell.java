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
	 * metodo: constructor <br>
	 * Este metodo inicializa los parametros de la celda (valor, cubierto y marcado). <br>
	 * covered y marked hacen refencia a los estados de si la celda esta cubierta y/o marcada con flag. <br> 
	 * @param value - Corresponde al valor de la celda. <br>
	 * <b>pre: </b> value!=null && 0<=value<=9 || value can be ('.','*','*','P'). <br>
	 * <b>post: </b> cell inicialized with the parameter, covered and non market with flag. <br>
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
