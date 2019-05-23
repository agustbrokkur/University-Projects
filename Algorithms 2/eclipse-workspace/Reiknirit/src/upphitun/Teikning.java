package upphitun;

import edu.princeton.cs.algs4.StdDraw;

public class Teikning {
	static int width = 600;
	static int height = 600;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		StdDraw.setPenColor(200, 50, 32);
		StdDraw.filledEllipse(width/2, height/2, 290, 200);
		StdDraw.setPenColor(255, 0, 255);
		//StdDraw.filledSquare(width/4, height/4, 35);
		StdDraw.square(width/4, height/4, 35);
		StdDraw.setPenColor(0, 255, 0);
        StdDraw.line(450, 150, 150, 450);
		StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(width/2, height/1.32, "Hee ho ho, er miklu meira vanur Processing.pde");
	}
	
}
