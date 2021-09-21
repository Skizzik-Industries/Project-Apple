package com.skizzium.projectapple.entity.boss.skizzik.ai;

import com.skizzium.projectapple.entity.boss.skizzik.skizzie.KaboomSkizzie;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class KaboomSkizzieSwellGoal extends Goal {
    private final KaboomSkizzie skizzie;
    private LivingEntity target;

    public KaboomSkizzieSwellGoal(KaboomSkizzie entity) {
        this.skizzie = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    public boolean canUse() {
        LivingEntity livingentity = this.skizzie.getTarget();
        return this.skizzie.getSwellDir() > 0 || livingentity != null && this.skizzie.distanceToSqr(livingentity) < 10.0D;
    }

    public void start() {
        this.skizzie.getNavigation().stop();
        this.target = this.skizzie.getTarget();
    }

    public void stop() {
        this.target = null;
    }

    public void tick() {
        if (this.target == null) {
            this.skizzie.setSwellDir(-1);
        }
        else if (this.skizzie.distanceToSqr(this.target) > 49.0D) {
            this.skizzie.setSwellDir(-1);
        }
        else if (!this.skizzie.getSensing().hasLineOfSight(this.target)) {
            this.skizzie.setSwellDir(-1);
        }
        else {
            this.skizzie.setSwellDir(1);
        }
    }
}
