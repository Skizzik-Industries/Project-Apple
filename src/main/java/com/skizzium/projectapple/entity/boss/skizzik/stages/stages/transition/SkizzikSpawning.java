package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.transition;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base.SkizzikStage1;

public class SkizzikSpawning extends SkizzikStage1 {
    public SkizzikSpawning(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public void skizzieSpawning() {}

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.SPAWNING;
    }
}
