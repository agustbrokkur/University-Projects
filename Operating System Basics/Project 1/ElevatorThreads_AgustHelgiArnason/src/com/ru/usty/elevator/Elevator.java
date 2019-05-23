package com.ru.usty.elevator;

public class Elevator implements Runnable {
	
	int elevatorNumber;
	int currentFloor;
	boolean goingUp;
	boolean bottomFloor;
	
	public Elevator(int currentFloor) {
		this.elevatorNumber = 0;
		this.currentFloor = currentFloor;
		this.goingUp = true;
		this.bottomFloor = true;
	}

	@Override
	public void run() {
		while(true) {
			
			if(ElevatorScene.scene.elevatorsMayDie) {
				return;
			}
			
			elevatorSleeping();
			
			//Exit the elevator
			int count = ElevatorScene.scene.getNumberOfPeopleInElevator(this.elevatorNumber);
			if(count > 0) {
				while(count > 0 && ElevatorScene.scene.elevatorLeaveFloorMutex.get(this.currentFloor).hasQueuedThreads()) {
					ElevatorScene.scene.elevatorLeaveFloorMutex.get(this.currentFloor).release();
					count--;
					elevatorSleeping();
					//System.out.println("outside");
				}
				
			}

			//Enter the elevator
			if(ElevatorScene.scene.getNumberOfPeopleWaitingAtFloor(this.currentFloor) > 0 && count < 6) {
				System.out.println("");
				System.out.println("---------");
				while(count < 6 && ElevatorScene.scene.getNumberOfPeopleWaitingAtFloor(this.currentFloor) > 0) {
					ElevatorScene.scene.elevatorEnterFloorMutex.get(this.currentFloor).release();
					count++;
					System.out.println("Going");
					elevatorSleeping();
					//System.out.println("inside");
				}
				System.out.println("---------");
				System.out.println("");
			}
			

			elevatorSleeping();
			
			changeFloor();
			
			ElevatorScene.scene.setCurrentFloorForElevator(this.currentFloor);
			
		}
	}
	
	private void elevatorSleeping() {
		
		try {
			
			Thread.sleep(ElevatorScene.scene.VISUALIZATION_WAIT_TIME);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void changeFloor() {
		if(goingUp) {
			currentFloor++;
		}
		else {
			currentFloor--;
		}
		
		if(currentFloor == 0) {
			bottomFloor = true;
			goingUp = true;
		}
		else {
			bottomFloor = false;
		}
		
		if(currentFloor == ElevatorScene.scene.getNumberOfFloors() - 1) {
			goingUp = false;
		}
	}
}
