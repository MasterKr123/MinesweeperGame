package modelo;

public class Cell {

	public char value;

	public final static char UNSELECTED_CELL = '.';
	public final static char DISABLE_CELL = '-';
	public final static char MINE_CELL = '*';
	public final static char FLAG_CELL = 'P';

	/**
	 * clase: Cell <br>
	 * metodo: constructor <br>
	 * Este metodo inicializa los parametros de la celda <br>
	 * @param value - Corresponde al valor de la celda <br>
	 * <b>pre: </b> value!=null && 0<=value<=9 || value can be ('.','*','*','P')<br>
	 * <b>post: </b> cell inicialized with the parameter <br>
	 */
	public Cell(char value){
		this.value = value;
	}

	public char getValue(){
		return value;	
	}

	public void setValue(char value){
		this.value = value;
	}
}
