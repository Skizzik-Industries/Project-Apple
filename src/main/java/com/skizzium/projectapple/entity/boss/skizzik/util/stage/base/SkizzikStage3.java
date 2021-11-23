package com.skizzium.projectapple.entity.boss.skizzik.util.stage.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.WitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;

public class SkizzikStage3 extends AbstractSkizzikStage {
    public SkizzikStage3(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public int transitionTime() {
        return 7;
    }

    @Override
    public int maxStageHealth() {
        return 620;
    }

    @Override
    public int armorValue() {
        return 8;
    }

    @Override
    public int skullLevel() {
        return 2;
    }

    @Override
    public int safeLightningTicks() {
        return 50;
    }
    
    @Override
    public int lightningTicks() {
        return 160;
    }
    
    @Override
    public int skizzieSpawnTicks() {
        return 40;
    }
    
    @Override
    public int destroyBlocksTicks() {
        return 20;
    }

    @Override
    public float floatY() {
        return 5.0F;
    }

    @Override
    public EntityDimensions hitbox() {
        return new EntityDimensions(2.5F, 3.0F, true);
    }
    
    @Override
    public void skizzieSpawning() {
        /*if (Math.random() < 0.05) {
            spawnSkizzie(new MinigunSkizzie(PA_Entities.MINIGUN_SKIZZIE, this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
        }
        else {*/
            if (Math.random() < 0.5) {
                spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
            } else {
                if (Math.random() < 0.5) {
                    spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                } else {
                    spawnSkizzie(new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                }
            }
        //}
    }

    @Override
    public double getHeadY(int head) {
        if (head == 1) {
            return skizzik.getY() + 1.948D;
        }
        return super.getHeadY(head);
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_3;
    }
}
