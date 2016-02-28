package com.hackathon.game.actors.plonster;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hackathon.game.actors.plonster.part.Body;
import com.hackathon.game.actors.plonster.part.Eye;
import com.hackathon.game.actors.plonster.part.Horn;
import com.hackathon.game.actors.plonster.part.Mouth;

/**
 * Created by tjago on 2016-02-22.
 */
public class Plonster implements PlonsterInterface {

    private PlonsterState state;
    private float statetime;
    private Vector2 position = new Vector2(160,230); // Starting position

    private Eye bigEye;
    private Body body;
    private Mouth mouth;
    private Horn leftHorn, rightHorn;
    private Animation idleBodyAnimation;

    public Plonster(Stage stage) {

        System.out.println("assemble Plonster");

        this.body = new Body(position);
        stage.addActor(body);

        this.bigEye = new Eye(position);
        stage.addActor(bigEye);

        this.leftHorn = new Horn(position);
        stage.addActor(leftHorn);

        this.rightHorn = new Horn();

        this.becomeIdle();
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
