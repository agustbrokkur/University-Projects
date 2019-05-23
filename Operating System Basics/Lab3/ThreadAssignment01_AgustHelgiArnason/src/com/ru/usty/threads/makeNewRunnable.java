package com.ru.usty.threads;

public class makeNewRunnable implements Runnable {
	private final Problem problem;
	
	public makeNewRunnable(Problem nextProblem) {
		this.problem = nextProblem;
	}

	@Override
	public void run() {
    	Solver.findAndPrintSolution(problem);
		
	}

}
