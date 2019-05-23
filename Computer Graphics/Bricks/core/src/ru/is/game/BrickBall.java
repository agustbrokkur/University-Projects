package ru.is.game;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;

public class BrickBall {
	private static FloatBuffer vertexBuffer;
	private static int vertexPointer;
	private static int verticesPerCircle = 40;
	
	public static void create(int vertexPointer) {
		BrickBall.vertexPointer = vertexPointer;
		
		double f = 0.0f;
		vertexBuffer = BufferUtils.newFloatBuffer(2*verticesPerCircle);
		
		for(int i = 0; i < verticesPerCircle; i++) {
			vertexBuffer.put(2*i, (float)Math.cos(f));
			vertexBuffer.put(2*i + 1, (float)Math.sin(f));
			
			f += 2.0*Math.PI / (double)verticesPerCircle;
		}
		
		vertexBuffer.rewind();
		
		/*
		BrickBall.vertexPointer = vertexPointer;
		float[] array = { - 0.5f,  - 0.5f,       
				 - 0.5f,   0.5f,       
				 0.5f,  - 0.5f,       
				 0.5f,   0.5f}; 
		 
		  vertexBuffer = BufferUtils.newFloatBuffer(8);
		  vertexBuffer.put(array);
		  vertexBuffer.rewind();*/
		
	}
	
	public static void drawBrickBall() {
		Gdx.gl.glVertexAttribPointer(vertexPointer, 2, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 0, verticesPerCircle);
		
	}

}
