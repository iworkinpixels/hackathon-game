package com.hackathon.game.util;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Comparator;

/**
 * Created by tjago on 2016-03-01.
 */
public class ActorComparator implements Comparator<Actor> {
    @Override
    public int compare(Actor first, Actor second) {
        if (first.getZIndex() < second.getZIndex()) {
            return -1;
        } else if (first.getZIndex() == second.getZIndex()) {
            return 0;
        } else {
            return 1;
        }
    }
}
