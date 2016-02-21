package com.hackathon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Pleep {
    private Rectangle boundingBox;
    private boolean direction;
    private float velocity;
    private String state;
    private boolean isAlive;
    private float statetime;

    public Animation walkAnimation;
    public Texture spritemap;
    public TextureRegion[] walkframes;
    public TextureRegion currentFrame;

    private static final int FRAME_WIDTH = 256;
    private static final int FRAME_HEIGHT = 256;
    public Pleep () {
        boundingBox = new Rectangle();
        this.velocity = 0f;
    }

    public Pleep(int x, int y, float velocity, boolean direction, String state, boolean isAlive) {
        this.boundingBox = new Rectangle(x, y, 256, 256);
        this.velocity = velocity;
        this.direction = direction;
        this.state = state;
        this.isAlive = isAlive;
        this.spritemap = new Texture(Gdx.files.internal("images/sprite-map.png"));

        TextureRegion[][] tmp = TextureRegion.split(spritemap, FRAME_WIDTH, FRAME_HEIGHT);
        this.walkframes = new TextureRegion[4];
        this.walkframes[0] = tmp[0][8];
        this.walkframes[1] = tmp[0][9];
        this.walkframes[2] = tmp[1][0];
        this.walkframes[3] = tmp[1][1];

        this.walkAnimation = new Animation(0.1f, this.walkframes);

        this.statetime = 0f;
    }

    public float getVelocity() {
        return this.velocity;
    }

    public boolean getDirection() {
        return direction;
    }

    public float getX() {
        return this.boundingBox.getX();
    }

    public float getY() {
        return this.boundingBox.getY();
    }

    public boolean isDead () {
        return this.isAlive;
    }

    public String getState() {
        return this.state;
    }

    public Rectangle getRectangle() {
        return this.boundingBox;
    }

    public void setPosition(int x, int y) {
        this.boundingBox.setPosition(x, y);
    }

    public void setSize(int width, int height) {
        this.boundingBox.setSize(width, height);
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void killPleep() {
        this.isAlive = false;
    }

    public void birthPleep() {
        this.isAlive = true;
    }

    public TextureRegion getCurrentFrame() {
        this.statetime += Gdx.graphics.getDeltaTime();
        this.boundingBox.x = this.boundingBox.x + (float) this.velocity;
        return this.walkAnimation.getKeyFrame(this.statetime, true);
    }
}
