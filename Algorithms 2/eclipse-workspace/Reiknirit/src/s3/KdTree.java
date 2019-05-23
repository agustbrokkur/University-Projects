package s3;


import java.text.DecimalFormat;

/*************************************************************************
 *************************************************************************/

import java.util.Arrays;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class KdTree {
	
	private int size;
	private Node root;
	
	private static class Node {
		private Point2D p;		// the point
		private RectHV rect;	// the axis-aligned rectangle corresponding to this node
		private Node botleft; 		// the left/bottom subtree
		private Node topright; 		// the right/top subtree
		
		private Node(Point2D p, RectHV rect, Node botleft, Node topright) {
			this.p = p;
			this.rect = rect;
			this.botleft = botleft;
			this.topright = topright;
		}
	}
	
    // construct an empty set of points
    public KdTree() {
    	size = 0;
    	root = null;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
    	if(p == null) {
    		throw new NullPointerException("Point cannot be null");
    	}
    	
    	// passa upp a ad tad er ekki haegt ad setja tvo nakvaemlega eins punkta
    	if(!contains(p)) {
        	root = insert(root, null, p, true);
    	}
    };

    // helper method fyrir insert()
    // orientation er boolean breyta sem segjir til hvernig nodan liggur
    // true 	= lodrett
    // false 	= larett (Gaman ad laera ad Mooshak kvartar um comments sem nota islenska stafi)
    private Node insert(Node n, Node parent, Point2D p, boolean orientation) {
    	if(n == null) {
    		RectHV tangle;
    		
    		// ef foreldrin er null ta erum vid a rotinni
    		// og ta er rectangle hja root allt svaedid
    		// (0.0)-(1.1)
    		if(parent == null) {
    			tangle = new RectHV(0.0, 0.0, 1.0, 1.0);
        		size++;
        		return new Node(p, tangle, null, null);
    		}
    		
    		// na i hnit hja foreldra nodu
    		double x0 = parent.rect.xmin();
    		double y0 = parent.rect.ymin();
    		double x1 = parent.rect.xmax();
    		double y1 = parent.rect.ymax();
    		
    		// ef tad er lodrett(true) ta tekur nodan y gildi fra forelda sinum
			// ef foreldra y er staerra tekur nodan tad i rect.ymax()
			// annars tekur nodan foreldra y hnutin i rect.ymin()
    		if(orientation) {
    			if(p.y() < parent.p.y()) {
    				y1 = parent.p.y();
    			}
    			else {
    				y0 = parent.p.y();
    			}
    			
    		}
    		// ef tad er lagrett(false) ta tekur nodan x gildi fra forelda sinum
			// ef foreldra x er staerra tekur nodan tad i rect.xmax()
			// annars tekur nodan foreldra x hnutin i rect.xmin()
    		else {
    			if(p.x() < parent.p.x()) {
    				x1 = parent.p.x();
    			}
    			else {
    				x0 = parent.p.x();
    			}
    		}
    		
    		tangle = new RectHV(x0, y0, x1, y1);
    		size++;
    		return new Node(p, tangle, null, null);
    	}

    	// TODO: if og else eru nanast sami kodinn. Liklegast haegt ad refractora tetta i annad function
    	// ef tad er lodrett(true) ta er gad hvort x punktur hja hniti p se staerra en x punktur hja punkti a nodu n
    	// ef svo er ta er farid til vinstri, annars er til haegri
    	// svo er orientation svissad yfir i ofugt liggjandi (false/larett -> true/lodrett) eda (true/lodrett -> false/larett)
    	if(orientation) {
    		if(p.x() < n.p.x()) {
    			n.botleft = insert(n.botleft, n, p, !orientation);
    		}
    		else {
    			n.topright = insert(n.topright, n, p, !orientation);
    		}
    	}
    	// ef tad er larett(false) ta er gad hvort y punktur hja hniti p se staerra en y punktur hja punkti a nodu n
    	// ef svo er ta er farid til vinstri, annars er til haegri
    	// svo er orientation svissad yfir i ofugt liggjandi (false/larett -> true/lodrett) eda (true/lodrett -> false/larett)
    	else {
    		if(p.y() < n.p.y()) {
    			n.botleft = insert(n.botleft, n, p, !orientation);
    		}
    		else {
    			n.topright = insert(n.topright, n, p, !orientation);
    		}
    	}
    	
    	return n;
    };
    
    // does the set contain the point p?
    public boolean contains(Point2D p) {
    	if(p == null) {
    		throw new NullPointerException("Point cannot be null");
    	}
    	
        return contains(root, p, true);
    }
    
    // helper method fyrir contains()
    // orientation er boolean breyta sem segjir til hvernig nodan liggur
    // true 	= lodrett
    // false 	= larett (Gaman ad laera ad Mooshak kvartar um comments sem nota islenska stafi)
    private boolean contains(Node n, Point2D p, boolean orientation) {
    	if(n == null) {
    		return false;
    	}
    	if(n.p.equals(p)) {
    		return true;
    	}
    	
    	// copy og paste fra insert, sja tad fyrir lysingu
    	if(orientation) {
    		if(p.x() < n.p.x()) {
    			return contains(n.botleft, p, !orientation);
    		}
    		else {
    			return contains(n.topright, p, !orientation);
    		}
    	}
    	else {
    		if(p.y() < n.p.y()) {
    			return contains(n.botleft, p, !orientation);
    		}
    		else {
    			return contains(n.topright, p, !orientation);
    		}
    	}
    }

    // draw all of the points to standard draw
    public void draw() {

    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
    	if(rect == null) {
    		throw new NullPointerException("Rectangle cannot be null");
    	}
    	
    	// queueid sem heldur utan um alla punta
    	Queue<Point2D> temp = new Queue<Point2D>();
    	
    	return range(root, rect, temp);
    }

    // helper method fyrir range()
    private Queue<Point2D> range(Node n, RectHV rect, Queue<Point2D> temp) {
    	// ef noda er tom ta er enginn tilgangur ad leita
    	if(n == null) {
    		return temp;
    	}
    	
    	// ef gefinn rect og rect hja nodunni skerast(intersect) ekki ta er enginn tilgangur ad leita
    	if(!rect.intersects(n.rect)) {
    		return temp;
    	}
    	
    	// ef vid komumst hingad langt, ta getum vid gad ef hnutur nodunar dettur innan vid
    	// mork rect, og ef svo ta er sa hnuti baett inni
    	if(rect.contains(n.p)) {
    		temp.enqueue(n.p);
    	}
    	
    	// leitad er fyrst i vinstri og svö haegri nodum
    	range(n.botleft, rect, temp);
    	range(n.topright, rect, temp);
    	return temp;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
    	if(p == null) {
    		throw new NullPointerException("Rectangle cannot be null");
    	}
    	// treid verdur audvitad ad innihalda einhverja hluti til ad bera saman vid
    	// annars er enginn punktur, og tar med er skilad null
    	if(isEmpty()) {
    		return null;
    	}
    	
    	return nearest(root, p, root.p, true);
    }

    // helper method fyrir nearest()
    private Point2D nearest(Node n, Point2D p, Point2D near, boolean orientation) {
    	// ef nodan er null ta er ekki haegt ad fara nedar og near hlytur ad vera su nalaegasta
    	if(n == null) {
    		return near;
    	}
    	
    	// ef ad nalaegasti fundinn punturinn hefur minni vegalengd en kassi(rect) nodurnar er enginn
    	// tilgangur ad leita dypra i tessum parti tresins
    	if(near.distanceSquaredTo(p) <= n.rect.distanceSquaredTo(p)) {
    		return near;
    	}
    	
    	// ef near hnuturinn er fjaer ta er hann augljoslega ekki sa nalaegasti hnutur
    	if (near.distanceSquaredTo(p) > n.p.distanceSquaredTo(p)) {
    		near = n.p;
    	}

    	// TODO: if og else eru nanast sami kodinn. Liklegast haegt ad refractora tetta i annad function
    	// nanast bara copy paste og fra contains, nema tad er farid i baedi botleft og topright nodur.
    	if(orientation) {
    		if(p.x() < n.p.x()) {
    			near = nearest(n.botleft, p, near, !orientation);
    			near = nearest(n.topright, p, near, !orientation);
    		}
    		else {
    			near = nearest(n.topright, p, near, !orientation);
    			near = nearest(n.botleft, p, near, !orientation);
    		}
    	}
    	else {
    		if(p.y() < n.p.y()) {
    			near = nearest(n.botleft, p, near, !orientation);
    			near = nearest(n.topright, p, near, !orientation);
    		}
    		else {
    			near = nearest(n.topright, p, near, !orientation);
    			near = nearest(n.botleft, p, near, !orientation);
    		}
    	}
    	
    	return near;
    	
    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {
    	

        String filename = args[0];
        In in = new In(filename);

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }

    	double n = 10000000;
    	SET<Point2D> set = new SET<Point2D>();
    	KdTree tree = new KdTree();
    	
    	while(set.size() != n) {
    		Point2D x = new Point2D(StdRandom.uniform(), StdRandom.uniform());
    		set.add(x);
    	}
    	
    	Stopwatch watch;
    	watch = new Stopwatch();
    	for(Point2D p : set) {
    		kdtree.nearest(p);
    	}
    	double timi = watch.elapsedTime();
    	StdOut.println(n/timi);
        
        
    	
    	/*
    	int n = 6400000;
    	SET<Point2D> set = new SET<Point2D>();
    	KdTree tree = new KdTree();
    	
    	while(set.size() != n) {
    		Point2D x = new Point2D(StdRandom.uniform(), StdRandom.uniform());
    		set.add(x);
    	}
    	
    	Stopwatch watch;
    	
    	watch = new Stopwatch();
    	for(Point2D p : set) {
    		tree.insert(p);
    	}
    	double timi = watch.elapsedTime();
    	StdOut.println(timi + " sek");*/
    	
    	/*
        In in = new In();
        Out out = new Out();
        int nrOfRecangles = in.readInt();
        int nrOfPointsCont = in.readInt();
        int nrOfPointsNear = in.readInt();
        RectHV[] rectangles = new RectHV[nrOfRecangles];
        Point2D[] pointsCont = new Point2D[nrOfPointsCont];
        Point2D[] pointsNear = new Point2D[nrOfPointsNear];
        for (int i = 0; i < nrOfRecangles; i++) {
            rectangles[i] = new RectHV(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsCont; i++) {
            pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsNear; i++) {
            pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        KdTree set = new KdTree();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }
        for (int i = 0; i < nrOfRecangles; i++) {
            // Query on rectangle i, sort the result, and print
            Iterable<Point2D> ptset = set.range(rectangles[i]);
            int ptcount = 0;
            for (Point2D p : ptset)
                ptcount++;
            Point2D[] ptarr = new Point2D[ptcount];
            int j = 0;
            for (Point2D p : ptset) {
                ptarr[j] = p;
                j++;
            }
            Arrays.sort(ptarr);
            out.println("Inside rectangle " + (i + 1) + ":");
            for (j = 0; j < ptcount; j++)
                out.println(ptarr[j]);
        }
        out.println("Contain test:");
        for (int i = 0; i < nrOfPointsCont; i++) {
            out.println((i + 1) + ": " + set.contains(pointsCont[i]));
        }

        out.println("Nearest test:");
        for (int i = 0; i < nrOfPointsNear; i++) {
            out.println((i + 1) + ": " + set.nearest(pointsNear[i]));
        }

        out.println();*/
    }
}
