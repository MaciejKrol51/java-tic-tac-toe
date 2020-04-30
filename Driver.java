package sample;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {

		final int numPlayers = 2;
		Scanner key = new Scanner(System.in);

		System.out.print("\t\t\t======================\n\n");
		System.out.print("\t\t\tWelcome to Tic-Tac-Toe\n\n");
		System.out.print("\t\t\t======================\n\n");

		int size;
		Game g;
		Player[] players;
		boolean winner;

		String answer = "Y";
		while (answer.compareTo("Y") == 0) {
			System.out.print("Please enter the size of the board (between 3 - 9): ");
			size = key.nextInt();

			while(size < 3 || size > 9) {
				System.out.print("\nInvalid input... Please enter the size of the board (between 3 - 9): ");
				size = key.nextInt();
			}

			g = new Game(size);
			players = new Player[numPlayers];
			winner = false;

			for (int i = 0; i<players.length; i++) {
				System.out.print("Enter player "+(i+1)+" name: ");
				String name = key.next();

				System.out.print("Enter player "+(i+1)+" token: ");
				char token = key.next().charAt(0);
				players[i] = new Player(name,token);

				System.out.print("\n");
			}
			
			System.out.print("\n\n\t\t\tLet the game start...\n\n");
			while (!g.isFull() && !winner) {
				for (int i = 0; i<players.length; i++) {
					System.out.println(g);
					
					System.out.print("\nEnter row (character) of spot to mark: ");
					char row = key.next().toUpperCase().charAt(0);

					while (row < 'A' || row > 'A'+g.getSize()-1) {
						System.out.print("\nInvalid row. Please enter row (character) of spot to mark: ");
						row = key.next().toUpperCase().charAt(0);
						
					}
					
					System.out.print("Enter column of spot to mark: ");
					int col = key.nextInt();

					System.out.print("\n");

					boolean isMarked = players[i].mark(g, row, col);

					while (!isMarked) {
						System.out.print("A token already exists there, enter row of spot to mark: ");
						row = key.next().toUpperCase().charAt(0);

						System.out.print("Enter column of spot to mark: ");
						col = key.nextInt();

						isMarked = players[i].mark(g, row, col);

						System.out.print("\n");
					}

					if (g.didWin(players[i].gettoken())) {
						System.out.println(g);
						System.out.println(players[i].getName()+" is the winner!");
						winner = true;
						break;
					}
				}
			}
			if (g.isFull() && !winner) {
				System.out.print("It's a draw! Intense competition\n\n");
			}
			System.out.print("Would you like to play again (Y/N)?");
			answer = key.next().toUpperCase();
			winner = false;
		}
		System.out.print("\n\nHope to see you again...");
	}
}
