package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;

public class SkizzikStage1 extends AbstractSkizzikStage {
    public SkizzikStage1(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        if (stageManager.getPreviousStage() instanceof SkizzikSleeping) {
            skizzik.setHealth(1020);
        }
    }

    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.STAGE_1;
    }
}
