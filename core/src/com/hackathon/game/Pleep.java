package com.hackathon.game;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Daniel Miller on 2016-02-20.
 */

public class Pleep {
    private Rectangle pleep;
    private String direction;
    private double velocity;
    private String state;
    private boolean isAlive;

    public Pleep () {
        pleep = new Rectangle();
        this.velocity = 0;
    }

    public Pleep(int x, int y, int width, int height, double velocity, String direction, String state, boolean isAlive) {
        pleep = new Rectangle(x, y, width, height);
        this.velocity = velocity;
        this.direction = direction;
        this.state = state;
        this.isAlive = isAlive;
    }

    public double GetVelocity() {
        return velocity;
    }

    public String GetDirection() {
        return direction;
    }

    public double GetX() {
        return this.pleep.getX();
    }
    public double GetY() {
        return this.pleep.getY();
    }

    public boolean IsDead () {
        return this.isAlive;
    }

    public String GetState() {
        return this.state;
    }

    public Rectangle GetRectangle() {
        return this.pleep;
    }

    public void SetPosition(int x, int y) {
        pleep.setPosition(x, y);
    }

    public void SetSize(int width, int height) {
        pleep.setSize(width, height);
    }

    public void SetState(String state) {
        this.state = state;
    }

    public void SetDirection(String direction) {
        this.direction = direction;
    }

    public void SetVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void KillPleep() {
        this.isAlive = false;
    }

    public void BornPleep() {
        this.isAlive = true;
    }
}
