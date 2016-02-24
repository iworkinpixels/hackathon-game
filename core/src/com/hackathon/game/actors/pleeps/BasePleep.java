package com.hackathon.game.actors.pleeps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hackathon.game.Constants;
import com.hackathon.game.util.Helpers;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BasePleep extends Actor implements Pleep {

    protected static final int FRAME_WIDTH  = 256;
    protected static final int FRAME_HEIGHT = 256;
    protected static final int BOUNCE_MIN   = -(Constants.VIEWPORT_WIDTH/4)-(FRAME_WIDTH/4);
    protected static final int BOUNCE_MAX   = Constants.VIEWPORT_WIDTH;

    /** shared Texture resource among objects, should be static */
    final static protected Texture spritemap = new Texture(Gdx.files.internal(Constants.PLEEP_SPRITE_MAP));
    public static final int DURATION_OF_DEATH_ANIMATION = 1400;
    static protected TextureRegion[][] spritePosition = TextureRegion.split(spritemap, FRAME_WIDTH, FRAME_HEIGHT);

    MoveDirection direction;
    private Rectangle boundingBox;
    private int velocity;
    private float statetime;

    protected Animation walkAnimation;
    protected Animation deathAnimation;

    protected PleepState pleepState;

    public BasePleep() {
        this.pleepState = PleepState.WALKING;
        this.direction = MoveDirection.RIGHT;

        boundingBox = new Rectangle();
        this.velocity = 2;
        this.boundingBox.x = -FRAME_WIDTH;
        this.statetime = 0f;

        walkAnimation = initWalkAnimation();
        deathAnimation = initDeathAnimation();

        birthPleep();
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
                //wait 1.5 sec to finish animation
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        pleepState = PleepState.DEAD;
                    }
                }, DURATION_OF_DEATH_ANIMATION);
                break;

            case DEAD:
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
    }

    private void animate(Batch batch, Animation animation, MoveDirection direction) {
        if (animation == null || batch == null) {
            System.out.println("NULL");
            return;
        }


        switch (pleepState) {
            case WALKING:
            case DYING:
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
                            this.getX() + FRAME_WIDTH, //the x-coordinate in screen space
                            this.getY(), //the y-coordinate in screen space
                            -FRAME_WIDTH / 2, //width
                            FRAME_HEIGHT / 2 // height
                    );
                }
                break;
            default:
                // You should NEVER be here.
        }
    }

    /********** ACTIONS **************/

    @Override
    public void killPleep() {
        this.pleepState = PleepState.DYING;
        this.velocity = 0;
    }

    @Override
    public void birthPleep() { //TODO refactor - logics outside
        // Set the pleep to walking
        this.pleepState = PleepState.WALKING;
        this.statetime = 0;
        // Set a direction for the pleep
        int randd = Helpers.randomInt(0,1);
        if (randd > 0) {
            this.direction = MoveDirection.LEFT;
            this.boundingBox.x = BOUNCE_MAX;
            this.velocity = -2;
        } else {
            this.direction = MoveDirection.RIGHT;
            this.boundingBox.x = BOUNCE_MIN;
            this.velocity = 2;
        }
        //Set a random Y height for the pleep
        int ypos = Helpers.randomInt(0,25);
        this.boundingBox.y = ypos;
        //In your travels, you must have learned that pleeps are mortal, therefore I can clearly not
        //choose the glass in front of you!
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                killPleep();
            }
        }, 3000);
    }

    @Override
    public void startRunning() {
        pleepState = PleepState.RUNNING;
    }

    @Override
    public void startWalking() {
        pleepState = PleepState.WALKING;
    }

    //TODO add more actions - add abstract actions for actions not possessed by each Pleep

    /********** Regular getters / setters **************/
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

    public PleepState getPleepState() {
        return pleepState;
    }

    public void setPleepState(PleepState pleepState) {
        this.pleepState = pleepState;
    }

}
