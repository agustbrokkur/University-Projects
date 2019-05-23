package com.ru.usty.elevator;

public class Elevator implements Runnable  {

	
	int currentFloor;
	boolean bottomFloor;
	boolean goingUp;
	
	public Elevator(int currentFloor) {
		this.currentFloor = currentFloor;
		this.bottomFloor = true;
		this.goingUp = true;
	}
	
	
	@Override
	public void run() {
		while(true) {
			
			if(ElevatorScene.scene.elevatorsMayDie) {
				return;
			}
			elevatorSleeping();
			
			//System.out.println(this.currentFloor);
			int count = ElevatorScene.scene.getNumberOfPeopleInElevator(this.currentFloor);
			if(count > 0) {
				while(count > 0 && ElevatorScene.scene.elevatorLeaveFloorMutex.get(this.currentFloor).hasQueuedThreads()) {
					ElevatorScene.scene.elevatorLeaveFloorMutex.get(this.currentFloor).release();
					count--;
					elevatorSleeping();
				}
			}
			
			//System.out.println(ElevatorScene.scene.getNumberOfFloors());
			if(ElevatorScene.scene.getNumberOfPeopleWaitingAtFloor(this.currentFloor) > 0 && count < 6) {
				while(count < 6 && ElevatorScene.scene.getNumberOfPeopleWaitingAtFloor(this.currentFloor) > 0) {
					ElevatorScene.scene.elevatorEnterFloorMutex.get(this.currentFloor).release();
					count++;
					elevatorSleeping();
				}
			}

			elevatorSleeping();
			changeFloor();
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
		ElevatorScene.scene.setCurrentFloorForElevator(this.currentFloor);
	}

}
