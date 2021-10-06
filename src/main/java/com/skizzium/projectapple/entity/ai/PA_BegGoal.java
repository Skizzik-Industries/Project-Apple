package com.skizzium.projectapple.entity.ai;

import com.skizzium.projectapple.init.item.PA_Items;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.EnumSet;

public class PA_BegGoal extends Goal {
    private final Wolf wolf;
    private Player player;
    private final Level level;
    private final float lookDistance;
    private int lookTime;
    private final TargetingConditions begTargeting;

    public PA_BegGoal(Wolf wolf, float distance) {
        this.wolf = wolf;
        this.level = wolf.level;
        this.lookDistance = distance;
        this.begTargeting = TargetingConditions.forNonCombat().range(distance);
        this.setFlags(EnumSet.of(Goal.Flag.LOOK));
    }

    public boolean canUse() {
        this.player = this.level.getNearestPlayer(this.begTargeting, this.wolf);
        return this.player != null && this.playerHoldingInteresting(this.player);
    }
    
    public boolean canContinueToUse() {
        if (!this.player.isAlive()) {
            return false;
        } else if (this.wolf.distanceToSqr(this.player) > (double)(this.lookDistance * this.lookDistance)) {
            return false;
        } else {
            return this.lookTime > 0 && this.playerHoldingInteresting(this.player);
        }
    }
    
    public void start() {
        this.wolf.setIsInterested(true);
        this.lookTime = 40 + this.wolf.getRandom().nextInt(40);
    }
    
    public void stop() {
        this.wolf.setIsInterested(false);
        this.player = null;
    }
    
    public void tick() {
        this.wolf.getLookControl().setLookAt(this.player.getX(), this.player.getEyeY(), this.player.getZ(), 10.0F, (float)this.wolf.getMaxHeadXRot());
        --this.lookTime;
    }
    
    private boolean playerHoldingInteresting(Player player) {
        for(InteractionHand hand : InteractionHand.values()) {
            ItemStack itemstack = player.getItemInHand(hand);
            if (this.wolf.isTame() && itemstack.is(PA_Items.SKIZZIK_BONE.get())) {
                return true;
            }
        }

        return false;
    }
}
