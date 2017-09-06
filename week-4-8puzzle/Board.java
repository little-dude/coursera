/******************************************************************************
 *  Compilation:  javac Board.java
 *  Execution:    N/A
 *  Dependencies:
 ******************************************************************************/
import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;


public class Board {
    private final int N;
    private int[][] board;
    private int hamming;
    private int manhattan;
    private int zero;

    // FIXME: this is too complicated
    // The idea was to iterate lazily over the neighbors, but there is little
    // gain since we always have at most 4 neighbors. A queue or stack would be
    // just as good, and much simpler.
    private class NeighborsIterator implements Iterator<Board> {
        private static final int UP = 1 << 0;
        private static final int RIGHT = 1 << 1;
        private static final int DOWN = 1 << 2;
        private static final int LEFT = 1 << 3;

        private int neighbors;
        private final int zero_i;
        private final int zero_j;
        private int count;

        public NeighborsIterator() {
            zero_i = getZero() / N;
            zero_j = getZero() % N;
            count = 0;

            neighbors = UP + RIGHT + DOWN + LEFT;
            if (zero_i == 0) {
                neighbors -= UP;
            }
            if (zero_i == N - 1) {
                neighbors -= DOWN;
            }
            if (zero_j == 0) {
                neighbors -= LEFT;
            }
            if (zero_j == N - 1) {
                neighbors -= RIGHT;
            }
        }

        public Board next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (hasNeighbor(UP) && ((count & UP) == 0)) {
                count += UP;
                return getNeighbor(zero_i - 1, zero_j);
            }

            if (hasNeighbor(LEFT) && ((count & LEFT) == 0)) {
                count += LEFT;
                return getNeighbor(zero_i, zero_j - 1);
            }
            if (hasNeighbor(DOWN) && ((count & DOWN) == 0)) {
                count += DOWN;
                return getNeighbor(zero_i + 1, zero_j);
            }
            if (hasNeighbor(RIGHT) && ((count & RIGHT) == 0)) {
                count += RIGHT;
                return getNeighbor(zero_i, zero_j + 1);
            }
            throw new NoSuchElementException();
        }

        private boolean hasNeighbor(int direction) {
            return ((neighbors & direction) != 0);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return (neighbors > count);
        }

        private Board getNeighbor(int i, int j) {
            swap(zero_i, zero_j, i, j);
            Board neighbor = new Board(board);
            swap(zero_i, zero_j, i, j);
            return neighbor;
        }
    }

    private class NeighborsIterable implements Iterable<Board> {
        public Iterator<Board> iterator() {
            return new NeighborsIterator();
        }
    }

    public Board(int[][] blocks) {
        hamming = -1;
        manhattan = -1;
        zero = -1;
        N = blocks.length;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        if (hamming != -1) {
            return hamming;
        }
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N -1 && j == N -1) {
                    continue;
                }
                int expected = i * N + j + 1;
                if (board[i][j] != expected) {
                    distance++;
                }
            }
        }
        hamming = distance;
        return hamming;
    }

    public int manhattan() {
        if (manhattan != -1) {
            return manhattan;
        }
        int distance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int value = board[i][j];
                if (value == 0) {
                    continue;
                }
                int expected_i = (value - 1) / N;
                if (i < expected_i) {
                    distance += expected_i - i;
                } else {
                    distance += i - expected_i;
                }
                int expected_j = (value - 1) % N;
                if (j < expected_j) {
                    distance += expected_j - j;
                } else {
                    distance += j - expected_j;
                }
            }
        }
        manhattan = distance;
        return manhattan;
    }

    public boolean isGoal() {
        return (manhattan() == 0);
    }

    public Board twin() {
        Board twin;
        if (board[0][0] == 0) {
            // if (0, 0) is blank, swap (0,1) and (1,1)
            swap(0, 1, 1, 1);
            twin = new Board(board);
            swap(0, 1, 1, 1);
        } else {
            if (board[0][1] == 0) {
                // if (0, 1) is blank, swap (0,0) and (0,1)
                swap(0, 0, 1, 0);
                twin = new Board(board);
                swap(0, 0, 1, 0);
            } else {
                // if (1, 0) is blank, swap (0,0) and (0,1)
                swap(0, 0, 0, 1);
                twin = new Board(board);
                swap(0, 0, 0, 1);
            }
        }
        return twin;
    }

    private void swap(int i, int j, int x, int y) {
        int tmp = board[i][j];
        board[i][j] = board[x][y];
        board[x][y] = tmp;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null) {
            return false;
        }
        if (getClass() != y.getClass()) {
            return false;
        }
        Board yBoard = (Board) y;
        return Arrays.deepEquals(board, yBoard.board);
    }

    private int getZero() {
        if (zero > -1) {
            return zero;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    zero = i * N + j;
                }
            }
        }
        return zero;
    }

    public Iterable<Board> neighbors() {
        return new NeighborsIterable();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}
