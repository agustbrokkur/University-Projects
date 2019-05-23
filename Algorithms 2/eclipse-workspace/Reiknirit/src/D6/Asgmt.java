package D6;


import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/**
 *  The {@code Asgmt} class represents a boolean assignment.
 *  It is comparable to the the representation of a sparse vector.
 *  Assignments are mutable: their values can be changed after they are created.
 *  It includes methods for addition, subtraction,
 *  dot product, scalar product, unit vector, and Euclidean norm.
 *  <p>
 *  The implementation is a symbol table of variables (as integers) and their boolean values.
 *  This makes it efficient for partial assignments, when only a few of the variables receive a value.
 *
 *  @author Magnus M. Halldorsson
 */
// Asgmt is a boolean assignment to (a subset of) the variables.

public class Asgmt {
	ST<Integer,Boolean> asg;	
	
    /**
     * Initializes an (empty) boolean assignment
     */
    public Asgmt() {
        this.asg = new ST<Integer, Boolean>();
    }

    /**
     * Create a clause from a string representation
     * Comma-separated list of integer/character pairs (divided by a colon)
     * integer is the variable, and the character is either T or F
     * @param astring an assignment in a string representation
     */
    public Asgmt(String astring) {
        this.asg = new ST<Integer, Boolean>();
    	String[] tokens = astring.split(",");
    	for (String lit : tokens) {
    		String[] pair = lit.split(":");
    		int var = Integer.parseInt(pair[0]);
    		boolean val = (pair[1].charAt(0) == 'T' ? true : false); 
    		add(var,val);
    	}
    }
    
	/**
     * Check if a variable is assigned a value in the assignment
     * @param v the variable tested
     * @return return true if the variable is assigned a value
     */
	public boolean contains(int v) { return asg.contains(v); }
	/**
     * Return the value assigned to the given variable
     * @param v the variable whose value is returned
     * @return the value of the variable given
     */
	public boolean get(int v) { return asg.get(v); }
	/**
     * Insert a single variable's value into the assignment
     * @param v the variable to be assigned
     * @param value the value assigned to the variable
     */
	public void add(int v, boolean value) { asg.put(v,value); }
	/**
     * Remove the assignment to a variable
     * @param v the variable whose assignment will be nulled
     */
	public void remove(int v) { asg.put(v,  null); }
	/**
     * Return how many variables are assigned a value
     * @return the number of variables assigned
     */
	public int size() { return asg.size(); }
	
	   // Merge two symbol tables
	/**
     * Merge two assignments. If a variable appears in both assignments, keep the value in the parameter assignment.
     * @param asg2 another boolean assignment
     */
    public void joinAsgmt(Asgmt asg2) {
		for (int v : asg2.vars())
		    add(v,asg2.get(v));
    }

    /**
     * Set difference of two assignments: unset all variables appearing in the passed assignment
     * @param asg2 another boolean assignment
     */
     public void unset(Asgmt asg2) {
		for (int v : asg2.vars())
		    remove(v);
    }

     /**
      * Returns a string representation of this assignment.
      * @return a string representation of this assignment, which consists of the 
      *         the variables and their values,  separated by commas
      */
       public String toString() {
    	StringBuilder s = new StringBuilder(" ");
		for (int v : asg.keys())
		    s.append(v+":"+(asg.get(v) ? "T":"F")+" ");
		return s.toString();  	
       }

       /**
       // Print a variable graphically at location (x,y)
       // Caveat: The scale-to-canvas-size ratio needs to be constant; 
       //   if you change canvas size, you must scale the geometry accordingly
       public void plotVariable(int var, double x, double y) {
       	Font origfont = StdDraw.getFont();
       	Font curfont = origfont.deriveFont(Font.BOLD);
       	float smallsize = curfont.getSize2D() - (float) 2;
       	Font smallfont = curfont.deriveFont(smallsize);
       	double charwidth = curfont.getSize()*0.35*0.003;
       	double charheight = charwidth*1.0;

       	StdDraw.setFont(curfont);

   		StdDraw.textLeft(x,y, "x");
   		// Draw variable index as subscript
   		StdDraw.setFont(smallfont);
   		String varstr = Integer.toString(var);
   		StdDraw.textLeft(x+charwidth, y-charheight/2, varstr);
   		
   		StdDraw.setFont(origfont);	
       }
       

       public void plot(double x, double y, double width) {
    	   Color curcol = StdDraw.getPenColor();
    	//   StdDraw.textLeft(x, y, "Assignment");
    	//   y -= 0.04;
    	   double curx = x;
    	   for (int var : vars()) {
    		   StdDraw.setPenColor((get(var) ? StdDraw.BLUE : StdDraw.GREEN));
    		   plotVariable(var, curx, y);
    		   curx += 0.05;
    	   }
    	   StdDraw.setPenColor(curcol);
       }
**/
       
       /**
        * Returns an iterator that iterates over the variables used in the assignment (in their natural order).
        * @return an iterator that iterates over the variables used in the assignment (in their natural order).
        */
	    public Iterable<Integer> vars() {
	        return asg.keys();
	    }
    /*****************************************************************************
     *  Test client
     *****************************************************************************/
	    /**
	     * Unit tests the {@code Asgmt} data type.
	     *
	     * @param args the command-line arguments
	     */
    public static void main(String[] args) { 
    	 Asgmt asg = new Asgmt();
    	 asg.add(1, Boolean.TRUE);
    	 asg.add(2, Boolean.FALSE);
    	 StdOut.println("Assignment #1:"+asg + " of size: "+asg.size());
    	 StdOut.println("Contains 1: "+asg.contains(1));
    	 StdOut.println("Contains 3: "+asg.contains(3));
    	 Asgmt asg2 = new Asgmt();
    	 asg2.add(3, Boolean.FALSE);
    	 asg2.add(2, Boolean.TRUE);
    	 StdOut.println("Assignment  #2:"+asg2 + " of size: "+asg2.size());
    	 asg.joinAsgmt(asg2);
    	 StdOut.println("Merging the two:"+asg);
    	 asg.unset(asg2);
    	 StdOut.println("#1 after unsetting:"+asg);
    	 
    	 Asgmt asg3 = new Asgmt("1:T,2:F");
    	 StdOut.println("1:T,2:F" + " <-->" + asg3);
     }    	 
 
}
