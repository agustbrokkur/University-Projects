package ru.is.game;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;

public class BoxShape {
	private static FloatBuffer vertexBuffer;
	private static int vertexPointer;
	
	public static void create(int vertexPointer, float size) {
		BoxShape.vertexPointer = vertexPointer;
		float[] array = { - size,  - size,       
				 - size,   size,       
				 size,  - size,       
				 size,   size}; 
		 
		vertexBuffer = BufferUtils.newFloatBuffer(8);
		vertexBuffer.put(array);
		vertexBuffer.rewind();
		  
	}
	
	public static void drawBoxGraphic() {
		Gdx.gl.glVertexAttribPointer(vertexPointer, 2, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_STRIP, 0, 4);
		
	}


}
