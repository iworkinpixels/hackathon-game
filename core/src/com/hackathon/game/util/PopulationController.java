package com.hackathon.game.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.hackathon.game.actors.pleeps.BasePleep;
import com.hackathon.game.actors.pleeps.Pleep;
import com.hackathon.game.actors.pleeps.PleepFactory;

import java.util.Random;

/**
 * Class for adding removing Pleeps from scene
 * implements Singleton Pattern
 *
 * Created by tjago on 2016-03-01.
 */
public class PopulationController {

//    public static final int POPULATION_LIMIT = 10;
    private static PopulationController instance;

    private static Stage stage;
    private static PleepFactory pleepFactory;
    private static int currentPleepsNumber;
    private static int maxPleepsPopulation;
    private static long timeFromInit;

    public PopulationController() {
        currentPleepsNumber = 0;
        pleepFactory = new PleepFactory();
    }

    public static void regulatePopulation() {

        if (currentPleepsNumber < maxPleepsPopulation) {
            Pleep newPleep = pleepFactory.createRandomPleep();
            stage.addActor((BasePleep)newPleep);

            currentPleepsNumber++;
        }
    }

    public static PopulationController getInstance() {
        if (instance == null) {
            timeFromInit = System.currentTimeMillis();
            return new PopulationController();
        } else {
            return instance;
        }
    }

    public PopulationController setMax(int num) {
        maxPleepsPopulation = num;
        return this;
    }

    public PopulationController setStage(Stage gameStage){
        stage = gameStage;
        return this;
    }

    /** random kill ! */
    public static void russianRoulette() {

        Random RANDOM = new Random(); //start the roulette
        int actorsNumber = stage.getActors().size;

        if (actorsNumber > 0 && currentPleepsNumber == maxPleepsPopulation) {
            Actor chosenActor = stage.getActors().get(RANDOM.nextInt(actorsNumber));

            if(chosenActor instanceof Pleep) {
                ((Pleep)chosenActor).killPleep();
            }
        }
    }

}
