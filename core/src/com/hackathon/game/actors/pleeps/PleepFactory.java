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
        SCIENTIST,
        SOLDIER;

        private static final List<Type> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static Type randomType()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }
    }

    public synchronized Pleep createPleep(Type pleepType){

        switch (pleepType) {
            case COMMON:
                return new CommonPleep();
            case SOLDIER:
                return new SoldierPleep();
            case SCIENTIST:
                return new ScientistPleep();

            default:
                throw new EnumConstantNotPresentException(Type.class, pleepType.toString());
        }
    }

    public Pleep createRandomPleep() {

        return createPleep(Type.randomType());
    }

}
