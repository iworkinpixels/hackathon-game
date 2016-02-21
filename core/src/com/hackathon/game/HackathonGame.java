package com.hackathon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class HackathonGame extends Game {
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture background,spritemap;
    Pleep thePleep;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1365, 1024);
		background = new Texture("images/background.gif");
        spritemap = new Texture("images/sprite-map.png");
        thePleep = new Pleep(0,80,2,true,"walking",true);
	}
	
	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
		
	}

	@Override
	public void render () {
			Gdx.gl.glClearColor(0.125f,0.125f,0.125f,1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			batch.draw(background, camera.position.x - background.getWidth() / 2, 0);
            batch.draw(thePleep.getCurrentFrame(), thePleep.getX(), thePleep.getY());
			batch.end();
	}
}
