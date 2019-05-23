package ru.is.game;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.BufferUtils;

public class BouncingBricks extends ApplicationAdapter {
	
	private FloatBuffer vertexBuffer;
	
	private FloatBuffer modelMatrix;
	private FloatBuffer projectionMatrix;

	private int renderingProgramID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private int positionLoc;

	private int modelMatrixLoc;
	private int projectionMatrixLoc;

	private int colorLoc;
	
	// Coordinates and vector of the ball
	private Point2D positionA;
	private Vector2D motionA;
	
	// Sets the size(not amount) of floats of an array in a VertexBuffer
	private float size = 0.5f;
	
	// Helps to find the corners of a brick-box
	private Point2D checkBottomLeft;
	private Point2D checkBottomRight;
	private Point2D checkTopLeft;
	private Point2D checkTopRight;
	
	// Checks to see if the goes down the bottom of the screen
	private boolean death = false;
	
	// Stops and starts the game
	private boolean gameUnPaused = false;

	private Brick playerPaddle = new Brick(0, -42.5f, 12.5f, 2.5f, 0.5f, 0.5f, 0.5f);
	
	private Brick leftWall = new Brick(-47.5f, 0, 5, 100, 0.5f, 0.5f, 0.5f);
	private Brick rightWall = new Brick(47.5f, 0, 5, 100, 0.5f, 0.5f, 0.5f);
	private Brick topWall = new Brick(0, 47.5f, 100, 5, 0.5f, 0.5f, 0.5f);
	private Brick bottomDeath = new Brick(0, -52.5f, 100, 5, 0.5f, 0.5f, 0.5f);
	
	private ArrayList<Brick> outerWalls = new ArrayList<Brick>();
	
	private ArrayList<Brick> level1 = new ArrayList<Brick>();
	
	@Override
	public void create () {

		String vertexShaderString;
		String fragmentShaderString;

		vertexShaderString = Gdx.files.internal("shaders/simple2D.vert").readString();
		fragmentShaderString =  Gdx.files.internal("shaders/simple2D.frag").readString();

		vertexShaderID = Gdx.gl.glCreateShader(GL20.GL_VERTEX_SHADER);
		fragmentShaderID = Gdx.gl.glCreateShader(GL20.GL_FRAGMENT_SHADER);
	
		Gdx.gl.glShaderSource(vertexShaderID, vertexShaderString);
		Gdx.gl.glShaderSource(fragmentShaderID, fragmentShaderString);
	
		Gdx.gl.glCompileShader(vertexShaderID);
		Gdx.gl.glCompileShader(fragmentShaderID);

		renderingProgramID = Gdx.gl.glCreateProgram();
	
		Gdx.gl.glAttachShader(renderingProgramID, vertexShaderID);
		Gdx.gl.glAttachShader(renderingProgramID, fragmentShaderID);
	
		Gdx.gl.glLinkProgram(renderingProgramID);

		GraphicsEnvironment.positionLoc				= Gdx.gl.glGetAttribLocation(renderingProgramID, "a_position");
		Gdx.gl.glEnableVertexAttribArray(positionLoc);

		GraphicsEnvironment.modelMatrixLoc			= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_modelMatrix");
		GraphicsEnvironment.projectionMatrixLoc	= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_projectionMatrix");

		GraphicsEnvironment.colorLoc				= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_color");

		Gdx.gl.glUseProgram(renderingProgramID);
		
		GraphicsEnvironment.setWindow(0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight());
		//GraphicsEnvironment.initializeModelMatrix();

		//COLOR IS SET HERE
		Gdx.gl.glUniform4f(GraphicsEnvironment.colorLoc, 0.7f, 0.2f, 0, 1);
		
		ModelMatrix.main = new ModelMatrix();
		
		BrickBall.create(GraphicsEnvironment.positionLoc);
		BoxShape.create(GraphicsEnvironment.positionLoc, size);
		
		// Non point-block objects
		outerWalls.add(leftWall);
		outerWalls.add(rightWall);
		outerWalls.add(topWall);
		outerWalls.add(bottomDeath);
		outerWalls.add(playerPaddle);
		
		positionA = new Point2D();
		positionA.x = 0f;
		positionA.y = -35.0f;
		
		motionA = new Vector2D();
		motionA.x = 30.0f;
		motionA.y = 20.0f;
		
		createLevel(level1);
	}
	
	public void createLevel(ArrayList<Brick> level12) {
		
		// Red blocks
		for(float i = -42.5f; i < 45; i += 5) {
			level12.add(new Brick(i, 35, 5, 5, 1, 0, 0));
		}

		// Orange blocks
		for(float i = -42.5f; i < 45; i += 5) {
			level12.add(new Brick(i, 30, 5, 5, 1, 0.5f, 0));
		}

		// Yellow blocks
		for(float i = -42.5f; i < 45; i += 5) {
			level12.add(new Brick(i, 25, 5, 5, 1, 1, 0));
		}

		// Green blocks
		for(float i = -42.5f; i < 45; i += 5) {
			level12.add(new Brick(i, 20, 5, 5, 0, 1, 0));
		}

		// Blue blocks
		for(float i = -42.5f; i < 45; i += 5) {
			level12.add(new Brick(i, 15, 5, 5, 0, 0, 1));
		}

		// Purple blocks
		for(float i = -42.5f; i < 45; i += 5) {
			level12.add(new Brick(i, 10, 5, 5, 0.5f, 0, 1));
		}

		// Violet blocks
		for(float i = -42.5f; i < 45; i += 5) {
			level12.add(new Brick(i, 5, 5, 5, 1, 0, 1));
		}
	}
	
	// Checks the distance between two points. Useful in finding out if one point is between two other points.
	public float calculateDistanceBetweenPoints(float x1, float y1, float x2, float y2) {
		return (float) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	// Calculates time until collision(timeToHit) and location(answer) and returns the time
	public float checkCollisionLocation(Point2D particle, Vector2D direction, Point2D lineA, Point2D lineB) {
		Point2D v = new Point2D((lineB.x - lineA.x), (lineB.y - lineA.y));
		Point2D n = new Point2D(- v.y, v.x);
		
		v.x = lineA.x - particle.x;
		v.y = lineA.y - particle.y;
		
		float timeToHit = ((n.x * v.x) + (n.y * v.y)) / ((n.x * direction.x) + (n.y * direction.y));
		
		n.x = timeToHit * direction.x;
		n.y = timeToHit * direction.y;

		Point2D answer = new Point2D(particle.x + n.x, particle.y + n.y);
		
		
		// Checks if the points intersects between the two points
		float ac = calculateDistanceBetweenPoints(lineA.x, lineA.y, answer.x, answer.y);
		float bc = calculateDistanceBetweenPoints(lineB.x, lineB.y, answer.x, answer.y);
		float ab = calculateDistanceBetweenPoints(lineA.x, lineA.y, lineB.x, lineB.y);
		boolean check = ac + bc == ab;
		
		if(!Float.isInfinite(timeToHit) && check) {
			return timeToHit;
		} else {
			return Float.MAX_VALUE;
		}
		
	}
	
	// Calculates the new vector after a collision and returns it.
	public Vector2D collisionDirectionChange(Vector2D direction, Point2D lineA, Point2D lineB) {
		Point2D v = new Point2D((lineB.x - lineA.x), (lineB.y - lineA.y));
		Point2D n = new Point2D(- v.y, v.x);
		
		float f = (direction.x * n.x) + (direction.y * n.y);
		float f1 = (n.x * n.x)+ (n.y * n.y);
		f = f / f1;
		f = f * 2;
		
		v = new Point2D(f * n.x, f * n.y);
		
		Vector2D result = new Vector2D(direction.x - v.x, direction.y - v.y);
		
		return result;
	}
	
	// Takes an array of Brick objects, and compares all of their sides to check if the ball collides with them
	public void collisionArrayCheck(ArrayList<Brick> listi, float checkTime) {
		Point2D firstPoint = null;
		Point2D secondPoint = null;
		Brick tmp = null;
		float lowestTime = Float.MAX_VALUE;
		float tmpTime;
		do {
			for(Brick b : listi) {
				checkBottomLeft = new Point2D(b.position.x - (b.sx / 2), b.position.y - (b.sy / 2));
				checkBottomRight = new Point2D(b.position.x + (b.sx / 2), b.position.y - (b.sy / 2));
				checkTopLeft = new Point2D(b.position.x - (b.sx / 2), b.position.y + (b.sy / 2));
				checkTopRight = new Point2D(b.position.x + (b.sx / 2), b.position.y + (b.sy / 2));
				
				tmpTime = checkCollisionLocation(positionA, motionA, checkBottomLeft, checkBottomRight);
				if(lowestTime > tmpTime && tmpTime > 0) {
					tmp = b;
					lowestTime = tmpTime;
					firstPoint = checkBottomLeft;
					secondPoint = checkBottomRight;
					if(b == bottomDeath) {
						death = true;
					}
					else {
						death = false;
					}
				}
				
				tmpTime = checkCollisionLocation(positionA, motionA, checkBottomLeft, checkTopLeft);
				if(lowestTime > tmpTime && tmpTime > 0) {
					tmp = b;
					lowestTime = tmpTime;
					firstPoint = checkBottomLeft;
					secondPoint = checkTopLeft;
					if(b == bottomDeath) {
						death = true;
					}
					else {
						death = false;
					}
				}
				
				tmpTime = checkCollisionLocation(positionA, motionA, checkBottomRight, checkTopRight);
				if(lowestTime > tmpTime && tmpTime > 0) {
					tmp = b;
					lowestTime = tmpTime;
					firstPoint = checkBottomRight;
					secondPoint = checkTopRight;
					if(b == bottomDeath) {
						death = true;
					}
					else {
						death = false;
					}
				}
				
				tmpTime = checkCollisionLocation(positionA, motionA, checkTopLeft, checkTopRight);
				if(lowestTime > tmpTime && tmpTime > 0) {
					tmp = b;
					lowestTime = tmpTime;
					firstPoint = checkTopLeft;
					secondPoint = checkTopRight;
					if(b == bottomDeath) {
						death = true;
					}
					else {
						death = false;
					}
				}
			}
			
			// Reset the ball
			
			if(death && lowestTime < checkTime) {
				positionA.x = 0f;
				positionA.y = -35.0f;
				motionA.x = 30.0f;
				motionA.y = 20.0f;
				
				death = false;
				break;
			}
			
			System.out.println();
			System.out.println("Lowest Time: " + lowestTime);
			System.out.println("Delta Time: " + checkTime);
			System.out.println();
			if(lowestTime < 0.01f) {
				lowestTime = 0.0f;
			}
			if(lowestTime < checkTime && firstPoint != null && secondPoint != null) {
				motionA = collisionDirectionChange(motionA, firstPoint, secondPoint);
				if(level1.contains(tmp) && tmp != null) {
					level1.remove(tmp);
				}
			}
			
		} while(lowestTime < 0);
	}
	
	public void update() {
		// Pauses and unpauses the game
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			gameUnPaused = !gameUnPaused;
		}
		
		if(gameUnPaused) {
			float deltatime = Gdx.graphics.getDeltaTime();
			playerPaddle.update(deltatime);
			positionA.x += motionA.x * deltatime;
			positionA.y += motionA.y * deltatime;
			
			collisionArrayCheck(outerWalls, deltatime);

			collisionArrayCheck(level1, deltatime);
		}
	}

	public void display() {
		GraphicsEnvironment.setWindow(-50, 50, -50, 50);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		// Displays the walls
		leftWall.display(GraphicsEnvironment.colorLoc);
		rightWall.display(GraphicsEnvironment.colorLoc);
		topWall.display(GraphicsEnvironment.colorLoc);
		bottomDeath.display(GraphicsEnvironment.colorLoc);
		
		// Displays the player paddle
		playerPaddle.display(GraphicsEnvironment.colorLoc);
		
		for(Brick b : level1) {
			b.display(GraphicsEnvironment.colorLoc);
		}
		
		
		// Displays the ball
		ModelMatrix.main.loadIdentityMatrix();
		ModelMatrix.main.addTranslation(positionA.x, positionA.y, 0);
		GraphicsEnvironment.setColor(1, 1, 1);
		GraphicsEnvironment.setShaderModelMatrix(ModelMatrix.main);
		BrickBall.drawBrickBall();
		
		
		
		//ModelMatrix.main.loadIdentityMatrix();
		//GraphicsEnvironment.setShaderModelMatrix(ModelMatrix.main);
	}

	@Override
	public void render () {
		update();
		display();
	}
	
	@Override
	public void dispose () {
	}
}
