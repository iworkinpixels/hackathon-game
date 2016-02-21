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
    }
}
