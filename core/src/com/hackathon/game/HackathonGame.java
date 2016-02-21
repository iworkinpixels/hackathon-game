package com.hackathon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class HackathonGame extends Game {

    public static final float EVERY_FRAME = 1 / 30.0f;
	public static final float EVERY_SECOND = 1.0f;
	public static final int START_NOW = 0;

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture cityTexture, midgroundTexture, skyTexture;
	private Sprite sprite, sprite2, midground, citySprite, skySprite;
	private int currentFrame = 1, sprite2V =1, worldV = 6;
	private String currentAtlasKey = new String("1");
    private TextureAtlas textureAtlas;
    
	@Override
	public void create () {
        setScreen(new GameScreen());

/*
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);

        citySprite = new Sprite(new TextureRegion(new Texture("images/bg/city.png")));
        citySprite.setPosition(0, 0);

        skySprite = new Sprite(new TextureRegion(new Texture("images/bg/sky.png")));
        skySprite.setPosition(0, 0);
*/

//        midground = new Sprite(new TextureRegion(new Texture("images/midground.png")));
//        midground.setPosition(0, 0);
//
//		textureAtlas = new TextureAtlas(Gdx.files.internal("images/tonybike.pack"));
//        AtlasRegion region = textureAtlas.findRegion("1");
//
//        sprite = new Sprite(region);
//        sprite2 = new Sprite(region);
//
//		sprite.setX(cityTexture.getWidth()/2 - sprite.getWidth()/2);
//		sprite.setY(-35);
//
//		sprite2.setX(cityTexture.getWidth()/2 - sprite.getWidth()/2);
//		sprite2.setY(10);
//		sprite2V = 1;

/*

		Timer.schedule(new Task() {
			@Override
			public void run() {
				worldV = (int) (Math.random() * 3) + 3;
			}
		}, START_NOW, EVERY_SECOND);

*/

		/**
		 * Every frame changing position of sky Background
		 */
/*
		Timer.schedule(new Task() {
			@Override
			public void run() {
                skySprite.setX(skySprite.getX()-(3*worldV));
				if(skySprite.getX() < -320) {
                    skySprite.setX(0);
				}
			}
		},START_NOW, EVERY_FRAME);
*/

		/**
		 * Every frame changing position of secondary Background
		 */
//		Timer.schedule(new Task() {
//			@Override
//			public void run() {
//				midground.setX(midground.getX()-(3*worldV));
//				if(midground.getX() < -320) {
//					midground.setX(0);
//				}
//			}
//		},START_NOW, EVERY_FRAME);

        /**
         * Every frame swap animation texture of biker
         */
//		Timer.schedule(new Task(){
//            @Override
//            public void run() {
//                currentFrame++;
//                if(currentFrame > 4)
//                    currentFrame = 1;
//
//                // ATTENTION! String.format() doesnt work under GWT for god knows why...
//                currentAtlasKey = new Integer(currentFrame).toString();
//                sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
//                sprite2.setRegion(textureAtlas.findRegion(currentAtlasKey));
//            }
//        }
//        ,START_NOW, EVERY_FRAME);
	}
	
/*	@Override
	public void dispose() {
		batch.dispose();
//		cityTexture.dispose();
	}

	@Override
	public void render () {
			Gdx.gl.glClearColor(0.125f,0.125f,0.125f,1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			batch.setProjectionMatrix(camera.combined);
			batch.begin();

			drawScene();

			batch.end();
	}*/
/*
	private void drawScene() {

        skySprite.draw(batch);
        citySprite.draw(batch);
//        midground.draw(batch);

//		if(sprite2V < 0) {
//            sprite2.setX(sprite2.getX()-6);
//            if (sprite2.getX() < -160) {
//                sprite2V = 1;
//            }
//        } else {
//            sprite2.setX(sprite2.getX()+6);
//            if (sprite2.getX() > 560) {
//                sprite2V = -1;
//            }
//        }
//		sprite2.draw(batch);
//		sprite.draw(batch);
	}*/
}
