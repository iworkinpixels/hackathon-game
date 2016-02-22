package com.hackathon.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.hackathon.game.actors.pleeps.CommonPleep;
import com.hackathon.game.actors.scene.BackgroundCity;
import com.hackathon.game.actors.scene.BackgroundSky;

/**
 * Created by tjago on 2016-02-21.
 */
public class GameStage extends Stage {

    private static final float START_AFTER_5_SECONDS = 5f;
    private static final float START_AFTER_3_SECONDS = 3f;
    private static final float START_NOW = 0f;

    public GameStage() {
        addActor(new BackgroundSky());
        addActor(new BackgroundCity());

        final CommonPleep walkingPleep = new CommonPleep();
        addActor(walkingPleep);


        /** experiment - schedule Pleep death */
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                walkingPleep.killPleep();
            }
        }, START_AFTER_3_SECONDS);
    }

    @Override
    public void draw() {
        super.draw();
    }


}
