import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.math.*;

public class PercolationStats {

    private int trials;
    private int n;
    private double fraction;
    private double[] results;
    private int counter = 0;

    public PercolationStats(int n, int trials){
        this.trials = trials;
        this.n = n;
        this.results = new double[trials];
    }

    // Monte Carlo Simulation
    private void performSingleSimulation(int n){
        Percolation newGrid = new Percolation(n);
        while (!newGrid.percolates()){
            int row = StdRandom.uniformInt(1,n + 1);
            int col = StdRandom.uniformInt(1, n +1 );
            newGrid.open(row,col);
        }
        fraction = (double) newGrid.numberOfOpenSites() / (n*n);
        results[counter++] =  fraction;
    }

    private void performMultipleSimulations(int trials){
        for (int i = trials; i > 0; i--){
            performSingleSimulation(n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return (mean()) - ((1.96) / Math.sqrt(results.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return (mean()) + ((1.96) / Math.sqrt(results.length));
    }

    // test client (see below)
    public static void main(String[] args){
        PercolationStats test = new PercolationStats(100, 200);
        test.performMultipleSimulations(200);
        System.out.println("Mean: " + test.mean());
        System.out.println("Std: " + test.stddev());
        System.out.println("High: " + test.confidenceHi() );
        System.out.println("Low: " + test.confidenceLo() );

    }
}
