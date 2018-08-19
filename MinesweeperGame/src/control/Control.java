package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.CreateBoardException;
import modelo.Game;

public class Control {

	public static void main(String[] args) {
		try {
			start();
		} catch (IOException e) {
			System.out.println("Error en el flujo de datos.");	
		} catch (NumberFormatException e2){
			System.out.println("Ha ingresado los datos incorrectamente, intente de nuevo.");
		} catch (ArrayIndexOutOfBoundsException e3){
			System.out.println("Ha ingresado los datos incorrectamente, intente de nuevo.");
		}
	}

	/**
	 * clase: Control <br>
	 * metodo: start <br>
	 * Este metodo obtiene los valores de width, height y numMines del juego. <br>
	 * <b> pre: </b> Los comando son ingresados correctamente, es decir con el formato:
	 * width+" "+height+" "+numMines donde los tres valores son enteros. <br>
	 * <b> post: </b> El juego es inicializado. <br>
	 * @throws IOException <br>
	 *         1. Si hay algun error en el flujo de datos. <br>
	 */
	private static void start() throws IOException{
		BufferedReader buferReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("WELCOME TO MINES WEEPER");

		System.out.println("Enter: width height numberMines");
		String[] line = buferReader.readLine().split(" ");
		int width = Integer.parseInt(line[0]);
		int height = Integer.parseInt(line[1]);
		int numMines = Integer.parseInt(line[2]);	

		playGame(width, height, numMines, buferReader);

		System.out.println("THE END");
	}

	/**
	 * clase: Control <br>
	 * metodo: playGame <br>
	 * Este metodo comienza la interactividad del juego. <br>
	 * @param width - Corresponde al ancho del tablero. width!=null && width>0 <br>
	 * @param height - Corresponde a la altura del tablero.  height!=null && height>0 <br>
	 * @param numMines - Corresponde al numero de minas que contiene el tablero. numMines!=null && numMines>0 <br>
	 * @param buferReader - Flujo de entrada. buferReader!=null <br>
	 * <b> pre: </b> Los comando son ingresados correctamente, es decir con el formato:
	 * width+" "+height+" "+action donde los dos primeros son valores enteros y el tercero es "M" o "U". 
	 * "M" corresponde a marcar y "U" a seleccionar la celda. <br>
	 * <b> post: </b> El juego es interactivo. <br>
	 * @throws IOException <br>
	 *         1. Si hay algun error en el flujo de datos. <br>
	 */
	private static void playGame(int width, int height, int numMines, BufferedReader buferReader) throws IOException{
		try {
			Game game = new Game(width, height, numMines);
			System.out.println("\n" + game.showBoard() + "\n");

			int row = 0;
			int column = 0;
			char action = ' ';
			String[] line = null;

			while(!game.isFinished()){
				System.out.println("Enter: row column action(U o M)");
				line = buferReader.readLine().split(" ");

				row = Integer.parseInt(line[0]);
				column = Integer.parseInt(line[1]);
				action = line[2].charAt(0);

				game.play(row, column, action);
				System.out.println("\n" + game.showBoard() + "\n");
			}

			System.out.println("\n" + game.getBoard().uncoveredCells() + "\n");

		} catch (CreateBoardException e) {
			System.out.println(e.getMessage());
		}

	}

}
