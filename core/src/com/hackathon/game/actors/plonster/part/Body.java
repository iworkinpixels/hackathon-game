package com.hackathon.game.actors.plonster.part;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tjago on 2016-02-27.
 */
public class Body extends BodyPart {
    private enum BodyState {
        IDLE
    }
    private Animation bodyAnimation;
    private BodyState bodyState;
    private float statetime;

    public Body() {

        this.bodyState = BodyState.IDLE;
        this.bodyAnimation = initBodyAnimation();
        setScale(0.3f);
        setZIndex(BACKGROUND_PLANE);
    }

    public Body(Vector2 position) {
        this();
        this.setPosition(getX()+ position.x, getY() + position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

//        movementCalculations();

        switch (bodyState) {
            case IDLE:
                animate(batch, bodyAnimation);
                break;
            default:
                // this block should not be entered ever!
                break;
        }

    }

    @Override
    public void act(float delta) {
        statetime = statetime + delta;
    }

    private void animate(Batch batch, Animation animation) {
        if (animation == null || batch == null) {
            System.out.println("Animation or batch variable has NULL value");
            return;
        }

        batch.draw(animation.getKeyFrame(this.statetime, true),
                this.getX(), //the x-coordinate in screen space
                this.getY(), //the y-coordinate in screen space
                this.FRAME_WIDTH * this.getScale(), //width
                this.FRAME_HEIGHT * this.getScale() // height
        );
    }


    public Animation initBodyAnimation() {
        TextureRegion[] frames = new TextureRegion[3];
        frames[0] = spritePosition[2][0];
        frames[1] = spritePosition[2][1];
        frames[2] = spritePosition[2][2];

        return new Animation(0.4f, frames);
    }
}
