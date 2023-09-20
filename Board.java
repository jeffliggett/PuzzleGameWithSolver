import java.util.*;

public class Board implements Comparable<Board> {
	public boolean[][] b = { { true, true, true, true, true, },
			{ true, false, false, false, true },
			{ true, false, false, false, true },
			{ true, false, false, false, true },
			{ true, true, true, true, true, }
	};
	public HashSet<String> boards = new HashSet<>();
	public HashMap<Integer, Character> keys = new HashMap<>();
	public HashMap<Character, Integer> moveKeys = new HashMap<>();

	/**
	 * Construct a puzzle board by beginning with a solved board and then
	 * making a number of random moves. Moves that don't change how the board
	 * looks are possible.
	 */
	public Board(int moves) {
		keys.put(0, 'a');
		keys.put(1, 'b');
		keys.put(2, 'c');
		keys.put(3, 'd');
		keys.put(4, 'e');
		keys.put(5, 'A');
		keys.put(6, 'B');
		keys.put(7, 'C');
		keys.put(8, 'D');
		keys.put(9, 'E');
		keys.put(10, 'v');
		keys.put(11, 'w');
		keys.put(12, 'x');
		keys.put(13, 'y');
		keys.put(14, 'z');
		keys.put(15, 'V');
		keys.put(16, 'W');
		keys.put(17, 'X');
		keys.put(18, 'Y');
		keys.put(19, 'Z');
		Board gameBoard = new Board(b);
		Random rand = new Random();
		for (int i = 0; i < moves; i++) {
			int randMove = rand.nextInt(20);
			char moveChar = keys.get(randMove);
			gameBoard.move(moveChar);
			this.b = gameBoard.b;
		}
		boards.add(gameBoard.toString());
	}

	/**
	 * Checks if the board is solved.
	 */
	public boolean isSolved() {
		boolean[][] solvedB = { { true, true, true, true, true },
				{ true, false, false, false, true },
				{ true, false, false, false, true },
				{ true, false, false, false, true },
				{ true, true, true, true, true } };
		Board solvedBoard = new Board(solvedB);
		if (this.toString().equals(solvedBoard.toString()))
			return true;
		return false;
	}

	/**
	 * Construct a puzzle board using a 2D array of booleans to indicate which cells
	 * are filled and which are empty.
	 */
	public Board(boolean[][] b) {
		keys.put(0, 'a');
		keys.put(1, 'b');
		keys.put(2, 'c');
		keys.put(3, 'd');
		keys.put(4, 'e');
		keys.put(5, 'A');
		keys.put(6, 'B');
		keys.put(7, 'C');
		keys.put(8, 'D');
		keys.put(9, 'E');
		keys.put(10, 'v');
		keys.put(11, 'w');
		keys.put(12, 'x');
		keys.put(13, 'y');
		keys.put(14, 'z');
		keys.put(15, 'V');
		keys.put(16, 'W');
		keys.put(17, 'X');
		keys.put(18, 'Y');
		keys.put(19, 'Z');
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				this.b[i][j] = b[i][j];
			}
		}
	}

	/**
	 * Makes a move on the board the board. Moves 'a' through 'e' shift the given
	 * row
	 * to the left while moves 'A' through 'E' shift the given row to the right.
	 * Moves 'v' through 'z' shift the given column down while moves 'V' through 'Z'
	 * shift the given column up.
	 */
	public void move(char m) {
		// if ()
		moveKeys.put('a', 0);
		moveKeys.put('b', 1);
		moveKeys.put('c', 2);
		moveKeys.put('d', 3);
		moveKeys.put('e', 4);
		moveKeys.put('A', 5);
		moveKeys.put('B', 6);
		moveKeys.put('C', 7);
		moveKeys.put('D', 8);
		moveKeys.put('E', 9);
		moveKeys.put('v', 10);
		moveKeys.put('w', 11);
		moveKeys.put('x', 12);
		moveKeys.put('y', 13);
		moveKeys.put('z', 14);
		moveKeys.put('V', 15);
		moveKeys.put('W', 16);
		moveKeys.put('X', 17);
		moveKeys.put('Y', 18);
		moveKeys.put('Z', 19);
		int choice = moveKeys.get(m);
		if (choice < 5) {
			boolean temp = b[choice][4];
			b[choice][4] = b[choice][0];
			b[choice][0] = b[choice][1];
			b[choice][1] = b[choice][2];
			b[choice][2] = b[choice][3];
			b[choice][3] = temp;
		} else if (choice < 10) {
			choice -= 5;
			boolean temp = b[choice][0];
			b[choice][0] = b[choice][4];
			b[choice][4] = b[choice][3];
			b[choice][3] = b[choice][2];
			b[choice][2] = b[choice][1];
			b[choice][1] = temp;
		} else if (choice < 15) {
			choice -= 10;
			boolean temp = b[0][choice];
			b[0][choice] = b[4][choice];
			b[4][choice] = b[3][choice];
			b[3][choice] = b[2][choice];
			b[2][choice] = b[1][choice];
			b[1][choice] = temp;
		} else if (choice < 20) {
			choice -= 15;
			boolean temp = b[4][choice];
			b[4][choice] = b[0][choice];
			b[0][choice] = b[1][choice];
			b[1][choice] = b[2][choice];
			b[2][choice] = b[3][choice];
			b[3][choice] = temp;
		}

	}

	/**
	 * Returns {@code true} if a given cell of the board is filled and {@code false}
	 * otherwise. The top row (corresponding to 'a' moves) is row 0. The leftmost
	 * column (corresponding to 'v' moves) is column 0.
	 */
	public boolean isFilled(int row, int col) {
		return b[row][col];
	}

	public String toString() {
		StringBuilder boardS = new StringBuilder();
		int n = 0;
		String lower = "abcde";
		String upper = "ABCDE";
		boardS.append(" V W X Y Z ");
		for (boolean[] i : b) {
			boardS.append("\n");
			boardS.append(" +-+-+-+-+-+ ");
			boardS.append("\n");
			boardS.append(lower.charAt(n) + "|");
			for (boolean j : i) {
				if (j)
					boardS.append("*|");
				else
					boardS.append(" |");
			}
			boardS.append(upper.charAt(n));
			n += 1;
		}
		boardS.append("\n");
		boardS.append(" +-+-+-+-+-+ ");
		boardS.append("\n");
		boardS.append(" v w x y z ");
		return boardS.toString();
	}

	public int compareTo(Board b) {
		return this.toString().compareTo(b.toString());
	}

	public boolean equals(Board b) {
		return this.toString().equals(b.toString());
	}
}