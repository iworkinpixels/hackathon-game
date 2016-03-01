package com.hackathon.game.actors.pleeps;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by tjago on 2016-02-24.
 */
public class SoldierPleep extends BasePleep {

    public SoldierPleep() {
        super();
    }

    @Override
    Animation initWalkAnimation() { //carry bomb
        TextureRegion[] walkframes = new TextureRegion[4];
        walkframes[0] = spritePosition[5][6];
        walkframes[1] = spritePosition[5][7];
        walkframes[2] = spritePosition[5][8];
        walkframes[3] = spritePosition[5][9];
        return new Animation(0.1f, walkframes);
    }


    Animation initThrowBombAnimation() {
        TextureRegion[] throwframes = new TextureRegion[2];
        throwframes[0] = spritePosition[5][0];
        throwframes[1] = spritePosition[5][1];
        return new Animation(0.1f, throwframes);
    }

    @Override
    Animation initPanicAnimation() {
        TextureRegion[] panicframes = new TextureRegion[4];
        panicframes[0] = spritePosition[5][2];
        panicframes[1] = spritePosition[5][3];
        panicframes[2] = spritePosition[5][4];
        panicframes[3] = spritePosition[5][5];
        return new Animation(0.1f, panicframes);
    }


}
