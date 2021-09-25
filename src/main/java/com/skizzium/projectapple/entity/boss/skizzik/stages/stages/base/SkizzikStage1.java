package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageManager;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;

public class SkizzikStage1 extends AbstractSkizzikStage {
    public SkizzikStage1(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public String modelLocation() {
        if (skizzik.isTransitioning()) {
            return "skizzik/skizzik_sleeping";
        }
        return super.modelLocation();
    }

    @Override
    public boolean hostileAI() {
        if (skizzik.isTransitioning()) {
            return false;
        }
        return super.hostileAI();
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        skizzik.setHealth(1020);
        skizzik.setInvulnerableTicks(72);
        skizzik.setTransitioning(true);

        super.begin(stageManager);
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
    public void tick() {
        super.tick();
        
        if (skizzik.getInvulnerableTicks() > 0) {
            skizzik.setInvulnerableTicks(skizzik.getInvulnerableTicks() - 1);
        }
        else {
            skizzik.setTransitioning(false);
            this.addGoals();
        }
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_1;
    }
}
