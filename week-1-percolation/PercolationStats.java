/******************************************************************************
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats 100 100
 *  Dependencies: Percolation.java
 *
 *  This program takes a grid size and a number of trials as arguments.
 ******************************************************************************/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // size of the grid
    private final int gridSize;

    // number of times we run the experiment
    private final int nbTrials;

    // results
    private double[] outcome;

    public PercolationStats(int gridSize, int nbTrials) {
        if (gridSize <= 0) {
            throw new IllegalArgumentException("grid size must be strictly positive");
        }
        if (nbTrials <= 0) {
            throw new IllegalArgumentException("number of trials must be strictly positive");
        }
        this.gridSize = gridSize;
        this.nbTrials = nbTrials;
        this.outcome = new double[nbTrials];
        run();
    }

    private void run() {
        for (int t = 0; t < nbTrials; t++) {
            int count = 0;
            Percolation percolation = new Percolation(gridSize);

            while (!percolation.percolates()) {
                int siteIndex = StdRandom.uniform(gridSize * gridSize);
                // siteIndex = i*gridSize + j;
                int i = siteIndex / gridSize;
                int j = siteIndex - i * gridSize;

                if (percolation.isOpen(i+1, j+1)) {
                    continue;
                }
                percolation.open(i+1, j+1);
                count++;
            }
            outcome[t] = count/((double) gridSize*gridSize);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(outcome);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(outcome);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96*stddev()/Math.sqrt(nbTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96*stddev()/Math.sqrt(nbTrials);
    }

    public static void main(String[] args) {
        int gridSize = Integer.parseInt(args[0]);
        int nbTrials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(gridSize, nbTrials);
        StdOut.printf("mean                    = %f\n", stats.mean());
        StdOut.printf("stddev                  = %f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(), stats.confidenceHi());
    }
}
