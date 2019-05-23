package com.ru.usty.elevator;

public class Person implements Runnable {

	int sourceFloor, destinationFloor;
	
	public Person(int sourceFloor, int destinationFloor) {
		this.sourceFloor = sourceFloor;
		this.destinationFloor = destinationFloor;
	}
	
	@Override
	public void run() {
		try {
			
			ElevatorScene.scene.incrementNumberOfPeopleWaitingAtFloor(sourceFloor);
			//System.out.println("Waiting");
			ElevatorScene.scene.elevatorEnterFloorMutex.get(this.sourceFloor).acquire();
			ElevatorScene.scene.incrementPeopleInElevator();
			ElevatorScene.scene.decrementNumberOfPeopleWaitingAtFloor(sourceFloor);
			ElevatorScene.scene.elevatorLeaveFloorMutex.get(this.destinationFloor).acquire();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		ElevatorScene.scene.decrementPeopleInElevator();
		ElevatorScene.scene.personExitsAtFloor(destinationFloor);
		//System.out.println("Person Thread released");
	}

}
