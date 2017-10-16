import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats{
    public double[] threshold;


    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        threshold = new double [trials];
        for (int i=0;i<trials;i++){
            Percolation p=new Percolation(n);
            while (!p.percolates()) {
                int c = StdRandom.uniform(1,n+1);
                int r = StdRandom.uniform(1,n+1);
                while(p.isOpen(c,r)){
                    c = StdRandom.uniform(1,n+1);
                    r = StdRandom.uniform(1,n+1);
                }
                p.open(c,r);
            }
            threshold[i]=(double)p.numberOfOpenSites()/(n*n);
        }




    }
    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(this.threshold);

    }
    // sample standard deviation of percolation threshold
    public double stddev()   {
        return StdStats.stddev(this.threshold);

    }
    // low  endpoint of 95% confidence interval
    public double confidenceLo()     {
        return mean()-1.96*stddev()/Math.sqrt(threshold.length);

    }
    // high endpoint of 95% confidence interval
    public double confidenceHi()   {
        return  mean()+1.96*stddev()/Math.sqrt(threshold.length);

    }
    // test client (described below)
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(10,100);

        System.out.println("mean="+ percolationStats.mean());
        System.out.println("stddev="+ percolationStats.stddev());
        System.out.println("95%% confidence Interval="+percolationStats.confidenceLo()+"  "+ percolationStats.confidenceHi());

    }

}