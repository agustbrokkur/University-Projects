package com.ru.usty.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadAssignment01Main {

    private static final int NUMBER_OF_PROBLEMS = 30;
    private static final int POOL_SIZE = 6;

    public static void main(String[] args) {
        System.out.println("Processors: " + Runtime.getRuntime().availableProcessors());
        System.out.println("Solutions:");

        //Solver.findAndPrintSolution(Problematic.nextProblem());
        long startTime = System.currentTimeMillis();
        /* Lausn nr. 1
        for(int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
        	Solver.findAndPrintSolution(Problematic.nextProblem());
        }
        */
        
        
        // Lausn nr. 2
        /*
        Thread[] threads = new Thread[NUMBER_OF_PROBLEMS];
        for(int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            threads[i] = new Thread(new makeNewRunnable(Problematic.nextProblem()));
            threads[i].start();
        }
        try {
        	for(int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            	threads[i].join();
        	}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        
        // Lausn nr. 3
        ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);
        Thread[] threads = new Thread[NUMBER_OF_PROBLEMS];
        for(int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            threads[i] = new Thread(new makeNewRunnable(Problematic.nextProblem()));
            threadPool.execute(threads[i]);
        }
        try { 
        	threadPool.shutdown();
        	threadPool.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
        	e.printStackTrace();
        } 
        try {
        	for(int i = 0; i < NUMBER_OF_PROBLEMS; i++) {
            	threads[i].join();
        	}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("All done");

        System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + " ms");
    }
}
