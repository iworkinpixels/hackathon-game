package com.hackathon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

public class HackathonGame extends Game {
    
	@Override
	public void create () {
        Gdx.app.setLogLevel(Logger.INFO);
        setScreen(new GameScreen());
    }
}
