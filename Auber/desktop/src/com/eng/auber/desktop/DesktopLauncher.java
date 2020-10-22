package com.eng.auber.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.eng.auber.AuberGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AuberGame(), config);
		config.width = Gdx.graphics.getWidth();
		config.height = Gdx.graphics.getHeight();
		config.fullscreen = false;
	}
}
