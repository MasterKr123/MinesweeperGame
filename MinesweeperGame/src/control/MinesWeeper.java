package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.CreateBoardException;
import modelo.Game;

public class MinesWeeper {

	
	/**
	 * clase: Control <br>
	 * metodo: main <br>
	 * Main method that starts the game. <br>
	 * <b> post: </b> The game is started. <br>
	 */
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
	 * This method obtains the values ​​of width, height and numMines of the game. <br>
	 * <b> pre: </b> The commands are entered correctly, that is, with the format:
	 * width+" "+height+" "+numMines where the three values ​​are integers. <br>
	 * <b> post: </b> The game is initialized. <br>
	 * @throws IOException <br>
	 *         1. If there is an error in the data flow. <br>
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

		System.out.println("GAME OVER");
	}

	/**
	 * clase: Control <br>
	 * metodo: playGame <br>
	 * This method begins the interactivity of the game. <br>
	 * @param width - Corresponds to the width of the board. width!=null && width>0 <br>
	 * @param height - Corresponds to the height of the board.  height!=null && height>0 <br>
	 * @param numMines - Corresponds to the number of mines contained in the board. numMines!=null && numMines>0 <br>
	 * @param buferReader - Input flow. buferReader!=null <br>
	 * <b> pre: </b> The commands are entered correctly, that is, with the format:
	 * width+" "+height+" "+action Where the first two are integer values ​​and the third is "M" o "U". 
	 * "M" corresponds to mark and "U" to select the cell. <br>
	 * <b> post: </b> The game is interactive. <br>
	 * @throws IOException <br>
	 *         1. If there is an error in the data flow. <br>
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
			System.out.println(game.getBoard().isCorrect()?"YOU WIN":"YOU LOST");
		} catch (CreateBoardException e) {
			System.out.println(e.getMessage());
		}

	}

}
