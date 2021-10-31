package com.skizzium.projectapple.entity.boss.skizzik.stages.stages.base;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import com.skizzium.projectapple.entity.boss.skizzik.skizzie.Skizzie;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageInterface;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStageManager;
import com.skizzium.projectapple.entity.boss.skizzik.stages.SkizzikStages;
import com.skizzium.projectapple.entity.boss.skizzik.stages.stages.AbstractSkizzikStage;
import com.skizzium.projectapple.init.entity.PA_Entities;
import net.minecraft.world.level.Explosion;

public class SkizzikStage1 extends AbstractSkizzikStage {
    public SkizzikStage1(Skizzik skizzik) {
        super(skizzik);
    }

    @Override
    public int transitionTime() {
        return 73;
    }

    @Override
    public int maxStageHealth() {
        return 1020;
    }

    @Override
    public boolean attackDirectly() {
        if (skizzik.isTransitioning()) {
            return false;
        }
        return super.attackDirectly();
    }

    @Override
    public void begin(SkizzikStageManager stageManager) {
        super.begin(stageManager);
        skizzik.setHealth(1.0F);
        skizzik.bossBar.setProgress(0.0F);
    }

    @Override
    public void tick() {
        if (skizzik.getTransitionTicks() > 0) {
            int i = skizzik.getTransitionTicks() - 1;
            skizzik.bossBar.setProgress(1.0F - (float) i / 73.0F);
            if (i <= 0) {
                Explosion.BlockInteraction explosion = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(skizzik.level, skizzik) ? Explosion.BlockInteraction.DESTROY : Explosion.BlockInteraction.NONE;
                skizzik.level.explode(skizzik, skizzik.getX(), skizzik.getEyeY(), skizzik.getZ(), 8.5F, true, explosion);
                if (!skizzik.isSilent()) {
                    skizzik.level.globalLevelEvent(1023, skizzik.blockPosition(), 0);
                }
            }

            if (skizzik.tickCount % 2 == 0) {
                skizzik.heal(35.0F);
            }
        }

        super.tick();
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
    public void tickParts() {
        super.tickParts();
    }

    @Override
    public SkizzikStages<? extends SkizzikStageInterface> getStage() {
        return SkizzikStages.STAGE_1;
    }
}
