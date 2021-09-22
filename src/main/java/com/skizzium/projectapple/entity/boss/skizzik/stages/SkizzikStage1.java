package com.skizzium.projectapple.entity.boss.skizzik.stages;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.init.entity.PA_Entities;

public class SkizzikStage1 extends AbstractSkizzikStage {
    public SkizzikStage1(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        super.begin(stageManager);
        
        if (stageManager.getPreviousStage() instanceof SkizzikSleeping) {
            skizzik.setHealth(1020);
        }
    }

    @Override
    public void skizzieSpawning() {
        if (Math.random() < 0.05) {
            spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
        } 
        else {
            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
        }
    }

    @Override
    public SkizzikStage<? extends SkizzikStageInterface> getStage() {
        return SkizzikStage.STAGE_1;
    }
}
