package com.hackathon.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.hackathon.game.actors.pleeps.BasePleep;
import com.hackathon.game.actors.pleeps.Pleep;
import com.hackathon.game.actors.pleeps.PleepFactory;
import com.hackathon.game.actors.scene.BackgroundCity;
import com.hackathon.game.actors.scene.BackgroundSky;

/**
 * Created by tjago on 2016-02-21.
 */
public class GameStage extends Stage {

    private static final float START_NOW = 0f;
    private static final float START_AFTER_5_SECONDS = 5f;
    private static final float START_AFTER_3_SECONDS = 3f;
    private static final float START_AFTER_1_SECOND = 3f;
    private static final float EVERY_2_SECONDS = 2L;
    private static final float EVERY_1_SECOND = 1L;

    public GameStage() {
        addActor(new BackgroundSky());
        addActor(new BackgroundCity());

        final PleepFactory pleepFactory = new PleepFactory();

        /** scheduler - add Pleep to stage every 2 secs */
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Pleep newPleep = pleepFactory.createRandomPleep();
                addActor((BasePleep) newPleep);
            }
        }, START_NOW, EVERY_1_SECOND);
    }

    @Override
    public void draw() {
        super.draw();
    }


}
