package com.hackathon.game;

import com.badlogic.gdx.math.Rectangle;

public class Pleep {
    private Rectangle boundingBox;
    private boolean direction;
    private double velocity;
    private String state;
    private boolean isAlive;

    public Pleep () {
        boundingBox = new Rectangle();
        this.velocity = 0;
    }

    public Pleep(int x, int y, int width, int height, double velocity, boolean direction, String state, boolean isAlive) {
        boundingBox = new Rectangle(x, y, width, height);
        this.velocity = velocity;
        this.direction = direction;
        this.state = state;
        this.isAlive = isAlive;
    }

    public double GetVelocity() {
        return velocity;
    }

    public boolean GetDirection() {
        return direction;
    }

    public double GetX() {
        return this.boundingBox.getX();
    }
    public double GetY() {
        return this.boundingBox.getY();
    }

    public boolean IsDead () {
        return this.isAlive;
    }

    public String GetState() {
        return this.state;
    }

    public Rectangle GetRectangle() {
        return this.boundingBox;
    }

    public void SetPosition(int x, int y) {
        boundingBox.setPosition(x, y);
    }

    public void SetSize(int width, int height) {
        boundingBox.setSize(width, height);
    }

    public void SetState(String state) {
        this.state = state;
    }

    public void SetDirection(boolean direction) {
        this.direction = direction;
    }

    public void SetVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void KillPleep() {
        this.isAlive = false;
    }

    public void BirthPleep() {
        this.isAlive = true;
    }
}
