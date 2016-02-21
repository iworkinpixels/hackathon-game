package com.hackathon.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hackathon.game.actors.BackgroundCity;
import com.hackathon.game.actors.BackgroundSky;
import com.hackathon.game.actors.Pleep;

/**
 * Created by tjago on 2016-02-21.
 */
public class GameStage extends Stage {

    public GameStage() {
        addActor(new BackgroundSky());
        addActor(new BackgroundCity());
        addActor(new Pleep());
    }

    @Override
    public void draw() {
        super.draw();
    }
}
