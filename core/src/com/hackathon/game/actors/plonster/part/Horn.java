package com.hackathon.game.actors.plonster.part;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tjago on 2016-02-27.
 */
public class Horn extends BodyPart {

    public static final float HORN_SCALE = 0.25f;
    private Sprite hornSprite;
    private Animation animation;

    public Horn() {

        // eye position relative to body
        setPosition(10, 60);

        //scale eye sprite so it wont be too big
        setScale(HORN_SCALE);
        setRotation(45f);

        //be sure to draw on front
        setZIndex(BACKGROUND_PLANE);
        animation = initHornAnimation();

    }

    public Horn(Vector2 position) {
        this();
        this.setPosition(getX() + position.x, getY() + position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        if (animation == null || batch == null) {
            System.out.println("Animation or batch variable has NULL value");
            return;
        }

        batch.draw(animation.getKeyFrame(0f, true),
                this.getX(), //the x-coordinate in screen space
                this.getY(), //the y-coordinate in screen space
                this.FRAME_WIDTH * this.getScale(), //width
                this.FRAME_HEIGHT * this.getScale() // height
        );
    }

    public Animation initHornAnimation() {
        TextureRegion[] frames = new TextureRegion[1];
        frames[0] = spritePosition[0][9];
        return new Animation(1f, frames);
    }
//    Doesn't work
//
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        hornSprite.setPosition(getX() /2, getY()/2);
//        hornSprite.draw(batch);
//    }

}