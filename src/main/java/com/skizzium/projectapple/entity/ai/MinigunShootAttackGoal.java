package com.skizzium.projectapple.entity.ai;

import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Items;

import java.util.EnumSet;

public class MinigunShootAttackGoal<T extends Monster & RangedAttackMob> extends Goal {
    private final T entity;
    private final double speedModifier;
    private int attackIntervalMin;
    private final float attackRadiusSqr;
    private int attackTime = -1;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public MinigunShootAttackGoal(T entity, double speedModifier, int attackIntervalMin, float attackRadiusSqr) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.attackIntervalMin = attackIntervalMin;
        this.attackRadiusSqr = attackRadiusSqr * attackRadiusSqr;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    public void setMinAttackInterval(int attackIntervalMin) {
        this.attackIntervalMin = attackIntervalMin;
    }

    @Override
    public boolean canUse() {
        return this.entity.getTarget() != null && this.isHoldingMinigun();
    }

    protected boolean isHoldingMinigun() {
        return true; //this.entity.isHolding(item -> item instanceof BowItem);
    }

    public boolean canContinueToUse() {
        return (this.canUse() || !this.entity.getNavigation().isDone()) && this.isHoldingMinigun();
    }

    public void start() {
        super.start();
        this.entity.setAggressive(true);
    }

    public void stop() {
        super.stop();
        this.entity.setAggressive(false);
        this.seeTime = 0;
        this.attackTime = -1;
        this.entity.stopUsingItem();
    }

    public void tick() {
        LivingEntity target = this.entity.getTarget();

        if (target != null) {
            double distance = this.entity.distanceToSqr(target.getX(), target.getY(), target.getZ());
            boolean flag = this.entity.getSensing().hasLineOfSight(target);
            boolean flag1 = this.seeTime > 0;
            if (flag != flag1) {
                this.seeTime = 0;
            }

            if (flag) {
                ++this.seeTime;
            }
            else {
                --this.seeTime;
            }

            if (!(distance > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
                this.entity.getNavigation().stop();
                ++this.strafingTime;
            }
            else {
                this.entity.getNavigation().moveTo(target, this.speedModifier);
                this.strafingTime = -1;
            }

            if (this.strafingTime >= 20) {
                if ((double)this.entity.getRandom().nextFloat() < 0.3D) {
                    this.strafingClockwise = !this.strafingClockwise;
                }

                if ((double)this.entity.getRandom().nextFloat() < 0.3D) {
                    this.strafingBackwards = !this.strafingBackwards;
                }

                this.strafingTime = 0;
            }

            if (this.strafingTime > -1) {
                if (distance > (double)(this.attackRadiusSqr * 0.75F)) {
                    this.strafingBackwards = false;
                }
                else if (distance < (double)(this.attackRadiusSqr * 0.25F)) {
                    this.strafingBackwards = true;
                }

                this.entity.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
                this.entity.lookAt(target, 30.0F, 30.0F);
            }
            else {
                this.entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
            }

            if (this.entity.isUsingItem()) {
                if (!flag && this.seeTime < -60) {
                    this.entity.stopUsingItem();
                }
                else if (flag) {
                    int i = this.entity.getTicksUsingItem();
                    if (i >= 20) {
                        this.entity.stopUsingItem();
                        this.entity.performRangedAttack(target, BowItem.getPowerForTime(i));
                        this.attackTime = this.attackIntervalMin;
                    }
                }
            }
            else if (--this.attackTime <= 0 && this.seeTime >= -60) {
                this.entity.startUsingItem(ProjectileUtil.getWeaponHoldingHand(this.entity, Items.BOW));
            }
        }
    }
}
