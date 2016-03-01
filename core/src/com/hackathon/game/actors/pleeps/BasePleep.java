package com.hackathon.game.actors.pleeps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.hackathon.game.Constants;
import com.hackathon.game.util.Helpers;
import com.hackathon.game.util.PopulationController;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BasePleep extends Actor implements Pleep {

    protected static final int FRAME_WIDTH = 256;
    public static final int ANIMATION_WIDTH_SIZE = FRAME_WIDTH / 4;
    protected static final int FRAME_HEIGHT = 256;
    public static final int ANIMATION_HEIGHT_SIZE = FRAME_HEIGHT / 4;
    protected static final int BOUNCE_MIN = -(Constants.VIEWPORT_WIDTH / 4) - (FRAME_WIDTH / 4);
    protected static final int BOUNCE_MAX = Constants.VIEWPORT_WIDTH;

    /**
     * shared Texture resource among Pleep objects, should be static
     */
    final static protected Texture spritemap = new Texture(Gdx.files.internal(Constants.PLEEP_SPRITE_MAP));
    public static final int DURATION_OF_DEATH_ANIMATION = 840;
    static protected TextureRegion[][] spritePosition = TextureRegion.split(spritemap, FRAME_WIDTH, FRAME_HEIGHT);

    private MoveDirection direction;
    private int velocity;
    private float statetime;
    protected PleepState pleepState;

    protected Animation walkAnimation;
    protected Animation panicAnimation;
    protected Animation deathAnimation;

    /**
     * Class Constructor
     */
    public BasePleep() {

        walkAnimation = initWalkAnimation();
        panicAnimation = initPanicAnimation();
        deathAnimation = initDeathAnimation();

        initPleepFields();
        birthPleep();
    }

    private void initPleepFields() {
        this.direction = MoveDirection.RIGHT;
        this.setX(-FRAME_WIDTH);
        this.velocity = 2;
        this.statetime = 0f;

        // Set a direction for the pleep
        int randd = Helpers.randomInt(0, 1);
        if (randd > 0) {
            this.direction = MoveDirection.LEFT;
            this.setX(BOUNCE_MAX);
            this.velocity = -2;
        } else {
            this.direction = MoveDirection.RIGHT;
            this.setX(BOUNCE_MIN);
            this.velocity = 2;
        }
        //Set a random Y height for the pleep
        int ypos = Helpers.randomInt(0, 90);
        this.setY(ypos);

        //last step: update z-index in the collection of Actors
        PopulationController.updateZIndexforPleeps();
    }


    /**
     * methods to be implemented by subclasses
     */

    abstract Animation initWalkAnimation();
    abstract Animation initPanicAnimation();


    /**
     * draw function gets called every frame
     * don't put heavy methods here
     *
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {

        switch (pleepState) {

            case WALKING:
                animate(batch, walkAnimation, this.direction);
                break;

            case RUNNING:
                //TODO put animation here
                break;

            case PANICING:
                animate(batch, panicAnimation, this.direction);
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
                PopulationController.pleepKilledNotification();
                this.remove();
                break;
            default:
                // this block should not be entered ever!
                break;
        }

    }

    /**
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {

        movementCalculations(delta);
    }

    private void movementCalculations(float delta) {
        statetime += delta;

        if ((this.direction == MoveDirection.RIGHT && this.getX() > BOUNCE_MAX)
                || (this.direction == MoveDirection.LEFT && this.getX() < BOUNCE_MIN)) {

            this.velocity = -this.velocity;
            this.direction = direction.getOppositeDirection();

            if (this.direction == MoveDirection.RIGHT) {
                this.incrementPosX(FRAME_WIDTH / 2);
            } else {
                this.incrementPosX(-(FRAME_WIDTH / 2));
            }
        }
        this.incrementPosX(this.velocity);
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
                    ANIMATION_WIDTH_SIZE, //width
                    ANIMATION_HEIGHT_SIZE // height
            );
        }
        if (direction == MoveDirection.LEFT) {
            batch.draw(animation.getKeyFrame(this.statetime, true),
                    this.getX() + FRAME_WIDTH,  //the x-coordinate in screen space
                    this.getY(),                //the y-coordinate in screen space
                    //negative value allows to draw mirrored animation to left
                    -ANIMATION_WIDTH_SIZE,      //width,
                    ANIMATION_HEIGHT_SIZE       // height
            );
        }

    }

    private Animation initDeathAnimation() {

        TextureRegion[] frames = new TextureRegion[21];
        frames[0] = spritePosition[12][0];
        frames[1] = spritePosition[12][1];
        frames[2] = spritePosition[12][0];
        frames[3] = spritePosition[12][1];
        frames[4] = spritePosition[12][2];
        frames[5] = spritePosition[12][3];
        frames[6] = spritePosition[12][4];
        frames[7] = spritePosition[12][5];
        frames[8] = spritePosition[12][6];
        frames[9] = spritePosition[12][7];
        frames[10] = spritePosition[12][8];
        frames[11] = spritePosition[12][9];
        frames[12] = spritePosition[13][0];
        frames[13] = spritePosition[13][1];
        frames[14] = spritePosition[13][2];
        frames[15] = spritePosition[13][3];
        frames[16] = spritePosition[13][4];
        frames[17] = spritePosition[13][5];
        frames[18] = spritePosition[13][6];
        frames[19] = spritePosition[13][7];
        frames[20] = spritePosition[13][8];

        return new Animation(0.04f, frames);
    }

    /**********
     * ACTIONS
     **************/

    @Override
    public void killPleep() {
        if (!pleepState.equals(PleepState.DEAD)) {

            this.pleepState = PleepState.DYING;
            this.velocity = 0;
            this.statetime = 0;

            //wait to finish animation
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    pleepState = PleepState.DEAD;
                }
            }, DURATION_OF_DEATH_ANIMATION);
        }
    }

    @Override
    public void birthPleep() {
        // Set the pleep to walking
        this.pleepState = PleepState.WALKING;
        this.statetime = 0;
    }

    @Override
    public void startRunning() {
        pleepState = PleepState.RUNNING;
    }

    @Override
    public void startWalking() {
        pleepState = PleepState.WALKING;
    }

    @Override
    public void startPanicing() {

        statetime = 0;
        pleepState = PleepState.PANICING;
    }

    /**********
     * Regular getters
     **************/

    public PleepState getPleepState() {
        return pleepState;
    }

    public int getVelocity() {
        return this.velocity;
    }

    private void incrementPosX(float val) {
        this.setX(getX() + val);
    }

}
