package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;

public class SkizzikStage3 extends AbstractSkizzikStage {
    public SkizzikStage3(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public int armorValue() {
        return 8;
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
        return SkizzikStage.STAGE_3;
    }
}
