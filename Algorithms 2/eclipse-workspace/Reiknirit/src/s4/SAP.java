package s4;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
	// Svo madur hafi adgang að Digraphinu inn í klasanum
	private Digraph G;
	// Rotin a digraphinu
	private int root;
	// Til ad minka tima sem forritid tekur, hafa staerd i ser faeribreytu
	private int size;

	 // constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		// Thad sem vid turfum ad gera til ad finna ef digraphid er rooted og acyclical
		// er ad fara i gegnum alla puntana i digraphinu og ga ef sa punktur er tengdur vid alla adra punkta
		// ef hann er svo, ta er hann rotinn og ta hofum vid fundid rotina. Ef ekker fundid neitt, ta er enginn rot.
		// Og ef fundid eru fleiri en eina rot ta er digraphid ekki acyclical og skilar tvi villu

		int counter = 0;
		int tala;
		BreadthFirstDirectedPaths paths;
		BreadthFirstDirectedPaths cycle;
		boolean check = true;
		boolean notCycle = true;
		for(int i = 0; i < G.V(); i++) {
			tala = 0;
			
			while((notCycle || check) && tala < G.V()) {
				paths = new BreadthFirstDirectedPaths(G, tala);
				cycle = new BreadthFirstDirectedPaths(G, i);
				if(!paths.hasPathTo(i) && check) {
					check = false;
				}
				if(paths.hasPathTo(i) && cycle.hasPathTo(tala) && (tala != i)) {
					notCycle = false;
				}
				
				tala++;
			}
			
			if(check) {
				root = i;
				counter++;
			}
			check = true;
		}
		
		if(!notCycle) {
			throw new IllegalArgumentException("Graph is not acyclic");
		}
		else if(counter < 1) {
			throw new IllegalArgumentException("Graph is not rooted");
		}
		else {
			this.G = new Digraph(G);
			size = G.V();
		}
		
	}
	
	// Gair ef baedi stokin eru a milli 0 og G.V() - 1
	private void villa(int v, int w) {
		boolean a = v < 0 || size < v;
		boolean b = w < 0 || size < w;
		if(a || b) {
			 throw new IndexOutOfBoundsException();
		}
	}
	
	// Sama og venjulega nema tad fer i gegnum oll stok sem eru i A og B
	private void villa(Iterable<Integer> A, Iterable<Integer> B) {
		for(int i : A) {
			boolean a = i < 0 || size < i;
			if(a) {
				 throw new IndexOutOfBoundsException();
			}
		}
		
		for(int i : B) {
			boolean b = i < 0 || size < i;
			if(b) {
				 throw new IndexOutOfBoundsException();
			}
		}
		
	}
	
	// Hjalpar function
	private int hjalpar(BreadthFirstDirectedPaths kor, BreadthFirstDirectedPaths tan) {
		int ancestor = -1;
		int lengd = Integer.MAX_VALUE;
		int fjard;
		
		for(int i = 0; i < size; i++) {
			if(kor.hasPathTo(i) && tan.hasPathTo(i)) {
				
				fjard = kor.distTo(i) + tan.distTo(i);
				
				if(lengd >= fjard) {
					ancestor = i;
					lengd = fjard;
				}
			}
		}
		
		return ancestor;
	}
	
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		villa(v, w);
		
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, w);
		
		int ancestor = hjalpar(kor, tan);;
		
		return kor.distTo(ancestor) + tan.distTo(ancestor);
		
		/*
		// Tharf ad gera fyrir badar tolur utaf ef þaer eru ekki tengdar i gegnum
		// BFDP tha eru thaer mogulega tengdar i gegnum ancestor
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, w);
		
		// Thetta fall gaeti verid mjog redundant ef vid notum ekki bara ancestor her
		int ancestor = ancestor(v, w);
		
		// Vid vitum nuna ancestor med minstu vegalengd, notum bara tad med distTo hja badum
		// kor og tan med at plusa tau saman til ad fa rett svar
		return kor.distTo(ancestor) + tan.distTo(ancestor);
		
		// Redundant kodi ad nedan ------
		/*
		// Tharf ad gera fyrir badar tolur utaf ef þaer eru ekki tengdar i gegnum
		// BFDP tha eru thaer mogulega tengdar i gegnum ancestor
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, w);
		// Faeribreytan sem vid skilum frá okkur
		// Hun faer haesta gildi sem til er svo ad hvada fjarlaegd sem kemur inn fyrst
		// verdur minni en nuverandi lengd
		int lengd = Integer.MAX_VALUE;
		// Faeribreyturnan fyrir vegalengdinar a milli v, w og sameiginlega ancestor.
		int fjard = -1;
		
		// Farid er í gegnum oll stok í digraphinu til að finna minnstu lengd a milli
		for(int i = 0; i < G.V(); i++) {
			
			// Gaum ef baedi kor og tan hafa leid að i
			if(kor.hasPathTo(i) && tan.hasPathTo(i)) {
				
				// Ef svo er tha plusum við saman fjarlaegdir sem ad kor og tan hafa fra i
				// og berum thaer svo saman vid lengd.
				fjard = kor.distTo(i) + tan.distTo(i);
				
				// Ef lengdin er staerri tha erum við bunnir að finna styttri fjarlaegd
				// og tha tekur lengd a sig nyja gildid.
				if(lengd > fjard) {
					lengd = fjard;
				}
			}
		}
		
		// Skilar -1 ef ekkert enginn leid er til
		return fjard;
		*/
	}
	
	// a shortest common common ancestor of v and w; -1 if no such path
	public int ancestor(int v, int w) {
		villa(v, w);
		
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, w);

		return hjalpar(kor, tan);
		
		/*
		// Tharf ad gera fyrir badar tolur utaf ef þaer eru ekki tengdar i gegnum
		// BFDP tha eru thaer mogulega tengdar i gegnum ancestor
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, w);
		// Faeribreytan sem vid skilum frá okkur
		int ancestor = -1;
		// Farebreytan sem heldur um lengd hja ancestor
		// Hun faer haesta gildi sem til er svo ad hvada fjarlaegd sem kemur inn fyrst
		// verdur minni en nuverandi lengd
		int lengd = Integer.MAX_VALUE;
		// Faeribreyturnan fyrir vegalengdinar a milli v, w og sameiginlega ancestor.
		int fjard;
		
		// Farid er í gegnum oll stok í digraphinu til að finna ancestor sem er med minnstu lengd a milli
		for(int i = 0; i < size; i++) {
			
			// Gaum ef baedi kor og tan hafa leid að i
			if(kor.hasPathTo(i) && tan.hasPathTo(i)) {
				
				// Ef svo er tha plusum við saman fjarlaegdir sem ad kor og tan hafa fra i
				// og berum thaer svo saman vid lengd.
				fjard = kor.distTo(i) + tan.distTo(i);
				
				// Ef ad nyja fjarlaegdin er styttri ta setjum vid ancestor sem talan sem baedi kor og tan hafa
				// og setjum nyju stystu fjarlaegdina lika
				if(lengd >= fjard) {
					ancestor = i;
					lengd = fjard;
				}
			}
		}

		// Skilar -1 ef ekkert enginn leid er til
		return ancestor;
		*/
		
	}
	
	// length of shortest ancestral path of vertex subsets A and B; -1 if no such path
	public int length(Iterable<Integer> A, Iterable<Integer> B) {
		// Kodinn her ad nedan er sa sami og er i length fallinu ad ofan nema breytt er um hvad
		// BFDP follin taka inn
		villa(A, B);
		
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, A);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, B);
		
		int ancestor = hjalpar(kor, tan);;
		
		return kor.distTo(ancestor) + tan.distTo(ancestor);
	}
	
	// a shortest common ancestor of vertex subsets A and B; -1 if no such path
	public int ancestor(Iterable<Integer> A, Iterable<Integer> B) {
		// Kodinn her ad nedan er sa sami og er i ancestor fallinu ad ofan nema breytt er um hvad
		// BFDP follin taka inn
		villa(A, B);
		
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, A);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, B);

		return hjalpar(kor, tan);
	}
	
	// do unit testing of this class
	public static void main(String[] args) {
		/*
		In in = new In();
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		/*
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, 3);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, 10);

		StdOut.println("Kor:");
		for(int i : kor.pathTo(sap.root)) {
			StdOut.println(i);
		}
		StdOut.println("Tan:");
		for(int i : tan.pathTo(sap.root)) {
			StdOut.println(i);
		}
		
		
		/*
		BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, 3);
		BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, 10);
		StdOut.println("Has Path to " + " from 3 " + kor.hasPathTo(3));
		StdOut.println("Has Path to " + " from 10 " + tan.hasPathTo(10));
		for(int i = 0; i < G.V(); i++) {
			
		}
		*/
		
		
		//BreadthFirstDirectedPaths kor = new BreadthFirstDirectedPaths(G, 3);
		//BreadthFirstDirectedPaths tan = new BreadthFirstDirectedPaths(G, 10);
		//StdOut.println(kor.distTo(1));
		//StdOut.println(tan.distTo(1));
		//StdOut.println(sap.length(1, 5));
		//StdOut.println(sap.ancestor(1, 5));
		//int[] v = {3, 8, 6, 1};
		//int[] w = {10, 2};
		/*
		StdOut.println(sap.ancestor(1, 2));
		StdOut.println(sap.length(1, 2));
		StdOut.println(sap.ancestor(1, 3));
		StdOut.println(sap.length(1, 3));
		StdOut.println(sap.ancestor(1, 4));
		StdOut.println(sap.length(1, 4));
		//StdOut.println(sap.length(v, w));
		/*
		Queue<Integer> v = new Queue<Integer>();
		Queue<Integer> w = new Queue<Integer>();
		v.enqueue(3);
		v.enqueue(8);
		v.enqueue(6);
		v.enqueue(1);
		w.enqueue(10);
		w.enqueue(2);
		StdOut.println(sap.length(v, w));
		StdOut.println(sap.ancestor(v, w));
		 
		//StdOut.println(sap.G.reverse());
		//StdOut.println(sap.G.indegree(1));
		//StdOut.println(sap.G.outdegree(1));
		*/
	}

}
