import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;

public class Solver {
    private List<Board> solution;
    private boolean isSolvable;
    private int moves;

    private class SearchNode {
        private final SearchNode previous;
        private final int moves;
        private final Board board;

        public SearchNode(SearchNode previous_, Board board_) {
            board = board_;
            previous = previous_;
            if (previous == null) {
                moves = 0;
            } else {
                moves = previous.moves() + 1;
            }
        }

        public Board board() {
            return board;
        }

        public boolean isGoal() {
            return board.isGoal();
        }

        public SearchNode previous() {
            return previous;
        }

        public int priority() {
            return board.manhattan() + moves;
        }

        public int moves() {
            return moves;
        }
    }

    private class BoardComparator implements Comparator<SearchNode> {
        public int compare(SearchNode n1, SearchNode n2) {
            return n1.priority() - n2.priority();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<SearchNode> pq = new MinPQ<>(new BoardComparator());
        MinPQ<SearchNode> twinPq = new MinPQ<>(new BoardComparator());

        SearchNode n = new SearchNode(null, initial);
        pq.insert(n);

        n = new SearchNode(null, initial.twin());
        twinPq.insert(n);

        while (true) {
            n = move(pq);
            if (n.isGoal()) {
                getSolution(n);
                isSolvable = true;
                break;
            }

            n = move(twinPq);
            if (n.isGoal()) {
                isSolvable = false;
                break;
            }
        }
    }

    private void getSolution(SearchNode n) {
        moves = n.moves();
        Board[] arr = new Board[n.moves + 1];
        for (int i = n.moves ; i >= 0 ; i--) {
            arr[i] = n.board();
            if (i > 0) {
                n = n.previous();
            }
        }
        solution = Arrays.asList(arr);
    }

    private SearchNode move(MinPQ<SearchNode> q) {
        SearchNode node = q.delMin();

        Board previousBoard = null;
        if (node.previous() != null) {
            previousBoard = node.previous.board();
        }

        if (!node.isGoal()) {
            for (Board neighbor: node.board().neighbors()) {

                if (!neighbor.equals(previousBoard)) {
                    q.insert(new SearchNode(node, neighbor));
                }
            }
        }
        return node;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable) {
            return moves;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) {
            return null;
        }
        return solution;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();

        Board initial = new Board(blocks);
        Solver solver = new Solver(initial);
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
