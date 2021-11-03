package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.WitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.util.Mth;

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
    public double getHeadX(int head) {
        if (head == 3) {
            float f = skizzik.yBodyRot * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);

            return skizzik.getX() - f1 * -0.063F;
        }
        return super.getHeadX(head);
    }

    @Override
    public double getHeadY(int head) {
        if (head == 3) {
            return skizzik.getY() + 3.198D;
        }
        return super.getHeadY(head);
    }

    @Override
    public double getHeadZ(int head) {
        if (head == 3) {
            float f = skizzik.yBodyRot * ((float)Math.PI / 180F);
            float f1 = Mth.sin(f);

            return skizzik.getZ() + (double)f1 * -0.25D;
        }
        return super.getHeadZ(head);
    }
    
    @Override
    public void tickParts() {
        super.tickParts();
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_2;
    }
}
