package com.hackathon.game;

import com.badlogic.gdx.Game;

public class HackathonGame extends Game {
    
	@Override
	public void create () {
        setScreen(new GameScreen());
    }
}
