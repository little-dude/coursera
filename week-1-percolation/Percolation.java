/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    N/A
 *  Dependencies:
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    // an extra virtual site connected to all the opened sites in the top row
    private final static int TOP = 0;
    // keep track of opened sites
    private boolean[][] opened;
    // an extra virtual site connected to all the opened sites in the bottom row
    private final int bottom;
    // size of the grid
    private final int size;
    // Weighted Union-Find With Path Compression
    private final WeightedQuickUnionUF unionFind;
    // keep track of the number of opened sites
    private int numberOfOpenSites;

    // Create n-by-n grid, with all sites blocked.
    public Percolation(int n) {
        size = n;
        bottom = size * size + 1;
        unionFind = new WeightedQuickUnionUF(size * size + 2);
        opened = new boolean[size][size];
    }

    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

     // open site (i, j)
    public void open(int i, int j) {
        validateSite(i, j);

        if (opened[i-1][j-1]) {
            return;
        }

        opened[i-1][j-1] = true;
        numberOfOpenSites++;

        if (i == 1) {
            unionFind.union(getIndex(i, j), TOP);
        }

        if (i == size) {
            unionFind.union(getIndex(i, j), bottom);
        }

        if (j > 1 && isOpen(i, j - 1)) {
            unionFind.union(getIndex(i, j), getIndex(i, j - 1));
        }

        if (j < size && isOpen(i, j + 1)) {
            unionFind.union(getIndex(i, j), getIndex(i, j + 1));
        }

        if (i > 1 && isOpen(i - 1, j)) {
            unionFind.union(getIndex(i, j), getIndex(i - 1, j));
        }

        if (i < size && isOpen(i + 1, j)) {
            unionFind.union(getIndex(i, j), getIndex(i + 1, j));
        }
    }

    // return true if the site (i, j) is open
    public boolean isOpen(int i, int j) {
        validateSite(i, j);
        return opened[i-1][j-1];
    }

    // return true if the site (i, j) is full
    public boolean isFull(int i, int j) {
        validateSite(i, j);
        if (!isOpen(i, j)) {
            return false;
        }
        return unionFind.connected(TOP, getIndex(i, j));
    }

    // return true if the given site is valid
    private boolean isValidSite(int i, int j)  {
        return (i > 0 && i <= size && j > 0 && j <= size);
    }

    // throw an exception if the site is invalid
    private void validateSite(int i, int j) {
        if (!isValidSite(i, j)) {
            throw new IllegalArgumentException(String.format("Invalid site (%d, %d)", i, j));
        }
    }

    // return true if the system percolates
    public boolean percolates() {
        return unionFind.connected(TOP, bottom);
    }

    // return the index of the given site in the UnionFind object
    private int getIndex(int i, int j) {
        validateSite(i, j);
        return size * (i - 1) + j;
    }
}
