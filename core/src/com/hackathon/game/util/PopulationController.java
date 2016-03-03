package com.hackathon.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hackathon.game.actors.pleeps.Pleep;
import com.hackathon.game.actors.pleeps.PleepFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Class for adding removing Pleeps from scene
 * implements Singleton Pattern
 *
 * Created by tjago on 2016-03-01.
 */
public class PopulationController {

    public static final String LOGTAG ="UTIL";
    private static PopulationController instance;

    private PleepFactory pleepFactory;
    private Stage stage;
    private int currentPleepsNumber;
    private int maxPleepsPopulation;
    private long timeFromInit;

    public PopulationController() {
        Gdx.app.log(LOGTAG, "created Population Controller");
        currentPleepsNumber = 0;
        pleepFactory = new PleepFactory();
        timeFromInit = System.currentTimeMillis();
    }

    public synchronized void regulatePopulation() {

        if (currentPleepsNumber <= maxPleepsPopulation) {
            Gdx.app.log(LOGTAG, "Pleeps population: " + currentPleepsNumber + " , increasing: +1");
            Pleep newPleep = pleepFactory.createRandomPleep();
            stage.addActor((Actor) newPleep);

            currentPleepsNumber++;
        }
    }

    public static PopulationController getInstance() {
        if (instance == null) {
            instance = new PopulationController();
        }
        return instance;
    }

    public PopulationController setMax(int num) {
        maxPleepsPopulation = num;
        return this;
    }

    public PopulationController setStage(Stage gameStage){
        stage = gameStage;
        return this;
    }

    /** random kill */
    public synchronized void russianRoulette() {

        Random RANDOM = new Random(); //start the roulette
        int actorsNumber = stage.getActors().size; //somehow Actors size array is double than actors actually inserted..

        if (currentPleepsNumber >= maxPleepsPopulation) {
            Actor chosenActor = stage.getActors().get(RANDOM.nextInt(actorsNumber));

            Gdx.app.log(LOGTAG, "actorsNumber: " + actorsNumber);

            if(chosenActor instanceof Pleep) {
                Gdx.app.log(LOGTAG, "Russian roulette - killPleep");
                ((Pleep)chosenActor).killPleep();
            }
        }
    }


    public void pleepKilledNotification() {
        currentPleepsNumber--;
    }

    /**
     * Update z-index after creating a new actor in scene
     * Not so trivial as it seems
     * @link http://gamedev.stackexchange.com/questions/80068/libgdx-z-index-for-groups
     * @link http://stackoverflow.com/questions/16129903/how-do-you-sort-actors-in-a-libgdx-stage
     */
    public void updateZIndexforPleeps() {
        Collections.sort(Arrays.asList(stage.getActors().toArray()), new ActorComparator());
    }
}
