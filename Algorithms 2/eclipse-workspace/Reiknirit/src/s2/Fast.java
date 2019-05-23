package s2;

import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class Fast {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        In in = new In();
        Out out = new Out();
        int n = in.readInt();
        Point[] points = new Point[n];
        Point[] tests = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt(), y = in.readInt();
            Point q  = new Point(x, y);
            points[i] = q;
            tests[i] = q;
        }
		/*
        Out out = new Out();
		int n = 15;
        Point[] points = new Point[n];
        Point[] tests = new Point[n];
        points[0] = new Point(10, 0);
        points[1] = new Point(8, 2);
        points[2] = new Point(2, 8);
        points[3] = new Point(0, 10);
        points[4] = new Point(20, 0);
        points[5] = new Point(18, 2);
        points[6] = new Point(2, 18);
        points[7] = new Point(10, 20);
        points[8] = new Point(30, 0);
        points[9] = new Point(0, 30);
        points[10] = new Point(20, 10);
        points[11] = new Point(13, 0);
        points[12] = new Point(11, 3);
        points[13] = new Point(5, 12);
        points[14] = new Point(9, 6);
        
        tests[0] = new Point(10, 0);
        tests[1] = new Point(8, 2);
        tests[2] = new Point(2, 8);
        tests[3] = new Point(0, 10);
        tests[4] = new Point(20, 0);
        tests[5] = new Point(18, 2);
        tests[6] = new Point(2, 18);
        tests[7] = new Point(10, 20);
        tests[8] = new Point(30, 0);
        tests[9] = new Point(0, 30);
        tests[10] = new Point(20, 10);
        tests[11] = new Point(13, 0);
        tests[12] = new Point(11, 3);
        tests[13] = new Point(5, 12);
        tests[14] = new Point(9, 6);*/
		
    	Arrays.sort(points);
        
        for(int i = 0; i < n; i++) {
        	Point p = points[i];
        	Arrays.sort(tests);
        	Arrays.sort(tests, p.SLOPE_ORDER);

    		for(int j = 1; j < n - 1; j++) {
    			double slope = p.slopeTo(tests[j]);

    			if(slope == p.slopeTo(tests[j + 1])) {
    				int counter = j + 1;

    				for (int k = counter; k < n; k++) {
    					if(slope == p.slopeTo(tests[k])) {
    						//counter++;
    						counter = k;
    					}
    					if(slope != p.slopeTo(tests[k]) || (k == n - 1)) {
    						break;
    					}
    				}
        			if((counter - j > 1) && (p.compareTo(tests[j]) < 0)) {
        				out.print(p);
        				for(int k = j; k <= counter; k++) {
        					out.print(" -> " + tests[k]);
        				}
        				out.println();
        			}
        			j = counter + 1;
    			}
    		}
        }
	}

}
