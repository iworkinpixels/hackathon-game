package com.hackathon.game.util;

import java.util.Random;

/**
 * Created by tjago on 2016-02-24.
 */
public class Helpers {

    public static int randomInt(int min, int max) {
        Random rand;
        rand = new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
}
