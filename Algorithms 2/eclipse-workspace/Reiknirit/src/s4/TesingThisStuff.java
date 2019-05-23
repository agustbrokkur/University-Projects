package s4;

import edu.princeton.cs.algs4.LSD;
import edu.princeton.cs.algs4.StdOut;

public class TesingThisStuff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a[] = {"CAG", "CGC", "ACG", "CCT",  "GCT", "GGA"};
		int N = a.length;
		
		StdOut.println("Unsorted strings: ");
		for(String s : a) {
			StdOut.println(s);
		}
		StdOut.println();
		StdOut.println();
		
		int W = a[0].length();
		
		LSD.sort(a, W);

		StdOut.println("Sorted strings: ");
		for(String s : a) {
			StdOut.println(s);
		}
	}

}