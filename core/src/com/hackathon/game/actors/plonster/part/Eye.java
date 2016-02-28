package com.hackathon.game.actors.plonster.part;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by tjago on 2016-02-27.
 */
public class Eye extends BodyPart {

    private enum EyeState {
        CLOSED, OPENING, OPEN, CLOSING, NARROWED
    }

    public static final int FOREGROUND_PLANE = 10;
    private Animation openEYEAnimation = initOpenEyeAnimation();
    private EyeState state;
    private float statetime;

    public Eye() {

        // eye position relative to body
        setPosition(20,30);

        //scale eye sprite so it wont be too big
        setScale(0.15f);

        //starting state
        this.state = EyeState.OPEN;

        //be sure to draw on front
        setZIndex(FOREGROUND_PLANE);
    }

    public Eye(Vector2 position) {
        this();
        this.setPosition(getX()+ position.x, getY() + position.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        switch (state) {
            case OPEN:
                animate(batch, openEYEAnimation);
                break;
            case CLOSING:
                break;
            case CLOSED:
                break;
            case NARROWED:
                break;
            case OPENING:
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


    public Animation initOpenEyeAnimation() {
        TextureRegion[] frames = new TextureRegion[5];
        frames[0] = spritePosition[0][0];
        frames[1] = spritePosition[0][1];
        frames[2] = spritePosition[0][2];
        frames[3] = spritePosition[0][3];
        frames[4] = spritePosition[0][4];

        return new Animation(0.4f, frames);
    }
}
