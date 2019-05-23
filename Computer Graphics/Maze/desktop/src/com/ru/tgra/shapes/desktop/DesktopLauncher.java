package com.ru.tgra.shapes.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ru.tgra.shapes.First3DGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Lab1"; // or whatever you like
		config.width = 768;  //experiment with
		config.height = 768;  //the window size
		config.x = 150;
		config.y = 50;
		//config.fullscreen = true;

		new LwjglApplication(new First3DGame(), config);
	}
}
