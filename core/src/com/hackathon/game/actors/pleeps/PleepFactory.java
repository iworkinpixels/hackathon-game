package com.hackathon.game.actors.pleeps;

/**
 * Pleep Factory class
 *
 * @see "http://www.tutorialspoint.com/design_pattern/factory_pattern.htm"
 *
 * Created by tjago on 2016-02-24.
 */
public class PleepFactory {

    public Pleep createPleep(String pleepType){
        if(pleepType == null){
            return null;
        }
        if(pleepType.equalsIgnoreCase(CommonPleep.FACTORY_NAME)){
            return new CommonPleep();

        } else if(pleepType.equalsIgnoreCase(SoldierPleep.FACTORY_NAME)){
            return new SoldierPleep();

        } else if(pleepType.equalsIgnoreCase(ScientistPleep.FACTORY_NAME)){
            return new ScientistPleep();
        }

        return null;
    }

}
