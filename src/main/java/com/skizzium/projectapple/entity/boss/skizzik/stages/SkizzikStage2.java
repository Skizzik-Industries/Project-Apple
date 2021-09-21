package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;

public class SkizzikStage2 extends AbstractSkizzikStage {
    public SkizzikStage2(Skizzik skizzik) {
        super(skizzik);
    }
    
    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.STAGE_2;
    }
}
