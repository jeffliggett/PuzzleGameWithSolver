import java.util.*;

public class Game {
	public static void main(String[] args) {
		Scanner newScanner = new Scanner(System.in);
		System.out.println("What difficulty do you want (1-9): ");
		int amt = Integer.valueOf(newScanner.nextLine());
		Board gameBoard = new Board(amt);
		while (gameBoard.isSolved()) {
			gameBoard = new Board(amt);
		}
		while (!gameBoard.isSolved()) {
			System.out.println(gameBoard);
			System.out.println("\n");
			System.out.println("Enter a letter for your move: ");
			String moveCharS = newScanner.nextLine();
			char moveChar = moveCharS.charAt(0);
			gameBoard.move(moveChar);
			if (gameBoard.boards.contains(gameBoard.toString())) {
				System.out.println("You repeated a board position.  You LOSE");
				break;
			} else {
				gameBoard.boards.add(gameBoard.toString());
			}
		}
		if (gameBoard.isSolved())
			System.out.println("GOOD JOB!");
		newScanner.close();
	}
}