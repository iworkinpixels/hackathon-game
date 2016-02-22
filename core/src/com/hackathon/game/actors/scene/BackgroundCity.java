package com.hackathon.game.actors.scene;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hackathon.game.Constants;

/**
 * Created by tjago on 2016-02-21.
 */
public class BackgroundCity extends Actor {

    private Sprite bgSprite;

    public BackgroundCity() {
        bgSprite = new Sprite(new TextureRegion(new Texture(Constants.BACKGROUND_CITY_IMAGE_PATH)));
        bgSprite.setPosition(0,0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bgSprite.draw(batch);
    }
}
