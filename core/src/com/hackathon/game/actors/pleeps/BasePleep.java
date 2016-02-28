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

    protected static final int FRAME_WIDTH = 256;
    protected static final int FRAME_HEIGHT = 256;
    protected static final int BOUNCE_MIN = -(Constants.VIEWPORT_WIDTH / 4) - (FRAME_WIDTH / 4);
    protected static final int BOUNCE_MAX = Constants.VIEWPORT_WIDTH;

    /**
     * shared Texture resource among objects, should be static
     */
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

    /**
     * method to be implemented by subclasses
     */
    abstract Animation initWalkAnimation();


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
                this.boundingBox.x += FRAME_WIDTH / 2;
            } else {
                this.boundingBox.x -= FRAME_WIDTH / 2;
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
                            FRAME_WIDTH / 4, //width
                            FRAME_HEIGHT / 4 // height
                    );
                }
                if (direction == MoveDirection.LEFT) {
                    batch.draw(animation.getKeyFrame(this.statetime, true),
                            this.getX() + FRAME_WIDTH, //the x-coordinate in screen space
                            this.getY(), //the y-coordinate in screen space
                            -FRAME_WIDTH / 4, //width
                            FRAME_HEIGHT / 4 // height
                    );
                }
                break;
            default:
                // You should NEVER be here.
        }
    }

    /**********
     * ACTIONS
     **************/

    @Override
    public void killPleep() {
        this.pleepState = PleepState.DYING;
        this.velocity = 0;

        //wait 1.5 sec to finish animation
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                pleepState = PleepState.DEAD;
            }
        }, DURATION_OF_DEATH_ANIMATION);
    }

    @Override
    public void birthPleep() { //TODO refactor - logics outside
        // Set the pleep to walking
        this.pleepState = PleepState.WALKING;
        this.statetime = 0;
        // Set a direction for the pleep
        int randd = Helpers.randomInt(0, 1);
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
        int ypos = Helpers.randomInt(0, 25);
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

    /**********
     * Regular getters / setters
     **************/
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
