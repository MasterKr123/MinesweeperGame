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
			
		} catch (IOException e) {
			System.out.println("Error en el flujo de datos");	
		} catch (CreateBoardException e) {
			System.out.println(e.getMessage());
		}
	}
}
