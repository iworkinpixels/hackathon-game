package com.hackathon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class HackathonGame extends Game {
	OrthographicCamera camera;
	SpriteBatch batch;
	Texture background, midgroundTexture;
	Sprite sprite, sprite2, midground;
    int currentFrame = 1, sprite2V =1, worldV = 6;
    String currentAtlasKey = new String("01");
    private TextureAtlas textureAtlas;
    
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 600, 450);
		background = new Texture("images/background.jpg");
		
		textureAtlas = new TextureAtlas(Gdx.files.internal("images/tonybike.pack"));
        AtlasRegion region = textureAtlas.findRegion("01");
        sprite = new Sprite(region);
        sprite2 = new Sprite(region);
        
		sprite.setX(background.getWidth()/2-sprite.getWidth()/2);
		sprite.setY(-35);	
		
		sprite2.setX(background.getWidth()/2-sprite.getWidth()/2);
		sprite2.setY(10);
		sprite2V = 1;
		
		midground = new Sprite(new TextureRegion(new Texture("images/midground.png")));
		midground.setY(0);
		midground.setX(0);
		Timer.schedule(new Task() {
			@Override
			public void run() {
				worldV = (int) (Math.random() * 3) + 3;
			}
		},0,1.0f);
		Timer.schedule(new Task() {
			@Override
			public void run() {
				midground.setX(midground.getX()-(3*worldV));
				if(midground.getX() < -320) {
					midground.setX(0);
				}
			}
		},0,1/30.0f);
		Timer.schedule(new Task(){
            @Override
            public void run() {
                currentFrame++;
                if(currentFrame > 4)
                    currentFrame = 1;
                
                // ATTENTION! String.format() doesnt work under GWT for god knows why...
                currentAtlasKey = String.format("%02d", currentFrame);
                sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
                sprite2.setRegion(textureAtlas.findRegion(currentAtlasKey));
            }
        }
        ,0,1/30.0f);
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
			midground.draw(batch);
			if(sprite2V<0) {
				sprite2.setX(sprite2.getX()-6);
				if (sprite2.getX() < -160) {
					sprite2V = 1;
				}
			} else {
				sprite2.setX(sprite2.getX()+6);
				if (sprite2.getX() > 560) {
					sprite2V = -1;
				}
			}
			sprite2.draw(batch);
			sprite.draw(batch);
			batch.end();
	}
}
