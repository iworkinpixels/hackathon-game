package com.hackathon.game.actors.plonster;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by tjago on 2016-02-22.
 */
public class Plonster extends Actor {

    private PlonsterState state;

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
