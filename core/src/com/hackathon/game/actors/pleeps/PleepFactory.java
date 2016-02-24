package com.hackathon.game.actors.pleeps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Pleep Factory class
 *
 * @see "http://www.tutorialspoint.com/design_pattern/factory_pattern.htm"
 *
 * Created by tjago on 2016-02-24.
 */
public class PleepFactory {

    public enum Type {
        COMMON,
//        SCIENTIST,
        SOLDIER;

        private static final List<Type> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Type randomType()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

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

    public Pleep createRandomPleep() {

        Type randomType = Type.randomType();

        switch (randomType) {
            case COMMON:
                return new CommonPleep();
            case SOLDIER:
                return new SoldierPleep();

            default:
                throw new EnumConstantNotPresentException(Type.class, randomType.toString());
        }
    }

}
