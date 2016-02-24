package com.hackathon.game.actors.pleeps;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ScientistPleep extends BasePleep {
    public static final String FACTORY_NAME = "SCIENTIST_PLEEP";

    public ScientistPleep() {
        super();
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

    //TODO add more actions
}
