package com.hackathon.game.actors.pleeps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hackathon.game.Constants;

public abstract class BasePleep extends Actor {

    private Rectangle boundingBox;
    MoveDirection direction;
    private int velocity;
    private float statetime;

    final private Texture spritemap = new Texture(Gdx.files.internal(Constants.PLEEP_SPRITE_MAP));
    private Animation walkAnimation;
    private Animation deathAnimation;

    private PleepState pleepState;
    private static final int FRAME_WIDTH = 256;
    private static final int FRAME_HEIGHT = 256;
    private static final int BOUNCE_MIN = -(Constants.VIEWPORT_WIDTH/4)-(FRAME_WIDTH/4);
    private static final int BOUNCE_MAX = Constants.VIEWPORT_WIDTH;

    public BasePleep() {

        this.pleepState = PleepState.WALKING;
        this.direction = MoveDirection.RIGHT;

        boundingBox = new Rectangle();
        this.velocity = 2;
        this.boundingBox.x = -FRAME_WIDTH;
        this.statetime = 0f;

        walkAnimation = initWalkAnimation();
        deathAnimation = initDeathAnimation();
    }

    private Animation initDeathAnimation() {

        TextureRegion[][] tmp = TextureRegion.split(spritemap, FRAME_WIDTH, FRAME_HEIGHT);
        TextureRegion[] frames = new TextureRegion[20];
        for(int x = 0; x < 10; x++) {
            frames[x] = tmp[11][x];
            frames[x +10] = tmp[12][x];
        }

        return new Animation(0.1f, frames);
    }

    private Animation initWalkAnimation() {

        TextureRegion[][] tmp = TextureRegion.split(spritemap, FRAME_WIDTH, FRAME_HEIGHT);
        TextureRegion[] walkframes = new TextureRegion[4];
        walkframes[0] = tmp[0][8];
        walkframes[1] = tmp[0][9];
        walkframes[2] = tmp[1][0];
        walkframes[3] = tmp[1][1];

        return new Animation(0.1f, walkframes);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {

        movementCalculations();

        switch (pleepState) {
            case WALKING:
                animate(batch, walkAnimation, this.direction);
                break;
            case RUNNING:
                //TODO put animation here
                break;
            case THROWING:
                //TODO put animation here
                break;
            case POINTING:
                //TODO put animation here
                break;
            case STOPPING:
                //TODO put animation here
                break;
            case DYING:
                animate(batch, deathAnimation, this.direction);
                break;
            case DEAD:
//                animate(batch, deathAnimation, this.direction);
                break;
            default:
                // this block should not be entered ever!
                break;
        }

    }

    private void movementCalculations() {

        float delta = Gdx.graphics.getDeltaTime();
        statetime += delta;

        if ((this.direction == MoveDirection.RIGHT && this.getX() > BOUNCE_MAX)
                || (this.direction == MoveDirection.LEFT && this.getX() < BOUNCE_MIN)) {
            this.velocity = -this.velocity;
            this.direction = direction.getOppositeDirection();

            if (this.direction == MoveDirection.RIGHT) {
                this.boundingBox.x += FRAME_WIDTH/2;
            }else {
                this.boundingBox.x -= FRAME_WIDTH/2;
            }
        }
        this.boundingBox.x += this.velocity;
        System.out.println(this.getX());
    }

    private void animate(Batch batch, Animation animation, MoveDirection direction) {
        if (animation == null || batch == null) {
            System.out.println("NULL");
            return;
        }

        if (direction == MoveDirection.RIGHT) {
            batch.draw(animation.getKeyFrame(this.statetime, true),
                    this.getX(), //the x-coordinate in screen space
                    this.getY(), //the y-coordinate in screen space
                    FRAME_WIDTH / 2, //width
                    FRAME_HEIGHT / 2 // height
            );
        }
        if (direction == MoveDirection.LEFT) {
            batch.draw(animation.getKeyFrame(this.statetime, true),
                    this.getX()+FRAME_WIDTH, //the x-coordinate in screen space
                    this.getY(), //the y-coordinate in screen space
                    -FRAME_WIDTH/2, //width
                    FRAME_HEIGHT/2 // height
            );
        }
    }

    /********** ACTIONS **************/

    public void killPleep() {
        this.pleepState = PleepState.DYING;
    }

    public void birthPleep() {
        //TODO implement me
    }

    //TODO add more actions - add abstract actions for actions not possessed by each Pleep

    abstract void startRunning();


    /********** ******* **************/
    public int getVelocity() {
        return this.velocity;
    }

    public float getX() {
        return this.boundingBox.getX();
    }

    public float getY() {
        return this.boundingBox.getY();
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

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Deprecated
    public TextureRegion getCurrentFrame() {
        this.statetime += Gdx.graphics.getDeltaTime();
        this.boundingBox.x = this.boundingBox.x + this.velocity;

        return this.walkAnimation.getKeyFrame(this.statetime, true);
    }
}