package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.CreateBoardException;
import modelo.Game;

public class Control {

	public static void main(String[] args) {

		BufferedReader buferReader = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("WOLCOME TO MINES WEEPER");
			System.out.println("Ingress: width height numberMines");
			String[] line = buferReader.readLine().split(" ");
			
			int width = Integer.parseInt(line[0]);
			int height = Integer.parseInt(line[1]);
			int numMines = Integer.parseInt(line[2]);	
			
			Game game = new Game(width, height, numMines);
			System.out.println("\n" + game.showBoard() + "\n");
			
			int row = 0;
			int column = 0;
			char action = ' ';
			
			while(!game.isFinished()){
				System.out.println("Ingress: row column action");
				line = buferReader.readLine().split(" ");
			
				row = Integer.parseInt(line[0]);
				column = Integer.parseInt(line[1]);
				action = line[2].charAt(0);
				
				game.play(row, column, action);
				System.out.println("\n" + game.showBoard() + "\n");
			}
			
			System.out.println("\n" + game.getBoard().uncoveredCells() + "\n");
			System.out.println("THE END");
			
		} catch (IOException e) {
			System.out.println("Error en el flujo de datos");	
		} catch (CreateBoardException e) {
			System.out.println(e.getMessage());
		}
	}
}
