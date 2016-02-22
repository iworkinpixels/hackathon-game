package com.hackathon.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hackathon.game.Constants;
import com.hackathon.game.HackathonGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = Constants.VIEWPORT_WIDTH;
		config.height = Constants.VIEWPORT_HEIGHT;
		new LwjglApplication(new HackathonGame(), config);
	}
}
