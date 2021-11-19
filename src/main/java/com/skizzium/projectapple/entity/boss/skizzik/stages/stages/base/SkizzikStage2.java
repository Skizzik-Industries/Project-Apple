package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.WitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;

public class SkizzikStage2 extends AbstractSkizzikStage {
    public SkizzikStage2(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public int transitionTime() {
        return 21;
    }

    @Override
    public int maxStageHealth() {
        return 820;
    }

    @Override
    public float floatY() {
        return 3.0F;
    }

    @Override
    public void skizzieSpawning() {
        if (Math.random() < 0.05) {
            spawnSkizzie(new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
        } else {
            if (Math.random() < 0.5) {
                spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
            } else {
                spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
            }
        }
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_2;
    }
}
