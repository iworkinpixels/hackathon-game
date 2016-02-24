package com.hackathon.game.actors.pleeps;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by tjago on 2016-02-24.
 */
public class SoldierPleep extends BasePleep {

    public static final String FACTORY_NAME = "SOLDIER_PLEEP";

    public SoldierPleep() {
        super();
        newWalkAnimation();
    }

    private void newWalkAnimation() {

        TextureRegion[] walkframes = new TextureRegion[7];
        walkframes[0] = spritePosition[5][3];
        walkframes[1] = spritePosition[5][4];
        walkframes[2] = spritePosition[5][5];
        walkframes[3] = spritePosition[5][6];
        walkframes[4] = spritePosition[5][7];
        walkframes[5] = spritePosition[5][8];
        walkframes[6] = spritePosition[5][9];

        this.walkAnimation = new Animation(0.1f, walkframes);
    }
}
