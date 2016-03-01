package com.hackathon.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;
import com.hackathon.game.actors.scene.BackgroundCity;
import com.hackathon.game.actors.scene.BackgroundSky;
import com.hackathon.game.util.PopulationController;

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
    private static final float EVERY_10_SECOND = 1L;

    public GameStage() {
        addActor(new BackgroundSky());
        addActor(new BackgroundCity());

        PopulationController.getInstance().setStage(this).setMax(10);

        /** scheduler - add Pleep to stage every 2 secs */
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                PopulationController.regulatePopulation();
            }
        }, START_NOW, EVERY_1_SECOND);

        /**  deadpool Timer */
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                PopulationController.russianRoulette();
            }
        }, START_AFTER_5_SECONDS, EVERY_10_SECOND);
    }

    @Override
    public void draw() {
        super.draw();
    }

    /**
     * Calls the Actor.act(float) method for each actor in the stage.
     * Typically called each frame.
     * This method also fires enter and exit events.
     *
     * @param delta Time in seconds since the last frame. */
    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
