package com.hackathon.game.actors.plonster;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hackathon.game.actors.plonster.part.Body;
import com.hackathon.game.actors.plonster.part.Eye;
import com.hackathon.game.actors.plonster.part.Horn;
import com.hackathon.game.actors.plonster.part.Mouth;

/**
 * Created by tjago on 2016-02-22.
 */
public class Plonster extends Actor {

    private PlonsterState state;

    private Eye leftEye;
    private Eye rightEye;
    private Body body;
    private Mouth mouth;
    private Horn leftHorn;
    private Horn rightHorn;

    public Plonster() {
        assemblePlonster();
    }

    private void assemblePlonster() {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

//        movementCalculations();

        switch (state) {
            case LANDING:
//                animate(batch, walkAnimation, this.direction);
                break;
            default:
                // this block should not be entered ever!
                break;
        }

    }
}
