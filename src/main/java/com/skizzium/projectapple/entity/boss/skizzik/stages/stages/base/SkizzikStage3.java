package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.WitchSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.world.entity.EntityDimensions;

public class SkizzikStage3 extends AbstractSkizzikStage {
    public SkizzikStage3(Skizzik skizzik) {
        super(skizzik);
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
    public int skizzieSpawnTicks() {
        return 40;
    }
    
    @Override
    public int destroyBlocksTick() {
        return 20;
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
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_3;
    }
}