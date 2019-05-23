package com.ru.tgra.shapes;

import java.nio.FloatBuffer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Shader {

	public static int renderingProgramID;
	private int vertexShaderID;
	private int fragmentShaderID;

	private int positionLoc;
	private int normalLoc;
	private int uvLoc;

	public int modelMatrixLoc;
	public int viewMatrixLoc;
	public int projectionMatrixLoc;
	
	private int materialDiffuseLoc;
	private int materialSpecularLoc;
	private int materialShininessLoc;
	
	public int lightPositionLoc;
	private int lightAmbientLoc;
	private int lightColorLoc;
	
	public int eyePositionLoc;
	

	public Shader() {

		String vertexShaderString;
		String fragmentShaderString;

		vertexShaderString = Gdx.files.internal("shaders/simple3D_pix_lighting.vert").readString();
		fragmentShaderString =  Gdx.files.internal("shaders/simple3D_pix_lighting.frag").readString();

		vertexShaderID = Gdx.gl.glCreateShader(GL20.GL_VERTEX_SHADER);
		fragmentShaderID = Gdx.gl.glCreateShader(GL20.GL_FRAGMENT_SHADER);
	
		Gdx.gl.glShaderSource(vertexShaderID, vertexShaderString);
		Gdx.gl.glShaderSource(fragmentShaderID, fragmentShaderString);
	
		Gdx.gl.glCompileShader(vertexShaderID);
		System.out.println(Gdx.gl.glGetShaderInfoLog(vertexShaderID));
		
		Gdx.gl.glCompileShader(fragmentShaderID);
		System.out.println(Gdx.gl.glGetShaderInfoLog(fragmentShaderID));

		renderingProgramID = Gdx.gl.glCreateProgram();
	
		Gdx.gl.glAttachShader(renderingProgramID, vertexShaderID);
		Gdx.gl.glAttachShader(renderingProgramID, fragmentShaderID);
	
		Gdx.gl.glLinkProgram(renderingProgramID);

		positionLoc				= Gdx.gl.glGetAttribLocation(renderingProgramID, "a_position");
		Gdx.gl.glEnableVertexAttribArray(positionLoc);

		normalLoc				= Gdx.gl.glGetAttribLocation(renderingProgramID, "a_normal");
		Gdx.gl.glEnableVertexAttribArray(normalLoc);
		
		uvLoc				= Gdx.gl.glGetAttribLocation(renderingProgramID, "a_uv");
		Gdx.gl.glEnableVertexAttribArray(uvLoc);

		modelMatrixLoc			= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_modelMatrix");
		viewMatrixLoc			= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_viewMatrix");
		projectionMatrixLoc	= Gdx.gl.glGetUniformLocation(renderingProgramID, "u_projectionMatrix");

		materialDiffuseLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_material_diffuse");
		materialSpecularLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_material_specular");
		materialShininessLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_material_shininess");

		lightPositionLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_light_position");
		lightAmbientLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_light_ambient");
		lightColorLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_light_color");

		eyePositionLoc = Gdx.gl.glGetUniformLocation(renderingProgramID, "u_eye_position");

		Gdx.gl.glUseProgram(renderingProgramID);
	}


	public void setLightAmbient(float r, float g, float b, float a) {
		Gdx.gl.glUniform4f(lightAmbientLoc, r, g, b, a);
		
	}
	
	public void setLightColor(float r, float g, float b, float a) {
		Gdx.gl.glUniform4f(lightColorLoc, r, g, b, a);
		
	} 
	public void setMaterialDiffuse(float r, float g, float b, float a) {
		Gdx.gl.glUniform4f(materialDiffuseLoc, r, g, b, a);
		
	}
	
	public void setMaterialSpecular(float r, float g, float b, float a) {
		Gdx.gl.glUniform4f(materialSpecularLoc, r, g, b, a);
		
	}
	
	public void setMaterialShininess(float shininess) {
		Gdx.gl.glUniform1f(materialShininessLoc, shininess);
		
	}

	public void setModelMatrix(FloatBuffer matrix)
	{
		Gdx.gl.glUniformMatrix4fv(modelMatrixLoc, 1, false, matrix);
	}


	public int getVertexPointer()
	{
		return positionLoc;
	}
	public int getNormalPointer()
	{
		return normalLoc;
	}
	public int getUVPointer()
	{
		return uvLoc;
	}
}
