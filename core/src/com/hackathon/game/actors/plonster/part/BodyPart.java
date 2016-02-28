package com.hackathon.game.actors.plonster.part;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackathon.game.Constants;

/**
 * Created by tjago on 2016-02-27.
 */
public class BodyPart {

    private static final int FRAME_WIDTH  = 256;
    private static final int FRAME_HEIGHT = 256;

    /** shared Texture resource among objects, should be static */
    final static protected Texture spritemap = new Texture(Gdx.files.internal(Constants.PLONSTER_SPRITE_MAP));
    static protected TextureRegion[][] spritePosition = TextureRegion.split(spritemap, FRAME_WIDTH, FRAME_HEIGHT);

    private float scale = 1f;

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
