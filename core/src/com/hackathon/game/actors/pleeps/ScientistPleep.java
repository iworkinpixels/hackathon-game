package com.hackathon.game.actors.pleeps;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ScientistPleep extends BasePleep {

    public ScientistPleep() {
        super();
    }

    @Override
    Animation initWalkAnimation() {

        TextureRegion[] walkframes = new TextureRegion[4];
        walkframes[0] = spritePosition[2][8];
        walkframes[1] = spritePosition[2][9];
        walkframes[2] = spritePosition[3][0];
        walkframes[3] = spritePosition[3][1];

        return  new Animation(0.1f, walkframes);
    }

    @Override
    Animation initPanicAnimation() {
        return null; //TODO set panic animation if exists
    }

    //TODO add more actions
}
