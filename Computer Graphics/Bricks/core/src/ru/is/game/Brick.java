package ru.is.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Brick {
	public Point2D position = new Point2D(); // 2D coordinates
	public float sx, sy; // Brick translation coordinates
	public float r, g, b; // Brick colour
	
	public Brick(float x, float y, float sx, float sy, float r, float g, float b) {
		position.x = x;
		position.y = y;
		
		this.sx = sx;
		this.sy = sy;
		
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void update() {
		
	}
	
	public void update(float time) {
		if(Gdx.input.isKeyPressed(Input.Keys.D) && position.x + (sx / 2) < 45) {
			position.x += 50 * time;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.A) && position.x - (sx / 2) > -45) {
			position.x -= 50 * time;
		}
	}
	
	public void display(int colorLoc) {
		ModelMatrix.main.loadIdentityMatrix();
		GraphicsEnvironment.setColor(r, g, b);
		ModelMatrix.main.addTranslation(position.x, position.y, 0);
		ModelMatrix.main.addScale(sx, sy, 0);
		ModelMatrix.main.setShaderMatrix();
		BoxShape.drawBoxGraphic();
	}

}
