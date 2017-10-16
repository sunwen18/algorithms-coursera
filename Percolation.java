import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    public int[][] allsites;
    public boolean[] sitestatus;
    public int opensitenumber;
    public int number;
    public WeightedQuickUnionUF wqu;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n){
        if (n<=0)
            throw new IllegalArgumentException();
        number=n;
        wqu = new  WeightedQuickUnionUF (n*n+2);
        for (int i=0;i<n;i++){
            wqu.union(n*n,i);
            wqu.union(n*n+1,n*(n-1)+i);
        }
        opensitenumber=0;
        allsites=new int[n+1][n+1];
        sitestatus=new boolean[n*n];
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                allsites[i][j]=n*(i-1)+j-1;
            }
        }



    }
    // open site (row, col) if it is not open already and check around for union
    public void open(int row, int col){
        if(!this.isOpen(row,col)){
            sitestatus[allsites[row][col]]=true;
            opensitenumber += 1;
            if(row==1 && col==1 ){
                if (this.isOpen(1,2))
                    wqu.union(allsites[1][1],allsites[1][2]);
                if (this.isOpen(2,1))
                    wqu.union(allsites[1][1],allsites[2][1]);
            }
            else if(row==1 && col==number ){
                if (this.isOpen(1,number-1))
                    wqu.union(allsites[1][number],allsites[1][number-1]);
                if (this.isOpen(2,number))
                    wqu.union(allsites[1][number],allsites[2][number]);
            }
            else if(row==number && col==1 ){
                if (this.isOpen(number,2))
                    wqu.union(allsites[number][1],allsites[number][2]);
                if (this.isOpen(number-1,1))
                    wqu.union(allsites[number][1],allsites[number-1][1]);
            }
            else if(row==number && col==number ){
                if (this.isOpen(number-1,number))
                    wqu.union(allsites[number][number],allsites[number-1][number]);
                if (this.isOpen(number,number-1))
                    wqu.union(allsites[number][number],allsites[number][number-1]);
            }
            else if (row==1){
                if(this.isOpen(1,col+1))
                    wqu.union(allsites[1][col+1],allsites[1][col]);
                if (this.isOpen(1,col-1))
                    wqu.union(allsites[1][col-1],allsites[1][col]);
                if (this.isOpen(2,col))
                    wqu.union(allsites[2][col],allsites[1][col]);

            }
            else if (row==number){
                if(this.isOpen(number,col+1))
                    wqu.union(allsites[number][col+1],allsites[number][col]);
                if (this.isOpen(number,col-1))
                    wqu.union(allsites[number][col-1],allsites[number][col]);
                if (this.isOpen(number-1,col))
                    wqu.union(allsites[number-1][col],allsites[number][col]);

            }
            else if (col==1){
                if(this.isOpen(row-1,1))
                    wqu.union(allsites[row-1][1],allsites[row][1]);
                if (this.isOpen(row+1,1))
                    wqu.union(allsites[row+1][1],allsites[row][1]);
                if (this.isOpen(row,2))
                    wqu.union(allsites[row][2],allsites[row][1]);

            }
            else if (col==number){
                if(this.isOpen(row-1,number))
                    wqu.union(allsites[row-1][number],allsites[row][col]);
                if (this.isOpen(row+1,number))
                    wqu.union(allsites[row+1][number],allsites[row][col]);
                if (this.isOpen(row,number-1))
                    wqu.union(allsites[row][number-1],allsites[row][col]);

            }
            else{
                if(this.isOpen(row-1,col))
                    wqu.union(allsites[row-1][col],allsites[row][col]);
                if(this.isOpen(row+1,col))
                    wqu.union(allsites[row+1][col],allsites[row][col]);
                if (this.isOpen(row,col+1))
                    wqu.union(allsites[row][col+1],allsites[row][col]);
                if (this.isOpen(row,col-1))
                    wqu.union(allsites[row][col-1],allsites[row][col]);

            }


        }


    }



    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        if(sitestatus[allsites[row][col]])
            return true;
        else
            return false;

    }
    // is site (row, col) full? check if it is connected with virtual top site
    public boolean isFull(int row, int col){
        if (wqu.connected(number*number,allsites[row][col]))
            return true;
        else
            return false;
    }
    // number of open sites
    public int numberOfOpenSites(){
        return opensitenumber;

    }
    // does the system percolate? check the upper line and the down line if they have similar numbers
    public boolean percolates(){
        if (wqu.connected(number*number,number*number+1))
            return true;
        else
            return false;



    }
    // test client (optional)
    public static void main(String[] args){
        Percolation p=new Percolation(5);
        while (!p.percolates()) {
            int c = StdRandom.uniform(1,5+1);
            int r = StdRandom.uniform(1,5+1);
            while(p.isOpen(c,r)){
                c = StdRandom.uniform(1,5+1);
                r = StdRandom.uniform(1,5+1);
            }
            p.open(c,r);
        }
        System.out.println(p.numberOfOpenSites());


    }

}