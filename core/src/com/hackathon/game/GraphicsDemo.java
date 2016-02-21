package com.hackathon.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class GraphicsDemo implements ApplicationListener {
    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Sprite sprite;
    private int currentFrame = 1;
    private String currentAtlasKey = new String("1");
    
    @Override
    public void create() {        
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("data/tonybike.pack"));
        AtlasRegion region = textureAtlas.findRegion("1");
        sprite = new Sprite(region);
        sprite.setPosition(120, 100);
        
        Timer.schedule(new Task(){
                @Override
                public void run() {
                    currentFrame++;
                    if(currentFrame > 4)
                        currentFrame = 1;
                    
                    // ATTENTION! String.format() doesnt work under GWT for god knows why...
                    currentAtlasKey = new Integer(currentFrame).toString();
                    sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
                }
            }
            ,0,1/30.0f);
    }

    @Override
    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();
    }

    @Override
    public void render() {        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}