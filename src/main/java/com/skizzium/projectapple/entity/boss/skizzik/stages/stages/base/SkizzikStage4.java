package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.CorruptedSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.WitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;

public class SkizzikStage4 extends AbstractSkizzikStage {
    public SkizzikStage4(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public int transitionTime() {
        return 21;
    }

    @Override
    public int maxStageHealth() {
        return 420;
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
    public int skizzieSpawnTicks() {
        return 40;
    }
    
    @Override
    public int destroyBlocksTick() {
        return 20;
    }

    @Override
    public void skizzieSpawning() {
        if (Math.random() < 0.05) {
            spawnSkizzie(new CorruptedSkizzie(PA_Entities.CORRUPTED_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
        } else {
            if (Math.random() < 0.5) {
                spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
            } else {
                if (Math.random() < 0.5) {
                    spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                } else {
                    //if (Math.random() < 0.5) {
                        spawnSkizzie(new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                    /*}
                    else {
                        spawnSkizzie(new MinigunSkizzie(PA_Entities.MINIGUN_SKIZZIE, this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                    }*/
                }
            }
        }
    }

    @Override
    public void tickParts() {
        super.tickParts();
        skizzik.tickPart(skizzik.bottomRightHead, 0.0F, 3.135F, -0.688F);
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_4;
    }
}
