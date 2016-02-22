package com.hackathon.game.actors.pleeps;

/**
 * Created by tjago on 2016-02-22.
 */
public enum MoveDirection {

    LEFT, RIGHT;

    private MoveDirection opposite;

    static {
        LEFT.opposite = RIGHT;
        RIGHT.opposite = LEFT;
    }

    MoveDirection getOppositeDirection() {
        return opposite;
    }
}
