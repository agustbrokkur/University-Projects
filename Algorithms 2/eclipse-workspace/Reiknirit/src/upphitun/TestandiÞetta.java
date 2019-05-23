package upphitun;

import edu.princeton.cs.algs4.StdOut;

public class Testandiﬁetta {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		for(int x = 0; x < 24; x++) {
			n = x;
			int sum = 0;
			for (int t = n; t > 0; t /= 2)
			   for (int i = 0; i < t; i++)
			      sum++;
			
			StdOut.println(x + " = " + sum);
		}
	}

}
