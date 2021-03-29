/**
 * 
 */
package com.snakeandladder.main;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Vaibhav Pensalwar
 *
 */
public class SnakeAndLadder {
	public static final String TEXT_RESET = "\u001B[0m";
	public static final String TEXT_BLACK = "\u001B[30m";
	public static final String TEXT_RED = "\u001B[31m";
	public static final String TEXT_GREEN = "\u001B[32m";
	public static final String TEXT_YELLOW = "\u001B[33m";
	public static final String TEXT_BLUE = "\u001B[34m";
	public static final String TEXT_PURPLE = "\u001B[35m";
	public static final String TEXT_CYAN = "\u001B[36m";
	public static final String TEXT_WHITE = "\u001B[37m";

	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static final Integer WIN_POSITION = 100;
	public static final String Y = "Y";
	public static final String R = "r";
	public static final HashMap<Integer, Integer> LADDERS = new HashMap<Integer, Integer>();
	public static final HashMap<Integer, Integer> SNAKES = new HashMap<Integer, Integer>();
	Scanner scanner = null;

	public static void main(String args[]) {

		/**
		 * ladders to and from
		 */
		LADDERS.put(7, 33);
		LADDERS.put(37, 85);
		LADDERS.put(51, 72);
		LADDERS.put(63, 99);

		/***
		 * snakes to and from.
		 */
		SNAKES.put(36, 19);
		SNAKES.put(65, 35);
		SNAKES.put(87, 32);
		SNAKES.put(97, 21);

		SnakeAndLadder lad = new SnakeAndLadder();
		lad.displayDashboard();
		/**
		 * To start the game.
		 */
		lad.startGame();
	}

	/**
	 * Displaying Game board
	 */
	void displayDashboard() {

		for (int i = 100; i >= 1; i--) {
			if (i % 10 == 0) {
				System.out.println(TEXT_PURPLE + i);
			} else {
				System.out.print(TEXT_PURPLE + i + " ");
			}
		}
	}

	/***
	 * This method checks that whether player has reached to 100 mark which is
	 * wining position.
	 * 
	 * @param position
	 * @return
	 */
	public boolean checkWins(int position) {
		return position == WIN_POSITION;
	}

	/***
	 * Generating random number in between range of 1 to 6 as dice has 6 faces.
	 * 
	 * @return
	 */
	public int rollDice() {
		Random random = new Random();
		int rand = random.nextInt(6) + 1;
		return rand;
	}

	/***
	 * THis method will return the new position of which also checks whether it got
	 * a ladder or bite by Snake.
	 * 
	 * @param previousPosition
	 * @param diceValue
	 * @return
	 */
	int getPlayerPosition(int previousPosition, int diceValue) {

		int newPosition = previousPosition + diceValue;

		// to stop game at 100 position
		if (newPosition > WIN_POSITION) {
			return previousPosition;
		}

		// Check whether got a lucky to get a ladder.
		if (LADDERS.get(newPosition) != null) {
			System.out.println("You got a ladder.");
			return newPosition = LADDERS.get(newPosition);
		}

		// Check whether got unlucky to have snake.
		if (SNAKES.get(newPosition) != null) {
			System.out.println("Oh you caught by a Snake.");
			return newPosition = SNAKES.get(newPosition);
		}
		return newPosition;
	}

	/***
	 * This method is used to start the game and takes the input from user about
	 * starting a game.
	 */
	public void startGame() {
		int firstPlayer = 0;
		scanner = new Scanner(System.in);
		String str = null;
		int diceValue = 0;

		System.out.println(TEXT_BLACK + "\n\nFIRST PLAYER TURN");
		System.out.println(TEXT_BLACK + "Press R to roll Dice");
		str = scanner.next();

		while (R.equals(str)) {

			diceValue = rollDice();

			firstPlayer = getPlayerPosition(firstPlayer, diceValue);
			System.out.println("First Player :: " + firstPlayer);
			System.out.println("------------------");
			if (checkWins(firstPlayer)) {
				System.out.println(TEXT_GREEN + "-----------------------");
				System.out.println(TEXT_GREEN + "!!!First player wins!!!");
				System.out.println(TEXT_GREEN + "-----------------------");
				restartGame();
				return;
			}

		}
		if (str != null && !"r".equals(str)) {
			System.out.println(TEXT_CYAN + "-------------------------------");
			System.out.println(TEXT_CYAN + "Wrong choice, please try again.");
			System.out.println(TEXT_CYAN + "-------------------------------");
			startGame();
		}

		scanner.close();
	}

	/***
	 * To restart the game.
	 */
	void restartGame() {
		scanner = new Scanner(System.in);
		String str = null;
		System.out.println(TEXT_RESET + "----------------------------");
		System.out.println(TEXT_RESET + "Do you want to play again?");
		System.out.println(TEXT_BLACK + "Press 'Y' or 'N'");
		System.out.println(TEXT_RESET + "----------------------------");
		str = scanner.next();

		if (Y.equalsIgnoreCase(str)) {
			startGame();
		} else {
			System.out.println(TEXT_RESET + "----------------------------");
			System.out.println(TEXT_RESET + "Thank you, visit again.");
			System.out.println(TEXT_RESET + "----------------------------");
			System.exit(1);
		}
		//closing resources.
		scanner.close();
	}
}
