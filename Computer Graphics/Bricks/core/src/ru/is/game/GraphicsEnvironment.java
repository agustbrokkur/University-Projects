package ru.is.game;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

public class GraphicsEnvironment {
	
	public static FloatBuffer projectionMatrix;
	public static int projectionMatrixLoc;
	
	public static int modelMatrixLoc;
	public static int positionLoc;
	
	public static int colorLoc;
	
	private static FloatBuffer modelMatrix;
	
	public static void setColor(float r, float g, float b) {
		Gdx.gl.glUniform4f(GraphicsEnvironment.colorLoc, r, g, b, 1);
	}
	
	public static void setWindow(float left, float right, float bottom, float top) {

		float[] pm = new float[16];

		pm[0] = 2.0f / (right - left); pm[4] = 0.0f; pm[8] = 0.0f; pm[12] = -(right + left) / (right - left);
		pm[1] = 0.0f; pm[5] = 2.0f / (top - bottom); pm[9] = 0.0f; pm[13] = -(top + bottom) / (top - bottom);
		pm[2] = 0.0f; pm[6] = 0.0f; pm[10] = 1.0f; pm[14] = 0.0f;
		pm[3] = 0.0f; pm[7] = 0.0f; pm[11] = 0.0f; pm[15] = 1.0f;

		projectionMatrix = BufferUtils.newFloatBuffer(16);
		projectionMatrix.put(pm);
		projectionMatrix.rewind();
		Gdx.gl.glUniformMatrix4fv(projectionMatrixLoc, 1, false, projectionMatrix);
		
	}
	
	public static void setShaderModelMatrix(ModelMatrix m) {
		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, m.matrix);
	}
	
	public static void initializeModelMatrix() {

		float[] pm = new float[16];

		pm[0] = 1.0f; pm[4] = 0.0f; pm[8] = 0.0f; pm[12] = 0.0f;
		pm[1] = 0.0f; pm[5] = 1.0f; pm[9] = 0.0f; pm[13] = 0.0f;
		pm[2] = 0.0f; pm[6] = 0.0f; pm[10] = 1.0f; pm[14] = 0.0f;
		pm[3] = 0.0f; pm[7] = 0.0f; pm[11] = 0.0f; pm[15] = 1.0f;

		modelMatrix = BufferUtils.newFloatBuffer(16);
		modelMatrix.put(pm);
		modelMatrix.rewind();
		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrix);
	}
	
	public static void clearModelMatrix()
	{
		modelMatrix.put(0, 1.0f);
		modelMatrix.put(1, 0.0f);
		modelMatrix.put(2, 0.0f);
		modelMatrix.put(3, 0.0f);
		modelMatrix.put(4, 0.0f);
		modelMatrix.put(5, 1.0f);
		modelMatrix.put(6, 0.0f);
		modelMatrix.put(7, 0.0f);
		modelMatrix.put(8, 0.0f);
		modelMatrix.put(9, 0.0f);
		modelMatrix.put(10, 1.0f);
		modelMatrix.put(11, 0.0f);
		modelMatrix.put(12, 0.0f);
		modelMatrix.put(13, 0.0f);
		modelMatrix.put(14, 0.0f);
		modelMatrix.put(15, 1.0f);

		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrix);
	}
	
	public static void setModelMatrixTranslation(float xTranslate, float yTranslate)
	{
		modelMatrix.put(12, xTranslate);
		modelMatrix.put(13, yTranslate);

		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrix);
	}
	
	public static void setModelMatrixScale(float xScale, float yScale)
	{
		modelMatrix.put(0, xScale);
		modelMatrix.put(5, yScale);

		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, modelMatrix);
	}
	

}
