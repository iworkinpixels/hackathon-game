package com.hackathon.game.actors.plonster;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hackathon.game.actors.plonster.part.Body;
import com.hackathon.game.actors.plonster.part.Eye;
import com.hackathon.game.actors.plonster.part.Horn;
import com.hackathon.game.actors.plonster.part.Mouth;

/**
 * Created by tjago on 2016-02-22.
 */
public class Plonster extends Actor implements PlonsterInterface {

    protected static final int FRAME_WIDTH  = 256;
    protected static final int FRAME_HEIGHT = 256;

    private PlonsterState state;
    private float statetime;
    private Vector2 position = new Vector2(160,230); // Starting position

    private Eye leftEye;
    private Eye rightEye;
    private Body body;
    private Mouth mouth;
    private Horn leftHorn;
    private Horn rightHorn;
    private Animation idleBodyAnimation;

    public Plonster() {
        assemblePlonster();
    }

    private void assemblePlonster() {

        System.out.println("assemble");

        this.body = new Body();
        idleBodyAnimation = body.initBodyAnimation();
        this.becomeIdle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

//        movementCalculations();

        switch (state) {
            case LANDING:
                break;
            case IDLE:
                animate(batch, idleBodyAnimation, body.getScale());
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

    private void animate(Batch batch, Animation animation, float scale) {
        if (animation == null || batch == null) {
            System.out.println("Animation or batch variable has NULL value");
            return;
        }

        batch.draw(animation.getKeyFrame(this.statetime, true),
                this.position.x, //the x-coordinate in screen space
                this.position.y, //the y-coordinate in screen space
                FRAME_WIDTH * scale, //width
                FRAME_HEIGHT * scale // height
        );
    }

    /**********
     * ACTIONS
     **************/

    @Override
    public void lickMouth() {

    }

    @Override
    public void narrowEyes() {

    }

    @Override
    public void emerge() {

    }

    @Override
    public void becomeIdle() {
        this.state = PlonsterState.IDLE;
        this.statetime = 0;
    }
}
