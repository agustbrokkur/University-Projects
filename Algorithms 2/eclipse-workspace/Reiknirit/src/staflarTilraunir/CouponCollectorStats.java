package staflarTilraunir;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class CouponCollectorStats {
	private int collection[];
	
	public CouponCollectorStats(int N, int T) {
		collection = new int[T];
		for (int i = 0; i < T; i++) {
			collection[i] = couponCollectorTest(N);
		}
	}
	
	public double mean() {
		double x = StdStats.mean(collection);
		return Math.floor(x * 100) / 100;
	}
	
	public double stddev() {
		double y = StdStats.stddev(collection);
		return  Math.floor(y * 100) / 100;
	}
	
	public static int couponCollectorTest(int N) {
		boolean check[] = new boolean[N];
		int count = 0;
		int result = 0;
		while(count < N) {
			int x = StdRandom.uniform(0, N);
			if (check[x] == false) {
				check[x] = true;
				count++;
			}
			result++;
		}
		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 1000;
		int T = 1;
		for(int i = 0; i < 4; i++) {
			T *= 10;
			CouponCollectorStats stats = new CouponCollectorStats(N, T);
			StdOut.println(T + " Tests(T) were conducted on numbers ranging from 0-" + N + "(N):");
			StdOut.println("The Average is: " + stats.mean());
			StdOut.println("The Standard Deviation is: " + stats.stddev() + "\n");
		}
	}

}
