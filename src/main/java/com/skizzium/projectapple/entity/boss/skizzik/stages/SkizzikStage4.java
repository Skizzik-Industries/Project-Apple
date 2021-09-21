package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;

public class SkizzikStage4 extends AbstractSkizzikStage {
    public SkizzikStage4(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public int armorValue() {
        return 10;
    }

    @Override
    public int skullLevel() {
        return 2;
    }
    
    @Override
    public int destroyBlocksTick() {
        return 20;
    }

    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.STAGE_4;
    }
}
