package com.skizzium.projectapple.entity.boss.skizzik.util.stage.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.CorruptedSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.WitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.util.Mth;

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
    public double getHeadY(int head) {
        if (head == 1) {
            return skizzik.getY() + 3.135D;
        }
        return super.getHeadY(head);
    }

    @Override
    public double getHeadZ(int head) {
        if (head == 1) {
            float f = skizzik.yBodyRot * ((float)Math.PI / 180F);
            float f1 = Mth.sin(f);

            return skizzik.getZ() + (double)f1 * 0.688D;
        }
        return super.getHeadZ(head);
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_4;
    }
}
