package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.util.PA_BossEvent;

public class SkizzikStage5 extends AbstractSkizzikStage {
    public SkizzikStage5(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public PA_BossEvent.PA_BossBarColor barColor() {
        return ProjectApple.holiday == 1 ? PA_BossEvent.PA_BossBarColor.GOLD : PA_BossEvent.PA_BossBarColor.AQUA;
    }

    @Override
    public int armorValue() {
        return 12;
    }

    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.STAGE_5;
    }
}
