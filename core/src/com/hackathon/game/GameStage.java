package com.hackathon.game;

import com.badlogic.gdx.scenes.scene2d.Stage;
<<<<<<< HEAD
import com.hackathon.game.actors.Background;
=======
import com.hackathon.game.actors.BackgroundCity;
import com.hackathon.game.actors.BackgroundSky;
import com.hackathon.game.actors.CrazyBiker;
>>>>>>> 7567aa270e1b2e1798c1a27e4d2a49194eb61e2b
import com.hackathon.game.actors.Pleep;

/**
 * Created by tjago on 2016-02-21.
 */
public class GameStage extends Stage {

    public GameStage() {
        addActor(new Background());
        addActor(new Pleep());
    }

    @Override
    public void draw() {
        super.draw();
    }
}
