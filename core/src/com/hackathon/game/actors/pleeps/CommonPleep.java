package com.hackathon.game.actors.pleeps;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CommonPleep extends BasePleep {

    public CommonPleep() {
        super();
    }

    @Override
    Animation initWalkAnimation() {
            TextureRegion[] walkframes = new TextureRegion[4];
            walkframes[0] = spritePosition[0][8];
            walkframes[1] = spritePosition[0][9];
            walkframes[2] = spritePosition[1][0];
            walkframes[3] = spritePosition[1][1];

            return new Animation(0.1f, walkframes);
    }


    //TODO add more actions
}
