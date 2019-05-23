package com.ru.tgra.shapes;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.Random;


public class First3DGame extends ApplicationAdapter implements InputProcessor {
	long startTimer = System.nanoTime();
	
	private Shader shader;
	
	private Level currentLevel;
	
	//private int pointCounter;
	
	private int[] blockColor = new int[1000];
	private int blockCounter = 0;
	
	//private float angle;
	
	private boolean moveBlocker = false;
	
	//0 == Top
	//1 == Front
	//2 == Left
	//3 == Right
	//4 == Back
	//5 == Bottom
	private int currentMovement = 0;
	
	//0 == up
	//1 == down
	//2 == left
	//3 == right
	private int direction = 0;
	
	private static Point3D topPoint;
	private static Point3D bottomPoint;
	private static Point3D frontPoint;
	private static Point3D leftPoint;
	private static Point3D rightPoint;
	private static Point3D backPoint;
	
	private static Point3D midPoint1;
	private static Point3D midPoint2;
	
	static float startTime;
	static float endTime;
	
	private Camera cam;
	private ArrayList<DropBox> boxes = new ArrayList<DropBox>();
	
	private ArrayList<Float> wireframeBoxes = new ArrayList<Float>();

	//private ModelMatrix modelMatrix;

	@Override
	public void create () {
		
		Random random = new Random();
		
		for(int i = 0; i < 1000; i++) {
			blockColor[i] = random.nextInt(3 - 1 + 1) + 1;
		}
		
		topPoint = new Point3D(10.0f, 0.0f, 0.0f);
		bottomPoint = new Point3D(-10.0f, 0.0f, 0.0f);
		frontPoint = new Point3D(0.0f, -10.0f, 0.0f);
		leftPoint = new Point3D(0.0f, 0.0f, 10.0f);
		rightPoint = new Point3D(0.0f, 0.0f, -10.0f);
		backPoint = new Point3D(0.0f, 10.0f, 0.0f);
		
		Gdx.input.setInputProcessor(this);
		
		shader = new Shader();
		currentLevel = new Level(shader);

		BoxGraphic.create();
		SphereGraphic.create();
		//SincGraphic.create(positionLoc);
		//CoordFrameGraphic.create(positionLoc);

		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		ModelMatrix.main = new ModelMatrix();
		ModelMatrix.main.loadIdentityMatrix();
		ModelMatrix.main.setShaderMatrix(shader.modelMatrixLoc);

		Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

		cam = new Camera();
		cam.look(new Point3D(10.0f, 0.0f, 0.0f), new Point3D(0,0,0), new Vector3D(0,1,0));
		cam.perspectiveProjection(60.0f, 1.0f, 0.4f, 40.0f);
		Gdx.gl.glUniformMatrix4fv(shader.projectionMatrixLoc, 1, false, cam.getProjectionMatrix());
		
		displayInstructions();
	}
	
	private void displayInstructions() {
		System.out.println("INSTRUCTIONS: ");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("RIGHT/LEFT CLICK: Places the next color block on the corresponding wireframe square.\n");
		System.out.println("DOWN ARROW: Moves the camera down to another view.");
		System.out.println("Only usable when viewing from the TOP or the FRONT.\n");
		System.out.println("UP ARROW: Moves the camera up to another view.");
		System.out.println("Only usable when viewing from the FRONT or the BOTTOM.\n");
		System.out.println("LEFT ARROW: Moves the camera view to the left.");
		System.out.println("Only usable when viewing from the FRONT, LEFT, RIGHT or BACK.\n");
		System.out.println("Right ARROW: Moves the camera view to the right.");
		System.out.println("Only usable when viewing from the FRONT, LEFT, RIGHT or BACK.");
		System.out.println("------------------------------------------------------------------------------------");
	}
	
	private void displayBoxes() {
		for(DropBox b : boxes) {
			b.drawDropBox();
		}
	}
	/*
	private void checkScore() {
		//for(currentLevel.get)
		for(DropBox b : boxes) {
			//pointCounter++;
			// ????
		}
	}*/
	
	public void setupCamera(int dir, Point3D mid1, Point3D mid2) {
		startTime = (System.nanoTime() - startTimer) * 0.000000001f;
		endTime = startTime + 3;
		moveBlocker = true;
		direction = dir;
		midPoint1 = mid1;
		midPoint2 = mid2;
	}
	
	public void moveCamera(int curMov, Point3D startPoint, Point3D endPoint,
							int ux, int uy, int uz) {
		if(((System.nanoTime() - startTimer) * 0.000000001f) > endTime) {
			moveBlocker = false;
			direction = 0;
			currentMovement = curMov;
		} else {
			Point3D temp = new Point3D();
			temp.lerp4(startPoint, midPoint1, midPoint2,
						endPoint, ((System.nanoTime() - startTimer) * 0.000000001f),
					startTime, endTime);
			cam.look(temp, new Point3D(0,0,0), new Vector3D(ux, uy, uz));
		}
	}
	
	public void showNextBox() {
		if(currentMovement == 0 && direction == 0) {
			DropBox newBox = new DropBox(shader, cam.eye.x - 8.0f, cam.eye.y + 3.8f, cam.eye.z - 3.8f, blockColor[blockCounter]);
			newBox.drawNextDropBox();
		}
	}
	
	public void checkLevel() {
		if(currentLevel.getCurrentLevel() < 4) {
			int ex = 0;
			int why = 0;
			int depthCheck[][] = currentLevel.getCurrentLevelCapacity();
			for(int i = 0; i < depthCheck.length; i++) {
				for(int j = 0; j < depthCheck[i].length; j++) {
					ex++;
					if(depthCheck[i][j] == 5) {
						why++;
					}
				}
			}
			if(ex == why) {
				currentLevel.setCurrentLevel(currentLevel.getCurrentLevel() + 1);
				boxes.clear();
			}
		}
	}

	private void input()
	{
		//float deltaTime = Gdx.graphics.getDeltaTime();
		//angle += 180.0f * deltaTime;
		if(!moveBlocker) {
			if(Gdx.input.justTouched()) {
				// Normalizes the x and y coordinates of the mouse click
				double normalizedX = -1.0 + 2.0 * (double)Gdx.input.getX() / Gdx.graphics.getWidth(); 
				double normalizedY = 1.0 - 2.0 * (double)Gdx.input.getY() / Gdx.graphics.getHeight(); 
				// increases the values by 5.
				// To make the X and Y values of the box to be correct relative to the 
				// camera, you need swap them and make the Y value negative.
				float thisX = (float)normalizedY * 5;
				float thisY = - ((float)normalizedX * 5);
				// Go through all of the boxes.
				// Checks if the mouse click hits just inside the box
				// wireframeBoxes.get(i) = x, wireframeBoxes.get(i + 1) = y
				// The boxes are, unscaled, 0.5 in all 4 directions.
				for(int i = 0; i < wireframeBoxes.size(); i += 2) {
					if(thisX > (wireframeBoxes.get(i) - 0.5) && thisX < (wireframeBoxes.get(i) + 0.5) &&
							thisY > (wireframeBoxes.get(i + 1) - 0.5) && thisY < (wireframeBoxes.get(i + 1) + 0.5)) {
						if(currentLevel.increaseCapacity(wireframeBoxes.get(i) ,wireframeBoxes.get(i+1))) {
							DropBox newBox = new DropBox(shader, 2.0f, wireframeBoxes.get(i), wireframeBoxes.get(i + 1),
									currentLevel.getCurrentCapacity(wireframeBoxes.get(i), wireframeBoxes.get(i + 1)),
									blockColor[blockCounter]);
							blockCounter++;
							boxes.add(newBox);
						}
						break;
					}
				}
			}
			// From top to front
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && currentMovement == 0) {
				setupCamera(1, new Point3D(7.5f, -2.5f, 0.0f), new Point3D(2.5f, -7.5f, 0.0f));
			}
			// From front to bottom
			if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && currentMovement == 1) {
				setupCamera(1, new Point3D(-2.5f, -7.5f, 0.0f), new Point3D(-7.5f, -2.5f, 0.0f));
			}
			// From front to Top
			if(Gdx.input.isKeyPressed(Input.Keys.UP) && currentMovement == 1) {
				setupCamera(0, new Point3D(2.5f, -7.5f, 0.0f), new Point3D(7.5f, -2.5f, 0.0f));
			}
			// From bottom to front
			if(Gdx.input.isKeyPressed(Input.Keys.UP) && currentMovement == 5) {
				setupCamera(0, new Point3D(-7.5f, -2.5f, 0.0f), new Point3D(-2.5f, -7.5f, 0.0f));
			}
			// From front to left
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && currentMovement == 1) {
				setupCamera(2, new Point3D(0.0f, -7.5f, 2.5f), new Point3D(0.0f, -2.5f, 7.5f));
			}
			// From front to right
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && currentMovement == 1) {
				setupCamera(3, new Point3D(0.0f, -7.5f, -2.5f), new Point3D(0.0f, -2.5f, -7.5f));
			}
			// From left to back
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && currentMovement == 2) {
				setupCamera(2, new Point3D(0.0f, 2.5f, 7.5f), new Point3D(0.0f, 7.5f, 2.5f));
			}
			// From left to front
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && currentMovement == 2) {
				setupCamera(3, new Point3D(0.0f, -2.5f, 7.5f), new Point3D(0.0f, -7.5f, 2.5f));
			}
			// From right to front
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && currentMovement == 3) {
				setupCamera(2, new Point3D(0.0f, -2.5f, -7.5f), new Point3D(0.0f, -7.5f, -2.5f));
			}
			// From right to back
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && currentMovement == 3) {
				setupCamera(3, new Point3D(0.0f, 2.5f, -7.5f), new Point3D(0.0f, 7.5f, -2.5f));
			}
			// From back to right
			if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && currentMovement == 4) {
				setupCamera(2, new Point3D(0.0f, 7.5f, -2.5f), new Point3D(0.0f, 2.5f, -7.5f));
			}
			// From back to left
			if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && currentMovement == 4) {
				setupCamera(3, new Point3D(0.0f, 7.5f, 2.5f), new Point3D(0.0f, 2.5f, 7.5f));
			}
		} else {
			if(currentMovement == 0) {
				if(direction == 1) {
					moveCamera(1, topPoint, frontPoint, 0, 1, 0);
				}
			}
			//1 == Front
			else if(currentMovement == 1) {
				if(direction == 0) {
					moveCamera(0, frontPoint, topPoint, 1, 0, 0);
				} else if(direction == 1) {
					moveCamera(5, frontPoint, bottomPoint, 1, -1, 0);
				} else if(direction == 2) {
					moveCamera(2, frontPoint, leftPoint, 1, 0, 0);
				} else if(direction == 3) {
					moveCamera(3, frontPoint, rightPoint, 1, 0, 0);
				}
			}
			//2 == Left
			else if(currentMovement == 2) {
				if(direction == 2) {
					moveCamera(4, leftPoint, backPoint, 1, 0, 0);
				} else if(direction == 3) {
					moveCamera(1, leftPoint, frontPoint, 1, 0, 0);
				}
			}
			//3 == Right
			else if(currentMovement == 3) {
				if(direction == 2) {
					moveCamera(1, rightPoint, frontPoint, 1, 0, 0);
				} else if(direction == 3) {
					moveCamera(4, rightPoint, backPoint, 1, 0, 0);
				}
			}
			//4 == Back
			else if(currentMovement == 4) {
				if(direction == 2) {
					moveCamera(3, backPoint, rightPoint, 1, 0, 0);
				} else if(direction == 3) {
					moveCamera(2, backPoint, leftPoint, 1, 0, 0);
				}
			}
			//5 == Bottom
			else if(currentMovement == 5) {
				if(direction == 0) {
					moveCamera(1, bottomPoint, frontPoint, 0, -1, 0);
				}
			}
		}
	}
	
	private void update()
	{
		//float deltaTime = Gdx.graphics.getDeltaTime();
		//angle += 180.0f * deltaTime;
		
		checkLevel();
		
		/*
		ModelMatrix mm = new ModelMatrix();
		mm.loadIdentityMatrix();
		mm.addTranslation(2.0f, 0.0f, 0.0f);

		//do all updates to the game
		cam.look(new Point3D(0.0f, 0.0f, 3.0f), mm.getOrigin(), new Vector3D(0,1,0));
*/
	}
	
	private void display()
	{
		//do all actual drawing and rendering here
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		Gdx.gl.glUniformMatrix4fv(shader.viewMatrixLoc, 1, false, cam.getViewMatrix());
		
		Gdx.gl.glUniform4f(shader.eyePositionLoc, cam.eye.x, cam.eye.y, cam.eye.z, 1.0f);

		Gdx.gl.glUniform4f(shader.lightPositionLoc, cam.eye.x, cam.eye.y, cam.eye.z, 1.0f);
		
		//System.out.println("(" + cam.eye.x + ", " + cam.eye.y + ", " + cam.eye.z + ")");
		

		shader.setLightColor(1.0f, 1.0f, 1.0f, 1.0f);
		shader.setLightAmbient(0.2f, 0.2f, 0.7f, 1.0f);

		shader.setMaterialDiffuse(0.2f, 0.2f, 0.7f, 1.0f);
		shader.setMaterialSpecular(0.2f, 0.2f, 0.7f, 1.0f);
		shader.setMaterialShininess(90.0f);
		
		ModelMatrix.main.loadIdentityMatrix();
		ModelMatrix.main.pushMatrix();
		ModelMatrix.main.addScale(20.0f, 20.0f, 20.0f);
		ModelMatrix.main.setShaderMatrix(shader.modelMatrixLoc);
		SphereGraphic.drawSolidSphere(shader);
		ModelMatrix.main.popMatrix();
		
		
		displayBoxes();
		//displayLevel();
		currentLevel.drawLevel(wireframeBoxes);
		showNextBox();
	}

	@Override
	public void render () {
		
		input();
		//put the code inside the update and display methods, depending on the nature of the code
		update();
		display();

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}


}

// examples
// Controls:
/*
if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
	cam.yaw(180.0f * deltaTime);
}
if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
	cam.yaw(-180.0f * deltaTime);
}
if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
	cam.pitch(180.0f * deltaTime);
}
if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
	cam.pitch(-180.0f * deltaTime);
}
if(Gdx.input.isKeyPressed(Input.Keys.A)) {
	cam.slide(-5.0f * deltaTime, 0.0f, 0.0f);
}
if(Gdx.input.isKeyPressed(Input.Keys.D)) {
	cam.slide(5.0f * deltaTime, 0.0f, 0.0f);
}
if(Gdx.input.isKeyPressed(Input.Keys.W)) {
	cam.slide(0.0f, 0.0f, -5.0f * deltaTime);
}
if(Gdx.input.isKeyPressed(Input.Keys.S)) {
	cam.slide(0.0f, 0.0f, 5.0f * deltaTime);
}
if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
	
	cam.slide(0.0f, 5.0f * deltaTime, 0.0f);
}
if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
	cam.slide(0.0f, -5.0f * deltaTime, 0.0f);
}*/

// Drawing
/*
shader.setLightColor(1.0f, 1.0f, 1.0f, 1.0f);
shader.setLightAmbient(0.3f, 0.3f, 0.3f, 1.0f);

shader.setMaterialDiffuse(0.8f, 0.3f, 0.3f, 1.0f);
shader.setMaterialSpecular(0.3f, 0.3f, 0.3f, 1.0f);
shader.setMaterialShininess(900.0f);

ModelMatrix.main.loadIdentityMatrix();
ModelMatrix.main.addTranslation(-10, 0, 0);
ModelMatrix.main.pushMatrix();
ModelMatrix.main.addRotationZ(angle);
ModelMatrix.main.addScale(2.0f, 2.0f, 2.0f);
ModelMatrix.main.setShaderMatrix(shader.modelMatrixLoc);
BoxGraphic.drawSolidCube(shader);
//BoxGraphic.drawOutlineCube();
//SphereGraphic.drawSolidSphere();
//SphereGraphic.drawOutlineSphere();
ModelMatrix.main.popMatrix();


//shader.setMaterialDiffuse(0.3f, 0.3f, 0.8f, 1.0f);
//shader.setMaterialSpecular(0.0f, 0.0f, 0.0f, 1.0f);
//shader.setMaterialShininess(30.f);

ModelMatrix.main.loadIdentityMatrix();
ModelMatrix.main.pushMatrix();
ModelMatrix.main.addScale(2.0f, 2.0f, 2.0f);
ModelMatrix.main.setShaderMatrix(shader.modelMatrixLoc);
//BoxGraphic.drawSolidCube(shader);
//BoxGraphic.drawOutlineCube();
SphereGraphic.drawSolidSphere(shader);
//SphereGraphic.drawOutlineSphere();
ModelMatrix.main.popMatrix();
*/
