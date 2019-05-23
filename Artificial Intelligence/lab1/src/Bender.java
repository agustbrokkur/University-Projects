import java.util.Collection;
import java.util.Random;

public class Bender implements Agent
{
	private State myState = State.TURN_ON;
	private int x = 0;
	private int y = 0;
	private int turns = 0;
	private boolean north = false;
	private boolean turned = false;
	
    public String nextAction(Collection<String> percepts) {
		System.out.print("perceiving:");
		for(String percept:percepts) {
			System.out.println("'" + percept + "', ");
		}
		System.out.println("");
		System.out.printf("Location: (%d,%d)\n", x, y);
		String[] actions = { "TURN_ON", "TURN_OFF", "TURN_RIGHT", "TURN_LEFT", "GO", "SUCK" };
		if(percepts.contains("DIRT")) {
			return actions[5];
		}
		switch(myState) {
		case TURN_ON:
			myState = State.FIND_WALL;
			return actions[0];
		case FIND_WALL:

			if(percepts.contains("BUMP")) {
				myState = State.FIND_CORNER;
				y--;
				return actions[3];
			}
			y++;
			return actions[4];
		case FIND_CORNER:
			x--;
			if(percepts.contains("BUMP")) {
				myState = State.SNAKE;
				x++;
				return actions[3];
			}
			return actions[4];
		case SNAKE:
			if(percepts.contains("BUMP")) {
				if(north) {
					myState = State.DOUBLE_RIGHT;
					y--;
					turns++;
					return actions[2];
				}
				else {
					myState = State.DOUBLE_LEFT;
					y++;
					turns++;
					return actions[3];
				}
			}
			if(north) y++;
			else y--;
			return actions[4];
		case DOUBLE_RIGHT:
			if(percepts.contains("BUMP")) {
				myState = State.TURN_HOME;
			}
			if(turns == 1) {
				x++;
				turns--;
				return actions[4];
			}
			else {
				north = false;
				myState = State.SNAKE;
				if(percepts.contains("BUMP")) {
					myState = State.GO_HOME;
				}
				return actions[2];
			}
		case DOUBLE_LEFT:
			if(turns == 1) {
				x++;
				turns--;
				return actions[4];
			}
			else {
				north = true;
				myState = State.SNAKE;
				if(percepts.contains("BUMP")) {
					myState = State.GO_HOME;
				}
				return actions[3];
			}
		case GO_HOME:
			if(y > 0) {
				y--;
				return actions[4];
			}
			else if(y < 0) {
				y++;
				return actions[4];
			}
			else if(y == 0 && !turned) {
				turned = true;
				if(north) {
					return actions[3];
				}
				else {
					return actions[2];
				}
			}
			if(x > 0) {
				x--;
				return actions[4];
			}
			if(x == 0 && y == 0) {
				return actions[1];
			}
		default:
			return actions[0];
		}
		
	}
    public enum State {
    	TURN_ON, FIND_WALL, FIND_CORNER, SNAKE, DOUBLE_RIGHT, DOUBLE_LEFT, TURN_HOME, GO_HOME
    }
}