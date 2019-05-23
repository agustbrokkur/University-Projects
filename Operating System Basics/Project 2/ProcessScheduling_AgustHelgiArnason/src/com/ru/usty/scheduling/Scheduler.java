package com.ru.usty.scheduling;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import com.ru.usty.scheduling.process.ProcessExecution;

public class Scheduler {

	ProcessExecution processExecution;
	Policy policy;
	int quantum;
	boolean deadthread;
	
	Queue<ProcessData> processQueue;
	Thread robinThread;
	
    PriorityQueue<ProcessData> priorityQueue;
	Comparator<ProcessData> comparator;
	boolean firstEnter;
	boolean firstExit;
	
	ProcessData shortestOne;

	/**
	 * Add any objects and variables here (if needed)
	 */


	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public Scheduler(ProcessExecution processExecution) {
		this.processExecution = processExecution;

		/**
		 * Add general initialization code here (if needed)
		 */
	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void startScheduling(Policy policy, int quantum) {

		this.policy = policy;
		this.quantum = quantum;
		
		this.processQueue = new LinkedList<ProcessData>();
		
		deadthread = true;
		
		if(robinThread != null) {
			if(robinThread.isAlive()) {
				
				try {
					
					robinThread.join();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		/*
		processQueue.add(5);
		processQueue.add(3);
		
		int i = processQueue.remove();
		processQueue.remove(new Integer(5));
		processQueue.remove(5);*/
		
		/**
		 * Add general initialization code here (if needed)
		 */

		switch(policy) {
		case FCFS:	//First-come-first-served
			System.out.println("Starting new scheduling task: First-come-first-served");
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case RR:	//Round robin
			System.out.println("Starting new scheduling task: Round robin, quantum = " + quantum);

			deadthread = false;
			this.robinThread = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					ProcessData temp = new ProcessData(-1, -1, -1, -1);
					while(true) {
						try {
							if(deadthread) {
								return;
							}
							Thread.sleep(quantum + 10);
							if(!processQueue.isEmpty()) {
								/*for(ProcessData data : processQueue) {
									System.out.print(data.processID + " ");
								}*/
								//System.out.println();
								if(processQueue.peek() != temp) {
									processExecution.switchToProcess(processQueue.peek().processID);
									temp = processQueue.peek();
								}
								else {
									processQueue.add(processQueue.remove());
									processExecution.switchToProcess(processQueue.peek().processID);
									temp = processQueue.peek();
								}
								//System.out.println(processExecution.getProcessInfo(processQueue.peek().processID).elapsedExecutionTime);
							}
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			});
			robinThread.start();
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN:	//Shortest process next
			System.out.println("Starting new scheduling task: Shortest process next");

	    	comparator = new ShortestProcessComparator();
	    	priorityQueue = new PriorityQueue<ProcessData>(comparator);
			firstEnter = false;
			firstExit = false;
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SRT:	//Shortest remaining time
			System.out.println("Starting new scheduling task: Shortest remaining time");

	    	comparator = new ShortestRemainingComparator();
	    	priorityQueue = new PriorityQueue<ProcessData>(comparator);
			shortestOne = null;
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN:	//Highest response ratio next
			System.out.println("Starting new scheduling task: Highest response ratio next");

	    	comparator = new HighestResponseRatioComparator();
	    	priorityQueue = new PriorityQueue<ProcessData>(comparator);
			firstEnter = false;
			firstExit = false;
	    	
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case FB:	//Feedback
			System.out.println("Starting new scheduling task: Feedback, quantum = " + quantum);
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		}

		/**
		 * Add general scheduling or initialization code here (if needed)
		 */

	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void processAdded(int processID) {
		
		switch(policy) {
		case FCFS:	//First-come-first-served
			
			processQueue.add(new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, 
					processExecution.getProcessInfo(processID).elapsedExecutionTime, processExecution.getProcessInfo(processID).elapsedWaitingTime));
			if(processQueue.size() == 1) {
				processExecution.switchToProcess(processID);
			}
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case RR:	//Round robin

			processQueue.add(new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, 
					processExecution.getProcessInfo(processID).elapsedExecutionTime, processExecution.getProcessInfo(processID).elapsedWaitingTime));
			if(processQueue.size() == 1) {
				processExecution.switchToProcess(processID);
			}
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN:	//Shortest process next
			priorityQueue.offer(new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, 
					processExecution.getProcessInfo(processID).elapsedExecutionTime, processExecution.getProcessInfo(processID).elapsedWaitingTime));
			if(!firstEnter) {
				firstEnter = true;
				processExecution.switchToProcess(processID);
			}
			/*
			for(ProcessData data : priorityQueue) {
				System.out.print("(" + data.processID + ", " + data.totalServiceTime + ") ");
			}
			System.out.println();
			ProcessData tr = new ProcessData(-1, -1);
			if(priorityQueue.size() == 15) {
				for(int i = 0; i < 15; i++) {
					tr = priorityQueue.poll();
					System.out.print("(" + tr.processID + ", " + tr.totalServiceTime + ") ");
				}
			}*/
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SRT:	//Shortest remaining time
			
			if(shortestOne == null) {
				shortestOne = new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, 
						processExecution.getProcessInfo(processID).elapsedExecutionTime, processExecution.getProcessInfo(processID).elapsedWaitingTime);
				processExecution.switchToProcess(processID);
			}
			else {
				shortestOne = new ProcessData(shortestOne.processID, shortestOne.totalServiceTime, 
						processExecution.getProcessInfo(shortestOne.processID).elapsedExecutionTime, processExecution.getProcessInfo(shortestOne.processID).elapsedWaitingTime);
				if(shortestOne.remainingTime > processExecution.getProcessInfo(processID).totalServiceTime) {
					//RSystem.out.println(shortestOne.remainingTime + ", " + shortestOne.totalServiceTime + "  -  " + processExecution.getProcessInfo(processID).totalServiceTime + ", " +  processExecution.getProcessInfo(processID).elapsedExecutionTime + " (Smaller)");
					priorityQueue.offer(shortestOne);
					shortestOne = new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, 
							processExecution.getProcessInfo(processID).elapsedExecutionTime, processExecution.getProcessInfo(processID).elapsedWaitingTime);
					processExecution.switchToProcess(processID);
				}
				else {
					//System.out.println(shortestOne.remainingTime + ", " + shortestOne.totalServiceTime + "  -  " + processExecution.getProcessInfo(processID).totalServiceTime + ", " +  processExecution.getProcessInfo(processID).elapsedExecutionTime + " (Bigger)");
					priorityQueue.offer(new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, 
							processExecution.getProcessInfo(processID).elapsedExecutionTime, processExecution.getProcessInfo(processID).elapsedWaitingTime));
				}
			}
			
			/*
			if(shortestOne == null) {
				shortestOne = new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, processExecution.getProcessInfo(processID).elapsedExecutionTime);
				processExecution.switchToProcess(processID);
			}
			else if((shortestOne.totalServiceTime - processExecution.getProcessInfo(shortestOne.processID).elapsedExecutionTime) > processExecution.getProcessInfo(processID).totalServiceTime) {
				priorityQueue.offer(new ProcessData(shortestOne.processID, shortestOne.totalServiceTime, shortestOne.remainingTime));
				shortestOne = new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, processExecution.getProcessInfo(processID).elapsedExecutionTime);
				processExecution.switchToProcess(processID);
			}
			else {
				processQueue.add(new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, processExecution.getProcessInfo(processID).elapsedExecutionTime));
			}*/
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN:	//Highest response ratio next
			
			priorityQueue.offer(new ProcessData(processID, processExecution.getProcessInfo(processID).totalServiceTime, 
					processExecution.getProcessInfo(processID).elapsedExecutionTime, processExecution.getProcessInfo(processID).elapsedWaitingTime));
			if(!firstEnter) {
				firstEnter = true;
				processExecution.switchToProcess(processID);
			}
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case FB:	//Feedback
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		}


		/**
		 * Add scheduling code here
		 */

	}

	/**
	 * DO NOT CHANGE DEFINITION OF OPERATION
	 */
	public void processFinished(int processID) {
		
		switch(policy) {
		case FCFS:	//First-come-first-served
			
			processQueue.remove();
			if(!processQueue.isEmpty()) {
				processExecution.switchToProcess(processQueue.peek().processID);
			}
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case RR:	//Round robin

			processQueue.remove();
			
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SPN:	//Shortest process next
			
			if(!firstExit) {
				firstExit = true;
				priorityQueue.remove();
			}
			if(priorityQueue.isEmpty()) {
				firstExit = false;
				firstEnter = false;
			}
			else {
				processExecution.switchToProcess(priorityQueue.poll().processID);
			}
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case SRT:	//Shortest remaining time

			if(!priorityQueue.isEmpty()) {
				shortestOne = priorityQueue.poll();
				processExecution.switchToProcess(shortestOne.processID);
			}
			else {
				shortestOne = null;
			}
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case HRRN:	//Highest response ratio next
			
			if(!firstExit) {
				firstExit = true;
				priorityQueue.remove();
			}
			if(priorityQueue.isEmpty()) {
				firstExit = false;
				firstEnter = false;
			}
			else {
				for(int i = 0; i < priorityQueue.size(); i++) {
					processQueue.add(priorityQueue.poll());
				}
				for(ProcessData data : processQueue) {
					priorityQueue.add(new ProcessData(data.processID, data.totalServiceTime, 
						processExecution.getProcessInfo(data.processID).elapsedExecutionTime, processExecution.getProcessInfo(data.processID).elapsedWaitingTime));
					
				}
				processExecution.switchToProcess(priorityQueue.poll().processID);
				processQueue.clear();
			}
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		case FB:	//Feedback
			
			/**
			 * Add your policy specific initialization code here (if needed)
			 */
			break;
		}


		/**
		 * Add scheduling code here
		 */

	}
}
