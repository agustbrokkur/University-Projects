import java.util.ArrayList;
import java.util.List;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CSPStateListener;
import aima.core.search.csp.Domain;
import aima.core.search.csp.ImprovedBacktrackingStrategy;
import aima.core.search.csp.NotEqualConstraint;
import aima.core.search.csp.SolutionStrategy;
import aima.core.search.csp.Variable;

public class Main {

	private static CSP setupCSP() {
		CSP csp = null;
//		In five houses, each with a different color, live five persons of different nationality,
//		each of whom prefers a different brand of cigarettes, a different drink, and a different pet.
//		The five houses are arranged in a row (no house has more than 2 neighbors).   
//		# The Englishman lives in the red house.
//		# The Spaniard owns the dog.
//		# Coffee is drunk in the green house.
//		# The Ukrainian drinks tea.
//		# The green house is immediately to the right of the ivory house.
//		# The Old Gold smoker owns snails.
//		# Kools are smoked in the yellow house.
//		# Milk is drunk in the middle house.
//		# The Norwegian lives in the first house.
//		# The man who smokes Chesterfields lives in the house next to the man with the fox.
//		# Kools are smoked in the house next to the house where the horse is kept.
//		# The Lucky Strike smoker drinks orange juice.
//		# The Japanese smokes Parliaments.
//		# The Norwegian lives next to the blue house.
//
//		Now, who drinks water? Who owns the zebra?
				
		String[] colors = {"Red", "Green", "Ivory", "Yellow", "Blue"};
		String[] nations = {"Englishman", "Spaniard", "Norwegian", "Ukrainian", "Japanese"};
		String[] cigarettes = {"Old Gold", "Kools", "Chesterfields", "Lucky Strike", "Parliaments"};
		String[] drink = {"Water", "Orange juice", "Tea", "Coffee", "Milk"};
		String[] pet = {"Zebra", "Dog", "Fox", "Snails", "Horse"};
		
		// TODO create variables, e.g.,
		// Variable var1 = new Variable("name of the variable 1");
		// Variable var2 = new Variable("name of the variable 2");
		Variable red = new Variable(colors[0]);
		Variable green = new Variable(colors[1]);
		Variable ivory = new Variable(colors[2]);
		Variable yellow = new Variable(colors[3]);
		Variable blue = new Variable(colors[4]);

		Variable englishman = new Variable(nations[0]);
		Variable spaniard = new Variable(nations[1]);
		Variable norwegian = new Variable(nations[2]);
		Variable ukrainian = new Variable(nations[3]);
		Variable japanese = new Variable(nations[4]);

		Variable oldGold = new Variable(cigarettes[0]);
		Variable kools = new Variable(cigarettes[1]);
		Variable chesterFields = new Variable(cigarettes[2]);
		Variable luckyStrike = new Variable(cigarettes[3]);
		Variable parliaments = new Variable(cigarettes[4]);

		Variable water = new Variable(drink[0]);
		Variable orangeJuice = new Variable(drink[1]);
		Variable tea = new Variable(drink[2]);
		Variable coffee = new Variable(drink[3]);
		Variable milk = new Variable(drink[4]);

		Variable zebra = new Variable(pet[0]);
		Variable dog = new Variable(pet[1]);
		Variable fox = new Variable(pet[2]);
		Variable snails = new Variable(pet[3]);
		Variable horse = new Variable(pet[4]);
		
		List<Variable> variables = new ArrayList<Variable>();
		// TODO add all your variables to this list, e.g.,
		// variables.add(var1);
		// variables.add(var2);
		variables.add(red);
		variables.add(green);
		variables.add(ivory);
		variables.add(yellow);
		variables.add(blue);
		
		variables.add(englishman);
		variables.add(spaniard);
		variables.add(norwegian);
		variables.add(ukrainian);
		variables.add(japanese);

		variables.add(oldGold);
		variables.add(kools);
		variables.add(chesterFields);
		variables.add(luckyStrike);
		variables.add(parliaments);
		
		variables.add(water);
		variables.add(orangeJuice);
		variables.add(tea);
		variables.add(coffee);
		variables.add(milk);
		
		variables.add(zebra);
		variables.add(dog);
		variables.add(fox);
		variables.add(snails);
		variables.add(horse);
	
		
		csp = new CSP(variables);

		// TODO set domains of variables, e.g.,
		// Domain d1 = new Domain(new String[]{"foo", "bar"});
		// csp.setDomain(var1, d1);
		// Domain d2 = new Domain(new Integer[]{1, 2});
		// csp.setDomain(var2, d2);
		Domain houses = new Domain(new Integer[] {1,2,3,4,5});
		for(Variable x : variables) {
			csp.setDomain(x, houses);
		}
		
		csp.removeValueFromDomain(norwegian, houses.get(1));
		csp.removeValueFromDomain(norwegian, houses.get(2));
		csp.removeValueFromDomain(norwegian, houses.get(3));
		csp.removeValueFromDomain(norwegian, houses.get(4));

		csp.removeValueFromDomain(milk, houses.get(0));
		csp.removeValueFromDomain(milk, houses.get(1));
		csp.removeValueFromDomain(milk, houses.get(3));
		csp.removeValueFromDomain(milk, houses.get(4));
		
		// TODO add constraints, e.g.,
		// csp.addConstraint(new NotEqualConstraint(var1, var2)); // meaning var1 != var2
		// csp.addConstraint(new EqualConstraint(var1, var2)); // meaning var1 == var2
		// csp.addConstraint(new SuccessorConstraint(var1, var2)); // meaning var1 == var2 + 1
		// csp.addConstraint(new DifferByOneConstraint(var1, var2)); // meaning var1 == var2 + 1 or var1 == var2 - 1 
		
		for(int i = 0; i < 5; i++) {
			for(int j = i + 1; j < 5; j++) {
				//System.out.println(i + " + " + j);
				csp.addConstraint(new NotEqualConstraint(variables.get(i), variables.get(j)));
				csp.addConstraint(new NotEqualConstraint(variables.get(i+5), variables.get(j+5)));
				csp.addConstraint(new NotEqualConstraint(variables.get(i+10), variables.get(j+10)));
				csp.addConstraint(new NotEqualConstraint(variables.get(i+15), variables.get(j+15)));
				csp.addConstraint(new NotEqualConstraint(variables.get(i+20), variables.get(j+20)));
			}
		}
		
		
		
		csp.addConstraint(new NotEqualConstraint(norwegian, milk));
		
		csp.addConstraint(new EqualConstraint(green, coffee));
		csp.addConstraint(new EqualConstraint(oldGold, snails));
		csp.addConstraint(new EqualConstraint(yellow, kools));
		csp.addConstraint(new EqualConstraint(luckyStrike, orangeJuice));
		
		csp.addConstraint(new SuccessorConstraint(ivory, green));
		
		csp.addConstraint(new DifferByOneConstraint(chesterFields, fox));
		csp.addConstraint(new DifferByOneConstraint(yellow, horse));
		csp.addConstraint(new DifferByOneConstraint(kools, horse));
		
		
		return csp;
	}

	private static void printSolution(Assignment solution) {
		// TODO print out useful answer
		// You can use the following to get the value assigned to a variable:
		// Object value = solution.getAssignment(var); 
		// For debugging it might be useful to print the complete assignment and check whether
		// it makes sense.
		System.out.println("solution:" + solution);
	}
	
	/**
	 * runs the CSP backtracking solver with the given parameters and print out some statistics
	 * @param description
	 * @param enableMRV
	 * @param enableDeg
	 * @param enableAC3
	 * @param enableLCV
	 */
	private static void findSolution(String description, boolean enableMRV, boolean enableDeg, boolean enableAC3, boolean enableLCV) {
		CSP csp = setupCSP();

		System.out.println("======================");
		System.out.println("running " + description);
		
		long startTime, endTime;
		startTime = System.currentTimeMillis();
		SolutionStrategy solver = new ImprovedBacktrackingStrategy(enableMRV, enableDeg, enableAC3, enableLCV);
		final int nbAssignments[] = {0};
		solver.addCSPStateListener(new CSPStateListener() {
			@Override
			public void stateChanged(Assignment arg0, CSP arg1) {
				nbAssignments[0]++;
			}
			@Override
			public void stateChanged(CSP arg0) {}
		});
		Assignment solution = solver.solve(csp);
		endTime = System.currentTimeMillis();
		System.out.println("runtime " + (endTime-startTime)/1000.0 + "s" + ", number of assignments (visited states):" + nbAssignments[0]);
		printSolution(solution);
	}

	/**
	 * main procedure
	 */
	public static void main(String[] args) throws Exception {
		// run solver with different parameters
		findSolution("backtracking + AC3 + most constrained variable + least constraining value", true, true, true, true);
		findSolution("backtracking + AC3 + most constrained variable", true, true, true, false);
		findSolution("backtracking + AC3", false, false, true, false);
		findSolution("backtracking + forward checking + most constrained variable + least constraining value", true, true, false, true);
		findSolution("backtracking + forward checking + most constrained variable", true, true, false, false);
		findSolution("backtracking + forward checking", false, false, false, false);
	}

}
