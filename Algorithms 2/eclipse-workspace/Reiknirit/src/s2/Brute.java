package s2;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class Brute {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            points[i] = new Point(x, y);
        }
        Arrays.sort(points);
        
        for(int a = 0; a < n; a++) {
        	for(int b = a + 1; b < n; b++) {
        		for(int c = b + 1; c < n; c++) {
        			for(int d = c + 1; d < n; d++) {
        	        	Point p = points[a];
        	        	Point q = points[b];
        	        	Point r = points[c];
        	        	Point s = points[d];
        	        	
        	        	double slope1 = p.slopeTo(q);
        	        	double slope2 = q.slopeTo(r);
        	        	double slope3 = r.slopeTo(s);
        	        	if(slope1 == slope2 && slope2 == slope3) {
        	        		out.println(p + " -> " + q + " -> " + r + " -> " + s);
        	        	}
        	        }
                }
            }
        }
        
	}

}
