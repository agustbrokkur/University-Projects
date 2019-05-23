package com.ru.tgra.shapes;

import java.util.ArrayList;

public class Level {
	private Shader shader;
	private int level;
	
	public Level(Shader shader) {
		this.shader = shader;
		level = 1;
	}
	
	public void setCurrentLevel(int level) {
		this.level = level;
	}
	
	public int getCurrentLevel() {
		return level;
	}
	
	public int[][] getCurrentLevelCapacity() {
		if(level == 1) {
			return depthCapacityLevel1;
		} else if(level == 2) {
			return depthCapacityLevel2;
		} else if(level == 3) {
			return depthCapacityLevel3;
		} else if(level == 4) {
			return depthCapacityLevel4;
		}
		return null;
	}
	
	// Level 1
	private float[] level1Col = new float[] { -0.5f, 0.5f };
	private float[] level1Row = new float[] { -0.5f, 0.5f };
	
	private int[][] depthCapacityLevel1 = new int[][] { 
		{0, 0},   
		{0, 0}}; 

	// Level 2
	private float[] level2Col = new float[] { -1.5f ,-0.5f, 0.5f };
	private float[] level2Row = new float[] { -0.5f, 0.5f};
	
	private int[][] depthCapacityLevel2 = new int[][] { 
		{0, 0},   
		{0, 0},
		{0, 0} }; 
		
	// Level 3
	private float[] level3Col = new float[] { -0.5f, 0.5f };
	private float[] level3Row = new float[] { -1.5f ,-0.5f, 0.5f};
	
	private int[][] depthCapacityLevel3 = new int[][] { 
		{0, 0, 0},   
		{0, 0, 0} }; 
		
	// Level 4
	private float[] level4Col = new float[] { -2.5f, -1.5f, -0.5f, 0.5f, 1.5f, 2.5f };
	private float[] level4Row = new float[] { -2.5f, -1.5f ,-0.5f, 0.5f, 1.5f, 2.5f };
	
	private int[][] depthCapacityLevel4 = new int[][] { 
		{0, 0, 0, 0 ,0 ,0},
		{0, 0, 0, 0 ,0 ,0},
		{0, 0, 0, 0 ,0 ,0},
		{0, 0, 0, 0 ,0 ,0},
		{0, 0, 0, 0 ,0 ,0},
		{0, 0, 0, 0 ,0 ,0} }; 

			
	public void drawLevel(ArrayList<Float> wireframeBoxes) {
		shader.setLightColor(1.0f, 1.0f, 1.0f, 1.0f);
		shader.setLightAmbient(0.3f, 0.3f, 0.3f, 1.0f);

		shader.setMaterialDiffuse(0.8f, 0.3f, 0.3f, 1.0f);
		shader.setMaterialSpecular(0.3f, 0.3f, 0.3f, 1.0f);
		wireframeBoxes.clear();
		if(level == 1) {
			drawTheLevel(wireframeBoxes, level1Col, level1Row, 0, 0);
		} else if(level == 2) {
			drawTheLevel(wireframeBoxes, level2Col, level2Row, 0, 0);
		} else if(level == 3) {
			drawTheLevel(wireframeBoxes, level3Col, level3Row, 0, 0);
		} else if(level == 4) {
			drawTheLevel(wireframeBoxes, level4Col, level4Row, 0, 0);
		}
	}
	
	public int getCurrentCapacity(Float col, Float row) {
		if(level == 1) {
			return depthCapacityLevel1[(int)(col + 0.5f)][(int)(row + 0.5f)];
		} else if (level == 2) {
			return depthCapacityLevel2[(int)(col + 1.5f)][(int)(row + 0.5f)];
		} else if (level == 3) {
			return depthCapacityLevel3[(int)(col + 0.5f)][(int)(row + 1.5f)];
		} else if (level == 4) {
			return depthCapacityLevel4[(int)(col + 2.5f)][(int)(row + 2.5f)];
		} else if (level == 5) {
			return depthCapacityLevel4[(int)(col + 2.5f)][(int)(row + 2.5f)];
		}
		return 0;
	}
	
	public boolean increaseCapacity(Float col, Float row) {
		if(level == 1 && depthCapacityLevel1[(int)(col + 0.5f)][(int)(row + 0.5f)] < 5) {
			depthCapacityLevel1[(int)(col + 0.5f)][(int)(row + 0.5f)]++;
			return true;
		} else if (level == 2 && depthCapacityLevel2[(int)(col + 1.5f)][(int)(row + 0.5f)] < 5) {
			depthCapacityLevel2[(int)(col + 1.5f)][(int)(row + 0.5f)]++;
			return true;
		} else if (level == 3 && depthCapacityLevel3[(int)(col + 0.5f)][(int)(row + 1.5f)] < 5) {
			depthCapacityLevel3[(int)(col + 0.5f)][(int)(row + 1.5f)]++;
			return true;
		} else if (level == 4 && depthCapacityLevel4[(int)(col + 2.5f)][(int)(row + 2.5f)] < 5) {
			depthCapacityLevel4[(int)(col + 2.5f)][(int)(row + 2.5f)]++;
			return true;
		}
		return false;
	}
	
	public boolean decreaseCapacity(Float col, Float row) {
		if(level == 1 && depthCapacityLevel1[(int)(row + 0.5f)][(int)(col + 0.5f)] < 5) {
			depthCapacityLevel1[(int)(row + 0.5f)][(int)(col + 0.5f)]--;
			return true;
		} else if(level == 2 && depthCapacityLevel2[(int)(row + 1.5f)][(int)(col + 0.5f)] < 5) {
			depthCapacityLevel2[(int)(row + 1.5f)][(int)(col + 0.5f)]--;
			return true;
		} else if(level == 3 && depthCapacityLevel3[(int)(row + 0.5f)][(int)(col + 1.5f)] < 5) {
			depthCapacityLevel3[(int)(row + 0.5f)][(int)(col + 1.5f)]--;
			return true;
		} else if (level == 4 && depthCapacityLevel4[(int)(col + 2.5f)][(int)(row + 2.5f)] < 5) {
			depthCapacityLevel4[(int)(col + 2.5f)][(int)(row + 2.5f)]--;
			return true;
		}
		return false;
	}
	
	private void drawTheLevel(ArrayList<Float> wireframeBoxes, float[] levelCol, float[] levelRow, int colStart, int colEnd) {
		for(int depth = 0; depth < 5; depth++) {
			for(int col = (0 + colStart); col < levelCol.length - colEnd; col++) {
				for(int row = 0; row < levelRow.length; row++) {
					wireframeBoxes.add(levelCol[col]);
					wireframeBoxes.add(levelRow[row]);
					ModelMatrix.main.loadIdentityMatrix();
					ModelMatrix.main.pushMatrix();
					ModelMatrix.main.addTranslation((depth - 2), levelCol[col], levelRow[row]);
					//ModelMatrix.main.addRotationZ(angle);
					ModelMatrix.main.addScale(1.0f, 1.0f, 1.0f);
					ModelMatrix.main.setShaderMatrix(shader.modelMatrixLoc);
					BoxGraphic.drawOutlineCube(shader);
					ModelMatrix.main.popMatrix();
					
				}
			}
		}
		/*
		for(int depth = 0; depth < 5; depth++) {
			for(float row = 0; row < level1.length; row++) {
				for(float col = 0; col < level1[(int)row].length; col++) {
					listi.add((row - 0.5f));
					listi.add((col - 0.5f));
					ModelMatrix.main.loadIdentityMatrix();
					ModelMatrix.main.pushMatrix();
					ModelMatrix.main.addTranslation((depth - 2), (row - 0.5f), (col - 0.5f));
					//ModelMatrix.main.addRotationZ(angle);
					ModelMatrix.main.addScale(1.0f, 1.0f, 1.0f);
					ModelMatrix.main.setShaderMatrix(shader.modelMatrixLoc);
					BoxGraphic.drawOutlineCube(shader);
					ModelMatrix.main.popMatrix();
					
				}
			}
		}*/
	}
}
