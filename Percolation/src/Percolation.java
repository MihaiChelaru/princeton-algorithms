import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private int openSites;
    private WeightedQuickUnionUF unionFArray;
    private byte[] status;
    private boolean percolates;

    /**
     * Create an n by n grid, with all sites blocked, and initialize the
     * grid_size attribute.
     */
    public Percolation(int n) {
        // Validate input
        if (n <= 0) {
            throw new IllegalArgumentException("n must be >= 1");
        }

        this.n = n;
        this.openSites = 0;
        this.unionFArray = new WeightedQuickUnionUF(n * n);
        this.status = new byte[n * n];
        this.percolates = false;

        // Set connected status of top and bottom sites
        for (int i = 1; i <= n; i++) {
            status[xyTo1D(1, i)] = (byte) (status[xyTo1D(1, i)] | 2);
            status[xyTo1D(n, i)] = (byte) (status[xyTo1D(n, i)] | 4);
        }
    }

    /**
     * Convert 2D mapping from grid to 1D mapping for union-find data structure
     */
    private int xyTo1D(int row, int col) {
        validate(row, col);
        return (row - 1) * (this.n) + col - 1;
    }


    /**
     * Checks the if a given bit in the status byte array is set
     *
     * @param index position in status array
     * @param stat  bit position, 0 for OPEN, 1 for CONNECTED_TO_TOP, 2 for
     *              CONNECTED_TO_BOTTOM
     * @return boolean value corresponding to whether bit is set
     */
    private boolean getStatus(int index, int stat) {
        return ((status[index] >> stat) & 1) == 1;
    }

    /**
     * Validation method for indices, throws IllegalArgumentException
     */
    private void validate(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw new IllegalArgumentException("Index outside the prescribed " +
                    "range of 1 to " + (this.n));
        }
    }

    /**
     * Connects cell to adjacent open cell if adjacent cell is also open
     */
    private void connectAdjacent(int row, int col, int rowAdj, int colAdj) {
        if (rowAdj >= 1 && rowAdj <= n && colAdj >= 1 && colAdj <= n &&
                isOpen(rowAdj, colAdj)) {
            int siteIndex = xyTo1D(row, col);
            int adjIndex = xyTo1D(rowAdj, colAdj);
            int siteRoot = unionFArray.find(siteIndex);
            int adjRoot = unionFArray.find(adjIndex);
            if (siteRoot != adjRoot) {
                byte newRootStatus = (byte) (status[siteRoot] | status[adjRoot]);
                unionFArray.union(siteIndex, adjIndex);
                status[unionFArray.find(siteIndex)] = newRootStatus;
            }
        }
    }


    /**
     * Open site at (row, col) if not open already, increment openSites by 1,
     * and connect the site to any adjacent open sites
     */
    public void open(int row, int col) {
        validate(row, col);
        // If not open already, open the site, then connect to adjacent open
        // sites
        if (!isOpen(row, col)) {
            int siteIndex = xyTo1D(row, col);
            status[siteIndex] = (byte) (status[siteIndex] | 1);
            openSites++;
            // Check if site is connected to the top or bottom
            // Connect sites to left, right, above, and below
            connectAdjacent(row, col, row - 1, col);
            connectAdjacent(row, col, row + 1, col);
            connectAdjacent(row, col, row, col - 1);
            connectAdjacent(row, col, row, col + 1);
            int siteRoot = unionFArray.find(siteIndex);
            if (getStatus(siteRoot, 1) && getStatus(siteRoot,
                    2)) {
                this.percolates = true;
            }
        }
    }

    /**
     * Is the site at (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return getStatus(xyTo1D(row, col), 0);
    }

    /**
     * Is the site at (row, col) full (i.e. connected to the top via
     * neighbouring sites)?
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        return isOpen(row, col) && getStatus(unionFArray.find(xyTo1D(row,
                col)), 1);
    }

    /**
     * Returns number of open sites in the grid
     */
    public int numberOfOpenSites() {
        return this.openSites;
    }

    /**
     * Tests if system percolates by checking if bottom is connected to the
     * top via WeightedQuickUnionUF.connected()
     */
    public boolean percolates() {
        return this.percolates;
    }
}
