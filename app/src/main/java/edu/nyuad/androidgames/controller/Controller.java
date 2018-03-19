package edu.nyuad.androidgames.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.nyuad.boardgames.Complica;
import edu.nyuad.boardgames.ConnectFour;
import edu.nyuad.boardgames.TicTacToe;
import edu.nyuad.boardgames.Game;
import edu.nyuad.boardgames.GameIndexOutOfBoundsException;

import edu.nyuad.boardgames.GameStateException;

public class Controller {
	private Scanner scan;
	private Game game;
	private final boolean requireRow;
	private final boolean requireColumn;
	
	public Controller(Game game) {
		this.scan = new Scanner(System.in);
		this.game = game;
		this.requireRow = game instanceof TicTacToe;
		this.requireColumn = game instanceof TicTacToe || game instanceof ConnectFour || game instanceof Complica;
	}
	
	public void round() {
		// receive user input of their desire column and place chip in board
		boolean pass = false;
		while (!pass) {
			try {
				int row = 0, col = 0;
				if (this.requireRow) {
					System.out.println("Choose a row: ");
					row = scan.nextInt();
				}
				if (this.requireColumn) {
					System.out.println("Choose a column: ");
					col = scan.nextInt();
				}
				game.placeChip(row, col);
				pass = true;
			} catch (InputMismatchException e) {
				System.out.println("Error: Wrong type of input");
				this.scan.skip("[^0-9]");
				e.printStackTrace();
			} catch (GameIndexOutOfBoundsException e) {
				System.out.println("Error: That place doesn't exist. Try another one");
			} catch (GameStateException e) {
				System.out.println("Error: Check that the game is running");
				e.printStackTrace();
			} catch (UnsupportedOperationException e) {
				// Do Nothing
			}
		}
	}
}
