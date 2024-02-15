import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
        private int gridSize;
        private WeightedQuickUnionUF unionFind;
        private int openSitesCounter;
        private boolean[][] grid;
        private int topVirtualSite;
        private int bottomVirtualSite;

        public Percolation(int n) {
                if (n <= 0) {
                        throw new IllegalArgumentException("Invalid grid size");
                }
                openSitesCounter = 0;
                gridSize = n;
                unionFind = new WeightedQuickUnionUF((n * n) + 2); // Add two for the virtual sites
                grid = new boolean[n][n];
                topVirtualSite = n * n; // Index of the virtual top site
                bottomVirtualSite = (n * n) + 1; // Index of the virtual bottom site

                // Connect top row sites to topVirtualSite
                for (int col = 1; col <= n; col++) {
                        unionFind.union(xyTo1D(1, col), topVirtualSite);
                }

                // Connect bottom row sites to bottomVirtualSite
                for (int col = 1; col <= n; col++) {
                        unionFind.union(xyTo1D(n, col), bottomVirtualSite);
                }
        }

        private int xyTo1D(int row, int col) {
                return (row - 1) * gridSize + (col - 1); // Adjust indices to 0-based
        }

        private void checkForNeighbours(int row, int col) {
                // Check right neighbour
                if (col < gridSize && grid[row - 1][col]) {
                        unionFind.union(xyTo1D(row, col + 1), xyTo1D(row, col));
                }
                // Check left neighbour
                if (col > 1 && grid[row - 1][col - 2]) {
                        unionFind.union(xyTo1D(row, col - 1), xyTo1D(row, col));
                }
                // Check bottom neighbour
                if (row < gridSize && grid[row][col - 1]) {
                        unionFind.union(xyTo1D(row + 1, col), xyTo1D(row, col));
                }
                // Check top neighbour
                if (row > 1 && grid[row - 2][col - 1]) {
                        unionFind.union(xyTo1D(row - 1, col), xyTo1D(row, col));
                }
        }

        public void open(int row, int col) {
                if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
                        throw new IllegalArgumentException("Invalid grid indices");
                }
                if (!grid[row - 1][col - 1]) {
                        grid[row - 1][col - 1] = true;
                        openSitesCounter++;
                        checkForNeighbours(row, col);
                }
        }

        public boolean isOpen(int row, int col) {
                if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
                        throw new IllegalArgumentException("Invalid grid indices");
                }
                return grid[row - 1][col - 1];
        }

        public boolean isFull(int row, int col) {
                if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
                        throw new IllegalArgumentException("Invalid grid indices");
                }
                return isOpen(row, col) && unionFind.find(xyTo1D(row, col)) == unionFind.find(topVirtualSite);
        }

        public int numberOfOpenSites() {
                return openSitesCounter;
        }

        public boolean percolates() {
                return unionFind.find(topVirtualSite) == unionFind.find(bottomVirtualSite);
        }
}
