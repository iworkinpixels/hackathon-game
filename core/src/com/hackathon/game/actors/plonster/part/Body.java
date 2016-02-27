package com.hackathon.game.actors.plonster.part;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by tjago on 2016-02-27.
 */
public class Body extends BodyPart {

    private Animation bodyAnimation;

    public Body() {

        this.bodyAnimation = initBodyAnimation();
    }

    private Animation initBodyAnimation() {
        TextureRegion[] frames = new TextureRegion[4];
        frames[0] = spritePosition[2][0];
        frames[1] = spritePosition[2][1];
        frames[2] = spritePosition[2][2];

        return new Animation(0.1f, frames);
    }
}
