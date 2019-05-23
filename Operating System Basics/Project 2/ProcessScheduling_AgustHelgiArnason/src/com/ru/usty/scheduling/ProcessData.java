package com.ru.usty.scheduling;

public class ProcessData {
	int processID;
	long totalServiceTime;
	long remainingTime;
	long ratio;
	
	public ProcessData(int processID, long totalServiceTime, long executionTime, long waitingTime) {
		this.processID = processID;
		this.totalServiceTime = totalServiceTime;
		this.remainingTime = this.totalServiceTime - executionTime;
		this.ratio = (waitingTime + this.totalServiceTime) / this.totalServiceTime;
	}
	
}
