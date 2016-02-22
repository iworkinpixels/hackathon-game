package com.hackathon.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class laser {
    private Sprite sprite;
    private float velocity;
    private boolean isAlive;

    public laser() {
        this.sprite = new Sprite();
        this.velocity = 0f;
    }

    public void setPosition(int x, int y) {
        sprite.setPosition(x, y);
    }

    public void setRoation(int degrees) {
        sprite.setRotation(degrees);
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public void turnOnLaser() {
        this.isAlive = true;
    }

    public void turnOffLaser() {
        this.isAlive = false;
    }

    public void translate(int x, int y) {
        sprite.translate(x, y);
    }

    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    public void draw() {

    }
}
