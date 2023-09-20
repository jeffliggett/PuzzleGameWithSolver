import java.util.*;

public class Solver {
    /**
     * Solves a given puzzle represented as a 5-by-5 boolean array with
     * 16 trues and 9 falses.
     */
    public char[] solve(boolean[][] start) {

        // Create a board object with the initial configuration
        Board initialBoard = new Board(start);

        // Create a search node class to represent a node in the search tree
        class SearchNode {
            Board board;
            SearchNode parent;
            char move;
            int depth;

            public SearchNode(Board board, SearchNode parent, char move, int depth) {
                this.board = board;
                this.parent = parent;
                this.move = move;
                this.depth = depth;
            }
        }

        List<Character> validMoves = new ArrayList<>();
        validMoves.add('a');
        validMoves.add('b');
        validMoves.add('c');
        validMoves.add('d');
        validMoves.add('e');
        validMoves.add('A');
        validMoves.add('B');
        validMoves.add('C');
        validMoves.add('D');
        validMoves.add('E');
        validMoves.add('v');
        validMoves.add('w');
        validMoves.add('x');
        validMoves.add('y');
        validMoves.add('z');
        validMoves.add('V');
        validMoves.add('W');
        validMoves.add('X');
        validMoves.add('Y');
        validMoves.add('Z');

        char[] movesList = { 'a', 'b', 'c', 'd', 'e', 'A', 'B', 'C', 'D', 'E',
                'v', 'w', 'x', 'y', 'z', 'V', 'W', 'X', 'Y', 'Z' };
        // Create a queue to store search nodes
        Queue<SearchNode> queue = new LinkedList<>();

        // Create a set to store visited board configurations
        Set<String> visited = new HashSet<>();

        // Enqueue the initial search node
        SearchNode initialNode = new SearchNode(initialBoard, null, '\0', 0);
        queue.add(initialNode);
        visited.add(initialBoard.toString());

        // Start BFS
        while (!queue.isEmpty()) {
            // Dequeue a search node
            SearchNode currentNode = queue.poll();

            // Check if the current board is the goal configuration
            if (currentNode.board.isSolved()) {
                // Solution found, trace back the path and return the moves as an array
                List<Character> moves = new ArrayList<>();
                SearchNode node = currentNode;
                while (node.parent != null) {
                    moves.add(node.move);
                    node = node.parent;
                }
                Collections.reverse(moves);
                char[] solution = new char[moves.size()];
                for (int i = 0; i < moves.size(); i++) {
                    solution[i] = moves.get(i);
                }
                return solution;
            }

            // Generate all possible moves from the current board configuration
            for (char move : movesList) {
                Board newBoard = new Board(currentNode.board.b);
                newBoard.move(move);

                // Create a new search node
                SearchNode newNode = new SearchNode(newBoard, currentNode, move, currentNode.depth + 1);

                // Check if the new board configuration has been visited before
                if (!visited.contains(newBoard.toString())) {
                    // Enqueue the new search node
                    queue.add(newNode);
                    visited.add(newBoard.toString());
                }
            }
        }

        // No solution found
        return new char[0];
    }

    // A simple toy exmaple of how a Solver object is used.
    public static void main(String[] args) {
        boolean[][] b = {
                { true, true, true, true, true },
                { true, true, false, false, false },
                { true, false, false, false, true },
                { true, false, false, false, true },
                { true, true, true, true, true }
        };

        boolean[][] bb = {
                { true, true, true, true, true },
                { false, true, true, false, false },
                { true, false, false, false, true },
                { true, false, false, false, true },
                { true, true, true, true, true }
        };

        boolean[][] Xbb = {
                { true, true, true, true, true },
                { false, true, true, false, false },
                { true, false, true, false, true },
                { true, false, false, false, true },
                { true, true, false, true, true }
        };

        boolean[][] aXbb = {
                { true, true, true, true, true },
                { true, true, true, false, false },
                { false, false, true, false, true },
                { true, false, false, false, true },
                { true, true, false, true, true }
        };

        Solver solver = new Solver();
        char[] sol = solver.solve(b);
        System.out.println(Arrays.toString(sol));
        sol = solver.solve(Xbb);
        System.out.println(Arrays.toString(sol));
        sol = solver.solve(aXbb);
        System.out.println(Arrays.toString(sol));
    }
}
