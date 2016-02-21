package com.hackathon.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by tjago on 2016-02-21.
 */
public class CrazyBiker extends Actor {

    public static final String IMAGE_BIKER_SOLO = "images/bikesolo.png";
    private Sprite bikerSprite;

    public CrazyBiker() {

        bikerSprite = new Sprite(new TextureRegion(new Texture(IMAGE_BIKER_SOLO)));
        bikerSprite.setPosition(0,0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        bikerSprite.draw(batch);
    }
}
