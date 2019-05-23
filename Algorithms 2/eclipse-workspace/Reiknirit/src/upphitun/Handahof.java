package upphitun;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Handahof {
	static int width = 600;
	static int height = 600;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int counter = 0;
		int[] collection = new int[10];
		for (int x = 0; x < 100; x++) {
			 counter = StdRandom.uniform(1, 10);
			 collection[counter]++;
		}
		counter = 1;
		for (int i : collection) {
			StdOut.println("Talan " + counter + " kemur fyrir " + i + " sinnum");
			counter++;
		}
		StdOut.println(collection.length);
		

		int teljari = 0;
		String strengur = "";
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, 10);
		StdDraw.setYscale(0, 10);
		StdDraw.setPenColor(StdDraw.GRAY);
		for(int i : collection) {
			if(i != 0) {
				i *= 0.6;
			}
			strengur = "" + (teljari + 1);
			StdDraw.text(teljari + 0.5, 0 + 0.5, strengur);
			for(int x = 0; x < i; x++) {
				StdDraw.filledSquare(teljari + 0.5, x + 1.5, 0.5);
			}
			teljari++;
		}
		
		teljari = 0;
		for (int i : collection) {
			StdDraw.setPenColor(StdDraw.BLACK);
	        StdDraw.line(0, teljari, 10, teljari);
	        StdDraw.line(teljari, 0, teljari, 10);
	        teljari++;
		}
		strengur = "Meðaltalið er = " + StdStats.mean(collection);
		StdDraw.textLeft(0 + 0.5, 8 + 0.5, strengur);
		strengur = "Staðalfrávikið er = " + StdStats.stddev(collection);
		StdDraw.textLeft(0 + 0.5, 6 + 0.5, strengur);
	}

}
