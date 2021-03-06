package percolation;

import edu.princeton.cs.algs4.QuickUnionUF;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private WeightedQuickUnionUF hlekkur;
	//private QuickUnionUF hlekkur;
	private boolean[][] opened;
	private int size;
	private int top;
	private int bottom;
	private int openedSites;

	// create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
    	if(N < 1) {
    		throw new java.lang.IllegalArgumentException("N cannot be equal to or less than 0.");
    	}
    	hlekkur = new WeightedQuickUnionUF(N * N + 2);
    	//hlekkur = new QuickUnionUF(N * N + 2);
    	opened = new boolean[N][N];
    	size = N;
    	top = 0;
    	bottom = N * N + 1;
    	openedSites = 0;
    }

	// breytir fr� 2D � 1D
	private int into1D(int row, int col) {
		villa(row, col);
		return (row * size) + col + 1;
	}
	
	// g�ir ef row e�a col eru fyrir utan fylkisins
	private void villa(int row, int col) throws IndexOutOfBoundsException {
		if(row < 0 || col < 0 || row >= size || col >= size) {
			throw new IndexOutOfBoundsException("Col/Row is out of bounds");
		}
	}
	
    // open the site (row, col) if it is not open
    public void open(int row, int col) {
		villa(row, col);
		
		if(isOpen(row, col)) {
			return;
		}
		
		openedSites++;
    	opened[row][col] = true;
    	
    	if(row == 0) {
    		hlekkur.union(into1D(row, col), top);
    	}
    	
    	if(row == (size - 1)) {
    		hlekkur.union(into1D(row, col), bottom);
    	}
    	
    	if(col > 0 && isOpen(row, (col - 1))) {
    		hlekkur.union(into1D(row, col), into1D(row, col - 1));
    	}
    	
    	if(col < (size - 1) && isOpen(row, col + 1)) {
    		hlekkur.union(into1D(row, col), into1D(row, col + 1));
    	}
    	
    	if(row > 0 && isOpen(row - 1, col)) {
    		hlekkur.union(into1D(row, col), into1D(row - 1, col));
    	}
    	
    	if(row < (size - 1) && isOpen(row + 1, col)) {
    		hlekkur.union(into1D(row, col), into1D(row + 1, col));
    	}
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
		villa(row, col);
		
    	return opened[row][col];
    }
    
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
		villa(row, col);
		
    	return hlekkur.connected(top, into1D(row, col));
    }
    
    // number of open sites
    public int numberOfOpenSites() {
    	return openedSites;
    }
    
    // does the system percolate?
    public boolean percolates() {
    	return hlekkur.connected(top, bottom);
    }
    
    // unit testing (required)
    public static void main(String[] args) {
    	Percolation test = new Percolation(2);
    	test.open(0, 1);
    	test.open(1, 1);
    	StdOut.println(test.percolates());
    }
}
