package com.skizzium.projectapple.entity.boss.skizzik.ai;

import com.skizzium.projectapple.entity.boss.skizzik.Skizzik;
import com.skizzium.projectapple.entity.boss.skizzik.util.stage.base.SkizzikStage5;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;

public class SkizzikBeamAttackGoal extends RangedAttackGoal {
    private final Skizzik skizzik;
    private int attackTime = -1;
    private final double speedModifier;
    private final int attackIntervalMin;
    private final int attackIntervalMax;
    private final float attackRadius;
    private final float attackRadiusSqr;

    public SkizzikBeamAttackGoal(Skizzik pRangedAttackMob, double pSpeedModifier, int pAttackIntervalMin, int pAttackIntervalMax, float pAttackRadius) {
        super(pRangedAttackMob, pSpeedModifier, pAttackIntervalMin, pAttackIntervalMax, pAttackRadius);
        this.skizzik = pRangedAttackMob;
        this.speedModifier = pSpeedModifier;
        this.attackIntervalMin = pAttackIntervalMin;
        this.attackIntervalMax = pAttackIntervalMax;
        this.attackRadius = pAttackRadius;
        this.attackRadiusSqr = pAttackRadius * pAttackRadius;
    }

    @Override
    public boolean canUse() {
        if (skizzik.stageManager.getCurrentStage() instanceof SkizzikStage5 && !skizzik.isTransitioning()) {
            LivingEntity livingentity = this.skizzik.getTarget();
            if (livingentity != null) {
                this.target = livingentity;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canContinueToUse() {
        return this.canUse() || !this.skizzik.getNavigation().isDone();
    }

    @Override
    public void tick() {
        double d0 = this.skizzik.distanceToSqr(this.target.getX(), this.target.getY(), this.target.getZ());
        boolean flag = this.skizzik.getSensing().hasLineOfSight(this.target);
        if (flag) {
            ++this.seeTime;
        } else {
            this.seeTime = 0;
        }

        if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 5) {
            this.skizzik.getNavigation().stop();
        } else {
            this.skizzik.getNavigation().moveTo(this.target, this.speedModifier);
        }

        this.skizzik.getLookControl().setLookAt(this.target, 30.0F, 30.0F);
        if (--this.attackTime == 0) {
            if (!flag) {
                return;
            }

            float f = (float)Math.sqrt(d0) / this.attackRadius;
            float f1 = Mth.clamp(f, 0.1F, 1.0F);
            this.skizzik.performBeamAttack(this.target, f1);
            this.attackTime = Mth.floor(f * (float)(this.attackIntervalMax - this.attackIntervalMin) + (float)this.attackIntervalMin);
        } else if (this.attackTime < 0) {
            this.attackTime = Mth.floor(Mth.lerp(Math.sqrt(d0) / (double)this.attackRadius, (double)this.attackIntervalMin, (double)this.attackIntervalMax));
        }
    }
}
