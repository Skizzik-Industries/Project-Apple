package com.skizzium.projectapple.entity.boss.skizzik.util.stage.base;

import com.skizzium.projectapple.ProjectApple;
import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.CorruptedSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.WitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.util.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;
import com.skizzium.projectlib.gui.PL_BossEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityDimensions;

public class SkizzikStage5 extends AbstractSkizzikStage {
    public SkizzikStage5(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public PL_BossEvent.PL_BossBarColor barColor() {
        return ProjectApple.holiday == 1 ? PL_BossEvent.PL_BossBarColor.GOLD : PL_BossEvent.PL_BossBarColor.AQUA;
    }

    @Override
    public int transitionTime() {
        return 41;
    }
    
    @Override
    public String textureLocation() {
        return String.format("%s/%s_stage-5", skizzik.getTranslationKey().getString().toLowerCase(), skizzik.getTranslationKey().getString().toLowerCase());
    }

    @Override
    public int maxStageHealth() {
        return 220;
    }

    @Override
    public int armorValue() {
        return 12;
    }

    @Override
    public int skullLevel() {
        return 3;
    }

    @Override
    public int safeLightningTicks() {
        return 15;
    }
    
    @Override
    public int lightningTicks() {
        return 60;
    }
    
    @Override
    public int skizzieSpawnTicks() {
        return 30;
    }
    
    @Override
    public int destroyBlocksTick() {
        return 10;
    }

    @Override
    public float eyeHeight() {
        return 2.75F;
    }

    @Override
    public float floatY() {
        return 6.5F;
    }

    @Override
    public EntityDimensions hitbox() {
        return new EntityDimensions(2.5F, 3.4F, true);
    }
    
    @Override
    public void skizzieSpawning() {
        if (Math.random() < 0.5) {
            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
            spawnSkizzie(new Skizzie(PA_Entities.SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
        } else {
            if (Math.random() < 0.5) {
                spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                spawnSkizzie(new KaboomSkizzie(PA_Entities.KABOOM_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
            } else {
                if (Math.random() < 0.5) {
                    spawnSkizzie(new WitchSkizzie(PA_Entities.WITCH_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                } else {
                    /*if (Math.random() < 0.5) {
                        spawnSkizzie(new MinigunSkizzie(PA_Entities.MINIGUN_SKIZZIE, this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                    }
                    else {*/
                        spawnSkizzie(new CorruptedSkizzie(PA_Entities.CORRUPTED_SKIZZIE.get(), this.skizzik.level), skizzik.getX(), skizzik.getY(), skizzik.getZ(), skizzik.level);
                    //}
                }
            }
        }
    }

    @Override
    public double getHeadY(int head) {
        if (head == 0) {
            return skizzik.getY() + 2.323D;
        }
        return super.getHeadY(head);
    }
    
    @Override
    public void tickParts() {
        super.tickParts();
        
        float f = skizzik.yBodyRot * ((float)Math.PI / 180F);
        float f1 = Mth.cos(f);
        float f2 = Mth.sin(f);
        
        skizzik.tickPartOffset(skizzik.commandBlockPart, -f2 * 1.257F, 0.87F, f1);
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_5;
    }
}
