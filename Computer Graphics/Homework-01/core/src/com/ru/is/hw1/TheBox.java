package com.ru.is.hw1;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;

public class TheBox {
	
	private static FloatBuffer vertexBuffer;
	private static int vertexPointer;
	
	public static void create(int vertexPointer) {
		TheBox.vertexPointer = vertexPointer;
		float[] array = { - 0.5f,  - 0.5f,       
				 - 0.5f,   0.5f,       
				 0.5f,  - 0.5f,       
				 0.5f,   0.5f}; 
		 
		  vertexBuffer = BufferUtils.newFloatBuffer(8);
		  vertexBuffer.put(array);
		  vertexBuffer.rewind();
		
	}
	
	public static void drawTheBox() {
		Gdx.gl.glVertexAttribPointer(vertexPointer, 2, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_STRIP, 0, 4);
		
	}


}
