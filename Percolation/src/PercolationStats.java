import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] results;
    private final int trials;
    private final double mean;
    private final double stddev;
    private final double CONFIDENCE_95 = 1.96;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Invalid number for n or " +
                    "trials.");
        }
        this.results = new double[trials];
        this.trials = trials;
        int randRow;
        int randCol;
        for (int i = 0; i < trials; i++) {
            Percolation percGrid = new Percolation(n);
            while (!percGrid.percolates()) {
                do {
                    randRow = StdRandom.uniform(1, n + 1);
                    randCol = StdRandom.uniform(1, n + 1);
                }
                while (percGrid.isOpen(randRow, randCol));
                percGrid.open(randRow, randCol);
            }
            results[i] = (double) percGrid.numberOfOpenSites() / (n * n);
        }
        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
    }

    public double mean() {
        return this.mean;
    }

    public double stddev() {
        if (this.trials == 1) {
            return Double.NaN;
        } else {
            return this.stddev;
        }
    }

    public double confidenceLo() {
        if (this.trials == 1) {
            return Double.NaN;
        } else {
            return mean - (CONFIDENCE_95 * stddev / Math.sqrt(trials));
        }
    }

    public double confidenceHi() {
        if (this.trials == 1) {
            return Double.NaN;
        } else {
            return mean + (CONFIDENCE_95 * stddev / Math.sqrt(trials));
        }
    }

    public static void main(String[] args) {
        PercolationStats percSim =
                new PercolationStats(Integer.parseInt(args[0]),
                        Integer.parseInt(args[1]));
        System.out.println("mean                    = " + percSim.mean());
        System.out.println("stddev                  = " + percSim.stddev());
        System.out.println("95% confidence interval = [" +
                percSim.confidenceLo() + ", " + percSim.confidenceHi() + "]");
    }
}
