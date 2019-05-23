package com.ru.tgra.shapes;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.BufferUtils;

public class BoxGraphic {

	private static FloatBuffer vertexBuffer;
	private static FloatBuffer normalBuffer;

	public static void create() {
		//VERTEX ARRAY IS FILLED HERE
		float[] vertexArray = {-0.5f, -0.5f, -0.5f, //1
						-0.5f, 0.5f, -0.5f,
						0.5f, 0.5f, -0.5f,
						0.5f, -0.5f, -0.5f,
						-0.5f, -0.5f, 0.5f, //2
						-0.5f, 0.5f, 0.5f,
						0.5f, 0.5f, 0.5f,
						0.5f, -0.5f, 0.5f,
						-0.5f, -0.5f, -0.5f, //3
						0.5f, -0.5f, -0.5f,
						0.5f, -0.5f, 0.5f,
						-0.5f, -0.5f, 0.5f,
						-0.5f, 0.5f, -0.5f, //4
						0.5f, 0.5f, -0.5f,
						0.5f, 0.5f, 0.5f,
						-0.5f, 0.5f, 0.5f,
						-0.5f, -0.5f, -0.5f, //5
						-0.5f, -0.5f, 0.5f,
						-0.5f, 0.5f, 0.5f,
						-0.5f, 0.5f, -0.5f,
						0.5f, -0.5f, -0.5f, //6
						0.5f, -0.5f, 0.5f,
						0.5f, 0.5f, 0.5f,
						0.5f, 0.5f, -0.5f};

		vertexBuffer = BufferUtils.newFloatBuffer(72);
		vertexBuffer.put(vertexArray);
		vertexBuffer.rewind();

		//NORMAL ARRAY IS FILLED HERE
		float[] normalArray = {0.0f, 0.0f, -1.0f, //1
							0.0f, 0.0f, -1.0f,
							0.0f, 0.0f, -1.0f,
							0.0f, 0.0f, -1.0f,
							0.0f, 0.0f, 1.0f, //2
							0.0f, 0.0f, 1.0f,
							0.0f, 0.0f, 1.0f,
							0.0f, 0.0f, 1.0f,
							0.0f, -1.0f, 0.0f, //3
							0.0f, -1.0f, 0.0f,
							0.0f, -1.0f, 0.0f,
							0.0f, -1.0f, 0.0f,
							0.0f, 1.0f, 0.0f, //4
							0.0f, 1.0f, 0.0f,
							0.0f, 1.0f, 0.0f,
							0.0f, 1.0f, 0.0f,
							-1.0f, 0.0f, 0.0f, //5
							-1.0f, 0.0f, 0.0f,
							-1.0f, 0.0f, 0.0f,
							-1.0f, 0.0f, 0.0f,
							1.0f, 0.0f, 0.0f, //6
							1.0f, 0.0f, 0.0f,
							1.0f, 0.0f, 0.0f,
							1.0f, 0.0f, 0.0f};

		normalBuffer = BufferUtils.newFloatBuffer(72);
		normalBuffer.put(normalArray);
		normalBuffer.rewind();
	}

	public static void drawSolidCube(Shader shader) {

		Gdx.gl.glVertexAttribPointer(shader.getVertexPointer(), 3, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glVertexAttribPointer(shader.getNormalPointer(), 3, GL20.GL_FLOAT, false, 0, normalBuffer);

		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 0, 4);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 4, 4);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 8, 4);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 12, 4);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 16, 4);
		Gdx.gl.glDrawArrays(GL20.GL_TRIANGLE_FAN, 20, 4);

	}

	public static void drawOutlineCube(Shader shader) {

		Gdx.gl.glVertexAttribPointer(shader.getVertexPointer(), 3, GL20.GL_FLOAT, false, 0, vertexBuffer);
		Gdx.gl.glVertexAttribPointer(shader.getNormalPointer(), 3, GL20.GL_FLOAT, false, 0, normalBuffer);
		
		Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 0, 4);
		Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 4, 4);
		Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 8, 4);
		Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 12, 4);
		Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 16, 4);
		Gdx.gl.glDrawArrays(GL20.GL_LINE_LOOP, 20, 4);
	}

}
