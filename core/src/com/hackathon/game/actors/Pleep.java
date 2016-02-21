package com.hackathon.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hackathon.game.Constants;

public class Pleep extends Actor {

    public static final String PLEEP_SPRITE_MAP_PNG = "images/sprite-map.png";
    private Rectangle boundingBox;
    private boolean direction;
    private int velocity;
    private String state;
    private boolean isAlive;
    private float statetime;

    private Animation walkAnimation;
    private Texture spritemap;
    private TextureRegion[] walkframes;
    private TextureRegion currentFrame;

    private static final int FRAME_WIDTH = 256;
    private static final int FRAME_HEIGHT = 256;
    private static final int BOUNCE_MIN = -(Constants.VIEWPORT_WIDTH/4)-(FRAME_WIDTH/4);
    private static final int BOUNCE_MAX = Constants.VIEWPORT_WIDTH;

    public Pleep () {
        boundingBox = new Rectangle();
        this.velocity = 2;
        this.direction = true;
        this.boundingBox.x = FRAME_WIDTH;
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


    @Override
    public void draw(Batch batch, float parentAlpha) {
        float delta = Gdx.graphics.getDeltaTime();
        statetime += delta;


        if ((this.direction && this.getX() > BOUNCE_MAX) || (!this.direction && this.getX() < BOUNCE_MIN)) {
            this.velocity = -this.velocity;
            this.direction = !this.direction;
            if (this.direction) {
                this.boundingBox.x += FRAME_WIDTH/2;
            }else {
                this.boundingBox.x -= FRAME_WIDTH/2;
            }
        }

        this.boundingBox.x += this.velocity;
        System.out.println(this.getX());


        if (walkAnimation != null) {
            if (this.direction) {
                batch.draw(walkAnimation.getKeyFrame(this.statetime, true),
                        this.getX(), //the x-coordinate in screen space
                        this.getY(), //the y-coordinate in screen space
                        FRAME_WIDTH/2, //width
                        FRAME_HEIGHT/2 // height
                );
            } else {
                batch.draw(walkAnimation.getKeyFrame(this.statetime, true),
                        this.getX()+FRAME_WIDTH, //the x-coordinate in screen space
                        this.getY(), //the y-coordinate in screen space
                        -FRAME_WIDTH/2, //width
                        FRAME_HEIGHT/2 // height
                );
            }
        } else {
            System.out.println("NULL");
        }
    }

    public int getVelocity() {
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

    public void setVelocity(int velocity) {
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
        this.boundingBox.x = this.boundingBox.x + this.velocity;

        return this.walkAnimation.getKeyFrame(this.statetime, true);
    }
}
