package com.ru.tgra.shapes;

import com.badlogic.gdx.Gdx;

public class DropBox {
	private Shader shader;
	private float x;
	private float y;
	private float z;
	
	private int capacity;
	
	private int color;
	
	private float blockTimer;
	
	// Normal boxes
	public DropBox(Shader shader, float x, float y, float z, int capacity, int color) {
		this.shader = shader;
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.capacity = capacity;
		
		this.color = color;
		
		this.blockTimer = 0;
	}
	
	// Boxe that shows the next color
	public DropBox(Shader shader, float x, float y, float z, int color) {
		this.shader = shader;
		this.x = x;
		this.y = y;
		this.z = z;
		
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public void drawDropBox() {
		// Red
		if(color == 1) {
			shader.setLightColor(0.5f, 1.0f, 1.0f, 1.0f);
			shader.setLightAmbient(0.5f, 0.0f, 0.0f, 1.0f);

			shader.setMaterialDiffuse(0.5f, 0.0f, 0.0f, 1.0f);
			shader.setMaterialSpecular(0.5f, 0.0f, 0.0f, 1.0f);
			shader.setMaterialShininess(90.0f);
		}
		// Green
		else if(color == 2) {
			shader.setLightColor(1.0f, 0.5f, 1.0f, 1.0f);
			shader.setLightAmbient(0.0f, 0.5f, 0.0f, 1.0f);

			shader.setMaterialDiffuse(0.0f, 0.5f, 0.0f, 1.0f);
			shader.setMaterialSpecular(0.0f, 0.5f, 0.0f, 1.0f);
			shader.setMaterialShininess(90.0f);
			
		}
		// Purple
		else {
			shader.setLightColor(1.0f, 1.0f, 0.5f, 1.0f);
			shader.setLightAmbient(0.25f, 0.0f, 0.5f, 1.0f);

			shader.setMaterialDiffuse(0.25f, 0.0f, 0.5f, 1.0f);
			shader.setMaterialSpecular(0.25f, 0.0f, 0.5f, 1.0f);
			shader.setMaterialShininess(90.0f);
			
		}
		blockTimer += Gdx.graphics.getDeltaTime();
		ModelMatrix.main.loadIdentityMatrix();
		ModelMatrix.main.pushMatrix();
		
		if(blockTimer > 1 && blockTimer < (6 - capacity)) {
			x -= Gdx.graphics.getDeltaTime();
			ModelMatrix.main.addTranslation(x, y, z);
		} else if(blockTimer > (6 - capacity)) {
			x = -3.0f + capacity;
			ModelMatrix.main.addTranslation(x, y, z);
		}
		else {
			ModelMatrix.main.addTranslation(2.0f, y, z);
		}
		
		//ModelMatrix.main.addRotationZ(angle);
		ModelMatrix.main.addScale(1.0f, 1.0f, 1.0f);
		ModelMatrix.main.setShaderMatrix(shader.modelMatrixLoc);
		BoxGraphic.drawSolidCube(shader);
		
		ModelMatrix.main.popMatrix();
	}
	
	public void drawNextDropBox() {
		// Red
		if(color == 1) {
			shader.setLightColor(0.5f, 1.0f, 1.0f, 1.0f);
			shader.setLightAmbient(0.5f, 0.0f, 0.0f, 1.0f);

			shader.setMaterialDiffuse(0.5f, 0.0f, 0.0f, 1.0f);
			shader.setMaterialSpecular(0.5f, 0.0f, 0.0f, 1.0f);
			shader.setMaterialShininess(90.0f);
		}
		// Green
		else if(color == 2) {
			shader.setLightColor(1.0f, 0.5f, 1.0f, 1.0f);
			shader.setLightAmbient(0.0f, 0.5f, 0.0f, 1.0f);

			shader.setMaterialDiffuse(0.0f, 0.5f, 0.0f, 1.0f);
			shader.setMaterialSpecular(0.0f, 0.5f, 0.0f, 1.0f);
			shader.setMaterialShininess(90.0f);
			
		}
		// Purple
		else {
			shader.setLightColor(1.0f, 1.0f, 0.5f, 1.0f);
			shader.setLightAmbient(0.25f, 0.0f, 0.5f, 1.0f);

			shader.setMaterialDiffuse(0.25f, 0.0f, 0.5f, 1.0f);
			shader.setMaterialSpecular(0.25f, 0.0f, 0.5f, 1.0f);
			shader.setMaterialShininess(90.0f);
			
		}
		ModelMatrix.main.loadIdentityMatrix();
		ModelMatrix.main.pushMatrix();
		ModelMatrix.main.addTranslation(x, y, z);
		
		//ModelMatrix.main.addRotationZ(angle);
		ModelMatrix.main.addScale(1.0f, 1.0f, 1.0f);
		ModelMatrix.main.setShaderMatrix(shader.modelMatrixLoc);
		BoxGraphic.drawSolidCube(shader);
		
		ModelMatrix.main.popMatrix();
	}
}
